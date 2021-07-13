package com.bladesinthedark.actor;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.bladesinthedark.crew.*;
import com.bladesinthedark.rules.*;

import model.*;

public class Rogue implements Actor {
	/*
	 * STATIC FIELDS
	 */
	private static final int MAX_STRESS = 9;

	private static final String[] NAMES = { "Adric", "Aldo", "Amosen", "Andrel", "Arden", "Arlyn", "Arquo", "Arvus",
			"Ashlyn", "Branon", "Brace", "Brance", "Brena", "Bricks", "Candra", "Carissa", "Carro", "Casslyn",
			"Cavelle", "Clave", "Corille", "Cross", "Crowl", "Cyrene", "Daphnia", "Drav", "Edlun", "Emeline", "Grine",
			"Helles", "Hix", "Holtz", "Kamelin", "Kelyr", "Kobb", "Kristov", "Laudius", "Lauria", "Lenia", "Lizete",
			"Lorette", "Lucella", "Lynthia", "Mara", "Milos", "Morlan", "Myre", "Narcus", "Naria", "Noggs", "Odrienne",
			"Orlan", "Phin", "Polonia", "Quess", "Remira", "Ring", "Roethe", "Sesereth", "Sethla", "Skannon", "Stavrul",
			"Stev", "Syra", "Talitha", "Tesslyn", "Thena", "Timoth", "Tocker", "Una", "Vaurin", "Veleris", "Veretta",
			"Vestine", "Vey", "Volette", "Vond", "Weaver", "Wester", "Zamira" };

	/*
	 * INSTANCE FIELDS
	 */
	private Faction crew;
	private String name;
	private RoguePlaybook playbook;
	private HashMap<Rating, Integer> attributes;
	private EnumSet<RogueSpecial> specials;

	//
	private RogueVice vice;
	private String[] harm;

	//
	private int playbookXP;
	private int insightXP;
	private int prowessXP;
	private int resolveXP;
	private int coin;
	private int stash;

	//
	private int stress;
	private int threshold;
	private boolean stressedOut;
	private EnumSet<Trauma> trauma;

	// constructors
	public Rogue(Faction crew) {
		// TODO
		this.crew = crew;
		this.name = randomName();
		this.playbook = RoguePlaybook.randomPlaybook();

		// requires playbook
		this.attributes = attributesInit(playbook, crew);
		RogueSpecial.crewSpecialSkills(crew, this);

		this.specials = EnumSet.noneOf(RogueSpecial.class);
		specials.add(RogueSpecial.randomSpecial(playbook));

		this.playbookXP = 0;
		this.insightXP = 0;
		this.prowessXP = 0;
		this.resolveXP = 0;
		this.coin = 0;
		this.stash = 0;

		this.vice = RogueVice.randomVice();
		this.harm = new String[5];

		this.stress = 0;
		this.threshold = Dice.roll(4) + 5;
		this.stressedOut = false;
		this.trauma = EnumSet.noneOf(Trauma.class);
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public int getAttribute(Attribute attribute) {
		int value = 0;
		if (attribute.equals(Attribute.INSIGHT))
			value = getInsight();
		else if (attribute.equals(Attribute.PROWESS))
			value = getProwess();
		else if (attribute.equals(Attribute.RESOLVE))
			value = getResolve();

		return value;
	}

	public Rating getRandomRogueRating() {
		Set<Rating> set = attributes.keySet();

		return Dice.randomFromSet(set);
	}

	public int getRating(Rating rating) {
		int score;
		if (attributes.containsKey(rating))
			score = attributes.get(rating);
		else
			score = 0;

		return score;
	}

	public int getInsight() {
		Rating[] array = Rating.INSIGHT;
		int total = 0;
		for (int i = 0; i < array.length; ++i) {
			if (attributes.containsKey(array[i]))
				++total;
		}

		return total;
	}

	public int getProwess() {
		Rating[] array = Rating.PROWESS;
		int total = 0;
		for (int i = 0; i < array.length; ++i) {
			if (attributes.containsKey(array[i]))
				++total;
		}

		return total;
	}

	public int getResolve() {
		Rating[] array = Rating.RESOLVE;
		int total = 0;
		for (int i = 0; i < array.length; ++i) {
			if (attributes.containsKey(array[i]))
				++total;
		}

		return total;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getStash() {
		return stash;
	}

	public void setStash(int stash) {
		this.stash = stash;
	}

	public int getStress() {
		return stress;
	}

	public void setStress(int stress) {
		this.stress = stress;

		// TODO
		if (stress >= MAX_STRESS)
			stressedOut = true;
	}

	public int getThreshold() {
		return threshold;
	}

	public boolean stressedOut() {
		return stressedOut;
	}

	public boolean goodToGo() {
		return (stressedOut != true);
	}

	public void resolveStress() {
		if (stress >= MAX_STRESS) {
			stress = 0;
			stressedOut = false;

			Trauma candidate = Trauma.randomTrauma();
			while (trauma.contains(candidate)) {
				candidate = Trauma.randomTrauma();
			}

			// TODO - testing
			// System.out.println(this + " gained the " + candidate + " trauma condition.");
			trauma.add(candidate);
		}
	}

	public EnumSet<Trauma> getTrauma() {
		return trauma;
	}

	public boolean improveAttribute(Rating rating) {
		boolean improved = false;
		if (attributes.containsKey(rating) != true) {
			attributes.put(rating, 1);
			improved = true;
			return improved;
		}

		Set<CrewUpgrade> crewUpgrades = crew.upgradeSet();
		int upgrade = attributes.get(rating);

		boolean trainer = false;
		if (rating.equals(Rating.HUNT) || rating.equals(Rating.STUDY) || rating.equals(Rating.SURVEY)
				|| rating.equals(Rating.TINKER)) {
			trainer = crewUpgrades.contains(CrewUpgrade.TRAINING_INSIGHT);
		}

		if (rating.equals(Rating.FINESSE) || rating.equals(Rating.PROWL) || rating.equals(Rating.SKIRMISH)
				|| rating.equals(Rating.WRECK)) {
			trainer = crewUpgrades.contains(CrewUpgrade.TRAINING_PROWESS);
		}

		if (rating.equals(Rating.ATTUNE) || rating.equals(Rating.COMMAND) || rating.equals(Rating.CONSORT)
				|| rating.equals(Rating.SWAY)) {
			trainer = crewUpgrades.contains(CrewUpgrade.TRAINING_RESOLVE);
		}

		if (upgrade + 1 == 2) {
			upgrade = 2;
			improved = true;
		} else if (upgrade + 1 == 4 && trainer) {
			upgrade = 4;
			improved = true;
		} else if (upgrade + 1 == 3) {
			upgrade = 3;
			improved = true;
		}

		attributes.put(rating, upgrade);
		return improved;
	}

	@Override
	public String toString() {
		return String.format("%s the %s (%d)", name, playbook, stress);
	}

	public String toStringDetailed() {
		String ratings = "";

		Rating rating;
		for (Iterator<Rating> it = attributes.keySet().iterator(); it.hasNext();) {
			rating = it.next();
			ratings += rating + " (" + attributes.get(rating) + ")";
			if (it.hasNext())
				ratings += ", ";
		}

		String string = String.format("%s the %s (Vice: %s) %nInsight: %d || Prowess: %d || Resolve: %d %n%s", name,
				playbook, vice, getInsight(), getProwess(), getResolve(), ratings);

		return string;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	private static HashMap<Rating, Integer> attributesInit(RoguePlaybook playbook, Faction crew) {
		HashMap<Rating, Integer> init = new HashMap<Rating, Integer>();

		if (playbook.equals(RoguePlaybook.CUTTER)) {
			init.put(Rating.SKIRMISH, 2);
			init.put(Rating.COMMAND, 1);

		} else if (playbook.equals(RoguePlaybook.HOUND)) {
			init.put(Rating.HUNT, 2);
			init.put(Rating.SURVEY, 1);

		} else if (playbook.equals(RoguePlaybook.LEECH)) {
			init.put(Rating.TINKER, 2);
			init.put(Rating.WRECK, 1);

		} else if (playbook.equals(RoguePlaybook.LURK)) {
			init.put(Rating.PROWL, 2);
			init.put(Rating.FINESSE, 1);

		} else if (playbook.equals(RoguePlaybook.SLIDE)) {
			init.put(Rating.SWAY, 2);
			init.put(Rating.CONSORT, 1);

		} else if (playbook.equals(RoguePlaybook.SPIDER)) {
			init.put(Rating.CONSORT, 2);
			init.put(Rating.STUDY, 1);

		} else if (playbook.equals(RoguePlaybook.WHISPER)) {
			init.put(Rating.ATTUNE, 2);
			init.put(Rating.STUDY, 1);

		}

		int points = 4, dice;
		Rating choice;
		while (points > 0) {
			dice = (points > 1 && Dice.roll(2) == 1) ? 2 : 1;
			choice = Rating.randomRating();

			if (init.containsKey(choice) != true) {
				init.put(choice, dice);
				points -= dice;
			}
		}

		return init;
	}

	public static Rogue bestRogueForAction(Rating rating, List<Rogue> team) {
		/*
		 * TODO - create additional criteria for choosing the best rogue
		 * 
		 */
		Rogue candidate, choice = null;
		int number;

		for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
			candidate = it.next();
			number = candidate.getRating(rating);

			if (choice == null && candidate.goodToGo()) {
				choice = candidate;
			} else if (choice != null && number > choice.getRating(rating) && candidate.goodToGo()) {
				choice = candidate;
			}
		}

		return choice;
	}

	public static String randomName() {
		return Dice.randomFromArray(NAMES);
	}

	/*
	 * COMPARATORS
	 * 
	 */
	public static class CoinAscending implements Comparator<Rogue> {
		@Override
		public int compare(Rogue rogue1, Rogue rogue2) {
			return rogue1.coin - rogue2.coin;
		}
	}

	public static class CoinDescending implements Comparator<Rogue> {
		@Override
		public int compare(Rogue rogue1, Rogue rogue2) {
			return rogue2.coin - rogue1.coin;
		}
	}

	public static class StressAscending implements Comparator<Rogue> {
		@Override
		public int compare(Rogue rogue1, Rogue rogue2) {
			return rogue1.stress - rogue2.stress;
		}
	}

	public static class StressDescending implements Comparator<Rogue> {
		@Override
		public int compare(Rogue rogue1, Rogue rogue2) {
			return rogue2.stress - rogue1.stress;
		}
	}
}
