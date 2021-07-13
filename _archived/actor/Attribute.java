package com.bladesinthedark.actor;

import com.bladesinthedark.rules.Dice;

public enum Attribute {
	INSIGHT, PROWESS, RESOLVE;

	public static Attribute randomAttribute() {
		Attribute[] array = new Attribute[] { INSIGHT, PROWESS, RESOLVE };
		return Dice.randomFromArray(array);
	}
}