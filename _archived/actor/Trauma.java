package com.bladesinthedark.actor;

import com.bladesinthedark.rules.Dice;

public enum Trauma {
	COLD, HAUNTED, OBSESSED, PARANOID, RECKLESS, SOFT, UNSTABLE, VICIOUS;

	public static final Trauma[] TRAUMA_CONDITIONS = { COLD, HAUNTED, OBSESSED, PARANOID, RECKLESS, SOFT, UNSTABLE,
			VICIOUS };

	public static Trauma randomTrauma() {
		return Dice.randomFromArray(TRAUMA_CONDITIONS);
	}
}