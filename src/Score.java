import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Score {
	public enum Goal {
		// CLIMB from "social climb;" SHAKE from "shaking someone else"
		ASSIST, CLIMB, CLAIM, SHAKE
	}

	public enum Plan {
		ASSAULT, DECEPTION, STEALTH, OCCULT, SOCIAL, TRANSPORT
	}

	public enum Activity {
		ACCIDENT, DISAPPEARANCE, MURDER, RANSOM, BATTLE, EXTORTION, SABOTAGE, SMASH_N_GRAB, ACQUISITION, AUGURY, CONSECRATION, SACRIFICE, SALES, SUPPLY, SHOW_OF_FORCE, SOCIALIZE, BURGLARY, ESPIONAGE, ROBBERY, CARGO_ARMS, CARGO_CONTRABAND, CARGO_PEOPLE, CARGO_WEIRD
	}

	public enum Consequence {
		MINOR_COMPLICATION, MAJOR_COMPLICATION, SEVERE_COMPLICATION, HARM_1, HARM_2, HARM_3, REDUCED_EFFECT, ESCALATE_RISKY, ESCALATE_DESPERATE, WITHDRAW, LOST_OPPORTUNITY
	}

	public enum Position {
		CONTROLLED, RISKY, DESPERATE
	}

	public enum Effect {
		EXTREME, GREAT, STANDARD, LIMITED, ZERO
	}

	public enum Act {
		INCITING, RISING, TURNING, FALLING, RELEASE
	}

	public enum Result {
		CRITICAL, SUCCESS, PARTIAL, FAILURE
	}

	public enum Entanglement {
		ARREST, COOPERATION, DEMONIC_NOTICE, FLIPPED, GANG_TROUBLE, INTERROGATION, QUESTIONING, REPRISALS, RIVALS, SHOW_OF_FORCE, UNQUIET_DEAD, USUAL_SUSPECTS
	}

	public enum Diversion {
		ACQUIRE_ASSET, LONG_TERM_PROJECT, RECOVER, REDUCE_HEAT, INDULGE_VICE
	}

	// static fields
	private static final int ACTIONS_PER_SCORE = 20;

	private static final Plan[] ALL_PLANS = { Plan.ASSAULT, Plan.DECEPTION, Plan.STEALTH, Plan.OCCULT, Plan.SOCIAL,
			Plan.TRANSPORT };
	private static final String[] DETAILS = { "Point of attack.", "Method of deception.", "Point of infiltration.",
			"Arcane method.", "Social connection.", "Route & means." };

	private static final Position[] POSITIONS = { Position.CONTROLLED, Position.RISKY, Position.DESPERATE };
	private static final Effect[] EFFECTS = { Effect.EXTREME, Effect.GREAT, Effect.STANDARD, Effect.LIMITED,
			Effect.ZERO };
	private static final Act[] ACTS = { Act.INCITING, Act.RISING, Act.TURNING, Act.FALLING, Act.RELEASE };

	private static final Activity[] ALL_ACTIVITIES = { Activity.ACCIDENT, Activity.DISAPPEARANCE, Activity.MURDER,
			Activity.RANSOM, Activity.BATTLE, Activity.EXTORTION, Activity.SABOTAGE, Activity.SMASH_N_GRAB,
			Activity.ACQUISITION, Activity.AUGURY, Activity.CONSECRATION, Activity.SACRIFICE, Activity.SALES,
			Activity.SUPPLY, Activity.SHOW_OF_FORCE, Activity.SOCIALIZE, Activity.BURGLARY, Activity.ESPIONAGE,
			Activity.ROBBERY, Activity.CARGO_ARMS, Activity.CARGO_CONTRABAND, Activity.CARGO_PEOPLE,
			Activity.CARGO_WEIRD };

	private static final Activity[] ASSASSIN_FLAVORS = { Activity.ACCIDENT, Activity.DISAPPEARANCE, Activity.MURDER,
			Activity.RANSOM };
	private static final Activity[] BRAVO_FLAVORS = { Activity.BATTLE, Activity.EXTORTION, Activity.SABOTAGE,
			Activity.SMASH_N_GRAB };
	private static final Activity[] CULT_FLAVORS = { Activity.ACQUISITION, Activity.AUGURY, Activity.CONSECRATION,
			Activity.SACRIFICE };
	private static final Activity[] HAWKER_FLAVORS = { Activity.SALES, Activity.SUPPLY, Activity.SHOW_OF_FORCE,
			Activity.SOCIALIZE };
	private static final Activity[] SHADOW_FLAVORS = { Activity.BURGLARY, Activity.ESPIONAGE, Activity.ROBBERY,
			Activity.SABOTAGE };
	private static final Activity[] SMUGGLER_FLAVORS = { Activity.CARGO_ARMS, Activity.CARGO_CONTRABAND,
			Activity.CARGO_PEOPLE, Activity.CARGO_WEIRD };

	private static final Effect[] INCITING_EFFECTS = { Effect.STANDARD, Effect.LIMITED };
	private static final Effect[] RISING_EFFECTS = { Effect.STANDARD, Effect.LIMITED };
	private static final Effect[] TURNING_EFFECTS = { Effect.GREAT, Effect.STANDARD, Effect.LIMITED };
	private static final Effect[] FALLING_EFFECTS = { Effect.GREAT, Effect.STANDARD };

	// approaches
	private static final Rogue.Rating[] APPROACHES = { Rogue.Rating.ATTUNE, Rogue.Rating.COMMAND, Rogue.Rating.CONSORT,
			Rogue.Rating.FINESSE, Rogue.Rating.HUNT, Rogue.Rating.PROWL, Rogue.Rating.SKIRMISH, Rogue.Rating.STUDY,
			Rogue.Rating.SURVEY, Rogue.Rating.SWAY, Rogue.Rating.TINKER, Rogue.Rating.WRECK };

	// approaches by crew type
	private static final Rogue.Rating[] ASSASSIN_APPROACH = { Rogue.Rating.FINESSE, Rogue.Rating.HUNT,
			Rogue.Rating.PROWL, Rogue.Rating.SKIRMISH, Rogue.Rating.STUDY, Rogue.Rating.TINKER };
	private static final Rogue.Rating[] BRAVO_APPROACH = { Rogue.Rating.COMMAND, Rogue.Rating.CONSORT,
			Rogue.Rating.FINESSE, Rogue.Rating.SKIRMISH, Rogue.Rating.SURVEY, Rogue.Rating.WRECK };
	private static final Rogue.Rating[] CULT_APPROACH = { Rogue.Rating.ATTUNE, Rogue.Rating.COMMAND,
			Rogue.Rating.CONSORT, Rogue.Rating.PROWL, Rogue.Rating.STUDY, Rogue.Rating.TINKER };
	private static final Rogue.Rating[] HAWKER_APPROACH = { Rogue.Rating.COMMAND, Rogue.Rating.CONSORT,
			Rogue.Rating.FINESSE, Rogue.Rating.SURVEY, Rogue.Rating.SWAY, Rogue.Rating.WRECK };
	private static final Rogue.Rating[] SHADOW_APPROACH = { Rogue.Rating.COMMAND, Rogue.Rating.FINESSE,
			Rogue.Rating.PROWL, Rogue.Rating.SURVEY, Rogue.Rating.TINKER, Rogue.Rating.WRECK };
	private static final Rogue.Rating[] SMUGGLER_APPROACH = { Rogue.Rating.CONSORT, Rogue.Rating.FINESSE,
			Rogue.Rating.PROWL, Rogue.Rating.SURVEY, Rogue.Rating.SWAY, Rogue.Rating.TINKER };

	// approaches by plan
	private static final Rogue.Rating[] ASSAULT_APPROACHES = { Rogue.Rating.COMMAND, Rogue.Rating.HUNT,
			Rogue.Rating.SKIRMISH, Rogue.Rating.SURVEY, Rogue.Rating.WRECK };
	private static final Rogue.Rating[] DECEPTION_APPROACHES = { Rogue.Rating.CONSORT, Rogue.Rating.FINESSE,
			Rogue.Rating.PROWL, Rogue.Rating.STUDY, Rogue.Rating.SURVEY, Rogue.Rating.SWAY };
	private static final Rogue.Rating[] OCCULT_APPROACHES = { Rogue.Rating.ATTUNE, Rogue.Rating.COMMAND,
			Rogue.Rating.CONSORT, Rogue.Rating.STUDY, Rogue.Rating.SWAY, Rogue.Rating.TINKER };
	private static final Rogue.Rating[] SOCIAL_APPROACHES = { Rogue.Rating.COMMAND, Rogue.Rating.CONSORT,
			Rogue.Rating.FINESSE, Rogue.Rating.STUDY, Rogue.Rating.SURVEY, Rogue.Rating.SWAY };
	private static final Rogue.Rating[] STEALTH_APPROACHES = { Rogue.Rating.FINESSE, Rogue.Rating.HUNT,
			Rogue.Rating.PROWL, Rogue.Rating.STUDY, Rogue.Rating.TINKER, Rogue.Rating.WRECK };
	private static final Rogue.Rating[] TRANSPORT_APPROACHES = { Rogue.Rating.COMMAND, Rogue.Rating.CONSORT,
			Rogue.Rating.FINESSE, Rogue.Rating.HUNT, Rogue.Rating.PROWL, Rogue.Rating.SURVEY, Rogue.Rating.SWAY };

	// approaches by tension level
	private static final Rogue.Rating[] HIGH_TENSION_APPROACH = { Rogue.Rating.COMMAND, Rogue.Rating.HUNT,
			Rogue.Rating.PROWL, Rogue.Rating.SKIRMISH, Rogue.Rating.SWAY, Rogue.Rating.WRECK };
	private static final Rogue.Rating[] LOW_TENSION_APPROACH = { Rogue.Rating.ATTUNE, Rogue.Rating.CONSORT,
			Rogue.Rating.FINESSE, Rogue.Rating.STUDY, Rogue.Rating.SURVEY, Rogue.Rating.TINKER };

	// consequences
	private static final Consequence[] MINOR_CONSEQUENCE = { Consequence.HARM_1, Consequence.MINOR_COMPLICATION,
			Consequence.ESCALATE_RISKY };
	private static final Consequence[] MAJOR_CONSEQUENCE = { Consequence.HARM_2, Consequence.MAJOR_COMPLICATION,
			Consequence.ESCALATE_DESPERATE, Consequence.LOST_OPPORTUNITY };
	private static final Consequence[] SEVERE_CONSEQUENCE = { Consequence.HARM_3, Consequence.SEVERE_COMPLICATION,
			Consequence.LOST_OPPORTUNITY };

	// fields
	private Crew crew;
	private ArrayList<Rogue> team;

	private Crew client;
	private Crew target;
	private Goal goal;
	private Plan plan;
	private Activity activity;

	//
	Crew.Claim claim;

	//
	private Clock window;
	private Clock openingMove;
	private Clock primaryObjective;
	private Clock getawayMove;

	//
	private Act act;
	private int scene;
	private int tension;
	private Rogue.Rating[][] beats;

	private ArrayList<Action> actions;

	// constructors
	public Score(Crew crew, List<Rogue> team, Crew client, Crew target) {
		this(crew, team, client, target, Goal.CLIMB);
	}

	public Score(Crew crew, List<Rogue> team, Crew client, Crew target, Goal goal) {
		/*
		 * TODO - I need to figure out (at some point) how to differentiate between a
		 * job given by another crew and a job given by a single patron
		 */
		this.crew = crew;
		this.team = new ArrayList<Rogue>(team);

		this.client = client;
		this.target = target;
		this.goal = goal;
		this.plan = randomPlan();
		this.activity = randomActivity(crew.getCrewType());

		if (goal.equals(Goal.CLAIM)) {
			Crew.Claim candidate;

			if (Dice.roll(3) == 1) {
				candidate = Crew.turfClaim(crew);
			} else {
				candidate = Crew.randomClaimByCrew(crew.getCrewType());
			}

			while (crew.getClaims().containsKey(candidate)) {
				candidate = Crew.randomClaimByCrew(crew.getCrewType());
			}
			this.claim = candidate;
		}

		//
		this.window = new Clock(Clock.Length.FOUR);
		this.openingMove = new Clock(Clock.Length.FOUR);
		this.primaryObjective = new Clock();
		this.getawayMove = new Clock();

		//
		this.act = Act.INCITING;
		this.scene = 1;
		this.tension = 0;
		this.beats = randomBeats();

		//
		this.actions = new ArrayList<Action>();
	}

	// methods
	public void action(int dice) {
		while (unresolved() && teamRemaining() > 0) {
			if (scene == 1) {
				// every mission begins with engagement roll
				Position start = engagementRoll(dice);
				if (openingMove.expired()) {
					actions.add(new Action(primaryObjective, start));

				} else {
					actions.add(new Action(openingMove, start));

				}
			} else {
				// TODO - DEBUG
				// objectiveCheck();
				if (getawayMove.expired()) {
					/*
					 * FIXME - currently here for debug only; replace with "end score"
					 */
					// actions.add(new Action(getawayMove, randomPosition()));
					System.out.println("Clean getaway.");
					break;

				} else if (primaryObjective.expired()) {
					actions.add(new Action(getawayMove, randomPosition()));

				} else if (openingMove.expired()) {
					actions.add(new Action(primaryObjective, randomPosition()));

				} else {
					actions.add(new Action(openingMove, randomPosition()));

				}
			}

			// while mission not resolved, advance()
			moraleCheck();
			if (window.expired() != true) {
				int[] results = Dice.fortune(++scene - ACTIONS_PER_SCORE + tension);
				if (results[3] > 0 || results[4] > 0 || results[5] > 0) {
					window.countDown();
					// while I still use the act structure...
					act = nextAct();
				}
			}
		}

		if (window.expired()) {
			System.out.println(" " + " " + " Window closed.");
			if (primaryObjective.expired() != true)
				System.out.println(" " + " " + " Primary objective failed.");
		}

		// TODO
		System.out.println();
		new Downtime(this);
	}

	public boolean advance() {
		boolean advance = false;

		if (window.expired() != true) {
			int[] results = Dice.fortune(++scene - ACTIONS_PER_SCORE + tension);
			if (results[3] > 0 || results[4] > 0 || results[5] > 0) {
				window.countDown();
				advance = true;
				// while I still use the act structure...
				act = nextAct();
			}
		}

		// if (act.equals(Act.RELEASE) != true) {
		// int[] results = Dice.fortune(++scene - ACTIONS_PER_SCORE + tension);
		// // System.out.println(String.format("Scene: %2d", scene));
		//
		// if (results[3] > 0 || results[4] > 0 || results[5] > 0) {
		// act = nextAct();
		// advance = true;
		// System.out.println(act.toString());
		// }
		// }

		return advance;
	}

	public Position engagementRoll(int dice) {
		Position position;
		int[] results = Dice.fortune(Dice.roll(dice));

		// TODO - testing
		System.out.println("Engagement roll.");
		if (results[5] > 1) {
			// critical result clears the opening
			position = Position.CONTROLLED;
			openingMove.clear();

		} else if (results[5] > 0) {
			// success results in controlled situation
			position = Position.CONTROLLED;

		} else if (results[3] > 0 || results[4] > 0) {
			// partial results in risky position
			position = Position.RISKY;

		} else {
			// failure results in desperate situation
			position = Position.DESPERATE;

		}

		// System.out.println(position);
		return position;
	}

	public Act getAct() {
		return act;
	}

	public int getScene() {
		return scene;
	}

	public Act nextAct() {
		Act nextAct = Act.INCITING;

		if (act.equals(Act.INCITING))
			nextAct = Act.RISING;
		else if (act.equals(Act.RISING))
			nextAct = Act.TURNING;
		else if (act.equals(Act.TURNING))
			nextAct = Act.FALLING;
		else if (act.equals(Act.FALLING))
			nextAct = Act.RELEASE;

		return nextAct;
	}

	public boolean patronage() {
		return (crew.sameAs(client) != true);
	}

	public boolean unresolved() {
		// return (act.equals(Act.RELEASE) != true);
		return (window.expired() != true);
	}

	public void objectiveCheck() {
		if (getawayMove.expired()) {
			System.out.println(" " + " " + " We're clear.");

		} else if (primaryObjective.expired()) {
			System.out.println(" " + " " + " Primary objective complete.");

		} else if (openingMove.expired()) {
			System.out.println(" " + " " + " We're in.");

		} else {
			System.out.println(" " + " " + " Approaching objective.");

		}
	}

	public boolean moraleCheck() {
		boolean morale = false;
		int moraleCheck = 0, penalty = 0;
		int tpk = 0;
		Rogue rogue;
		for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
			rogue = it.next();
			if (rogue.stressedOut()) {
				penalty += 4;
				++tpk;
			}
		}

		// base chance to abort mission is 5%
		moraleCheck = Dice.roll(20) + penalty;
		System.out.println("Morale check: " + moraleCheck + " (" + penalty + ")");
		boolean abort = (tpk == team.size()) ? true : (moraleCheck > 20) ? true : false;
		if (primaryObjective.expired() != true && abort)
			window.clear();

		return morale;
	}

	public int teamRemaining() {
		int remaining = 0;
		for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
			remaining += (it.next().goodToGo()) ? 1 : 0;
		}

		return remaining;
	}

	@Override
	public String toString() {
		String string = String.format("name %s [%s] %s", plan, detail(plan), activity);
		string += "\nTarget: " + target.toString();

		return string;
	}

	public String toStringDetailed() {
		String string = String.format("name %s [%s] %s || Team size: %d %n%s %nTarget: %s", plan, detail(plan),
				activity, team.size(), team.toString(), target.toString());

		return string;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Plan randomPlan() {
		return Dice.randomFromArray(ALL_PLANS);
	}

	private static String detail(Plan plan) {
		String string = "";

		if (plan.equals(Plan.ASSAULT))
			string = DETAILS[0];
		else if (plan.equals(Plan.DECEPTION))
			string = DETAILS[1];
		else if (plan.equals(Plan.STEALTH))
			string = DETAILS[2];
		else if (plan.equals(Plan.OCCULT))
			string = DETAILS[3];
		else if (plan.equals(Plan.SOCIAL))
			string = DETAILS[4];
		else if (plan.equals(Plan.TRANSPORT))
			string = DETAILS[5];

		return string;
	}

	public static Rogue.Rating[][] randomBeats() {
		Rogue.Rating[][] beats = new Rogue.Rating[4][];

		for (int i = 0; i < beats.length; ++i) {
			beats[i] = (Dice.roll(2) == 1) ? HIGH_TENSION_APPROACH : LOW_TENSION_APPROACH;
		}

		return beats;
	}

	public static Activity randomActivity() {
		return Dice.randomFromArray(ALL_ACTIVITIES);
	}

	public static Activity randomActivity(Crew.Type crew) {
		Activity[] array = ALL_ACTIVITIES;
		if (crew.equals(Crew.Type.ASSASSINS))
			array = ASSASSIN_FLAVORS;
		else if (crew.equals(Crew.Type.BRAVOS))
			array = BRAVO_FLAVORS;
		else if (crew.equals(Crew.Type.CULT))
			array = CULT_FLAVORS;
		else if (crew.equals(Crew.Type.HAWKERS))
			array = HAWKER_FLAVORS;
		else if (crew.equals(Crew.Type.SHADOWS))
			array = SHADOW_FLAVORS;
		else if (crew.equals(Crew.Type.SMUGGLERS))
			array = SMUGGLER_FLAVORS;

		Activity choice = array[Dice.roll(array.length) - 1];
		return choice;
	}

	public static Rogue.Rating approachByCrewType(Crew.Type crew) {
		Rogue.Rating[] array = APPROACHES;
		Rogue.Rating choice = randomApproach();

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

	public static Rogue.Rating approachByPlan(Plan plan) {
		Rogue.Rating choice = randomApproach();

		if (plan.equals(Plan.ASSAULT))
			choice = assaultApproach();
		else if (plan.equals(Plan.DECEPTION))
			choice = deceptionApproach();
		else if (plan.equals(Plan.OCCULT))
			choice = occultApproach();
		else if (plan.equals(Plan.SOCIAL))
			choice = socialApproach();
		else if (plan.equals(Plan.STEALTH))
			choice = stealthApproach();
		else if (plan.equals(Plan.TRANSPORT))
			choice = transportApproach();

		return choice;
	}

	public static Rogue.Rating randomApproach() {
		return Dice.randomFromArray(APPROACHES);
	}

	public static Rogue.Rating pseudoRandomApproach(Act act, Plan plan, Crew crew, Rogue.Rating[][] beats) {
		Crew.Type type = crew.getCrewType();
		Rogue.Rating choice;
		int dice = Dice.roll(100);
		if (dice < 21) {
			// TODO - testing
			// System.out.println(" " + " " + " What are we doing?");
			choice = approachByCrewType(crew.getCrewType());
		} else if (dice < 61) {
			// TODO - testing
			// System.out.println(" " + " " + " Stick to the plan.");
			choice = approachByPlan(plan);
		} else if (dice < 81) {
			// TODO - testing
			// System.out.println(" " + " " + " We don't have time.");
			Rogue.Rating[] array = APPROACHES;

			if (act.equals(Act.INCITING))
				array = beats[0];
			else if (act.equals(Act.RISING))
				array = beats[1];
			else if (act.equals(Act.TURNING))
				array = beats[2];
			else if (act.equals(Act.FALLING))
				array = beats[3];

			choice = array[Dice.roll(array.length) - 1];
		} else {
			// TODO - testing
			// System.out.println(" " + " " + " Ninjas attack.");
			choice = randomApproach();
		}

		return choice;
	}

	public static Rogue.Rating assaultApproach() {
		return Dice.randomFromArray(ASSAULT_APPROACHES);
	}

	public static Rogue.Rating deceptionApproach() {
		return Dice.randomFromArray(DECEPTION_APPROACHES);
	}

	public static Rogue.Rating occultApproach() {
		return Dice.randomFromArray(OCCULT_APPROACHES);
	}

	public static Rogue.Rating socialApproach() {
		return Dice.randomFromArray(SOCIAL_APPROACHES);
	}

	public static Rogue.Rating stealthApproach() {
		return Dice.randomFromArray(STEALTH_APPROACHES);
	}

	public static Rogue.Rating transportApproach() {
		return Dice.randomFromArray(TRANSPORT_APPROACHES);
	}

	public static Position randomPosition() {
		return Dice.randomFromArray(POSITIONS);
	}

	public static Effect effectsByAct(Act act) {
		Effect choice = randomEffect();

		if (act.equals(Act.INCITING))
			choice = incitingEffect();
		else if (act.equals(Act.RISING))
			choice = risingEffect();
		else if (act.equals(Act.TURNING))
			choice = turningEffect();
		else if (act.equals(Act.FALLING))
			choice = fallingEffect();

		return choice;
	}

	public static Effect randomEffect() {
		return Dice.randomFromArray(EFFECTS);
	}

	public static Effect incitingEffect() {
		return Dice.randomFromArray(INCITING_EFFECTS);
	}

	public static Effect risingEffect() {
		return Dice.randomFromArray(RISING_EFFECTS);
	}

	public static Effect turningEffect() {
		return Dice.randomFromArray(TURNING_EFFECTS);
	}

	public static Effect fallingEffect() {
		return Dice.randomFromArray(FALLING_EFFECTS);
	}

	public static Act randomAct() {
		return Dice.randomFromArray(ACTS);
	}

	public static Rogue randomTeamMember(List<Rogue> list) {
		return Dice.randomFromList(list);
	}

	public static EnumSet<Consequence> randomMinorConsequenceSet() {
		int maximum = MINOR_CONSEQUENCE.length;
		EnumSet<Consequence> set = EnumSet.noneOf(Score.Consequence.class);
		set.add(randomMinorConsequence());
		while (Dice.roll(6) == 6 && set.size() < maximum) {
			set.add(randomMinorConsequence());
		}

		return set;
	}

	public static EnumSet<Consequence> randomMajorConsequenceSet() {
		int maximum = MAJOR_CONSEQUENCE.length;
		EnumSet<Consequence> set = EnumSet.noneOf(Score.Consequence.class);
		set.add(randomMajorConsequence());
		while (Dice.roll(6) == 6 && set.size() < maximum) {
			set.add(randomMajorConsequence());
		}

		return set;
	}

	public static EnumSet<Consequence> randomSevereConsequenceSet() {
		int maximum = SEVERE_CONSEQUENCE.length;
		EnumSet<Consequence> set = EnumSet.noneOf(Score.Consequence.class);
		set.add(randomSevereConsequence());
		while (Dice.roll(6) == 6 && set.size() < maximum) {
			set.add(randomSevereConsequence());
		}

		return set;
	}

	public static Consequence randomMinorConsequence() {
		return Dice.randomFromArray(MINOR_CONSEQUENCE);
	}

	public static Consequence randomMajorConsequence() {
		return Dice.randomFromArray(MAJOR_CONSEQUENCE);
	}

	public static Consequence randomSevereConsequence() {
		return Dice.randomFromArray(SEVERE_CONSEQUENCE);
	}

	public static void entanglement(int heat, int wantedLevel) {
		int[] entangle = Dice.fortune(wantedLevel);
		Entanglement encounter;
		if (heat < 4 && entangle[5] > 0) {
			// heat 0-3 && roll 6
			encounter = Entanglement.COOPERATION;

		} else if (heat < 4 && (entangle[3] > 0 || entangle[4] > 0)) {
			// heat 0-3 && roll 4-5
			encounter = (Dice.roll(2) == 1) ? Entanglement.RIVALS : Entanglement.UNQUIET_DEAD;

		} else if (heat < 4) {
			// heat 0-3 && roll 1-3
			encounter = (Dice.roll(2) == 1) ? Entanglement.GANG_TROUBLE : Entanglement.USUAL_SUSPECTS;

		} else if (heat < 6 && entangle[5] > 0) {
			// heat 4-5 && roll 6
			encounter = Entanglement.SHOW_OF_FORCE;

		} else if (heat < 6 && (entangle[3] > 0 || entangle[4] > 0)) {
			// heat 4-5 && roll 4-5
			encounter = (Dice.roll(2) == 1) ? Entanglement.REPRISALS : Entanglement.UNQUIET_DEAD;

		} else if (heat < 6) {
			// heat 4-5 && roll 1-3
			encounter = (Dice.roll(2) == 1) ? Entanglement.GANG_TROUBLE : Entanglement.QUESTIONING;

		} else if (entangle[5] > 0) {
			// heat 6+ && roll 6
			encounter = Entanglement.ARREST;

		} else if (entangle[3] > 0 || entangle[4] > 0) {
			// heat 6+ && roll 4-5
			encounter = (Dice.roll(2) == 1) ? Entanglement.DEMONIC_NOTICE : Entanglement.SHOW_OF_FORCE;

		} else {
			// heat 6+ && roll 1-3
			encounter = (Dice.roll(2) == 1) ? Entanglement.FLIPPED : Entanglement.INTERROGATION;

		}

		// TODO - testing
		System.out.println();
		System.out.println("Current heat: " + heat + " || Wanted Level: " + wantedLevel);
		System.out.println(encounter + " entanglement");

		switch (encounter) {
		case ARREST:
			System.out.println("Bluecoats arrive to arrest you.");
			break;
		case COOPERATION:
			System.out.println("A close ally asks you for a favor.");
			break;
		case DEMONIC_NOTICE:
			System.out.println("A demon approaches you with a dark offer.");
			break;
		case FLIPPED:
			System.out.println("A rival has flipped one of your assets.");
			break;
		case GANG_TROUBLE:
			System.out.println("One of your gangs has caused trouble.");
			break;
		case INTERROGATION:
			System.out.println("The Bluecoats question one of the PCs.");
			break;
		case QUESTIONING:
			System.out.println("The Bluecoats round up one of your cohorts.");
			break;
		case REPRISALS:
			System.out.println("An enemy moves against your crew.");
			break;
		case RIVALS:
			System.out.println("A rival throws their weight around.");
			break;
		case SHOW_OF_FORCE:
			System.out.println("An enemy faction makes a play against you.");
			break;
		case UNQUIET_DEAD:
			System.out.println("A rogue spirit haunts your crew.");
			break;
		case USUAL_SUSPECTS:
			System.out.println("The Bluecoats grab a friend of a PC");
			break;
		}

	}

	/*
	 * ACTION - INNER CLASS
	 * 
	 */
	private class Action {
		// fields
		Rogue rogue;
		int dice;
		Clock clock;
		Rogue.Rating approach;
		Position position;
		Effect effect;
		boolean pushed;
		EnumSet<Consequence> consequences;

		//
		Result result;

		// constructors
		public Action(Clock clock, Position position) {
			this(clock, pseudoRandomApproach(act, plan, crew, beats), position, effectsByAct(act));
		}

		public Action(Clock clock, Rogue.Rating approach, Position position, Effect effect) {
			/*
			 * FIXME - there is a bug which causes a crash here once a TPK has occurred
			 * 
			 */
			this.rogue = Rogue.bestRogueForAction(approach, team);
			Collections.rotate(team, 1);

			this.clock = clock;
			this.approach = approach;
			this.position = position;
			this.effect = effect;
			this.consequences = EnumSet.noneOf(Score.Consequence.class);

			this.dice = rogue.getRating(approach);
			int stress = rogue.getStress();

			int pushCheck = dice + stress + rogue.getThreshold();
			if (pushCheck < 15) {
				// TODO - testing
				System.out.println(rogue + " takes 2 stress. (rolled " + pushCheck + ")");

				++dice;
				rogue.setStress(stress + 2);
				pushed = true;
			} else {
				pushed = false;
			}

			resolve();
			// System.out.println(toStringDetailed());
		}

		// methods
		public void resolve() {
			int[] results = { 0, 0, 0, 0, 0, 0 };
			for (int i = 0; i < dice; ++i) {
				++results[Dice.roll(6) - 1];
			}

			// PART ONE
			if (results[5] > 1) {
				// A critical is the same regardless of position
				result = Result.CRITICAL;
				this.increaseEffect();
				tension -= 2;

			} else if (results[5] > 0) {
				// Success is the same regardless of position
				result = Result.SUCCESS;
				tension -= 1;

			} else if (position.equals(Position.CONTROLLED) && (results[3] > 0 || results[4] > 0)) {
				// partial success - CONTROLLED
				result = Result.PARTIAL;
				tension += 1;

				consequences = randomMinorConsequenceSet();
				if (Dice.roll(2) == 1) {
					consequences.add(Consequence.REDUCED_EFFECT);
				}

			} else if (position.equals(Position.RISKY) && (results[3] > 0 || results[4] > 0)) {
				// partial success - RISKY
				result = Result.PARTIAL;
				tension += 1;

				consequences = randomMajorConsequenceSet();
				if (Dice.roll(2) == 1)
					consequences.add(Consequence.REDUCED_EFFECT);
			} else if (position.equals(Position.DESPERATE) && (results[3] > 0 || results[4] > 0)) {
				// partial success - DESPERATE
				result = Result.PARTIAL;
				tension += 1;

				consequences = randomSevereConsequenceSet();
				if (Dice.roll(2) == 1)
					consequences.add(Consequence.REDUCED_EFFECT);
			} else if (position.equals(Position.CONTROLLED)) {
				// failure - CONTROLLED
				result = Result.FAILURE;
				tension += 2;

				consequences = randomMinorConsequenceSet();
				if (Dice.roll(6) > 1)
					effect = Effect.ZERO;
			} else if (position.equals(Position.RISKY)) {
				// failure - RISKY
				result = Result.FAILURE;
				tension += 2;

				consequences = randomMajorConsequenceSet();
				if (Dice.roll(6) > 1)
					effect = Effect.ZERO;
			} else if (position.equals(Position.DESPERATE)) {
				// failure - DESPERATE
				result = Result.FAILURE;
				tension += 2;

				consequences = randomSevereConsequenceSet();
				if (Dice.roll(6) > 1)
					effect = Effect.ZERO;
			}

			// REDUCED EFFECT
			if (consequences.contains(Consequence.REDUCED_EFFECT))
				decreaseEffect();

			// PART TWO
			if (effect.equals(Effect.EXTREME)) {
				clock.countDown(4);
			} else if (effect.equals(Effect.GREAT)) {
				clock.countDown(3);

			} else if (effect.equals(Effect.STANDARD)) {
				clock.countDown(2);

			} else if (effect.equals(Effect.LIMITED)) {
				clock.countDown(1);

			} else if (effect.equals(Effect.ZERO)) {
				// FIXME - FOR DEBUG ONLY: NO EFFECT

			}
			// System.out.println(" " + " " + " Remaining: " + clock.remaining());

			if (rogue.stressedOut()) {
				System.out.println(rogue + " left for dead.");
			}
		}

		public void increaseEffect() {
			if (effect.equals(Effect.GREAT))
				effect = Effect.EXTREME;
			else if (effect.equals(Effect.STANDARD))
				effect = Effect.GREAT;
			else if (effect.equals(Effect.LIMITED))
				effect = Effect.STANDARD;
			else if (effect.equals(Effect.ZERO))
				effect = Effect.LIMITED;
		}

		public void decreaseEffect() {
			if (effect.equals(Effect.EXTREME))
				effect = Effect.GREAT;
			else if (effect.equals(Effect.GREAT))
				effect = Effect.STANDARD;
			else if (effect.equals(Effect.STANDARD))
				effect = Effect.LIMITED;
			else if (effect.equals(Effect.LIMITED))
				effect = Effect.ZERO;
		}

		@Override
		public String toString() {
			return String.format("Scene %2d: %s check -%s", scene, approach, result);
		}

		public String toStringDetailed() {
			String string = String.format("Scene %2d", scene);

			// standard
			String checkResult = (result.equals(Result.FAILURE)) ? result + " with " + consequences.toString()
					: (result.equals(Result.SUCCESS)) ? result + " with " + effect + " effect"
							: result + " success with " + effect + " effect";

			string = String.format("Scene %2d: %s attempts %s %s check %n   (%d dice) achieves %s", scene,
					rogue.toString(), position, approach, dice, checkResult);

			return string;
		}
	}

	/*
	 * DOWNTIME - INNER CLASS
	 */
	private class Downtime {
		ArrayList<Crew> changes;
		int heat;

		Crew crew;
		public Downtime(Score score) {
			this.crew = score.crew;
			ArrayList<Rogue> team = score.team;

			//
			Crew client = score.client;
			Crew target = score.target;
			Goal goal = score.goal;

			/*
			 * TODO - this ArrayList is to keep track of EACH faction status that changes;
			 * eventually when I rewrite the system, there will be no such thing as a
			 * "zero reputation," because "everyone knows everybody." All that changes is
			 * which SIDE of the friend/enemy spectrum characters fall on at present
			 */
			changes = new ArrayList<Crew>();

			// starting heat
			this.heat = Dice.roll(4) + Dice.roll(target.getTier() / 2) - 2;

			if (primaryObjective.expired() && goal.equals(Goal.ASSIST)) {
				String hold = (client.holdStrong()) ? "strong" : "weak";
				String report = String.format("%s (tier %d, hold %s) is stronger.", client, client.getTier(), hold);
				System.out.println(report);
				client.strengthenHold();

				hold = (client.holdStrong()) ? "strong" : "weak";
				System.out.println("New tier/hold: " + client.getTier() + " / " + hold);
				System.out.println();
			}

			if (primaryObjective.expired() && goal.equals(Goal.CLAIM)) {
				String report = String.format("Seized %s from %s", claim, target);
				System.out.println(report);
				crew.getClaims().put(claim, target);

				changes.add(target);
				crew.decreaseShip(target);
				System.out.println(target + " status decreased");
				System.out.println();
			} else if (goal.equals(Goal.CLAIM)) {
				target.getClaims().put(claim, target);
			}

			if (primaryObjective.expired() && goal.equals(Goal.SHAKE)) {
				String hold = (target.holdStrong()) ? "strong" : "weak";
				String report = String.format("%s (tier %d, hold %s) is weaker.", target, target.getTier(), hold);
				System.out.println(report);
				target.weakenHold();

				hold = (target.holdStrong()) ? "strong" : "weak";
				System.out.println("New tier/hold: " + target.getTier() + " / " + hold);
				System.out.println();
			}

			// resolve client status
			if (patronage() && primaryObjective.expired()) {
				changes.add(client);
				crew.increaseShip(client);
				// TODO - testing
				System.out.println("Client (" + client.toString() + ") satisfied");
			} else if (patronage() && window.expired()) {
				changes.add(client);
				crew.decreaseShip(client);
				// TODO - testing
				System.out.println("Client (" + client.toString() + ") disappointed");
			}

			if (patronage() && window.expired()) {
				// TODO - testing (appears to work)
				boolean incite = Ship.shipSet().add(new Ship(client, target, true));
				if (incite) {
					System.out.println(target + " declared war on " + client);
				}
			}

			// additional reputation changes
			Set<Crew> enemies = target.npcEnemyGet();
			Crew targetEnemy;
			for (Iterator<Crew> it = enemies.iterator(); it.hasNext();) {
				// FIXME - testing
				targetEnemy = it.next();

				if (targetEnemy.notSameAs(client) && Dice.roll(3) == 1) {
					// enemies of the target like you
					changes.add(targetEnemy);
					crew.increaseShip(targetEnemy);
					System.out.println(targetEnemy + " status increased");
				}
			}

			Set<Crew> allies = target.npcAllyGet();
			Crew targetAlly;
			for (Iterator<Crew> it = allies.iterator(); it.hasNext();) {
				// FIXME - testing
				targetAlly = it.next();

				if (targetAlly.notSameAs(client) && Dice.roll(3) == 1) {
					// allies of the target don't like you
					changes.add(targetAlly);
					crew.decreaseShip(targetAlly);
					System.out.println(targetAlly + " status decreased");
				}
			}

			// rep boost
			int crewTier = crew.getTier();
			int targetTier = target.getTier();
			int exp = (targetTier - crewTier + 2 < 1) ? 0 : targetTier - crewTier + 2;
			if (exp > 0) {
				changes.add(target);
				crew.decreaseShip(target);
				System.out.println(target + " status decreased");

				//
				crew.addEXP(exp);
				System.out.println("Rep gained: " + exp);
			}

			// payoff
			if (score.primaryObjective.expired()) {
				int personalCoin, personalStash, bonus;
				int crewCoin = crew.getCoin(), teamSize = team.size();
				int payoff = (patronage()) ? Dice.roll(2, 4) + 2 : Dice.roll(2, 6);

				// TODO - testing
				System.out.println();
				System.out.println("Coin gained: " + payoff);

				// determine individual pay (or none)
				if (payoff >= teamSize * 3)
					bonus = 3;
				else if (payoff >= teamSize * 2)
					bonus = 2;
				else if (payoff >= teamSize)
					bonus = 1;
				else
					bonus = 0;

				// distribute pay among team members
				if (bonus > 0) {
					Rogue rogue;
					for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
						rogue = it.next();

						personalCoin = rogue.getCoin();
						personalStash = rogue.getStash();
						if (personalCoin + bonus <= 4) {
							rogue.setCoin(personalCoin + bonus);
							payoff -= bonus;

						} else {
							rogue.setStash(personalStash + bonus);
							payoff -= bonus;

						}

						System.out.println(rogue + " received " + bonus);
					}
					// any remainder goes to the crew
					crew.addCoin(payoff);
					System.out.println(payoff + " went to the crew.");

				} else if (crewCoin + payoff >= teamSize) {
					int difference = teamSize - payoff;
					crew.setCoin(crewCoin - difference);
					bonus = 1;

					Rogue rogue;
					for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
						rogue = it.next();

						personalCoin = rogue.getCoin();
						personalStash = rogue.getStash();
						if (personalCoin + bonus <= 4) {
							rogue.setCoin(personalCoin + bonus);
							payoff -= bonus;

						} else {
							rogue.setStash(personalStash + bonus);
							payoff -= bonus;

						}

						// System.out.println(rogue + " received " + bonus);
					}
				} else {
					// not enough coin to distribute; all of it goes to the crew
					crew.addCoin(payoff);

				}
			}

			// post-payoff score resolution
			if (team.size() > 0) {
				Rogue rogue;
				for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
					rogue = it.next();
					rogue.resolveStress();
				}
			}

			// assign heat
			System.out.println("Received " + heat + " heat from score.");
			crew.setHeat(crew.getHeat() + heat);
			crew.resolveHeat();

			// entanglements
			int heat = crew.getHeat();
			int wantedLevel = crew.getWantedLevel();
			entanglement(heat, wantedLevel);

		}
		
	}
}
