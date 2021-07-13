package com.bladesinthedark.crew;

import java.util.HashMap;

import com.bladesinthedark.rules.Dice;

public enum Claim {
	LAIR, TURF_1, TURF_2, TURF_3, TURF_4, TURF_5, TURF_6, //
	TRAINING_ROOMS, VICE_DEN, FIXER, INFORMANTS, HAGFISH_FARM, VICTIM_TROPHIES, COVER_OPERATION, PROTECTION_RACKET, INFIRMARY, ENVOY, COVER_IDENTITIES_A, CITY_RECORDS, BARRACKS, TERRORIZED_CITIZENS, FIGHTING_PITS, BLUECOAT_INTIMIDATION, STREET_FENCE, WAREHOUSES, BLUECOAT_CONFEDERATES, CLOISTER, OFFERTORY, ANCIENT_OBELISK, ANCIENT_TOWER, SPIRIT_WELL, ANCIENT_GATE, SANCTUARY, SACRED_NEXUS, ANCIENT_ALTAR, PERSONAL_CLOTHIER, LOCAL_GRAFT, LOOKOUTS, LUXURY_VENUE, FOREIGN_MARKET, SURPLUS_CACHES, COVER_IDENTITIES_B, INTERROGATION_CHAMBER, GAMBLING_DEN, LOYAL_FENCE, TAVERN, DRUG_DEN, COVERT_DROPS, SECRET_PATHWAYS, SIDE_BUSINESS, LUXURY_FENCE, SECRET_ROUTES, FLEET;

	// claims by crew type
	public static final Claim[] ASSASSIN_CLAIMS = { LAIR, TRAINING_ROOMS, VICE_DEN, FIXER, INFORMANTS, HAGFISH_FARM,
			VICTIM_TROPHIES, COVER_OPERATION, PROTECTION_RACKET, INFIRMARY, ENVOY, COVER_IDENTITIES_A, CITY_RECORDS };
	public static final Claim[] BRAVO_CLAIMS = { LAIR, INFORMANTS, INFIRMARY, PROTECTION_RACKET, BARRACKS,
			TERRORIZED_CITIZENS, FIGHTING_PITS, BLUECOAT_INTIMIDATION, STREET_FENCE, WAREHOUSES,
			BLUECOAT_CONFEDERATES };
	public static final Claim[] CULT_CLAIMS = { LAIR, VICE_DEN, CLOISTER, OFFERTORY, ANCIENT_OBELISK, ANCIENT_TOWER,
			SPIRIT_WELL, ANCIENT_GATE, SANCTUARY, SACRED_NEXUS, ANCIENT_ALTAR };
	public static final Claim[] HAWKER_CLAIMS = { LAIR, INFORMANTS, VICE_DEN, COVER_OPERATION, PERSONAL_CLOTHIER,
			LOCAL_GRAFT, LOOKOUTS, LUXURY_VENUE, FOREIGN_MARKET, SURPLUS_CACHES, COVER_IDENTITIES_B };
	public static final Claim[] SHADOW_CLAIMS = { LAIR, GAMBLING_DEN, INFORMANTS, LOOKOUTS, HAGFISH_FARM, INFIRMARY,
			INTERROGATION_CHAMBER, LOYAL_FENCE, TAVERN, DRUG_DEN, COVERT_DROPS, SECRET_PATHWAYS };
	public static final Claim[] SMUGGLER_CLAIMS = { LAIR, VICE_DEN, TAVERN, ANCIENT_GATE, INFORMANTS, COVER_OPERATION,
			WAREHOUSES, SIDE_BUSINESS, LUXURY_FENCE, SECRET_ROUTES, FLEET };

	public static Claim randomClaimByCrew(CrewType type) {
		Claim claim = null;

		if (type.equals(CrewType.ASSASSINS))
			claim = Claim.randomAssassinClaim();
		else if (type.equals(CrewType.BRAVOS))
			claim = Claim.randomBravoClaim();
		else if (type.equals(CrewType.CULT))
			claim = Claim.randomCultClaim();
		else if (type.equals(CrewType.HAWKERS))
			claim = Claim.randomHawkerClaim();
		else if (type.equals(CrewType.SHADOWS))
			claim = Claim.randomShadowClaim();
		else if (type.equals(CrewType.SMUGGLERS))
			claim = Claim.randomSmugglerClaim();

		return claim;
	}

	public static Claim turfClaim(Crew crew) {
		HashMap<Claim, Crew> claims = crew.getClaims();
		Claim turf = TURF_1;

		if (claims.containsKey(TURF_5))
			turf = TURF_6;
		else if (claims.containsKey(TURF_4))
			turf = TURF_5;
		else if (claims.containsKey(TURF_3))
			turf = TURF_4;
		else if (claims.containsKey(TURF_2))
			turf = TURF_3;
		else if (claims.containsKey(TURF_1))
			turf = TURF_2;
		else
			turf = TURF_1;

		return turf;
	}

	public static Claim randomAssassinClaim() {
		return Dice.randomFromArray(ASSASSIN_CLAIMS);
	}

	public static Claim randomBravoClaim() {
		return Dice.randomFromArray(BRAVO_CLAIMS);
	}

	public static Claim randomCultClaim() {
		return Dice.randomFromArray(CULT_CLAIMS);
	}

	public static Claim randomHawkerClaim() {
		return Dice.randomFromArray(HAWKER_CLAIMS);
	}

	public static Claim randomShadowClaim() {
		return Dice.randomFromArray(SHADOW_CLAIMS);
	}

	public static Claim randomSmugglerClaim() {
		return Dice.randomFromArray(SMUGGLER_CLAIMS);
	}
}