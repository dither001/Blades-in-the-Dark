import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Rogue implements Actor {
	// cohort enums
	public enum CohortType {
		ADEPT, ROOK, ROVER, SKULK, THUG, EXPERT
	}

	public enum CohortEdge {
		FEARSOME, INDEPENDENT, LOYAL, TENACIOUS
	}

	public enum CohortFlaw {
		PRINCIPLED, SAVAGE, UNRELIABLE, WILD
	}

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

	// approaches by tension level
	private static final Rating[] HIGH_TENSION_APPROACH = { Rating.COMMAND, Rating.HUNT, Rating.PROWL, Rating.SKIRMISH,
			Rating.SWAY, Rating.WRECK };
	private static final Rating[] LOW_TENSION_APPROACH = { Rating.ATTUNE, Rating.CONSORT, Rating.FINESSE, Rating.STUDY,
			Rating.SURVEY, Rating.TINKER };

	/*
	 * INSTANCE FIELDS
	 * 
	 */
	private Faction crew;
	private String name;
	private Playbook playbook;
	private HashMap<Rating, Integer> attributes;
	private EnumSet<Special> specials;

	//
	private Vice vice;
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
		this.playbook = randomPlaybook();

		// requires playbook
		this.attributes = attributesInit(playbook, crew);
		crewSpecialSkills(crew, this);

		this.specials = EnumSet.noneOf(Special.class);
		specials.add(randomSpecial(playbook));

		this.playbookXP = 0;
		this.insightXP = 0;
		this.prowessXP = 0;
		this.resolveXP = 0;
		this.coin = 0;
		this.stash = 0;

		this.vice = randomVice();
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
		Rating[] array = INSIGHT;
		int total = 0;
		for (int i = 0; i < array.length; ++i) {
			if (attributes.containsKey(array[i]))
				++total;
		}

		return total;
	}

	public int getProwess() {
		Rating[] array = PROWESS;
		int total = 0;
		for (int i = 0; i < array.length; ++i) {
			if (attributes.containsKey(array[i]))
				++total;
		}

		return total;
	}

	public int getResolve() {
		Rating[] array = RESOLVE;
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

			Trauma candidate = randomTrauma();
			while (trauma.contains(candidate)) {
				candidate = randomTrauma();
			}

			// TODO - testing
			System.out.println(this + " gained the " + candidate + " trauma condition.");
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

		Set<Faction.Upgrade> crewUpgrades = crew.upgradeSet();
		int upgrade = attributes.get(rating);

		boolean trainer = false;
		if (rating.equals(Rating.HUNT) || rating.equals(Rating.STUDY) || rating.equals(Rating.SURVEY)
				|| rating.equals(Rating.TINKER)) {
			trainer = crewUpgrades.contains(Faction.Upgrade.TRAINING_INSIGHT);
		}

		if (rating.equals(Rating.FINESSE) || rating.equals(Rating.PROWL) || rating.equals(Rating.SKIRMISH)
				|| rating.equals(Rating.WRECK)) {
			trainer = crewUpgrades.contains(Faction.Upgrade.TRAINING_PROWESS);
		}

		if (rating.equals(Rating.ATTUNE) || rating.equals(Rating.COMMAND) || rating.equals(Rating.CONSORT)
				|| rating.equals(Rating.SWAY)) {
			trainer = crewUpgrades.contains(Faction.Upgrade.TRAINING_RESOLVE);
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
	private static HashMap<Rating, Integer> attributesInit(Playbook playbook, Faction crew) {
		HashMap<Rating, Integer> init = new HashMap<Rating, Integer>();

		if (playbook.equals(Playbook.CUTTER)) {
			init.put(Rating.SKIRMISH, 2);
			init.put(Rating.COMMAND, 1);

		} else if (playbook.equals(Playbook.HOUND)) {
			init.put(Rating.HUNT, 2);
			init.put(Rating.SURVEY, 1);

		} else if (playbook.equals(Playbook.LEECH)) {
			init.put(Rating.TINKER, 2);
			init.put(Rating.WRECK, 1);

		} else if (playbook.equals(Playbook.LURK)) {
			init.put(Rating.PROWL, 2);
			init.put(Rating.FINESSE, 1);

		} else if (playbook.equals(Playbook.SLIDE)) {
			init.put(Rating.SWAY, 2);
			init.put(Rating.CONSORT, 1);

		} else if (playbook.equals(Playbook.SPIDER)) {
			init.put(Rating.CONSORT, 2);
			init.put(Rating.STUDY, 1);

		} else if (playbook.equals(Playbook.WHISPER)) {
			init.put(Rating.ATTUNE, 2);
			init.put(Rating.STUDY, 1);

		}

		int points = 4, dice;
		Rating choice;
		while (points > 0) {
			dice = (points > 1 && Dice.roll(2) == 1) ? 2 : 1;
			choice = randomRating();

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

	public static Playbook randomPlaybook() {
		return Dice.randomFromArray(PLAYBOOKS);
	}

	public static Attribute randomAttribute() {
		Attribute[] array = new Attribute[] { Attribute.INSIGHT, Attribute.PROWESS, Attribute.RESOLVE };
		return Dice.randomFromArray(array);
	}

	public static Rating randomRating() {
		return Dice.randomFromArray(RATINGS);
	}

	public static Rating randomInsight() {
		return Dice.randomFromArray(INSIGHT);
	}

	public static Rating randomProwess() {
		return Dice.randomFromArray(PROWESS);
	}

	public static Rating randomResolve() {
		return Dice.randomFromArray(RESOLVE);
	}

	public static Vice randomVice() {
		return Dice.randomFromArray(VICES);
	}

	public static Trauma randomTrauma() {
		return Dice.randomFromArray(TRAUMA_CONDITIONS);
	}

	public static Special randomSpecial(Playbook playbook) {
		Special special = null;
		if (playbook.equals(Playbook.CUTTER))
			special = randomCutterSpecial();
		else if (playbook.equals(Playbook.HOUND))
			special = randomHoundSpecial();
		else if (playbook.equals(Playbook.LEECH))
			special = randomLeechSpecial();
		else if (playbook.equals(Playbook.LURK))
			special = randomLurkSpecial();
		else if (playbook.equals(Playbook.SLIDE))
			special = randomSlideSpecial();
		else if (playbook.equals(Playbook.SPIDER))
			special = randomSpiderSpecial();
		else if (playbook.equals(Playbook.WHISPER))
			special = randomWhisperSpecial();

		return special;
	}

	public static Special randomCutterSpecial() {
		return Dice.randomFromArray(CUTTER_SPECIAL);
	}

	public static Special randomHoundSpecial() {
		return Dice.randomFromArray(HOUND_SPECIAL);
	}

	public static Special randomLeechSpecial() {
		return Dice.randomFromArray(LEECH_SPECIAL);
	}

	public static Special randomLurkSpecial() {
		return Dice.randomFromArray(LURK_SPECIAL);
	}

	public static Special randomSlideSpecial() {
		return Dice.randomFromArray(SLIDE_SPECIAL);
	}

	public static Special randomSpiderSpecial() {
		return Dice.randomFromArray(SPIDER_SPECIAL);
	}

	public static Special randomWhisperSpecial() {
		return Dice.randomFromArray(WHISPER_SPECIAL);
	}

	private static void crewSpecialSkills(Faction crew, Rogue rogue) {
		EnumSet<Faction.Special> crewSpecials = crew.skillSpecials();

		Rating rating;
		if (crewSpecials.contains(Faction.Special.DEADLY)) {
			rating = chooseDeadlySkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = chooseDeadlySkill();
			}
		}

		if (crewSpecials.contains(Faction.Special.DANGEROUS)) {
			rating = chooseDangerousSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = chooseDangerousSkill();
			}
		}

		if (crewSpecials.contains(Faction.Special.CHOSEN)) {
			rating = chooseChosenSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = chooseChosenSkill();
			}
		}

		if (crewSpecials.contains(Faction.Special.SILVER_TONGUES)) {
			rating = chooseSilverTongueSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = chooseSilverTongueSkill();
			}
		}

		if (crewSpecials.contains(Faction.Special.EVERYONE_STEALS)) {
			rating = chooseEveryoneStealsSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = chooseEveryoneStealsSkill();
			}
		}

		if (crewSpecials.contains(Faction.Special.RENEGADES)) {
			rating = chooseRenegadeSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = chooseRenegadeSkill();
			}
		}

	}

	private static Rating chooseDeadlySkill() {
		Rating[] array = new Rating[] { Rating.HUNT, Rating.PROWL, Rating.SKIRMISH };
		return Dice.randomFromArray(array);
	}

	private static Rating chooseDangerousSkill() {
		Rating[] array = new Rating[] { Rating.HUNT, Rating.SKIRMISH, Rating.WRECK };
		return Dice.randomFromArray(array);
	}

	private static Rating chooseChosenSkill() {
		Rating[] array = new Rating[] { Rating.ATTUNE, Rating.STUDY, Rating.SWAY };
		return Dice.randomFromArray(array);
	}

	private static Rating chooseSilverTongueSkill() {
		Rating[] array = new Rating[] { Rating.COMMAND, Rating.CONSORT, Rating.SWAY };
		return Dice.randomFromArray(array);
	}

	private static Rating chooseEveryoneStealsSkill() {
		Rating[] array = new Rating[] { Rating.FINESSE, Rating.PROWL, Rating.TINKER };
		return Dice.randomFromArray(array);
	}

	private static Rating chooseRenegadeSkill() {
		Rating[] array = new Rating[] { Rating.FINESSE, Rating.PROWL, Rating.SKIRMISH };
		return Dice.randomFromArray(array);
	}

	public static Rating[][] randomBeats() {
		Rating[][] beats = new Rating[4][];

		for (int i = 0; i < beats.length; ++i) {
			beats[i] = (Dice.roll(2) == 1) ? HIGH_TENSION_APPROACH : LOW_TENSION_APPROACH;
		}

		return beats;
	}

	public static Rating approachByCrewType(Crew.Type crew) {
		Rating[] array = APPROACHES;
		Rating choice = randomApproach();

		if (crew.equals(Crew.Type.ASSASSINS))
			array = ASSASSIN_APPROACH;
		else if (crew.equals(Crew.Type.BRAVOS))
			array = BRAVO_APPROACH;
		else if (crew.equals(Crew.Type.CULT))
			array = CULT_APPROACH;
		else if (crew.equals(Crew.Type.HAWKERS))
			array = HAWKER_APPROACH;
		else if (crew.equals(Crew.Type.SHADOWS))
			array = SHADOW_APPROACH;
		else if (crew.equals(Crew.Type.SMUGGLERS))
			array = SMUGGLER_APPROACH;

		choice = array[Dice.roll(array.length) - 1];
		return choice;
	}

	public static Rating approachByPlan(Score.Approach plan) {
		Rating choice = randomApproach();

		if (plan.equals(Score.Approach.ASSAULT))
			choice = assaultApproach();
		else if (plan.equals(Score.Approach.DECEPTION))
			choice = deceptionApproach();
		else if (plan.equals(Score.Approach.OCCULT))
			choice = occultApproach();
		else if (plan.equals(Score.Approach.SOCIAL))
			choice = socialApproach();
		else if (plan.equals(Score.Approach.STEALTH))
			choice = stealthApproach();
		else if (plan.equals(Score.Approach.TRANSPORT))
			choice = transportApproach();

		return choice;
	}

	public static Rating randomApproach() {
		return Dice.randomFromArray(APPROACHES);
	}

	public static Rating pseudoRandomApproach(Score.Act act, Score.Approach plan, Faction crew, Rating[][] beats) {
		// Crew.Type type = crew.crewType();
		Rating choice;
		int dice = Dice.roll(100);
		if (dice < 21) {
			// TODO - testing
			// System.out.println(" " + " " + " What are we doing?");
			choice = approachByCrewType(crew.crewType());
		} else if (dice < 61) {
			// TODO - testing
			// System.out.println(" " + " " + " Stick to the plan.");
			choice = approachByPlan(plan);
		} else if (dice < 81) {
			// TODO - testing
			// System.out.println(" " + " " + " We don't have time.");
			Rating[] array = APPROACHES;

			if (act.equals(Score.Act.INCITING))
				array = beats[0];
			else if (act.equals(Score.Act.RISING))
				array = beats[1];
			else if (act.equals(Score.Act.TURNING))
				array = beats[2];
			else if (act.equals(Score.Act.FALLING))
				array = beats[3];

			choice = array[Dice.roll(array.length) - 1];
		} else {
			// TODO - testing
			// System.out.println(" " + " " + " Ninjas attack.");
			choice = randomApproach();
		}

		return choice;
	}

	public static Rating assaultApproach() {
		return Dice.randomFromArray(ASSAULT_APPROACHES);
	}

	public static Rating deceptionApproach() {
		return Dice.randomFromArray(DECEPTION_APPROACHES);
	}

	public static Rating occultApproach() {
		return Dice.randomFromArray(OCCULT_APPROACHES);
	}

	public static Rating socialApproach() {
		return Dice.randomFromArray(SOCIAL_APPROACHES);
	}

	public static Rating stealthApproach() {
		return Dice.randomFromArray(STEALTH_APPROACHES);
	}

	public static Rating transportApproach() {
		return Dice.randomFromArray(TRANSPORT_APPROACHES);
	}

	/*
	 * COHORTS - INNER CLASS
	 */

	private class Cohort {
		CohortType type;

		Cohort(CohortType type) {
			this.type = type;
		}

		boolean isExpert() {
			return (type.equals(CohortType.EXPERT));
		}

		boolean isGang() {
			return (type.equals(CohortType.EXPERT) != true);
		}
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
