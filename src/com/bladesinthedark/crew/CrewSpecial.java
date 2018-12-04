package com.bladesinthedark.crew;

public enum CrewSpecial {
	PATRON, VETERAN_1, VETERAN_2, VETERAN_3, DEADLY, CROWS_VEIL, EMBERDEATH, NO_TRACES, PREDATORS, VIPERS, DANGEROUS, BLOOD_BROTHERS, DOOR_KICKERS, FIENDS, FORGED_IN_THE_FIRE, WAR_DOGS, CHOSEN, ANNOINTED, BOUND_IN_DARKNESS, CONVICTION, GLORY_INCARNATE, SEALED_IN_BLOOD, ZEALOTRY, SILVER_TONGUES, ACCORD, THE_GOOD_STUFF, GHOST_MARKET, HIGH_SOCIETY, HOOKED, EVERYONE_STEALS, GHOST_ECHOES, PACK_RATS, SECOND_STORY, SLIPPERY, SYNCHRONIZED, LIKE_PART_OF_THE_FAMILY, ALL_HANDS, GHOST_PASSAGE, JUST_PASSING_THROUGH, LEVERAGE, REAVERS, RENEGADES;

	// specials by crew type
	public static final CrewSpecial[] ASSASSIN_SPECIALS = { DEADLY, CROWS_VEIL, EMBERDEATH, NO_TRACES, PREDATORS,
			VIPERS, PATRON };
	public static final CrewSpecial[] BRAVOS_SPECIALS = { DANGEROUS, BLOOD_BROTHERS, DOOR_KICKERS, FIENDS,
			FORGED_IN_THE_FIRE, WAR_DOGS, PATRON };
	public static final CrewSpecial[] CULT_SPECIALS = { CHOSEN, ANNOINTED, BOUND_IN_DARKNESS, CONVICTION,
			GLORY_INCARNATE, SEALED_IN_BLOOD, ZEALOTRY };
	public static final CrewSpecial[] HAWKERS_SPECIALS = { SILVER_TONGUES, ACCORD, THE_GOOD_STUFF, GHOST_MARKET,
			HIGH_SOCIETY, HOOKED, PATRON };
	public static final CrewSpecial[] SHADOWS_SPECIALS = { EVERYONE_STEALS, GHOST_ECHOES, PACK_RATS, SECOND_STORY,
			SLIPPERY, SYNCHRONIZED, PATRON };
	public static final CrewSpecial[] SMUGGLERS_SPECIALS = { LIKE_PART_OF_THE_FAMILY, ALL_HANDS, GHOST_PASSAGE,
			JUST_PASSING_THROUGH, LEVERAGE, REAVERS, RENEGADES };
	public static final CrewSpecial[] SKILL_SPECIALS = { DEADLY, DANGEROUS, CHOSEN, SILVER_TONGUES, EVERYONE_STEALS,
			RENEGADES };
}