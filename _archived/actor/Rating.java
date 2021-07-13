package com.bladesinthedark.actor;

import com.bladesinthedark.crew.*;
import com.bladesinthedark.rules.*;

import model.Plan.Approach;
import model.Score;
import model.Score.Act;

public enum Rating {
	ATTUNE, COMMAND, CONSORT, FINESSE, HUNT, PROWL, SKIRMISH, STUDY, SURVEY, SWAY, TINKER, WRECK;

	/*
	 * STATIC FIELDS
	 */
	public static final Rating[] RATINGS = { ATTUNE, COMMAND, CONSORT, FINESSE, HUNT, PROWL, SKIRMISH, STUDY, SURVEY,
			SWAY, TINKER, WRECK };
	public static final Rating[] INSIGHT = { HUNT, STUDY, SURVEY, TINKER };
	public static final Rating[] PROWESS = { FINESSE, PROWL, SKIRMISH, WRECK };
	public static final Rating[] RESOLVE = { ATTUNE, COMMAND, CONSORT, SWAY };

	// approaches
	public static final Rating[] APPROACHES = { ATTUNE, COMMAND, CONSORT, FINESSE, HUNT, PROWL, SKIRMISH, STUDY, SURVEY,
			SWAY, TINKER, WRECK };

	// approaches by crew type
	public static final Rating[] ASSASSIN_APPROACH = { FINESSE, HUNT, PROWL, SKIRMISH, STUDY, TINKER };
	public static final Rating[] BRAVO_APPROACH = { COMMAND, CONSORT, FINESSE, SKIRMISH, SURVEY, WRECK };
	public static final Rating[] CULT_APPROACH = { ATTUNE, COMMAND, CONSORT, PROWL, STUDY, TINKER };
	public static final Rating[] HAWKER_APPROACH = { COMMAND, CONSORT, FINESSE, SURVEY, SWAY, WRECK };
	public static final Rating[] SHADOW_APPROACH = { COMMAND, FINESSE, PROWL, SURVEY, TINKER, WRECK };
	public static final Rating[] SMUGGLER_APPROACH = { CONSORT, FINESSE, PROWL, SURVEY, SWAY, TINKER };

	// approaches by plan
	public static final Rating[] ASSAULT_APPROACHES = { COMMAND, HUNT, SKIRMISH, SURVEY, WRECK };
	public static final Rating[] DECEPTION_APPROACHES = { CONSORT, FINESSE, PROWL, STUDY, SURVEY, SWAY };
	public static final Rating[] OCCULT_APPROACHES = { ATTUNE, COMMAND, CONSORT, STUDY, SWAY, TINKER };
	public static final Rating[] SOCIAL_APPROACHES = { COMMAND, CONSORT, FINESSE, STUDY, SURVEY, SWAY };
	public static final Rating[] STEALTH_APPROACHES = { FINESSE, HUNT, PROWL, STUDY, TINKER, WRECK };
	public static final Rating[] TRANSPORT_APPROACHES = { COMMAND, CONSORT, FINESSE, HUNT, PROWL, SURVEY, SWAY };
	// approaches by tension level
	static final Rating[] HIGH_TENSION_APPROACH = { COMMAND, HUNT, PROWL, SKIRMISH, SWAY, WRECK };
	static final Rating[] LOW_TENSION_APPROACH = { ATTUNE, CONSORT, FINESSE, STUDY, SURVEY, TINKER };

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

	static Rating chooseDeadlySkill() {
		Rating[] array = new Rating[] { HUNT, PROWL, SKIRMISH };
		return Dice.randomFromArray(array);
	}

	static Rating chooseDangerousSkill() {
		Rating[] array = new Rating[] { HUNT, SKIRMISH, WRECK };
		return Dice.randomFromArray(array);
	}

	static Rating chooseChosenSkill() {
		Rating[] array = new Rating[] { ATTUNE, STUDY, SWAY };
		return Dice.randomFromArray(array);
	}

	static Rating chooseSilverTongueSkill() {
		Rating[] array = new Rating[] { COMMAND, CONSORT, SWAY };
		return Dice.randomFromArray(array);
	}

	static Rating chooseEveryoneStealsSkill() {
		Rating[] array = new Rating[] { FINESSE, PROWL, TINKER };
		return Dice.randomFromArray(array);
	}

	static Rating chooseRenegadeSkill() {
		Rating[] array = new Rating[] { FINESSE, PROWL, SKIRMISH };
		return Dice.randomFromArray(array);
	}

	public static Rating[][] randomBeats() {
		Rating[][] beats = new Rating[4][];

		for (int i = 0; i < beats.length; ++i) {
			beats[i] = (Dice.roll(2) == 1) ? HIGH_TENSION_APPROACH : LOW_TENSION_APPROACH;
		}

		return beats;
	}

	public static Rating approachByCrewType(CrewType crew) {
		Rating[] array = APPROACHES;
		Rating choice = Rating.randomApproach();

		if (crew.equals(CrewType.ASSASSINS))
			array = ASSASSIN_APPROACH;
		else if (crew.equals(CrewType.BRAVOS))
			array = BRAVO_APPROACH;
		else if (crew.equals(CrewType.CULT))
			array = CULT_APPROACH;
		else if (crew.equals(CrewType.HAWKERS))
			array = HAWKER_APPROACH;
		else if (crew.equals(CrewType.SHADOWS))
			array = SHADOW_APPROACH;
		else if (crew.equals(CrewType.SMUGGLERS))
			array = SMUGGLER_APPROACH;

		choice = array[Dice.roll(array.length) - 1];
		return choice;
	}

	public static Rating approachByPlan(Score.Approach plan) {
		Rating choice = Rating.randomApproach();

		if (plan.equals(Score.Approach.ASSAULT))
			choice = Rating.assaultApproach();
		else if (plan.equals(Score.Approach.DECEPTION))
			choice = Rating.deceptionApproach();
		else if (plan.equals(Score.Approach.OCCULT))
			choice = Rating.occultApproach();
		else if (plan.equals(Score.Approach.SOCIAL))
			choice = Rating.socialApproach();
		else if (plan.equals(Score.Approach.STEALTH))
			choice = Rating.stealthApproach();
		else if (plan.equals(Score.Approach.TRANSPORT))
			choice = Rating.transportApproach();

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
}