package com.bladesinthedark.crew;

import com.bladesinthedark.rules.Dice;

public enum CrewUpgrade {
	C2_COHORT_1, C2_COHORT_2, C2_COHORT_3, C2_COHORT_4, //
	BOAT_HOUSE_1, BOAT_HOUSE_2, CARRIAGE_HOUSE_1, CARRIAGE_HOUSE_2, //
	HIDDEN_LAIR, LIVING_QUARTERS, SECURE_LAIR_1, SECURE_LAIR_2, //
	TRAINING_INSIGHT, TRAINING_PROWESS, TRAINING_RESOLVE, TRAINING_PERSONAL, //
	STORAGE_VAULT_1, STORAGE_VAULT_2, WORKSHOP, C4_MASTERY,
	//
	QUALITY_DOCUMENTS, QUALITY_GEAR, QUALITY_IMPLEMENTS, QUALITY_SUPPLIES, QUALITY_TOOLS, QUALITY_WEAPONS, //
	ELITE_SKULKS, ELITE_THUGS, ELITE_ROVERS, ELITE_ADEPTS, ELITE_ROOKS,
	//
	ASSASSIN_RIGGING, BRAVOS_RIGGING, CULT_RIGGING, HAWKERS_RIGGING, THIEF_RIGGING, SMUGGLER_RIGGING, //
	IRONHOOK_CONTACTS, C3_COMPOSED, C3_HARDENED, RITUAL_SANCTUM, MAPS_AND_KEYS, CAMOUFLAGE, BARGE;

	/*
	 * STATIC FIELDS
	 */
	public static final CrewUpgrade[] GENERIC_UPGRADES = { BOAT_HOUSE_1, BOAT_HOUSE_2, CARRIAGE_HOUSE_1,
			CARRIAGE_HOUSE_2, HIDDEN_LAIR, LIVING_QUARTERS, SECURE_LAIR_1, SECURE_LAIR_2, TRAINING_INSIGHT,
			TRAINING_PROWESS, TRAINING_RESOLVE, TRAINING_PERSONAL, STORAGE_VAULT_1, STORAGE_VAULT_2, WORKSHOP,
			C4_MASTERY, QUALITY_DOCUMENTS, QUALITY_GEAR, QUALITY_IMPLEMENTS, QUALITY_SUPPLIES, QUALITY_TOOLS,
			QUALITY_WEAPONS };
	public static final CrewUpgrade[] COST_ONE_UPGRADES = { BOAT_HOUSE_1, BOAT_HOUSE_2, CARRIAGE_HOUSE_1,
			CARRIAGE_HOUSE_2, HIDDEN_LAIR, LIVING_QUARTERS, SECURE_LAIR_1, SECURE_LAIR_2, TRAINING_INSIGHT,
			TRAINING_PROWESS, TRAINING_RESOLVE, TRAINING_PERSONAL, STORAGE_VAULT_1, STORAGE_VAULT_2, WORKSHOP,
			QUALITY_DOCUMENTS, QUALITY_GEAR, QUALITY_IMPLEMENTS, QUALITY_SUPPLIES, QUALITY_TOOLS, QUALITY_WEAPONS };

	// upgrades by crew type
	public static final CrewUpgrade[] COST_ONE_ASSASSIN_UPGRADES = { ASSASSIN_RIGGING, IRONHOOK_CONTACTS, ELITE_SKULKS,
			ELITE_THUGS };
	public static final CrewUpgrade[] COST_ONE_BRAVOS_UPGRADES = { BRAVOS_RIGGING, IRONHOOK_CONTACTS, ELITE_ROVERS,
			ELITE_THUGS };
	public static final CrewUpgrade[] COST_ONE_CULT_UPGRADES = { CULT_RIGGING, RITUAL_SANCTUM, ELITE_ADEPTS,
			ELITE_THUGS };
	public static final CrewUpgrade[] COST_ONE_HAWKERS_UPGRADES = { HAWKERS_RIGGING, IRONHOOK_CONTACTS, ELITE_ROOKS,
			ELITE_THUGS };
	public static final CrewUpgrade[] COST_ONE_SHADOWS_UPGRADES = { THIEF_RIGGING, MAPS_AND_KEYS, ELITE_ROOKS,
			ELITE_SKULKS };
	public static final CrewUpgrade[] COST_ONE_SMUGGLERS_UPGRADES = { SMUGGLER_RIGGING, CAMOUFLAGE, ELITE_ROVERS,
			BARGE };
	public static final CrewUpgrade[] ASSASSIN_UPGRADES = { ASSASSIN_RIGGING, IRONHOOK_CONTACTS, ELITE_SKULKS,
			ELITE_THUGS, C3_HARDENED };
	public static final CrewUpgrade[] BRAVOS_UPGRADES = { BRAVOS_RIGGING, IRONHOOK_CONTACTS, ELITE_ROVERS, ELITE_THUGS,
			C3_HARDENED };
	public static final CrewUpgrade[] CULT_UPGRADES = { CULT_RIGGING, RITUAL_SANCTUM, ELITE_ADEPTS, ELITE_THUGS,
			C3_HARDENED };
	public static final CrewUpgrade[] HAWKERS_UPGRADES = { HAWKERS_RIGGING, IRONHOOK_CONTACTS, ELITE_ROOKS, ELITE_THUGS,
			C3_COMPOSED };
	public static final CrewUpgrade[] SHADOWS_UPGRADES = { THIEF_RIGGING, MAPS_AND_KEYS, ELITE_ROOKS, ELITE_SKULKS,
			C3_COMPOSED };
	public static final CrewUpgrade[] SMUGGLERS_UPGRADES = { SMUGGLER_RIGGING, CAMOUFLAGE, ELITE_ROVERS, BARGE,
			C3_COMPOSED };

	/*
	 * STATIC METHODS
	 */
	public static CrewUpgrade randomUpgradeByCrewType(CrewType type) {
		CrewUpgrade[] array = GENERIC_UPGRADES;
		if (type.equals(CrewType.ASSASSINS))
			array = ASSASSIN_UPGRADES;
		else if (type.equals(CrewType.BRAVOS))
			array = BRAVOS_UPGRADES;
		else if (type.equals(CrewType.CULT))
			array = CULT_UPGRADES;
		else if (type.equals(CrewType.HAWKERS))
			array = HAWKERS_UPGRADES;
		else if (type.equals(CrewType.SHADOWS))
			array = SHADOWS_UPGRADES;
		else if (type.equals(CrewType.SMUGGLERS))
			array = SMUGGLERS_UPGRADES;

		return Dice.randomFromArray(array);
	}

	public static CrewUpgrade randomUpgrade() {
		return Dice.randomFromArray(COST_ONE_UPGRADES);
	}

	public static CrewUpgrade randomUpgradeByCrew(CrewType type) {
		CrewUpgrade upgrade = null;

		if (type.equals(CrewType.ASSASSINS))
			upgrade = CrewUpgrade.randomAssassinUpgrade();
		else if (type.equals(CrewType.BRAVOS))
			upgrade = CrewUpgrade.randomBravosUpgrade();
		else if (type.equals(CrewType.CULT))
			upgrade = CrewUpgrade.randomCultUpgrade();
		else if (type.equals(CrewType.HAWKERS))
			upgrade = CrewUpgrade.randomHawkerUpgrade();
		else if (type.equals(CrewType.SHADOWS))
			upgrade = CrewUpgrade.randomShadowUpgrade();
		else if (type.equals(CrewType.SMUGGLERS))
			upgrade = CrewUpgrade.randomSmugglerUpgrade();

		return upgrade;
	}

	public static CrewUpgrade randomAssassinUpgrade() {
		return Dice.randomFromArray(COST_ONE_ASSASSIN_UPGRADES);
	}

	public static CrewUpgrade randomBravosUpgrade() {
		return Dice.randomFromArray(COST_ONE_BRAVOS_UPGRADES);
	}

	public static CrewUpgrade randomCultUpgrade() {
		return Dice.randomFromArray(COST_ONE_CULT_UPGRADES);
	}

	public static CrewUpgrade randomHawkerUpgrade() {
		return Dice.randomFromArray(COST_ONE_HAWKERS_UPGRADES);
	}

	public static CrewUpgrade randomShadowUpgrade() {
		return Dice.randomFromArray(COST_ONE_SHADOWS_UPGRADES);
	}

	public static CrewUpgrade randomSmugglerUpgrade() {
		return Dice.randomFromArray(COST_ONE_SMUGGLERS_UPGRADES);
	}
}