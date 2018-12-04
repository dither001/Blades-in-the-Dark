package com.bladesinthedark.actor;

import java.util.EnumSet;

import com.bladesinthedark.crew.CrewSpecial;
import com.bladesinthedark.crew.Faction;
import com.bladesinthedark.rules.Dice;

public enum RogueSpecial {
	VETERAN_1, VETERAN_2, VETERAN_3, //
	BATTLEBORN, BODYGUARD, GHOST_FIGHTER, LEADER, MULE, NOT_TO_BE_TRIFLED_WITH, SAVAGE, VIGOROUS, //
	SHARPSHOOTER, FOCUSED, GHOST_HUNTER_1, GHOST_HUNTER_2, SCOUT, SURVIVOR, TOUGH_AS_NAILS, VENGEFUL, //
	ALCHEMIST, ANALYST, ARTIFICER, FORTITUDE, GHOST_WARD, PHYSICKER, SABOTEUR, VENOMOUS, //
	INFILTRATOR, AMBUSH, DAREDEVIL, THE_DEVILS_FOOTSTEPS, EXPERTISE, GHOST_VEIL, REFLEXES, SHADOW, //
	ROOKS_GAMBIT, CLOAK_DAGGER, GHOST_VOICE, LIKE_LOOKING_INTO_A_MIRROR, SOMETHING_ON_THE_SIDE, MESMERISM, SUBTERFUGE, TRUST_IN_ME, //
	FORESIGHT, CALCULATING, CONNECTED, FUNCTIONING_VICE, GHOST_CONTRACT, JAIL_BIRD, MASTERMIND, WEAVING_THE_WEB, //
	COMPEL, GHOST_MIND, IRON_WILL, OCCULTIST, RITUAL, STRANGE_METHODS, TEMPEST, WARDED;

	/*
	 * STATIC FIELDS
	 */
	public static final RogueSpecial[] CUTTER_SPECIAL = { BATTLEBORN, BODYGUARD, GHOST_FIGHTER, LEADER, MULE,
			NOT_TO_BE_TRIFLED_WITH, SAVAGE, VIGOROUS };
	public static final RogueSpecial[] HOUND_SPECIAL = { SHARPSHOOTER, FOCUSED, GHOST_HUNTER_1, GHOST_HUNTER_2, SCOUT,
			SURVIVOR, TOUGH_AS_NAILS, VENGEFUL };
	public static final RogueSpecial[] LEECH_SPECIAL = { ALCHEMIST, ANALYST, ARTIFICER, FORTITUDE, GHOST_WARD,
			PHYSICKER, SABOTEUR, VENOMOUS };
	public static final RogueSpecial[] LURK_SPECIAL = { INFILTRATOR, AMBUSH, DAREDEVIL, THE_DEVILS_FOOTSTEPS, EXPERTISE,
			GHOST_VEIL, REFLEXES, SHADOW };
	public static final RogueSpecial[] SLIDE_SPECIAL = { ROOKS_GAMBIT, CLOAK_DAGGER, GHOST_VOICE,
			LIKE_LOOKING_INTO_A_MIRROR, SOMETHING_ON_THE_SIDE, MESMERISM, SUBTERFUGE, TRUST_IN_ME };
	public static final RogueSpecial[] SPIDER_SPECIAL = { FORESIGHT, CALCULATING, CONNECTED, FUNCTIONING_VICE,
			GHOST_CONTRACT, JAIL_BIRD, MASTERMIND, WEAVING_THE_WEB };
	public static final RogueSpecial[] WHISPER_SPECIAL = { COMPEL, GHOST_MIND, IRON_WILL, OCCULTIST, RITUAL,
			STRANGE_METHODS, TEMPEST, WARDED };

	/*
	 * STATIC METHODS
	 */
	public static RogueSpecial randomSpecial(RoguePlaybook playbook) {
		RogueSpecial special = null;
		if (playbook.equals(RoguePlaybook.CUTTER))
			special = RogueSpecial.randomCutterSpecial();
		else if (playbook.equals(RoguePlaybook.HOUND))
			special = RogueSpecial.randomHoundSpecial();
		else if (playbook.equals(RoguePlaybook.LEECH))
			special = RogueSpecial.randomLeechSpecial();
		else if (playbook.equals(RoguePlaybook.LURK))
			special = RogueSpecial.randomLurkSpecial();
		else if (playbook.equals(RoguePlaybook.SLIDE))
			special = RogueSpecial.randomSlideSpecial();
		else if (playbook.equals(RoguePlaybook.SPIDER))
			special = RogueSpecial.randomSpiderSpecial();
		else if (playbook.equals(RoguePlaybook.WHISPER))
			special = RogueSpecial.randomWhisperSpecial();

		return special;
	}

	public static RogueSpecial randomCutterSpecial() {
		return Dice.randomFromArray(CUTTER_SPECIAL);
	}

	public static RogueSpecial randomHoundSpecial() {
		return Dice.randomFromArray(HOUND_SPECIAL);
	}

	public static RogueSpecial randomLeechSpecial() {
		return Dice.randomFromArray(LEECH_SPECIAL);
	}

	public static RogueSpecial randomLurkSpecial() {
		return Dice.randomFromArray(LURK_SPECIAL);
	}

	public static RogueSpecial randomSlideSpecial() {
		return Dice.randomFromArray(SLIDE_SPECIAL);
	}

	public static RogueSpecial randomSpiderSpecial() {
		return Dice.randomFromArray(SPIDER_SPECIAL);
	}

	public static RogueSpecial randomWhisperSpecial() {
		return Dice.randomFromArray(WHISPER_SPECIAL);
	}

	static void crewSpecialSkills(Faction crew, Rogue rogue) {
		EnumSet<CrewSpecial> crewSpecials = crew.skillSpecials();

		Rating rating;
		if (crewSpecials.contains(CrewSpecial.DEADLY)) {
			rating = Rating.chooseDeadlySkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = Rating.chooseDeadlySkill();
			}
		}

		if (crewSpecials.contains(CrewSpecial.DANGEROUS)) {
			rating = Rating.chooseDangerousSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = Rating.chooseDangerousSkill();
			}
		}

		if (crewSpecials.contains(CrewSpecial.CHOSEN)) {
			rating = Rating.chooseChosenSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = Rating.chooseChosenSkill();
			}
		}

		if (crewSpecials.contains(CrewSpecial.SILVER_TONGUES)) {
			rating = Rating.chooseSilverTongueSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = Rating.chooseSilverTongueSkill();
			}
		}

		if (crewSpecials.contains(CrewSpecial.EVERYONE_STEALS)) {
			rating = Rating.chooseEveryoneStealsSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = Rating.chooseEveryoneStealsSkill();
			}
		}

		if (crewSpecials.contains(CrewSpecial.RENEGADES)) {
			rating = Rating.chooseRenegadeSkill();
			while (rogue.improveAttribute(rating) != true) {
				rating = Rating.chooseRenegadeSkill();
			}
		}

	}
}