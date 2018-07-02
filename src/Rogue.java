import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Rogue {
	public enum Playbook {
		CUTTER, HOUND, LEECH, LURK, SLIDE, SPIDER, WHISPER
	}

	public enum Attribute {
		INSIGHT, PROWESS, RESOLVE
	}

	public enum Rating {
		ATTUNE, COMMAND, CONSORT, FINESSE, HUNT, PROWL, SKIRMISH, STUDY, SURVEY, SWAY, TINKER, WRECK
	}

	public enum Vice {
		FAITH, GAMBLING, LUXURY, OBLIGATION, PLEASURE, STUPOR, WEIRD
	}

	public enum Special {
		VETERAN_1, VETERAN_2, VETERAN_3, BATTLEBORN, BODYGUARD, GHOST_FIGHTER, LEADER, MULE, NOT_TO_BE_TRIFLED_WITH, SAVAGE, VIGOROUS, SHARPSHOOTER, FOCUSED, GHOST_HUNTER_1, GHOST_HUNTER_2, SCOUT, SURVIVOR, TOUGH_AS_NAILS, VENGEFUL, ALCHEMIST, ANALYST, ARTIFICER, FORTITUDE, GHOST_WARD, PHYSICKER, SABOTEUR, VENOMOUS, INFILTRATOR, AMBUSH, DAREDEVIL, THE_DEVILS_FOOTSTEPS, EXPERTISE, GHOST_VEIL, REFLEXES, SHADOW, ROOKS_GAMBIT, CLOAK_DAGGER, GHOST_VOICE, LIKE_LOOKING_INTO_A_MIRROR, SOMETHING_ON_THE_SIDE, MESMERISM, SUBTERFUGE, TRUST_IN_ME, FORESIGHT, CALCULATING, CONNECTED, FUNCTIONING_VICE, GHOST_CONTRACT, JAIL_BIRD, MASTERMIND, WEAVING_THE_WEB, COMPEL, GHOST_MIND, IRON_WILL, OCCULTIST, RITUAL, STRANGE_METHODS, TEMPEST, WARDED
	}

	public enum Trauma {
		COLD, HAUNTED, OBSESSED, PARANOID, RECKLESS, SOFT, UNSTABLE, VICIOUS
	}

	// static fields
	private static final int MAX_STRESS = 9;

	private static final String[] NAMES = { "Adric", "Aldo", "Amosen", "Andrel", "Arden", "Arlyn", "Arquo", "Arvus",
			"Ashlyn", "Branon", "Brace", "Brance", "Brena", "Bricks", "Candra", "Carissa", "Carro", "Casslyn",
			"Cavelle", "Clave", "Corille", "Cross", "Crowl", "Cyrene", "Daphnia", "Drav", "Edlun", "Emeline", "Grine",
			"Helles", "Hix", "Holtz", "Kamelin", "Kelyr", "Kobb", "Kristov", "Laudius", "Lauria", "Lenia", "Lizete",
			"Lorette", "Lucella", "Lynthia", "Mara", "Milos", "Morlan", "Myre", "Narcus", "Naria", "Noggs", "Odrienne",
			"Orlan", "Phin", "Polonia", "Quess", "Remira", "Ring", "Roethe", "Sesereth", "Sethla", "Skannon", "Stavrul",
			"Stev", "Syra", "Talitha", "Tesslyn", "Thena", "Timoth", "Tocker", "Una", "Vaurin", "Veleris", "Veretta",
			"Vestine", "Vey", "Volette", "Vond", "Weaver", "Wester", "Zamira" };

	private static final Playbook[] PLAYBOOKS = { Playbook.CUTTER, Playbook.HOUND, Playbook.LEECH, Playbook.LURK,
			Playbook.SLIDE, Playbook.SPIDER, Playbook.WHISPER };

	private static final Rating[] RATINGS = { Rating.ATTUNE, Rating.COMMAND, Rating.CONSORT, Rating.FINESSE,
			Rating.HUNT, Rating.PROWL, Rating.SKIRMISH, Rating.STUDY, Rating.SURVEY, Rating.SWAY, Rating.TINKER,
			Rating.WRECK };
	private static final Rating[] INSIGHT = { Rating.HUNT, Rating.STUDY, Rating.SURVEY, Rating.TINKER };
	private static final Rating[] PROWESS = { Rating.FINESSE, Rating.PROWL, Rating.SKIRMISH, Rating.WRECK };
	private static final Rating[] RESOLVE = { Rating.ATTUNE, Rating.COMMAND, Rating.CONSORT, Rating.SWAY };

	private static final Vice[] VICES = { Vice.FAITH, Vice.GAMBLING, Vice.LUXURY, Vice.OBLIGATION, Vice.PLEASURE,
			Vice.STUPOR, Vice.WEIRD };

	private static final Special[] CUTTER_SPECIAL = { Special.BATTLEBORN, Special.BODYGUARD, Special.GHOST_FIGHTER,
			Special.LEADER, Special.MULE, Special.NOT_TO_BE_TRIFLED_WITH, Special.SAVAGE, Special.VIGOROUS };
	private static final Special[] HOUND_SPECIAL = { Special.SHARPSHOOTER, Special.FOCUSED, Special.GHOST_HUNTER_1,
			Special.GHOST_HUNTER_2, Special.SCOUT, Special.SURVIVOR, Special.TOUGH_AS_NAILS, Special.VENGEFUL };
	private static final Special[] LEECH_SPECIAL = { Special.ALCHEMIST, Special.ANALYST, Special.ARTIFICER,
			Special.FORTITUDE, Special.GHOST_WARD, Special.PHYSICKER, Special.SABOTEUR, Special.VENOMOUS };
	private static final Special[] LURK_SPECIAL = { Special.INFILTRATOR, Special.AMBUSH, Special.DAREDEVIL,
			Special.THE_DEVILS_FOOTSTEPS, Special.EXPERTISE, Special.GHOST_VEIL, Special.REFLEXES, Special.SHADOW };
	private static final Special[] SLIDE_SPECIAL = { Special.ROOKS_GAMBIT, Special.CLOAK_DAGGER, Special.GHOST_VOICE,
			Special.LIKE_LOOKING_INTO_A_MIRROR, Special.SOMETHING_ON_THE_SIDE, Special.MESMERISM, Special.SUBTERFUGE,
			Special.TRUST_IN_ME };
	private static final Special[] SPIDER_SPECIAL = { Special.FORESIGHT, Special.CALCULATING, Special.CONNECTED,
			Special.FUNCTIONING_VICE, Special.GHOST_CONTRACT, Special.JAIL_BIRD, Special.MASTERMIND,
			Special.WEAVING_THE_WEB };
	private static final Special[] WHISPER_SPECIAL = { Special.COMPEL, Special.GHOST_MIND, Special.IRON_WILL,
			Special.OCCULTIST, Special.RITUAL, Special.STRANGE_METHODS, Special.TEMPEST, Special.WARDED };

	private static final Trauma[] TRAUMA_CONDITIONS = { Trauma.COLD, Trauma.HAUNTED, Trauma.OBSESSED, Trauma.PARANOID,
			Trauma.RECKLESS, Trauma.SOFT, Trauma.UNSTABLE, Trauma.VICIOUS };

	// instance fields
	private Crew crew;
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
	public Rogue(Crew crew) {
		// TODO
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

		Set<Crew.Upgrade> crewUpgrades = crew.upgradeSet();
		int upgrade = attributes.get(rating);

		boolean trainer = false;
		if (rating.equals(Rating.HUNT) || rating.equals(Rating.STUDY) || rating.equals(Rating.SURVEY)
				|| rating.equals(Rating.TINKER)) {
			trainer = crewUpgrades.contains(Crew.Upgrade.TRAINING_INSIGHT);
		}

		if (rating.equals(Rating.FINESSE) || rating.equals(Rating.PROWL) || rating.equals(Rating.SKIRMISH)
				|| rating.equals(Rating.WRECK)) {
			trainer = crewUpgrades.contains(Crew.Upgrade.TRAINING_PROWESS);
		}

		if (rating.equals(Rating.ATTUNE) || rating.equals(Rating.COMMAND) || rating.equals(Rating.CONSORT)
				|| rating.equals(Rating.SWAY)) {
			trainer = crewUpgrades.contains(Crew.Upgrade.TRAINING_RESOLVE);
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

	// static methods
	private static HashMap<Rating, Integer> attributesInit(Playbook playbook, Crew crew) {
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

	private static void crewSpecialSkills(Crew crew, Rogue rogue) {
		Set<Crew.Special> crewSpecials = crew.containsSkillSpecials();

		Rating rating;
		if (crewSpecials.contains(Crew.Special.DEADLY)) {
			rating = chooseDeadlySkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = chooseDeadlySkill();
			}
		}

		if (crewSpecials.contains(Crew.Special.DANGEROUS)) {
			rating = chooseDangerousSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = chooseDangerousSkill();
			}
		}

		if (crewSpecials.contains(Crew.Special.CHOSEN)) {
			rating = chooseChosenSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = chooseChosenSkill();
			}
		}

		if (crewSpecials.contains(Crew.Special.SILVER_TONGUES)) {
			rating = chooseSilverTongueSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = chooseSilverTongueSkill();
			}
		}

		if (crewSpecials.contains(Crew.Special.EVERYONE_STEALS)) {
			rating = chooseEveryoneStealsSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = chooseEveryoneStealsSkill();
			}
		}

		if (crewSpecials.contains(Crew.Special.RENEGADES)) {
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
