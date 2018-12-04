package com.bladesinthedark.actor;

import com.bladesinthedark.rules.Dice;

public enum RoguePlaybook {
	CUTTER, HOUND, LEECH, LURK, SLIDE, SPIDER, WHISPER;

	public static final RoguePlaybook[] PLAYBOOKS = { CUTTER, HOUND, LEECH, LURK, SLIDE, SPIDER, WHISPER };

	public static RoguePlaybook randomPlaybook() {
		return Dice.randomFromArray(PLAYBOOKS);
	}
}