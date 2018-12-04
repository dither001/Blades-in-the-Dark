package com.bladesinthedark.crew;

import com.bladesinthedark.rules.Dice;

public enum CrewReputation {
	AMBITIOUS, BRUTAL, DARING, HONORABLE, PROFESSIONAL, SAVVY, SUBTLE, STRANGE;

	public static final CrewReputation[] ALL_REPS = { AMBITIOUS, BRUTAL, DARING, HONORABLE, PROFESSIONAL, SAVVY, SUBTLE,
			STRANGE };

	public static CrewReputation randomReputation() {
		return Dice.randomFromArray(ALL_REPS);
	}
}