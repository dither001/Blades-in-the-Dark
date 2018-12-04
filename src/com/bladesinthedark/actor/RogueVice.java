package com.bladesinthedark.actor;

import com.bladesinthedark.rules.*;

public enum RogueVice {
	FAITH, GAMBLING, LUXURY, OBLIGATION, PLEASURE, STUPOR, WEIRD;

	/*
	 * STATIC FIELDS
	 */
	public static final RogueVice[] VICES = { FAITH, GAMBLING, LUXURY, OBLIGATION, PLEASURE, STUPOR, WEIRD };

	/*
	 * STATIC METHODS
	 */
	public static RogueVice randomVice() {
		return Dice.randomFromArray(VICES);
	}
}