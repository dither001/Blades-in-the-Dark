
public interface Actor {

	enum Playbook {
		CUTTER, HOUND, LEECH, LURK, SLIDE, SPIDER, WHISPER
	}

	enum Attribute {
		INSIGHT, PROWESS, RESOLVE
	}

	enum Rating {
		ATTUNE, COMMAND, CONSORT, FINESSE, HUNT, PROWL, SKIRMISH, STUDY, SURVEY, SWAY, TINKER, WRECK
	}

	enum Vice {
		FAITH, GAMBLING, LUXURY, OBLIGATION, PLEASURE, STUPOR, WEIRD
	}

	enum Special {
		VETERAN_1, VETERAN_2, VETERAN_3, //
		BATTLEBORN, BODYGUARD, GHOST_FIGHTER, LEADER, MULE, NOT_TO_BE_TRIFLED_WITH, SAVAGE, VIGOROUS, //
		SHARPSHOOTER, FOCUSED, GHOST_HUNTER_1, GHOST_HUNTER_2, SCOUT, SURVIVOR, TOUGH_AS_NAILS, VENGEFUL, //
		ALCHEMIST, ANALYST, ARTIFICER, FORTITUDE, GHOST_WARD, PHYSICKER, SABOTEUR, VENOMOUS, //
		INFILTRATOR, AMBUSH, DAREDEVIL, THE_DEVILS_FOOTSTEPS, EXPERTISE, GHOST_VEIL, REFLEXES, SHADOW, //
		ROOKS_GAMBIT, CLOAK_DAGGER, GHOST_VOICE, LIKE_LOOKING_INTO_A_MIRROR, SOMETHING_ON_THE_SIDE, MESMERISM, SUBTERFUGE, TRUST_IN_ME, //
		FORESIGHT, CALCULATING, CONNECTED, FUNCTIONING_VICE, GHOST_CONTRACT, JAIL_BIRD, MASTERMIND, WEAVING_THE_WEB, //
		COMPEL, GHOST_MIND, IRON_WILL, OCCULTIST, RITUAL, STRANGE_METHODS, TEMPEST, WARDED
	}

	enum Trauma {
		COLD, HAUNTED, OBSESSED, PARANOID, RECKLESS, SOFT, UNSTABLE, VICIOUS
	}

	//
	public static final Playbook[] PLAYBOOKS = { Playbook.CUTTER, Playbook.HOUND, Playbook.LEECH, Playbook.LURK,
			Playbook.SLIDE, Playbook.SPIDER, Playbook.WHISPER };

	public static final Rating[] RATINGS = { Rating.ATTUNE, Rating.COMMAND, Rating.CONSORT, Rating.FINESSE, Rating.HUNT,
			Rating.PROWL, Rating.SKIRMISH, Rating.STUDY, Rating.SURVEY, Rating.SWAY, Rating.TINKER, Rating.WRECK };
	public static final Rating[] INSIGHT = { Rating.HUNT, Rating.STUDY, Rating.SURVEY, Rating.TINKER };
	public static final Rating[] PROWESS = { Rating.FINESSE, Rating.PROWL, Rating.SKIRMISH, Rating.WRECK };
	public static final Rating[] RESOLVE = { Rating.ATTUNE, Rating.COMMAND, Rating.CONSORT, Rating.SWAY };

	public static final Vice[] VICES = { Vice.FAITH, Vice.GAMBLING, Vice.LUXURY, Vice.OBLIGATION, Vice.PLEASURE,
			Vice.STUPOR, Vice.WEIRD };

	public static final Special[] CUTTER_SPECIAL = { Special.BATTLEBORN, Special.BODYGUARD, Special.GHOST_FIGHTER,
			Special.LEADER, Special.MULE, Special.NOT_TO_BE_TRIFLED_WITH, Special.SAVAGE, Special.VIGOROUS };
	public static final Special[] HOUND_SPECIAL = { Special.SHARPSHOOTER, Special.FOCUSED, Special.GHOST_HUNTER_1,
			Special.GHOST_HUNTER_2, Special.SCOUT, Special.SURVIVOR, Special.TOUGH_AS_NAILS, Special.VENGEFUL };
	public static final Special[] LEECH_SPECIAL = { Special.ALCHEMIST, Special.ANALYST, Special.ARTIFICER,
			Special.FORTITUDE, Special.GHOST_WARD, Special.PHYSICKER, Special.SABOTEUR, Special.VENOMOUS };
	public static final Special[] LURK_SPECIAL = { Special.INFILTRATOR, Special.AMBUSH, Special.DAREDEVIL,
			Special.THE_DEVILS_FOOTSTEPS, Special.EXPERTISE, Special.GHOST_VEIL, Special.REFLEXES, Special.SHADOW };
	public static final Special[] SLIDE_SPECIAL = { Special.ROOKS_GAMBIT, Special.CLOAK_DAGGER, Special.GHOST_VOICE,
			Special.LIKE_LOOKING_INTO_A_MIRROR, Special.SOMETHING_ON_THE_SIDE, Special.MESMERISM, Special.SUBTERFUGE,
			Special.TRUST_IN_ME };
	public static final Special[] SPIDER_SPECIAL = { Special.FORESIGHT, Special.CALCULATING, Special.CONNECTED,
			Special.FUNCTIONING_VICE, Special.GHOST_CONTRACT, Special.JAIL_BIRD, Special.MASTERMIND,
			Special.WEAVING_THE_WEB };
	public static final Special[] WHISPER_SPECIAL = { Special.COMPEL, Special.GHOST_MIND, Special.IRON_WILL,
			Special.OCCULTIST, Special.RITUAL, Special.STRANGE_METHODS, Special.TEMPEST, Special.WARDED };

	public static final Trauma[] TRAUMA_CONDITIONS = { Trauma.COLD, Trauma.HAUNTED, Trauma.OBSESSED, Trauma.PARANOID,
			Trauma.RECKLESS, Trauma.SOFT, Trauma.UNSTABLE, Trauma.VICIOUS };

	// approaches
	public static final Rating[] APPROACHES = { Rating.ATTUNE, Rating.COMMAND, Rating.CONSORT, Rating.FINESSE,
			Rating.HUNT, Rating.PROWL, Rating.SKIRMISH, Rating.STUDY, Rating.SURVEY, Rating.SWAY, Rating.TINKER,
			Rating.WRECK };

	// approaches by crew type
	public static final Rating[] ASSASSIN_APPROACH = { Rating.FINESSE, Rating.HUNT, Rating.PROWL, Rating.SKIRMISH,
			Rating.STUDY, Rating.TINKER };
	public static final Rating[] BRAVO_APPROACH = { Rating.COMMAND, Rating.CONSORT, Rating.FINESSE, Rating.SKIRMISH,
			Rating.SURVEY, Rating.WRECK };
	public static final Rating[] CULT_APPROACH = { Rating.ATTUNE, Rating.COMMAND, Rating.CONSORT, Rating.PROWL,
			Rating.STUDY, Rating.TINKER };
	public static final Rating[] HAWKER_APPROACH = { Rating.COMMAND, Rating.CONSORT, Rating.FINESSE, Rating.SURVEY,
			Rating.SWAY, Rating.WRECK };
	public static final Rating[] SHADOW_APPROACH = { Rating.COMMAND, Rating.FINESSE, Rating.PROWL, Rating.SURVEY,
			Rating.TINKER, Rating.WRECK };
	public static final Rating[] SMUGGLER_APPROACH = { Rating.CONSORT, Rating.FINESSE, Rating.PROWL, Rating.SURVEY,
			Rating.SWAY, Rating.TINKER };

	// approaches by plan
	public static final Rating[] ASSAULT_APPROACHES = { Rating.COMMAND, Rating.HUNT, Rating.SKIRMISH, Rating.SURVEY,
			Rating.WRECK };
	public static final Rating[] DECEPTION_APPROACHES = { Rating.CONSORT, Rating.FINESSE, Rating.PROWL, Rating.STUDY,
			Rating.SURVEY, Rating.SWAY };
	public static final Rating[] OCCULT_APPROACHES = { Rating.ATTUNE, Rating.COMMAND, Rating.CONSORT, Rating.STUDY,
			Rating.SWAY, Rating.TINKER };
	public static final Rating[] SOCIAL_APPROACHES = { Rating.COMMAND, Rating.CONSORT, Rating.FINESSE, Rating.STUDY,
			Rating.SURVEY, Rating.SWAY };
	public static final Rating[] STEALTH_APPROACHES = { Rating.FINESSE, Rating.HUNT, Rating.PROWL, Rating.STUDY,
			Rating.TINKER, Rating.WRECK };
	public static final Rating[] TRANSPORT_APPROACHES = { Rating.COMMAND, Rating.CONSORT, Rating.FINESSE, Rating.HUNT,
			Rating.PROWL, Rating.SURVEY, Rating.SWAY };

}
