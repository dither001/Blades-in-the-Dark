import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public interface Faction {
	/*
	 * ENUMS
	 */
	public enum Type {
		ASSASSINS, BRAVOS, CULT, HAWKERS, SHADOWS, SMUGGLERS
	}

	public enum Rep {
		AMBITIOUS, BRUTAL, DARING, HONORABLE, PROFESSIONAL, SAVVY, SUBTLE, STRANGE
	}

	public enum NamedFaction {
		BARROWCLEFT, BILLHOOKS, BLUECOATS, BRIGADE, BRIGHTSTONE, CABBIES, CHARHOLLOW, CHARTERHALL, CHURCH_OF_ECSTASY, CIRCLE_OF_FLAME, CITY_COUNCIL, COALRIDGE, CROWS, CROWS_FOOT, CYPHERS, DAGGER_ISLES_CONSULATE, DEATHLANDS_SCAVENGERS, DIMMER_SISTERS, DOCKERS, DOCKS, DUNSLOUGH, FOG_HOUNDS, FORGOTTEN_GODS, FOUNDATION, GONDOLIERS, GRAY_CLOAKS, GRINDERS, HIVE, HORDE, IMPERIAL_MILITARY, INK_RAKES, INSPECTORS, IRONHOOK_PRISON, IRUVIAN_CONSULATE, LABORERS, LAMPBLACKS, LEVIATHAN_HUNTERS, LORD_SCURLOCK, LOST, MINISTRY_OF_PRESERVATION, NIGHTMARKET, PATH_OF_ECHOES, RAIL_JACKS, RECONCILED, RED_SASHES, SAILORS, SERVANTS, SEVEROSI_CONSULATE, SILKSHORE, SILVER_NAILS, SIX_TOWERS, SKOVLAN_CONSULATE, SKOVLANDER_REFUGEES, SPARKWRIGHTS, SPIRIT_WARDENS, ULF_IRONBORN, UNSEEN, WEEPING_LADY, WHITECROWN, WRAITHS, VULTURES
	}

	public enum Claim {
		LAIR, TURF_1, TURF_2, TURF_3, TURF_4, TURF_5, TURF_6, //
		TRAINING_ROOMS, VICE_DEN, FIXER, INFORMANTS, HAGFISH_FARM, VICTIM_TROPHIES, COVER_OPERATION, PROTECTION_RACKET, INFIRMARY, ENVOY, COVER_IDENTITIES_A, CITY_RECORDS, BARRACKS, TERRORIZED_CITIZENS, FIGHTING_PITS, BLUECOAT_INTIMIDATION, STREET_FENCE, WAREHOUSES, BLUECOAT_CONFEDERATES, CLOISTER, OFFERTORY, ANCIENT_OBELISK, ANCIENT_TOWER, SPIRIT_WELL, ANCIENT_GATE, SANCTUARY, SACRED_NEXUS, ANCIENT_ALTAR, PERSONAL_CLOTHIER, LOCAL_GRAFT, LOOKOUTS, LUXURY_VENUE, FOREIGN_MARKET, SURPLUS_CACHES, COVER_IDENTITIES_B, INTERROGATION_CHAMBER, GAMBLING_DEN, LOYAL_FENCE, TAVERN, DRUG_DEN, COVERT_DROPS, SECRET_PATHWAYS, SIDE_BUSINESS, LUXURY_FENCE, SECRET_ROUTES, FLEET
	}

	public enum Upgrade {
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
		IRONHOOK_CONTACTS, C3_COMPOSED, C3_HARDENED, RITUAL_SANCTUM, MAPS_AND_KEYS, CAMOUFLAGE, BARGE
	}

	public enum Special {
		PATRON, VETERAN_1, VETERAN_2, VETERAN_3, DEADLY, CROWS_VEIL, EMBERDEATH, NO_TRACES, PREDATORS, VIPERS, DANGEROUS, BLOOD_BROTHERS, DOOR_KICKERS, FIENDS, FORGED_IN_THE_FIRE, WAR_DOGS, CHOSEN, ANNOINTED, BOUND_IN_DARKNESS, CONVICTION, GLORY_INCARNATE, SEALED_IN_BLOOD, ZEALOTRY, SILVER_TONGUES, ACCORD, THE_GOOD_STUFF, GHOST_MARKET, HIGH_SOCIETY, HOOKED, EVERYONE_STEALS, GHOST_ECHOES, PACK_RATS, SECOND_STORY, SLIPPERY, SYNCHRONIZED, LIKE_PART_OF_THE_FAMILY, ALL_HANDS, GHOST_PASSAGE, JUST_PASSING_THROUGH, LEVERAGE, REAVERS, RENEGADES
	}

	// static fields
	public static final Type[] ALL_TYPES = { Type.ASSASSINS, Type.BRAVOS, Type.CULT, Type.HAWKERS, Type.SHADOWS,
			Type.SMUGGLERS };
	public static final Rep[] ALL_REPS = { Rep.AMBITIOUS, Rep.BRUTAL, Rep.DARING, Rep.HONORABLE, Rep.PROFESSIONAL,
			Rep.SAVVY, Rep.SUBTLE, Rep.STRANGE };
	public static final NamedFaction[] ALL_FACTIONS = { NamedFaction.BARROWCLEFT, NamedFaction.BILLHOOKS,
			NamedFaction.BLUECOATS, NamedFaction.BRIGADE, NamedFaction.BRIGHTSTONE, NamedFaction.CABBIES,
			NamedFaction.CHARHOLLOW, NamedFaction.CHARTERHALL, NamedFaction.CHURCH_OF_ECSTASY,
			NamedFaction.CIRCLE_OF_FLAME, NamedFaction.CITY_COUNCIL, NamedFaction.COALRIDGE, NamedFaction.CROWS,
			NamedFaction.CROWS_FOOT, NamedFaction.CYPHERS, NamedFaction.DAGGER_ISLES_CONSULATE,
			NamedFaction.DEATHLANDS_SCAVENGERS, NamedFaction.DIMMER_SISTERS, NamedFaction.DOCKERS, NamedFaction.DOCKS,
			NamedFaction.DUNSLOUGH, NamedFaction.FOG_HOUNDS, NamedFaction.FORGOTTEN_GODS, NamedFaction.FOUNDATION,
			NamedFaction.GONDOLIERS, NamedFaction.GRAY_CLOAKS, NamedFaction.GRINDERS, NamedFaction.HIVE,
			NamedFaction.HORDE, NamedFaction.IMPERIAL_MILITARY, NamedFaction.INK_RAKES, NamedFaction.INSPECTORS,
			NamedFaction.IRONHOOK_PRISON, NamedFaction.IRUVIAN_CONSULATE, NamedFaction.LABORERS,
			NamedFaction.LAMPBLACKS, NamedFaction.LEVIATHAN_HUNTERS, NamedFaction.LORD_SCURLOCK, NamedFaction.LOST,
			NamedFaction.MINISTRY_OF_PRESERVATION, NamedFaction.NIGHTMARKET, NamedFaction.PATH_OF_ECHOES,
			NamedFaction.RAIL_JACKS, NamedFaction.RECONCILED, NamedFaction.RED_SASHES, NamedFaction.SAILORS,
			NamedFaction.SERVANTS, NamedFaction.SEVEROSI_CONSULATE, NamedFaction.SILKSHORE, NamedFaction.SILVER_NAILS,
			NamedFaction.SIX_TOWERS, NamedFaction.SKOVLAN_CONSULATE, NamedFaction.SKOVLANDER_REFUGEES,
			NamedFaction.SPARKWRIGHTS, NamedFaction.SPIRIT_WARDENS, NamedFaction.ULF_IRONBORN, NamedFaction.UNSEEN,
			NamedFaction.WEEPING_LADY, NamedFaction.WHITECROWN, NamedFaction.WRAITHS, NamedFaction.VULTURES };

	//
	public static final NamedFaction[] CITIZENRY = { NamedFaction.BARROWCLEFT, NamedFaction.BRIGHTSTONE,
			NamedFaction.CHARHOLLOW, NamedFaction.CHARTERHALL, NamedFaction.COALRIDGE, NamedFaction.CROWS_FOOT,
			NamedFaction.DOCKS, NamedFaction.DUNSLOUGH, NamedFaction.NIGHTMARKET, NamedFaction.SILKSHORE,
			NamedFaction.SIX_TOWERS, NamedFaction.WHITECROWN };
	public static final NamedFaction[] INSTITUTIONS = { NamedFaction.BLUECOATS, NamedFaction.BRIGADE,
			NamedFaction.CITY_COUNCIL, NamedFaction.DAGGER_ISLES_CONSULATE, NamedFaction.IMPERIAL_MILITARY,
			NamedFaction.INSPECTORS, NamedFaction.IRONHOOK_PRISON, NamedFaction.IRUVIAN_CONSULATE,
			NamedFaction.LEVIATHAN_HUNTERS, NamedFaction.MINISTRY_OF_PRESERVATION, NamedFaction.SEVEROSI_CONSULATE,
			NamedFaction.SKOVLAN_CONSULATE, NamedFaction.SPARKWRIGHTS, NamedFaction.SPIRIT_WARDENS };
	public static final NamedFaction[] LABOR_TRADE = { NamedFaction.CABBIES, NamedFaction.CYPHERS, NamedFaction.DOCKERS,
			NamedFaction.FOUNDATION, NamedFaction.GONDOLIERS, NamedFaction.INK_RAKES, NamedFaction.LABORERS,
			NamedFaction.RAIL_JACKS, NamedFaction.SAILORS, NamedFaction.SERVANTS };
	public static final NamedFaction[] THE_FRINGE = { NamedFaction.CHURCH_OF_ECSTASY,
			NamedFaction.DEATHLANDS_SCAVENGERS, NamedFaction.FORGOTTEN_GODS, NamedFaction.HORDE,
			NamedFaction.PATH_OF_ECHOES, NamedFaction.RECONCILED, NamedFaction.SKOVLANDER_REFUGEES,
			NamedFaction.WEEPING_LADY };
	public static final NamedFaction[] UNDERWORLD = { NamedFaction.BILLHOOKS, NamedFaction.CIRCLE_OF_FLAME,
			NamedFaction.CROWS, NamedFaction.DIMMER_SISTERS, NamedFaction.FOG_HOUNDS, NamedFaction.GRAY_CLOAKS,
			NamedFaction.GRINDERS, NamedFaction.HIVE, NamedFaction.LAMPBLACKS, NamedFaction.LORD_SCURLOCK,
			NamedFaction.LOST, NamedFaction.RED_SASHES, NamedFaction.SILVER_NAILS, NamedFaction.ULF_IRONBORN,
			NamedFaction.UNSEEN, NamedFaction.WRAITHS, NamedFaction.VULTURES };

	// specials by crew type
	public static final Special[] ASSASSIN_SPECIALS = { Special.DEADLY, Special.CROWS_VEIL, Special.EMBERDEATH,
			Special.NO_TRACES, Special.PREDATORS, Special.VIPERS, Special.PATRON };

	public static final Special[] BRAVOS_SPECIALS = { Special.DANGEROUS, Special.BLOOD_BROTHERS, Special.DOOR_KICKERS,
			Special.FIENDS, Special.FORGED_IN_THE_FIRE, Special.WAR_DOGS, Special.PATRON };

	public static final Special[] CULT_SPECIALS = { Special.CHOSEN, Special.ANNOINTED, Special.BOUND_IN_DARKNESS,
			Special.CONVICTION, Special.GLORY_INCARNATE, Special.SEALED_IN_BLOOD, Special.ZEALOTRY };

	public static final Special[] HAWKERS_SPECIALS = { Special.SILVER_TONGUES, Special.ACCORD, Special.THE_GOOD_STUFF,
			Special.GHOST_MARKET, Special.HIGH_SOCIETY, Special.HOOKED, Special.PATRON };

	public static final Special[] SHADOWS_SPECIALS = { Special.EVERYONE_STEALS, Special.GHOST_ECHOES, Special.PACK_RATS,
			Special.SECOND_STORY, Special.SLIPPERY, Special.SYNCHRONIZED, Special.PATRON };

	public static final Special[] SMUGGLERS_SPECIALS = { Special.LIKE_PART_OF_THE_FAMILY, Special.ALL_HANDS,
			Special.GHOST_PASSAGE, Special.JUST_PASSING_THROUGH, Special.LEVERAGE, Special.REAVERS, Special.RENEGADES };

	public static final Special[] SKILL_SPECIALS = { Special.DEADLY, Special.DANGEROUS, Special.CHOSEN,
			Special.SILVER_TONGUES, Special.EVERYONE_STEALS, Special.RENEGADES };

	// claims by crew type
	public static final Claim[] ASSASSIN_CLAIMS = { Claim.LAIR, Claim.TRAINING_ROOMS, Claim.VICE_DEN, Claim.FIXER,
			Claim.INFORMANTS, Claim.HAGFISH_FARM, Claim.VICTIM_TROPHIES, Claim.COVER_OPERATION, Claim.PROTECTION_RACKET,
			Claim.INFIRMARY, Claim.ENVOY, Claim.COVER_IDENTITIES_A, Claim.CITY_RECORDS };
	public static final Claim[] BRAVO_CLAIMS = { Claim.LAIR, Claim.INFORMANTS, Claim.INFIRMARY, Claim.PROTECTION_RACKET,
			Claim.BARRACKS, Claim.TERRORIZED_CITIZENS, Claim.FIGHTING_PITS, Claim.BLUECOAT_INTIMIDATION,
			Claim.STREET_FENCE, Claim.WAREHOUSES, Claim.BLUECOAT_CONFEDERATES };
	public static final Claim[] CULT_CLAIMS = { Claim.LAIR, Claim.VICE_DEN, Claim.CLOISTER, Claim.OFFERTORY,
			Claim.ANCIENT_OBELISK, Claim.ANCIENT_TOWER, Claim.SPIRIT_WELL, Claim.ANCIENT_GATE, Claim.SANCTUARY,
			Claim.SACRED_NEXUS, Claim.ANCIENT_ALTAR };
	public static final Claim[] HAWKER_CLAIMS = { Claim.LAIR, Claim.INFORMANTS, Claim.VICE_DEN, Claim.COVER_OPERATION,
			Claim.PERSONAL_CLOTHIER, Claim.LOCAL_GRAFT, Claim.LOOKOUTS, Claim.LUXURY_VENUE, Claim.FOREIGN_MARKET,
			Claim.SURPLUS_CACHES, Claim.COVER_IDENTITIES_B };
	public static final Claim[] SHADOW_CLAIMS = { Claim.LAIR, Claim.GAMBLING_DEN, Claim.INFORMANTS, Claim.LOOKOUTS,
			Claim.HAGFISH_FARM, Claim.INFIRMARY, Claim.INTERROGATION_CHAMBER, Claim.LOYAL_FENCE, Claim.TAVERN,
			Claim.DRUG_DEN, Claim.COVERT_DROPS, Claim.SECRET_PATHWAYS };
	public static final Claim[] SMUGGLER_CLAIMS = { Claim.LAIR, Claim.VICE_DEN, Claim.TAVERN, Claim.ANCIENT_GATE,
			Claim.INFORMANTS, Claim.COVER_OPERATION, Claim.WAREHOUSES, Claim.SIDE_BUSINESS, Claim.LUXURY_FENCE,
			Claim.SECRET_ROUTES, Claim.FLEET };

	// upgrades
	public static final Upgrade[] GENERIC_UPGRADES = { Upgrade.BOAT_HOUSE_1, Upgrade.BOAT_HOUSE_2,
			Upgrade.CARRIAGE_HOUSE_1, Upgrade.CARRIAGE_HOUSE_2, Upgrade.HIDDEN_LAIR, Upgrade.LIVING_QUARTERS,
			Upgrade.SECURE_LAIR_1, Upgrade.SECURE_LAIR_2, Upgrade.TRAINING_INSIGHT, Upgrade.TRAINING_PROWESS,
			Upgrade.TRAINING_RESOLVE, Upgrade.TRAINING_PERSONAL, Upgrade.STORAGE_VAULT_1, Upgrade.STORAGE_VAULT_2,
			Upgrade.WORKSHOP, Upgrade.C4_MASTERY, Upgrade.QUALITY_DOCUMENTS, Upgrade.QUALITY_GEAR,
			Upgrade.QUALITY_IMPLEMENTS, Upgrade.QUALITY_SUPPLIES, Upgrade.QUALITY_TOOLS, Upgrade.QUALITY_WEAPONS };
	public static final Upgrade[] COST_ONE_UPGRADES = { Upgrade.BOAT_HOUSE_1, Upgrade.BOAT_HOUSE_2,
			Upgrade.CARRIAGE_HOUSE_1, Upgrade.CARRIAGE_HOUSE_2, Upgrade.HIDDEN_LAIR, Upgrade.LIVING_QUARTERS,
			Upgrade.SECURE_LAIR_1, Upgrade.SECURE_LAIR_2, Upgrade.TRAINING_INSIGHT, Upgrade.TRAINING_PROWESS,
			Upgrade.TRAINING_RESOLVE, Upgrade.TRAINING_PERSONAL, Upgrade.STORAGE_VAULT_1, Upgrade.STORAGE_VAULT_2,
			Upgrade.WORKSHOP, Upgrade.QUALITY_DOCUMENTS, Upgrade.QUALITY_GEAR, Upgrade.QUALITY_IMPLEMENTS,
			Upgrade.QUALITY_SUPPLIES, Upgrade.QUALITY_TOOLS, Upgrade.QUALITY_WEAPONS };

	// upgrades by crew type
	public static final Upgrade[] COST_ONE_ASSASSIN_UPGRADES = { Upgrade.ASSASSIN_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_SKULKS, Upgrade.ELITE_THUGS };
	public static final Upgrade[] COST_ONE_BRAVOS_UPGRADES = { Upgrade.BRAVOS_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_ROVERS, Upgrade.ELITE_THUGS };
	public static final Upgrade[] COST_ONE_CULT_UPGRADES = { Upgrade.CULT_RIGGING, Upgrade.RITUAL_SANCTUM,
			Upgrade.ELITE_ADEPTS, Upgrade.ELITE_THUGS };
	public static final Upgrade[] COST_ONE_HAWKERS_UPGRADES = { Upgrade.HAWKERS_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_ROOKS, Upgrade.ELITE_THUGS };
	public static final Upgrade[] COST_ONE_SHADOWS_UPGRADES = { Upgrade.THIEF_RIGGING, Upgrade.MAPS_AND_KEYS,
			Upgrade.ELITE_ROOKS, Upgrade.ELITE_SKULKS };
	public static final Upgrade[] COST_ONE_SMUGGLERS_UPGRADES = { Upgrade.SMUGGLER_RIGGING, Upgrade.CAMOUFLAGE,
			Upgrade.ELITE_ROVERS, Upgrade.BARGE };

	public static final Upgrade[] ASSASSIN_UPGRADES = { Upgrade.ASSASSIN_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_SKULKS, Upgrade.ELITE_THUGS, Upgrade.C3_HARDENED };
	public static final Upgrade[] BRAVOS_UPGRADES = { Upgrade.BRAVOS_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_ROVERS, Upgrade.ELITE_THUGS, Upgrade.C3_HARDENED };
	public static final Upgrade[] CULT_UPGRADES = { Upgrade.CULT_RIGGING, Upgrade.RITUAL_SANCTUM, Upgrade.ELITE_ADEPTS,
			Upgrade.ELITE_THUGS, Upgrade.C3_HARDENED };
	public static final Upgrade[] HAWKERS_UPGRADES = { Upgrade.HAWKERS_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_ROOKS, Upgrade.ELITE_THUGS, Upgrade.C3_COMPOSED };
	public static final Upgrade[] SHADOWS_UPGRADES = { Upgrade.THIEF_RIGGING, Upgrade.MAPS_AND_KEYS,
			Upgrade.ELITE_ROOKS, Upgrade.ELITE_SKULKS, Upgrade.C3_COMPOSED };
	public static final Upgrade[] SMUGGLERS_UPGRADES = { Upgrade.SMUGGLER_RIGGING, Upgrade.CAMOUFLAGE,
			Upgrade.ELITE_ROVERS, Upgrade.BARGE, Upgrade.C3_COMPOSED };

	/*
	 * STATIC METHODS
	 */
	public static NamedFaction randomFactionEnum() {
		return Dice.randomFromArray(ALL_FACTIONS);
	}

	public static NamedFaction randomCitizenryEnum() {
		return Dice.randomFromArray(CITIZENRY);
	}

	public static NamedFaction randomInstitutionEnum() {
		return Dice.randomFromArray(INSTITUTIONS);
	}

	public static NamedFaction randomLaborTradeEnum() {
		return Dice.randomFromArray(LABOR_TRADE);
	}

	public static NamedFaction randomFringeEnum() {
		return Dice.randomFromArray(THE_FRINGE);
	}

	public static NamedFaction randomUnderworldEnum() {
		return Dice.randomFromArray(UNDERWORLD);
	}

	public static Type randomCrewType() {
		return Dice.randomFromArray(ALL_TYPES);
	}

	public static Rep randomReputation() {
		return Dice.randomFromArray(ALL_REPS);
	}

	public static Faction.Upgrade randomUpgradeByCrewType(Type type) {
		Faction.Upgrade[] array = GENERIC_UPGRADES;
		if (type.equals(Type.ASSASSINS))
			array = ASSASSIN_UPGRADES;
		else if (type.equals(Type.BRAVOS))
			array = BRAVOS_UPGRADES;
		else if (type.equals(Type.CULT))
			array = CULT_UPGRADES;
		else if (type.equals(Type.HAWKERS))
			array = HAWKERS_UPGRADES;
		else if (type.equals(Type.SHADOWS))
			array = SHADOWS_UPGRADES;
		else if (type.equals(Type.SMUGGLERS))
			array = SMUGGLERS_UPGRADES;

		return Dice.randomFromArray(array);
	}

	public static Faction.Upgrade randomUpgrade() {
		return Dice.randomFromArray(COST_ONE_UPGRADES);
	}

	public static Faction.Upgrade randomUpgradeByCrew(Type type) {
		Faction.Upgrade upgrade = null;

		if (type.equals(Type.ASSASSINS))
			upgrade = randomAssassinUpgrade();
		else if (type.equals(Type.BRAVOS))
			upgrade = randomBravosUpgrade();
		else if (type.equals(Type.CULT))
			upgrade = randomCultUpgrade();
		else if (type.equals(Type.HAWKERS))
			upgrade = randomHawkerUpgrade();
		else if (type.equals(Type.SHADOWS))
			upgrade = randomShadowUpgrade();
		else if (type.equals(Type.SMUGGLERS))
			upgrade = randomSmugglerUpgrade();

		return upgrade;
	}

	public static Faction.Upgrade randomAssassinUpgrade() {
		return Dice.randomFromArray(COST_ONE_ASSASSIN_UPGRADES);
	}

	public static Faction.Upgrade randomBravosUpgrade() {
		return Dice.randomFromArray(COST_ONE_BRAVOS_UPGRADES);
	}

	public static Faction.Upgrade randomCultUpgrade() {
		return Dice.randomFromArray(COST_ONE_CULT_UPGRADES);
	}

	public static Faction.Upgrade randomHawkerUpgrade() {
		return Dice.randomFromArray(COST_ONE_HAWKERS_UPGRADES);
	}

	public static Faction.Upgrade randomShadowUpgrade() {
		return Dice.randomFromArray(COST_ONE_SHADOWS_UPGRADES);
	}

	public static Faction.Upgrade randomSmugglerUpgrade() {
		return Dice.randomFromArray(COST_ONE_SMUGGLERS_UPGRADES);
	}

	public static Claim randomClaimByCrew(Type type) {
		Claim claim = null;

		if (type.equals(Type.ASSASSINS))
			claim = randomAssassinClaim();
		else if (type.equals(Type.BRAVOS))
			claim = randomBravoClaim();
		else if (type.equals(Type.CULT))
			claim = randomCultClaim();
		else if (type.equals(Type.HAWKERS))
			claim = randomHawkerClaim();
		else if (type.equals(Type.SHADOWS))
			claim = randomShadowClaim();
		else if (type.equals(Type.SMUGGLERS))
			claim = randomSmugglerClaim();

		return claim;
	}

	public static Claim turfClaim(Crew crew) {
		HashMap<Claim, Crew> claims = crew.getClaims();
		Claim turf = Faction.Claim.TURF_1;

		if (claims.containsKey(Faction.Claim.TURF_5))
			turf = Claim.TURF_6;
		else if (claims.containsKey(Faction.Claim.TURF_4))
			turf = Claim.TURF_5;
		else if (claims.containsKey(Faction.Claim.TURF_3))
			turf = Claim.TURF_4;
		else if (claims.containsKey(Faction.Claim.TURF_2))
			turf = Claim.TURF_3;
		else if (claims.containsKey(Faction.Claim.TURF_1))
			turf = Claim.TURF_2;
		else
			turf = Claim.TURF_1;

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

	public static NamedFaction[] factionAddOrder() {
		NamedFaction[] addOrder = new NamedFaction[] { NamedFaction.IMPERIAL_MILITARY, NamedFaction.CITY_COUNCIL,
				NamedFaction.LEVIATHAN_HUNTERS, NamedFaction.MINISTRY_OF_PRESERVATION, NamedFaction.WHITECROWN,
				NamedFaction.BRIGHTSTONE, NamedFaction.CHARTERHALL, NamedFaction.CHURCH_OF_ECSTASY,
				NamedFaction.FOUNDATION, NamedFaction.HIVE, NamedFaction.IRONHOOK_PRISON, NamedFaction.SPARKWRIGHTS,
				NamedFaction.SPIRIT_WARDENS, NamedFaction.UNSEEN, NamedFaction.BLUECOATS, NamedFaction.CIRCLE_OF_FLAME,
				NamedFaction.DOCKERS, NamedFaction.FORGOTTEN_GODS, NamedFaction.GONDOLIERS, NamedFaction.HORDE,
				NamedFaction.INSPECTORS, NamedFaction.IRUVIAN_CONSULATE, NamedFaction.LABORERS,
				NamedFaction.LORD_SCURLOCK, NamedFaction.PATH_OF_ECHOES, NamedFaction.RECONCILED, NamedFaction.SAILORS,
				NamedFaction.SILVER_NAILS, NamedFaction.SIX_TOWERS, NamedFaction.SKOVLAN_CONSULATE,
				NamedFaction.SKOVLANDER_REFUGEES, NamedFaction.BARROWCLEFT, NamedFaction.BILLHOOKS,
				NamedFaction.BRIGADE, NamedFaction.CABBIES, NamedFaction.COALRIDGE, NamedFaction.CROWS,
				NamedFaction.CROWS_FOOT, NamedFaction.CYPHERS, NamedFaction.DEATHLANDS_SCAVENGERS,
				NamedFaction.DIMMER_SISTERS, NamedFaction.DOCKS, NamedFaction.GRAY_CLOAKS, NamedFaction.GRINDERS,
				NamedFaction.INK_RAKES, NamedFaction.LAMPBLACKS, NamedFaction.NIGHTMARKET, NamedFaction.RAIL_JACKS,
				NamedFaction.RED_SASHES, NamedFaction.SERVANTS, NamedFaction.SILKSHORE, NamedFaction.WEEPING_LADY,
				NamedFaction.WRAITHS, NamedFaction.CHARHOLLOW, NamedFaction.DAGGER_ISLES_CONSULATE,
				NamedFaction.DUNSLOUGH, NamedFaction.FOG_HOUNDS, NamedFaction.LOST, NamedFaction.SEVEROSI_CONSULATE,
				NamedFaction.ULF_IRONBORN, NamedFaction.VULTURES };

		return addOrder;
	}

	/*
	 * 
	 */
	public int factionID();

	public String getName();

	public void setName(String name);

	public Set<Rep> getReputation();

	public void setReputation(Set<Rep> reputation);

	public Type crewType();

	public void setCrewType(Type type);

	public Set<Plan.Quest> getPlans();

	public void setPlans(Set<Plan.Quest> plans);

	public int getLevel();

	public void setLevel(int level);

	public int getCoin();

	public void setCoin(int coin);

	public int getExperience();

	public void setExperience(int experience);

	public EnumSet<Special> getSpecials();

	public void setSpecials(EnumSet<Special> specials);

	public Set<Upgrade> upgradeSet();

	public int getHeat();

	public void setHeat(int heat);

	public int getWantedLevel();

	public void setWantedLevel(int wantedLevel);

	public boolean active();

	public boolean inactive();

	public void activate();

	public void deactivate();

	public boolean atWar();

	public boolean atPeace();

	public Set<Ship> getShips();

	public void setShips(Set<Ship> ships);

	/*
	 * 
	 */
	public default Set<Ship> allies() {
		Set<Ship> set = new HashSet<Ship>();

		Ship candidate;
		for (Iterator<Ship> it = getShips().iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.getScore() > 6)
				set.add(candidate);
		}

		return set;
	}

	public default Set<Ship> friends() {
		Set<Ship> set = new HashSet<Ship>();

		Ship candidate;
		for (Iterator<Ship> it = getShips().iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.getScore() > 5)
				set.add(candidate);
		}

		return set;
	}

	public default Set<Ship> rivals() {
		Set<Ship> set = new HashSet<Ship>();

		Ship candidate;
		int score;
		for (Iterator<Ship> it = getShips().iterator(); it.hasNext();) {
			candidate = it.next();

			score = candidate.getScore();
			if (score == 3 || score == 4 || score == 5)
				set.add(candidate);
		}

		return set;
	}

	public default Set<Ship> hostiles() {
		Set<Ship> set = new HashSet<Ship>();

		Ship candidate;
		for (Iterator<Ship> it = getShips().iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.getScore() < 3)
				set.add(candidate);
		}

		return set;
	}

	public default Set<Ship> enemies() {
		Set<Ship> set = new HashSet<Ship>();

		Ship candidate;
		for (Iterator<Ship> it = getShips().iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.getScore() < 2)
				set.add(candidate);
		}

		return set;
	}

	/*
	 * 
	 */
	public default void Planning() {
		Set<Plan.Quest> plans = new HashSet<Plan.Quest>(getPlans());

		setPlans(plans);
	}

	/*
	 * 
	 */
	public default boolean transferCoinTo(int amount, Faction other) {
		boolean transfer = false;

		int myCoin = getCoin(), otherCoin = other.getCoin();
		if (myCoin >= amount) {
			setCoin(myCoin - amount);
			other.setCoin(otherCoin + amount);
			transfer = true;
		}

		return transfer;
	}

	public default EnumSet<Special> skillSpecials() {
		Special[] array = SKILL_SPECIALS;

		EnumSet<Special> set = EnumSet.noneOf(Special.class);
		for (int i = 0; i < array.length; ++i) {
			if (getSpecials().contains(array[i]))
				set.add(array[i]);
		}

		return set;
	}

	/*
	 * INNER CLASS - OBLIGATIONS
	 */
	public static class Obligations {
		private Faction owner;
		private Set<Faction> allies, friends, rivals, hostiles, enemies;

		public Obligations(Faction owner) {
			allies = new HashSet<Faction>();
			friends = new HashSet<Faction>();
			rivals = new HashSet<Faction>();
			hostiles = new HashSet<Faction>();
			enemies = new HashSet<Faction>();

			//
			updateObligations();
		}

		public Set<Faction> allies() {
			return allies;
		}

		public Set<Faction> friends() {
			return friends;
		}

		public Set<Faction> rivals() {
			return rivals;
		}

		public Set<Faction> hostiles() {
			return hostiles;
		}

		public Set<Faction> enemies() {
			return enemies;
		}

		public void updateObligations() {
			Ship ship;
			int score;
			Faction faction;
			for (Iterator<Ship> it = owner.getShips().iterator(); it.hasNext();) {
				ship = it.next();
				score = ship.getScore();
				faction = ship.getOther(owner);

				if (score < 2)
					enemies.add(faction);
				else if (score == 2)
					hostiles.add(faction);
				else if (score == 3 || score == 4 || score == 5)
					rivals.add(faction);
				else if (score == 6)
					friends.add(faction);
				else if (score > 6)
					allies.add(faction);
			}
		}

		public Faction selectObligation() {
			int[] obs = new int[] { 0, 0, 0, 0, 0, 0 };

			if (enemies.size() > 0)
				obs[0] = 5;

			if (hostiles.size() > 0)
				obs[1] = 10;

			if (rivals.size() > 0)
				obs[2] = 25;

			if (friends.size() > 0)
				obs[4] = 15;

			if (allies.size() > 0)
				obs[5] = 20;

			// job board is variable
			int totalObs = obs[0] + obs[1] + obs[2] + obs[4] + obs[5];
			if (totalObs < 31)
				obs[3] = 60 - totalObs;
			else if (totalObs < 61)
				obs[3] = 75 - totalObs;

			// selection process
			Faction choice;
			int dice = Dice.roll(100);
			if (dice < obs[0])
				choice = Dice.randomFromSet(enemies);
			else if (dice < obs[0] + obs[1])
				choice = Dice.randomFromSet(hostiles);
			else if (dice < obs[0] + obs[1] + obs[2])
				choice = Dice.randomFromSet(rivals);
			else if (dice < obs[0] + obs[1] + obs[2] + obs[3])
				choice = owner; // FIXME - grab quest from job board
			else if (dice < obs[0] + obs[1] + obs[2] + obs[3] + obs[4])
				choice = Dice.randomFromSet(friends);
			else if (dice < obs[0] + obs[1] + obs[2] + obs[3] + obs[4] + obs[5])
				choice = Dice.randomFromSet(allies);
			else
				choice = owner;

			return choice;
		}
	}

	/*
	 * PLOT OBJECT
	 */
	public class Plot {
		private Faction subject;
		private Faction opposition;

		// Means
		// Motive - Reward
		// Opportunity
		Score.Goal goal;
		Score.Approach plan;
		Score.Activity activity;

		// - - - - - -
		// Elder Sign:
		// Upkeep (if any)
		// Locked Die (if any)
		// Task(s)
		// Trophy
		// Rewards (only once)
		// Consequences (each failure)

		// Laundry Files:
		// Briefing (apparent situation)
		// Situation
		// Hostiles (more of an obstacle than the target)
		// Location
		// Red Tape
		// Set Piece

	}

}
