
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

public class Action {
	public enum Consequence {
		MINOR_COMPLICATION, MAJOR_COMPLICATION, SEVERE_COMPLICATION, HARM_1, HARM_2, HARM_3, REDUCED_EFFECT, ESCALATE_RISKY, ESCALATE_DESPERATE, WITHDRAW, LOST_OPPORTUNITY
	}

	public enum Position {
		CONTROLLED, RISKY, DESPERATE
	}

	public enum Effect {
		EXTREME, GREAT, STANDARD, LIMITED, ZERO
	}

	public enum Result {
		CRITICAL, SUCCESS, PARTIAL, FAILURE
	}

	/*
	 * STATIC FIELDS
	 * 
	 */
	private static final Position[] POSITIONS = { Position.CONTROLLED, Position.RISKY, Position.DESPERATE };
	private static final Effect[] EFFECTS = { Effect.EXTREME, Effect.GREAT, Effect.STANDARD, Effect.LIMITED,
			Effect.ZERO };

	private static final Effect[] INCITING_EFFECTS = { Effect.STANDARD, Effect.LIMITED };
	private static final Effect[] RISING_EFFECTS = { Effect.STANDARD, Effect.LIMITED };
	private static final Effect[] TURNING_EFFECTS = { Effect.GREAT, Effect.STANDARD, Effect.LIMITED };
	private static final Effect[] FALLING_EFFECTS = { Effect.GREAT, Effect.STANDARD };

	// consequences
	private static final Consequence[] MINOR_CONSEQUENCE = { Consequence.HARM_1, Consequence.MINOR_COMPLICATION,
			Consequence.ESCALATE_RISKY };
	private static final Consequence[] MAJOR_CONSEQUENCE = { Consequence.HARM_2, Consequence.MAJOR_COMPLICATION,
			Consequence.ESCALATE_DESPERATE, Consequence.LOST_OPPORTUNITY };
	private static final Consequence[] SEVERE_CONSEQUENCE = { Consequence.HARM_3, Consequence.SEVERE_COMPLICATION,
			Consequence.LOST_OPPORTUNITY };

	/*
	 * INSTANCE FIELDS
	 * 
	 */
	private Score score;
	private int tension;
	private List<Rogue> team;

	private Rogue rogue;
	private int dice;
	private Clock clock;
	private Actor.Rating approach;
	private Position position;
	private Effect effect;
	private boolean pushed;
	private EnumSet<Consequence> consequences;

	//
	private Result result;

	// constructors
	public Action(Score score, Clock clock, Position position) {
		this(score, clock,
				Rogue.pseudoRandomApproach(score.getAct(), score.getPlan(), score.getCrew(), score.getBeats()),
				position, randomEffect());
	}

	public Action(Score score, Clock clock, Actor.Rating approach, Position position, Effect effect) {
		this.score = score;
		this.tension = score.getTension();
		this.team = score.getTeam();

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
		this.consequences = EnumSet.noneOf(Consequence.class);

		this.dice = rogue.getRating(approach);
		int stress = rogue.getStress();

		int pushCheck = dice + stress + rogue.getThreshold();
		if (pushCheck < 15) {
			// TODO - testing
//			System.out.println(rogue + " takes 2 stress. (rolled " + pushCheck + ")");

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

//		if (rogue.stressedOut()) {
//			System.out.println(rogue + " left for dead.");
//		}
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
		return String.format("%s check -%s", approach, result);
	}

	public String toStringDetailed() {
		String string = String.format("");

		// standard
		String checkResult = (result.equals(Result.FAILURE)) ? result + " with " + consequences.toString()
				: (result.equals(Result.SUCCESS)) ? result + " with " + effect + " effect"
						: result + " success with " + effect + " effect";

		string = String.format("%s attempts %s %s check %n   (%d dice) achieves %s", rogue.toString(), position,
				approach, dice, checkResult);

		return string;
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Position randomPosition() {
		return Dice.randomFromArray(POSITIONS);
	}

	public static Effect effectsByAct(Score.Act act) {
		Effect choice = randomEffect();

		if (act.equals(Score.Act.INCITING))
			choice = incitingEffect();
		else if (act.equals(Score.Act.RISING))
			choice = risingEffect();
		else if (act.equals(Score.Act.TURNING))
			choice = turningEffect();
		else if (act.equals(Score.Act.FALLING))
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

	public static EnumSet<Consequence> randomMinorConsequenceSet() {
		int maximum = MINOR_CONSEQUENCE.length;
		EnumSet<Consequence> set = EnumSet.noneOf(Consequence.class);
		set.add(randomMinorConsequence());
		while (Dice.roll(6) == 6 && set.size() < maximum) {
			set.add(randomMinorConsequence());
		}

		return set;
	}

	public static EnumSet<Consequence> randomMajorConsequenceSet() {
		int maximum = MAJOR_CONSEQUENCE.length;
		EnumSet<Consequence> set = EnumSet.noneOf(Consequence.class);
		set.add(randomMajorConsequence());
		while (Dice.roll(6) == 6 && set.size() < maximum) {
			set.add(randomMajorConsequence());
		}

		return set;
	}

	public static EnumSet<Consequence> randomSevereConsequenceSet() {
		int maximum = SEVERE_CONSEQUENCE.length;
		EnumSet<Consequence> set = EnumSet.noneOf(Consequence.class);
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

}
