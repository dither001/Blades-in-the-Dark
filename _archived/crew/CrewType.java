package com.bladesinthedark.crew;

import com.bladesinthedark.rules.*;

/*
 * ENUMS
 */
public enum CrewType {
	ASSASSINS, BRAVOS, CULT, HAWKERS, SHADOWS, SMUGGLERS;

	public static final CrewType[] ALL_TYPES = { ASSASSINS, BRAVOS, CULT, HAWKERS, SHADOWS, SMUGGLERS };

	public static CrewType randomCrewType() {
		return Dice.randomFromArray(ALL_TYPES);
	}
}