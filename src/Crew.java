
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Crew {
	public enum Type {
		ASSASSINS, BRAVOS, CULT, HAWKERS, SHADOWS, SMUGGLERS
	}

	public enum Rep {
		AMBITIOUS, BRUTAL, DARING, HONORABLE, PROFESSIONAL, SAVVY, SUBTLE, STRANGE
	}

	public enum District {
		BARROWCLEFT, BRIGHTSTONE, CHARHOLLOW, CHARTERHALL, COALRIDGE, CROWS_FOOT, THE_DOCKS, DUNSLOUGH, NIGHTMARKET, SILKSHORE, SIX_TOWERS, WHITECROWN
	}

	public enum Estate {
		UNDERWORLD, INSTITUTION, LABOR_TRADE, CITIZENRY, THE_FRINGE
	}

	public enum Faction {
		BARROWCLEFT, BILLHOOKS, BLUECOATS, BRIGADE, BRIGHTSTONE, CABBIES, CHARHOLLOW, CHARTERHALL, CHURCH_OF_ECSTASY, CIRCLE_OF_FLAME, CITY_COUNCIL, COALRIDGE, CROWS, CROWS_FOOT, CYPHERS, DAGGER_ISLES_CONSULATE, DEATHLANDS_SCAVENGERS, DIMMER_SISTERS, DOCKERS, DOCKS, DUNSLOUGH, FOG_HOUNDS, FORGOTTEN_GODS, FOUNDATION, GONDOLIERS, GRAY_CLOAKS, GRINDERS, HIVE, HORDE, IMPERIAL_MILITARY, INK_RAKES, INSPECTORS, IRONHOOK_PRISON, IRUVIAN_CONSULATE, LABORERS, LAMPBLACKS, LEVIATHAN_HUNTERS, LORD_SCURLOCK, LOST, MINISTRY_OF_PRESERVATION, NIGHTMARKET, PATH_OF_ECHOES, RAIL_JACKS, RECONCILED, RED_SASHES, SAILORS, SERVANTS, SEVEROSI_CONSULATE, SILKSHORE, SILVER_NAILS, SIX_TOWERS, SKOVLAN_CONSULATE, SKOVLANDER_REFUGEES, SPARKWRIGHTS, SPIRIT_WARDENS, ULF_IRONBORN, UNSEEN, WEEPING_LADY, WHITECROWN, WRAITHS, VULTURES
	}

	public enum Claim {
		LAIR, TURF_1, TURF_2, TURF_3, TURF_4, TURF_5, TURF_6, TRAINING_ROOMS, VICE_DEN, FIXER, INFORMANTS, HAGFISH_FARM, VICTIM_TROPHIES, COVER_OPERATION, PROTECTION_RACKET, INFIRMARY, ENVOY, COVER_IDENTITIES_A, CITY_RECORDS, BARRACKS, TERRORIZED_CITIZENS, FIGHTING_PITS, BLUECOAT_INTIMIDATION, STREET_FENCE, WAREHOUSES, BLUECOAT_CONFEDERATES, CLOISTER, OFFERTORY, ANCIENT_OBELISK, ANCIENT_TOWER, SPIRIT_WELL, ANCIENT_GATE, SANCTUARY, SACRED_NEXUS, ANCIENT_ALTAR, PERSONAL_CLOTHIER, LOCAL_GRAFT, LOOKOUTS, LUXURY_VENUE, FOREIGN_MARKET, SURPLUS_CACHES, COVER_IDENTITIES_B, INTERROGATION_CHAMBER, GAMBLING_DEN, LOYAL_FENCE, TAVERN, DRUG_DEN, COVERT_DROPS, SECRET_PATHWAYS, SIDE_BUSINESS, LUXURY_FENCE, SECRET_ROUTES, FLEET
	}

	public enum Upgrade {
		C2_COHORT_1, C2_COHORT_2, C2_COHORT_3, C2_COHORT_4, //
		BOAT_HOUSE_1, BOAT_HOUSE_2, CARRIAGE_HOUSE_1, CARRIAGE_HOUSE_2, HIDDEN_LAIR, LIVING_QUARTERS, SECURE_LAIR_1, SECURE_LAIR_2, TRAINING_INSIGHT, TRAINING_PROWESS, TRAINING_RESOLVE, TRAINING_PERSONAL, STORAGE_VAULT_1, STORAGE_VAULT_2, WORKSHOP, C4_MASTERY, QUALITY_DOCUMENTS, QUALITY_GEAR, QUALITY_IMPLEMENTS, QUALITY_SUPPLIES, QUALITY_TOOLS, QUALITY_WEAPONS, ELITE_SKULKS, ELITE_THUGS, ELITE_ROVERS, ELITE_ADEPTS, ELITE_ROOKS, ASSASSIN_RIGGING, BRAVOS_RIGGING, CULT_RIGGING, HAWKERS_RIGGING, THIEF_RIGGING, SMUGGLER_RIGGING, IRONHOOK_CONTACTS, C3_HARDENED, C3_COMPOSED, RITUAL_SANCTUM, MAPS_AND_KEYS, CAMOUFLAGE, BARGE
	}

	public enum CohortType {
		ADEPT, ROOK, ROVER, SKULK, THUG, EXPERT
	}

	public enum CohortEdge {
		FEARSOME, INDEPENDENT, LOYAL, TENACIOUS
	}

	public enum CohortFlaw {
		PRINCIPLED, SAVAGE, UNRELIABLE, WILD
	}

	public enum Special {
		PATRON, VETERAN_1, VETERAN_2, VETERAN_3, DEADLY, CROWS_VEIL, EMBERDEATH, NO_TRACES, PREDATORS, VIPERS, DANGEROUS, BLOOD_BROTHERS, DOOR_KICKERS, FIENDS, FORGED_IN_THE_FIRE, WAR_DOGS, CHOSEN, ANNOINTED, BOUND_IN_DARKNESS, CONVICTION, GLORY_INCARNATE, SEALED_IN_BLOOD, ZEALOTRY, SILVER_TONGUES, ACCORD, THE_GOOD_STUFF, GHOST_MARKET, HIGH_SOCIETY, HOOKED, EVERYONE_STEALS, GHOST_ECHOES, PACK_RATS, SECOND_STORY, SLIPPERY, SYNCHRONIZED, LIKE_PART_OF_THE_FAMILY, ALL_HANDS, GHOST_PASSAGE, JUST_PASSING_THROUGH, LEVERAGE, REAVERS, RENEGADES
	}

	/*
	 * STATIC FIELDS
	 * 
	 */
	private static final int MAX_HEAT = 9;

	private static final Special[] ASSASSIN_SPECIALS = { Special.DEADLY, Special.CROWS_VEIL, Special.EMBERDEATH,
			Special.NO_TRACES, Special.PREDATORS, Special.VIPERS, Special.PATRON };

	private static final Special[] BRAVOS_SPECIALS = { Special.DANGEROUS, Special.BLOOD_BROTHERS, Special.DOOR_KICKERS,
			Special.FIENDS, Special.FORGED_IN_THE_FIRE, Special.WAR_DOGS, Special.PATRON };

	private static final Special[] CULT_SPECIALS = { Special.CHOSEN, Special.ANNOINTED, Special.BOUND_IN_DARKNESS,
			Special.CONVICTION, Special.GLORY_INCARNATE, Special.SEALED_IN_BLOOD, Special.ZEALOTRY };

	private static final Special[] HAWKERS_SPECIALS = { Special.SILVER_TONGUES, Special.ACCORD, Special.THE_GOOD_STUFF,
			Special.GHOST_MARKET, Special.HIGH_SOCIETY, Special.HOOKED, Special.PATRON };

	private static final Special[] SHADOWS_SPECIALS = { Special.EVERYONE_STEALS, Special.GHOST_ECHOES,
			Special.PACK_RATS, Special.SECOND_STORY, Special.SLIPPERY, Special.SYNCHRONIZED, Special.PATRON };

	private static final Special[] SMUGGLERS_SPECIALS = { Special.LIKE_PART_OF_THE_FAMILY, Special.ALL_HANDS,
			Special.GHOST_PASSAGE, Special.JUST_PASSING_THROUGH, Special.LEVERAGE, Special.REAVERS, Special.RENEGADES };

	private static final Special[] SKILL_SPECIALS = { Special.DEADLY, Special.DANGEROUS, Special.CHOSEN,
			Special.SILVER_TONGUES, Special.EVERYONE_STEALS, Special.RENEGADES };

	// claims by crew type
	private static final Claim[] ASSASSIN_CLAIMS = { Claim.LAIR, Claim.TRAINING_ROOMS, Claim.VICE_DEN, Claim.FIXER,
			Claim.INFORMANTS, Claim.HAGFISH_FARM, Claim.VICTIM_TROPHIES, Claim.COVER_OPERATION, Claim.PROTECTION_RACKET,
			Claim.INFIRMARY, Claim.ENVOY, Claim.COVER_IDENTITIES_A, Claim.CITY_RECORDS };
	private static final Claim[] BRAVO_CLAIMS = { Claim.LAIR, Claim.INFORMANTS, Claim.INFIRMARY,
			Claim.PROTECTION_RACKET, Claim.BARRACKS, Claim.TERRORIZED_CITIZENS, Claim.FIGHTING_PITS,
			Claim.BLUECOAT_INTIMIDATION, Claim.STREET_FENCE, Claim.WAREHOUSES, Claim.BLUECOAT_CONFEDERATES };
	private static final Claim[] CULT_CLAIMS = { Claim.LAIR, Claim.VICE_DEN, Claim.CLOISTER, Claim.OFFERTORY,
			Claim.ANCIENT_OBELISK, Claim.ANCIENT_TOWER, Claim.SPIRIT_WELL, Claim.ANCIENT_GATE, Claim.SANCTUARY,
			Claim.SACRED_NEXUS, Claim.ANCIENT_ALTAR };
	private static final Claim[] HAWKER_CLAIMS = { Claim.LAIR, Claim.INFORMANTS, Claim.VICE_DEN, Claim.COVER_OPERATION,
			Claim.PERSONAL_CLOTHIER, Claim.LOCAL_GRAFT, Claim.LOOKOUTS, Claim.LUXURY_VENUE, Claim.FOREIGN_MARKET,
			Claim.SURPLUS_CACHES, Claim.COVER_IDENTITIES_B };
	private static final Claim[] SHADOW_CLAIMS = { Claim.LAIR, Claim.GAMBLING_DEN, Claim.INFORMANTS, Claim.LOOKOUTS,
			Claim.HAGFISH_FARM, Claim.INFIRMARY, Claim.INTERROGATION_CHAMBER, Claim.LOYAL_FENCE, Claim.TAVERN,
			Claim.DRUG_DEN, Claim.COVERT_DROPS, Claim.SECRET_PATHWAYS };
	private static final Claim[] SMUGGLER_CLAIMS = { Claim.LAIR, Claim.VICE_DEN, Claim.TAVERN, Claim.ANCIENT_GATE,
			Claim.INFORMANTS, Claim.COVER_OPERATION, Claim.WAREHOUSES, Claim.SIDE_BUSINESS, Claim.LUXURY_FENCE,
			Claim.SECRET_ROUTES, Claim.FLEET };

	// upgrades
	private static final Upgrade[] GENERIC_UPGRADES = { Upgrade.BOAT_HOUSE_1, Upgrade.BOAT_HOUSE_2,
			Upgrade.CARRIAGE_HOUSE_1, Upgrade.CARRIAGE_HOUSE_2, Upgrade.HIDDEN_LAIR, Upgrade.LIVING_QUARTERS,
			Upgrade.SECURE_LAIR_1, Upgrade.SECURE_LAIR_2, Upgrade.TRAINING_INSIGHT, Upgrade.TRAINING_PROWESS,
			Upgrade.TRAINING_RESOLVE, Upgrade.TRAINING_PERSONAL, Upgrade.STORAGE_VAULT_1, Upgrade.STORAGE_VAULT_2,
			Upgrade.WORKSHOP, Upgrade.C4_MASTERY, Upgrade.QUALITY_DOCUMENTS, Upgrade.QUALITY_GEAR,
			Upgrade.QUALITY_IMPLEMENTS, Upgrade.QUALITY_SUPPLIES, Upgrade.QUALITY_TOOLS, Upgrade.QUALITY_WEAPONS };
	private static final Upgrade[] COST_ONE_UPGRADES = { Upgrade.BOAT_HOUSE_1, Upgrade.BOAT_HOUSE_2,
			Upgrade.CARRIAGE_HOUSE_1, Upgrade.CARRIAGE_HOUSE_2, Upgrade.HIDDEN_LAIR, Upgrade.LIVING_QUARTERS,
			Upgrade.SECURE_LAIR_1, Upgrade.SECURE_LAIR_2, Upgrade.TRAINING_INSIGHT, Upgrade.TRAINING_PROWESS,
			Upgrade.TRAINING_RESOLVE, Upgrade.TRAINING_PERSONAL, Upgrade.STORAGE_VAULT_1, Upgrade.STORAGE_VAULT_2,
			Upgrade.WORKSHOP, Upgrade.QUALITY_DOCUMENTS, Upgrade.QUALITY_GEAR, Upgrade.QUALITY_IMPLEMENTS,
			Upgrade.QUALITY_SUPPLIES, Upgrade.QUALITY_TOOLS, Upgrade.QUALITY_WEAPONS };

	// upgrades by crew type
	private static final Upgrade[] COST_ONE_ASSASSIN_UPGRADES = { Upgrade.ASSASSIN_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_SKULKS, Upgrade.ELITE_THUGS };
	private static final Upgrade[] COST_ONE_BRAVOS_UPGRADES = { Upgrade.BRAVOS_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_ROVERS, Upgrade.ELITE_THUGS };
	private static final Upgrade[] COST_ONE_CULT_UPGRADES = { Upgrade.CULT_RIGGING, Upgrade.RITUAL_SANCTUM,
			Upgrade.ELITE_ADEPTS, Upgrade.ELITE_THUGS };
	private static final Upgrade[] COST_ONE_HAWKERS_UPGRADES = { Upgrade.HAWKERS_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_ROOKS, Upgrade.ELITE_THUGS };
	private static final Upgrade[] COST_ONE_SHADOWS_UPGRADES = { Upgrade.THIEF_RIGGING, Upgrade.MAPS_AND_KEYS,
			Upgrade.ELITE_ROOKS, Upgrade.ELITE_SKULKS };
	private static final Upgrade[] COST_ONE_SMUGGLERS_UPGRADES = { Upgrade.SMUGGLER_RIGGING, Upgrade.CAMOUFLAGE,
			Upgrade.ELITE_ROVERS, Upgrade.BARGE };

	private static final Upgrade[] ASSASSIN_UPGRADES = { Upgrade.ASSASSIN_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_SKULKS, Upgrade.ELITE_THUGS, Upgrade.C3_HARDENED };
	private static final Upgrade[] BRAVOS_UPGRADES = { Upgrade.BRAVOS_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_ROVERS, Upgrade.ELITE_THUGS, Upgrade.C3_HARDENED };
	private static final Upgrade[] CULT_UPGRADES = { Upgrade.CULT_RIGGING, Upgrade.RITUAL_SANCTUM, Upgrade.ELITE_ADEPTS,
			Upgrade.ELITE_THUGS, Upgrade.C3_HARDENED };
	private static final Upgrade[] HAWKERS_UPGRADES = { Upgrade.HAWKERS_RIGGING, Upgrade.IRONHOOK_CONTACTS,
			Upgrade.ELITE_ROOKS, Upgrade.ELITE_THUGS, Upgrade.C3_COMPOSED };
	private static final Upgrade[] SHADOWS_UPGRADES = { Upgrade.THIEF_RIGGING, Upgrade.MAPS_AND_KEYS,
			Upgrade.ELITE_ROOKS, Upgrade.ELITE_SKULKS, Upgrade.C3_COMPOSED };
	private static final Upgrade[] SMUGGLERS_UPGRADES = { Upgrade.SMUGGLER_RIGGING, Upgrade.CAMOUFLAGE,
			Upgrade.ELITE_ROVERS, Upgrade.BARGE, Upgrade.C3_COMPOSED };

	// static fields
	private static final Type[] ALL_TYPES = { Type.ASSASSINS, Type.BRAVOS, Type.CULT, Type.HAWKERS, Type.SHADOWS,
			Type.SMUGGLERS };
	private static final Rep[] ALL_REPS = { Rep.AMBITIOUS, Rep.BRUTAL, Rep.DARING, Rep.HONORABLE, Rep.PROFESSIONAL,
			Rep.SAVVY, Rep.SUBTLE, Rep.STRANGE };
	private static final District[] ALL_DISTRICTS = { District.BARROWCLEFT, District.BRIGHTSTONE, District.CHARHOLLOW,
			District.CHARTERHALL, District.COALRIDGE, District.CROWS_FOOT, District.THE_DOCKS, District.DUNSLOUGH,
			District.NIGHTMARKET, District.SILKSHORE, District.SIX_TOWERS, District.WHITECROWN };
	private static final Faction[] ALL_FACTIONS = { Faction.BARROWCLEFT, Faction.BILLHOOKS, Faction.BLUECOATS,
			Faction.BRIGADE, Faction.BRIGHTSTONE, Faction.CABBIES, Faction.CHARHOLLOW, Faction.CHARTERHALL,
			Faction.CHURCH_OF_ECSTASY, Faction.CIRCLE_OF_FLAME, Faction.CITY_COUNCIL, Faction.COALRIDGE, Faction.CROWS,
			Faction.CROWS_FOOT, Faction.CYPHERS, Faction.DAGGER_ISLES_CONSULATE, Faction.DEATHLANDS_SCAVENGERS,
			Faction.DIMMER_SISTERS, Faction.DOCKERS, Faction.DOCKS, Faction.DUNSLOUGH, Faction.FOG_HOUNDS,
			Faction.FORGOTTEN_GODS, Faction.FOUNDATION, Faction.GONDOLIERS, Faction.GRAY_CLOAKS, Faction.GRINDERS,
			Faction.HIVE, Faction.HORDE, Faction.IMPERIAL_MILITARY, Faction.INK_RAKES, Faction.INSPECTORS,
			Faction.IRONHOOK_PRISON, Faction.IRUVIAN_CONSULATE, Faction.LABORERS, Faction.LAMPBLACKS,
			Faction.LEVIATHAN_HUNTERS, Faction.LORD_SCURLOCK, Faction.LOST, Faction.MINISTRY_OF_PRESERVATION,
			Faction.NIGHTMARKET, Faction.PATH_OF_ECHOES, Faction.RAIL_JACKS, Faction.RECONCILED, Faction.RED_SASHES,
			Faction.SAILORS, Faction.SERVANTS, Faction.SEVEROSI_CONSULATE, Faction.SILKSHORE, Faction.SILVER_NAILS,
			Faction.SIX_TOWERS, Faction.SKOVLAN_CONSULATE, Faction.SKOVLANDER_REFUGEES, Faction.SPARKWRIGHTS,
			Faction.SPIRIT_WARDENS, Faction.ULF_IRONBORN, Faction.UNSEEN, Faction.WEEPING_LADY, Faction.WHITECROWN,
			Faction.WRAITHS, Faction.VULTURES };

	//
	private static final Faction[] CITIZENRY = { Faction.BARROWCLEFT, Faction.BRIGHTSTONE, Faction.CHARHOLLOW,
			Faction.CHARTERHALL, Faction.COALRIDGE, Faction.CROWS_FOOT, Faction.DOCKS, Faction.DUNSLOUGH,
			Faction.NIGHTMARKET, Faction.SILKSHORE, Faction.SIX_TOWERS, Faction.WHITECROWN };
	private static final Faction[] INSTITUTIONS = { Faction.BLUECOATS, Faction.BRIGADE, Faction.CITY_COUNCIL,
			Faction.DAGGER_ISLES_CONSULATE, Faction.IMPERIAL_MILITARY, Faction.INSPECTORS, Faction.IRONHOOK_PRISON,
			Faction.IRUVIAN_CONSULATE, Faction.LEVIATHAN_HUNTERS, Faction.MINISTRY_OF_PRESERVATION,
			Faction.SEVEROSI_CONSULATE, Faction.SKOVLAN_CONSULATE, Faction.SPARKWRIGHTS, Faction.SPIRIT_WARDENS };
	private static final Faction[] LABOR_TRADE = { Faction.CABBIES, Faction.CYPHERS, Faction.DOCKERS,
			Faction.FOUNDATION, Faction.GONDOLIERS, Faction.INK_RAKES, Faction.LABORERS, Faction.RAIL_JACKS,
			Faction.SAILORS, Faction.SERVANTS };
	private static final Faction[] THE_FRINGE = { Faction.CHURCH_OF_ECSTASY, Faction.DEATHLANDS_SCAVENGERS,
			Faction.FORGOTTEN_GODS, Faction.HORDE, Faction.PATH_OF_ECHOES, Faction.RECONCILED,
			Faction.SKOVLANDER_REFUGEES, Faction.WEEPING_LADY };
	private static final Faction[] UNDERWORLD = { Faction.BILLHOOKS, Faction.CIRCLE_OF_FLAME, Faction.CROWS,
			Faction.DIMMER_SISTERS, Faction.FOG_HOUNDS, Faction.GRAY_CLOAKS, Faction.GRINDERS, Faction.HIVE,
			Faction.LAMPBLACKS, Faction.LORD_SCURLOCK, Faction.LOST, Faction.RED_SASHES, Faction.SILVER_NAILS,
			Faction.ULF_IRONBORN, Faction.UNSEEN, Faction.WRAITHS, Faction.VULTURES };

	//
	private static ArrayList<Crew> factions;

	// initialization
	static {
		factions = new ArrayList<Crew>();
		factions.add(new Crew(Faction.BARROWCLEFT, 2, true));
		factions.add(new Crew(Faction.BILLHOOKS, 2, false));
		factions.add(new Crew(Faction.BLUECOATS, 3, true));
		factions.add(new Crew(Faction.BRIGADE, 2, true));
		factions.add(new Crew(Faction.BRIGHTSTONE, 4, true));
		factions.add(new Crew(Faction.CABBIES, 2, false));
		factions.add(new Crew(Faction.CHARHOLLOW, 1, true));
		factions.add(new Crew(Faction.CHARTERHALL, 4, true));
		factions.add(new Crew(Faction.CHURCH_OF_ECSTASY, 4, true));
		factions.add(new Crew(Faction.CIRCLE_OF_FLAME, 3, true));
		factions.add(new Crew(Faction.CITY_COUNCIL, 5, true));
		factions.add(new Crew(Faction.COALRIDGE, 2, false));
		factions.add(new Crew(Faction.CROWS, 2, false));
		factions.add(new Crew(Faction.CROWS_FOOT, 2, true));
		factions.add(new Crew(Faction.CYPHERS, 2, true));
		factions.add(new Crew(Faction.DAGGER_ISLES_CONSULATE, 1, true));
		factions.add(new Crew(Faction.DEATHLANDS_SCAVENGERS, 2, false));
		factions.add(new Crew(Faction.DIMMER_SISTERS, 2, true));
		factions.add(new Crew(Faction.DOCKERS, 3, true));
		factions.add(new Crew(Faction.DOCKS, 2, true));
		factions.add(new Crew(Faction.DUNSLOUGH, 1, false));
		factions.add(new Crew(Faction.FOG_HOUNDS, 1, false));
		factions.add(new Crew(Faction.FORGOTTEN_GODS, 3, false));
		factions.add(new Crew(Faction.FOUNDATION, 4, true));
		factions.add(new Crew(Faction.GONDOLIERS, 3, true));
		factions.add(new Crew(Faction.GRAY_CLOAKS, 2, true));
		factions.add(new Crew(Faction.GRINDERS, 2, false));
		factions.add(new Crew(Faction.HIVE, 4, true));
		factions.add(new Crew(Faction.HORDE, 3, true));
		factions.add(new Crew(Faction.IMPERIAL_MILITARY, 6, true));
		factions.add(new Crew(Faction.INK_RAKES, 2, false));
		factions.add(new Crew(Faction.INSPECTORS, 3, true));
		factions.add(new Crew(Faction.IRONHOOK_PRISON, 4, true));
		factions.add(new Crew(Faction.IRUVIAN_CONSULATE, 3, true));
		factions.add(new Crew(Faction.LABORERS, 3, false));
		factions.add(new Crew(Faction.LAMPBLACKS, 2, false));
		factions.add(new Crew(Faction.LEVIATHAN_HUNTERS, 5, true));
		factions.add(new Crew(Faction.LORD_SCURLOCK, 3, true));
		factions.add(new Crew(Faction.LOST, 1, false));
		factions.add(new Crew(Faction.MINISTRY_OF_PRESERVATION, 5, true));
		factions.add(new Crew(Faction.NIGHTMARKET, 2, true));
		factions.add(new Crew(Faction.PATH_OF_ECHOES, 3, true));
		factions.add(new Crew(Faction.RAIL_JACKS, 2, false));
		factions.add(new Crew(Faction.RECONCILED, 3, true));
		factions.add(new Crew(Faction.RED_SASHES, 2, false));
		factions.add(new Crew(Faction.SAILORS, 3, false));
		factions.add(new Crew(Faction.SERVANTS, 2, false));
		factions.add(new Crew(Faction.SEVEROSI_CONSULATE, 1, true));
		factions.add(new Crew(Faction.SILKSHORE, 2, true));
		factions.add(new Crew(Faction.SILVER_NAILS, 3, true));
		factions.add(new Crew(Faction.SIX_TOWERS, 3, false));
		factions.add(new Crew(Faction.SKOVLAN_CONSULATE, 3, false));
		factions.add(new Crew(Faction.SKOVLANDER_REFUGEES, 3, false));
		factions.add(new Crew(Faction.SPARKWRIGHTS, 4, true));
		factions.add(new Crew(Faction.SPIRIT_WARDENS, 4, true));
		factions.add(new Crew(Faction.ULF_IRONBORN, 1, true));
		factions.add(new Crew(Faction.UNSEEN, 4, true));
		factions.add(new Crew(Faction.WEEPING_LADY, 2, true));
		factions.add(new Crew(Faction.WHITECROWN, 5, true));
		factions.add(new Crew(Faction.WRAITHS, 2, false));
		factions.add(new Crew(Faction.VULTURES, 1, false));

	}

	// fields
	private boolean active;
	private ArrayList<Rogue> roster;
	private ArrayList<Rogue> retired;

	private String name;
	private Estate estate;
	private Type type;
	private HashSet<Rep> rep;

	private int tier;
	private boolean holdStrong;
	private int coin;
	private int exp;

	//
	private String lair;
	private HashMap<Claim, Crew> claims;
	private EnumSet<Special> specials;
	private HashMap<Upgrade, Crew> upgrades;
	private int turf;

	//
	private int heat;
	private boolean mostWanted;
	private int wantedLevel;

	private boolean atWar;
	private HashMap<Crew, Integer> shipMap;
	private int[] shipArray;

	/*
	 * Hunting grounds provide +1d6 gather information and a free downtime activity
	 * to contribute to an operation of your preferred type; when you claim turf,
	 * you expand the size and/or type of your hunting grounds
	 */
	private String huntingGrounds;
	//
	private HashSet<Crew> huntingGroundsBoss;
	private int huntingGroundSize;
	private String operation;
	private HashSet<Score.Activity> favoredOps;

	// constructors
	public Crew() {
		// TODO - create additional constructors
		this.active = true;
		this.name = "Default";
		this.type = randomCrewType();
		this.tier = 0;
		this.holdStrong = true;
		this.coin = 2;
		this.exp = 0;

		//
		this.rep = new HashSet<Rep>();
		rep.add(randomReputation());

		//
		this.claims = new HashMap<Claim, Crew>();
		claims.put(Claim.LAIR, this);
		this.specials = EnumSet.noneOf(Special.class);
		this.upgrades = new HashMap<Upgrade, Crew>();
		this.turf = 0;

		//
		this.heat = 0;
		this.mostWanted = false;
		this.wantedLevel = 0;
		this.atWar = false;

		//
		this.huntingGroundSize = 1;
		this.favoredOps = new HashSet<Score.Activity>();
		favoredOps.add(Score.randomActivity(type));

		// setup ships
		shipMap = new HashMap<Crew, Integer>();
		shipArray = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < ALL_FACTIONS.length; ++i) {
			shipMap.put(Crew.getCrewByFaction(ALL_FACTIONS[i]), 0);
		}

		this.huntingGroundsBoss = new HashSet<Crew>();
		ArrayList<Crew> shipSetup = new ArrayList<Crew>();
		while (shipSetup.size() < 3) {
			shipSetup.add(Dice.randomFromList(factions));
		}
		Crew c = shipSetup.get(0);

		//
		huntingGroundsBoss.add(c);
		if (type.equals(Type.ASSASSINS)) {
			specials.add(Dice.randomFromArray(ASSASSIN_SPECIALS));
			upgrades.put(Upgrade.TRAINING_INSIGHT, c);
			upgrades.put(Upgrade.TRAINING_PROWESS, c);
		} else if (type.equals(Type.BRAVOS)) {
			// TODO - additional cohort details
			specials.add(Dice.randomFromArray(BRAVOS_SPECIALS));
			upgrades.put(Upgrade.C2_COHORT_1, c);
			upgrades.put(Upgrade.TRAINING_PROWESS, c);
		} else if (type.equals(Type.CULT)) {
			// TODO - additional cohort details
			specials.add(Dice.randomFromArray(CULT_SPECIALS));
			upgrades.put(Upgrade.C2_COHORT_1, c);
			upgrades.put(Upgrade.TRAINING_RESOLVE, c);
		} else if (type.equals(Type.HAWKERS)) {
			specials.add(Dice.randomFromArray(HAWKERS_SPECIALS));
			upgrades.put(Upgrade.SECURE_LAIR_1, c);
			upgrades.put(Upgrade.TRAINING_RESOLVE, c);
		} else if (type.equals(Type.SHADOWS)) {
			specials.add(Dice.randomFromArray(SHADOWS_SPECIALS));
			upgrades.put(Upgrade.HIDDEN_LAIR, c);
			upgrades.put(Upgrade.TRAINING_PROWESS, c);
		} else if (type.equals(Type.SMUGGLERS)) {
			specials.add(Dice.randomFromArray(SMUGGLERS_SPECIALS));
			upgrades.put(Upgrade.BOAT_HOUSE_1, c);
			upgrades.put(Upgrade.TRAINING_PROWESS, c);
		}

		int dice = Dice.roll(3);
		// hunting grounds
		if (dice == 1) {
			coin -= 1;
		} else if (dice == 2) {
			coin -= 2;
			increaseShip(c);
		} else {
			decreaseShip(c);
		}

		// upgrade one
		c = shipSetup.get(1);
		Upgrade upgrade = randomUpgradeByCrewType(type);
		while (upgrades.containsKey(upgrade)) {
			upgrade = randomUpgradeByCrewType(type);
		}

		dice = Dice.roll(2);
		increaseShip(c);
		upgrades.put(upgrade, c);
		if (dice == 1 && coin > 0) {
			coin -= 1;
			increaseShip(c);
		}

		// upgrade two
		c = shipSetup.get(2);
		while (upgrades.containsKey(upgrade)) {
			upgrade = randomUpgradeByCrewType(type);
		}

		upgrades.put(upgrade, c);
		dice = Dice.roll(2);
		decreaseShip(c);
		if (dice == 1 || coin < 1) {
			decreaseShip(c);
		} else if (coin > 0) {
			coin -= 1;
		}

		this.roster = new ArrayList<Rogue>();
		this.retired = new ArrayList<Rogue>();
		for (int i = Dice.roll(3, 3); i > 0; --i) {
			// starting roster is "3d3" or 3-9 rogues
			roster.add(new Rogue(this));
		}
	}

	public Crew(Faction name, int tier, boolean hold) {
		/*
		 * FIXME - I need to add more to faction initialization, but this works
		 */
		this.active = true;
		this.name = name.toString();
		// this.estate = estate;
		this.tier = tier;
		this.holdStrong = hold;
		//
		this.claims = new HashMap<Claim, Crew>();
		claims.put(Claim.LAIR, this);
	}

	/*
	 * INSTANCE METHODS
	 * 
	 */
	public void advance() {
		boolean canAdvance = true;
		int costToAdvance = (tier + 1) * 8;
		updateTurf();

		if (exp < 12 - turf)
			canAdvance = false;

		ArrayList<Rogue> contributors = new ArrayList<Rogue>(getRoguesWithCoin());
		Rogue.CoinDescending coinSort = new Rogue.CoinDescending();
		Collections.sort(contributors, coinSort);
		int availableCoin = coin;
		for (Iterator<Rogue> it = contributors.iterator(); it.hasNext();) {
			availableCoin += it.next().getCoin();
		}

		// TODO - testing
		// System.out.println("Available coin: " + availableCoin);
		if (availableCoin < costToAdvance)
			canAdvance = false;

		if (canAdvance && holdWeak() && atPeace()) {
			// TODO - testing
			System.out.println("\n - - - - - -");
			System.out.println(toStringBasic());
			System.out.println("IMPROVED CREW HOLD.");

			// advanceHelper
			advanceHelper(costToAdvance, contributors);
			holdStrong = true;
			exp -= 12 - turf;
		} else if (canAdvance && holdStrong()) {
			// TODO - testing
			System.out.println("\n - - - - - -");
			System.out.println(toStringBasic());
			System.out.println("ADVANCED CREW TIER.");

			// advanceHelper
			advanceHelper(costToAdvance, contributors);
			holdStrong = false;
			exp -= 12 - turf;
			++tier;
		}
	}

	private void updateTurf() {
		Claim[] array = new Claim[] { Claim.TURF_1, Claim.TURF_2, Claim.TURF_3, Claim.TURF_4, Claim.TURF_5,
				Claim.TURF_6 };
		int counter = 0;
		for (Claim el : array) {
			if (claims.containsKey(el))
				++counter;
		}

		turf = counter;
	}

	private void advanceHelper(int costToAdvance, List<Rogue> contributors) {
		while (costToAdvance > 0) {
			costToAdvance -= everyoneContribute(costToAdvance, contributors);

			if (costToAdvance > 0 && costToAdvance <= coin) {
				int difference = costToAdvance;
				costToAdvance -= difference;
				coin -= difference;

				// TODO - testing
				// System.out.println("Paid " + difference + " out of crew funds.");
			}
		}
	}

	private int everyoneContribute(int cost, List<Rogue> list) {
		int amountPaid = 0, currentCoin;
		Rogue rogue;
		for (Iterator<Rogue> it = list.iterator(); amountPaid < cost && it.hasNext();) {
			rogue = it.next();
			currentCoin = rogue.getCoin();

			if (currentCoin > 0) {
				rogue.setCoin(currentCoin - 1);
				++amountPaid;
				// TODO - testing
				// System.out.println(rogue + " paid toward crew advance.");
			}
		}

		return amountPaid;
	}

	public void findWork() {
		/*
		 * TODO
		 */
		List<Crew>[] array = updateShipArray();
		Crew client = clientFriendOrSelf(array), target;
		Score.Goal goal;

		if (client.sameAs(this)) {
			// working for self
			target = preferredTarget();

			if (atWar()) {
				// TODO - testing
				goal = Score.Goal.SHAKE;
				target = Dice.randomFromList(this.enemiesList());
			} else {
				goal = Score.Goal.CLAIM;

			}

			// if (tier < 6 && Dice.roll(2) == 1) {
			// goal = Score.Goal.CLIMB;
			//
			// } else {
			// goal = Score.Goal.CLAIM;
			//
			// }
		} else {
			/*
			 * TODO - I should figure out how much of the score a patron can dictate;
			 * whether it's the PLAN, just the ACTIVITY, whether they're going to the
			 * specific crew because of their inherent talents or reputation... what I
			 * should probably come up with a "referral system"
			 */
			// Score.Plan plan = Score.randomPlan();
			// Score.Activity activity = Score.randomActivity();
			target = client.npcRandomEnemyGet();

			if (client.tier < 6 && Dice.roll(2) == 1)
				goal = Score.Goal.ASSIST;
			else
				goal = Score.Goal.SHAKE;

		}

		if (array[6].contains(target) || rep.contains(Rep.HONORABLE)) {
			/*
			 * TODO - refuse to pull job on a friendly
			 */
		}

		// TODO - teams are chosen for each score
		int teamSize = Dice.roll(3) + 2;
		if (teamSize > roster.size())
			teamSize = roster.size();

		ArrayList<Rogue> team = new ArrayList<Rogue>();
		Rogue.StressAscending leastStressed = new Rogue.StressAscending();
		Collections.sort(roster, leastStressed);
		for (Iterator<Rogue> it = roster.iterator(); it.hasNext() && team.size() < teamSize;) {
			team.add(it.next());
		}

		Score score = new Score(this, team, client, target, goal);
		// TODO - testing
		if (client.sameAs(this)) {
			System.out.println("Crew job. (Goal: " + goal + ")");
		} else {
			System.out.println("Job for " + client.toString() + " (Goal: " + goal + ")");
		}
		System.out.println(score.toStringDetailed());
		System.out.println();

		// TODO - determine engagement dice
		int dice = Dice.roll(3);
		score.action(dice);

		Rogue rogue;
		for (Iterator<Rogue> it = team.iterator(); it.hasNext();) {
			rogue = it.next();
			if (rogue.getTrauma().size() > 3) {
				retired.add(rogue);
				roster.remove(rogue);

				// TODO - testing
				System.out.println(rogue + " has retired from the game.");
			}
		}
	}

	public Crew preferredTarget() {
		// ASSASSINS, BRAVOS, CULT, HAWKERS, SHADOWS, SMUGGLERS
		// CITIZENRY, INSTITUTION, LABOR_TRADE, THE_FRINGE, UNDERWORLD
		int[] targets = { 20, 20, 20, 20, 20 };

		/*
		 * ASSASSINS institutions & underworld CULT citizens & fringe HAWKERS citizens &
		 * labor SHADOWS institutions & underworld SMUGGLERS labor & fringe
		 */
		if (type.equals(Type.ASSASSINS)) {
			targets[0] = 10; // citizens
			targets[1] = 35; // institution
			targets[2] = 10; // labor & trade
			targets[3] = 10; // the fringe
			targets[4] = 35; // underworld
		} else if (type.equals(Type.BRAVOS)) {
			targets[0] = 20; // citizens
			targets[1] = 20; // institution
			targets[2] = 20; // labor & trade
			targets[3] = 20; // the fringe
			targets[4] = 20; // underworld
		} else if (type.equals(Type.CULT)) {
			targets[0] = 35; // citizens
			targets[1] = 10; // institution
			targets[2] = 10; // labor & trade
			targets[3] = 35; // the fringe
			targets[4] = 10; // underworld
		} else if (type.equals(Type.HAWKERS)) {
			targets[0] = 35; // citizens
			targets[1] = 10; // institution
			targets[2] = 35; // labor & trade
			targets[3] = 10; // the fringe
			targets[4] = 10; // underworld
		} else if (type.equals(Type.SHADOWS)) {
			targets[0] = 10; // citizens
			targets[1] = 35; // institution
			targets[2] = 10; // labor & trade
			targets[3] = 10; // the fringe
			targets[4] = 35; // underworld
		} else if (type.equals(Type.SMUGGLERS)) {
			targets[0] = 10; // citizens
			targets[1] = 10; // institution
			targets[2] = 35; // labor & trade
			targets[3] = 35; // the fringe
			targets[4] = 10; // underworld
		}

		int dice = Dice.roll(100);
		Faction faction;
		if (dice < 1 + targets[0]) {
			System.out.println(1 + targets[0] + " / " + dice);
			faction = randomCitizenryEnum();
		} else if (dice < 1 + targets[0] + targets[1]) {
			System.out.println(1 + targets[0] + targets[1] + " / " + dice);
			faction = randomInstitutionEnum();
		} else if (dice < 1 + targets[0] + targets[1] + targets[2]) {
			System.out.println(1 + targets[0] + targets[1] + targets[2] + " / " + dice);
			faction = randomLaborTradeEnum();
		} else if (dice < 1 + targets[0] + targets[1] + targets[2] + targets[3]) {
			System.out.println(1 + targets[0] + targets[1] + targets[2] + targets[3] + " / " + dice);
			faction = randomFringeEnum();
		} else {
			System.out.println(100 + " / " + dice);
			faction = randomUnderworldEnum();
		}

		return getCrewByFaction(faction);
	}

	public Crew clientFriendOrSelf() {
		List<Crew>[] array = updateShipArray();
		return clientFriendOrSelf(array);
	}

	public Crew clientFriendOrSelf(List<Crew>[] array) {
		Crew client;
		int[] obligations = new int[] { 0, 0, 0, 0, 0, 0, 0 };

		if (shipArray[0] > 0)
			obligations[0] = 20;

		if (shipArray[1] > 0)
			obligations[1] = 15;

		if (shipArray[2] > 0)
			obligations[2] = 10;

		if (shipArray[4] > 0)
			obligations[4] = 15;

		if (shipArray[5] > 0)
			obligations[5] = 10;

		if (shipArray[6] > 0)
			obligations[6] = 5;

		int totalObs = obligations[0] + obligations[1] + obligations[2] + obligations[4] + obligations[5]
				+ obligations[6];
		if (totalObs < 31)
			obligations[3] = 60 - totalObs;
		else if (totalObs < 61)
			obligations[3] = 75 - totalObs;

		// TODO - for testing
		// for (int el : obligations) {
		// System.out.println("Ship# " + el);
		// }

		int dice = Dice.roll(100);
		Crew faction;
		if (dice < obligations[0]) {
			faction = Dice.randomFromList(array[6]);
		} else if (dice < obligations[0] + obligations[1]) {
			faction = Dice.randomFromList(array[5]);
		} else if (dice < obligations[0] + obligations[1] + obligations[2]) {
			faction = Dice.randomFromList(array[4]);
		} else if (dice < obligations[0] + obligations[1] + obligations[2] + obligations[3]) {
			faction = Dice.randomFromList(array[3]);
		} else if (dice < obligations[0] + obligations[1] + obligations[2] + obligations[3] + obligations[4]) {
			faction = Dice.randomFromList(array[2]);
		} else if (dice < obligations[0] + obligations[1] + obligations[2] + obligations[3] + obligations[4]
				+ obligations[5]) {
			faction = Dice.randomFromList(array[1]);
		} else if (dice < obligations[0] + obligations[1] + obligations[2] + obligations[3] + obligations[4]
				+ obligations[5] + obligations[6]) {
			faction = Dice.randomFromList(array[0]);
		} else {
			return this;
		}

		// client = Crew.getCrewByFaction(faction);
		return faction;
	}

	public List<Rogue> getRoguesWithCoin() {
		ArrayList<Rogue> list = new ArrayList<Rogue>();
		Rogue rogue;
		for (Iterator<Rogue> it = roster.iterator(); it.hasNext();) {
			rogue = it.next();
			if (rogue.getCoin() > 0)
				list.add(rogue);
		}

		return list;
	}

	public boolean increaseShip(Crew crew) {
		boolean increased = false;
		int v = shipMap.get(crew);

		if (v < 3) {
			shipMap.put(crew, v + 1);
			increased = true;
		}

		checkAtWar();
		return increased;
	}

	public boolean decreaseShip(Crew crew) {
		boolean decreased = false;
		int v = shipMap.get(crew);

		if (v > -3) {
			shipMap.put(crew, v - 1);
			decreased = true;
		}

		checkAtWar();
		return decreased;
	}

	private void checkAtWar() {
		atWar = (shipMap.values().contains(-3)) ? true : false;
	}

	public String getName() {
		return name;
	}

	public Type getCrewType() {
		return type;
	}

	public Set<Rep> getReputation() {
		return rep;
	}

	public int getTier() {
		int crewTier = tier;
		if (atWar && holdStrong != true)
			crewTier = tier - 1;

		return crewTier;
	}

	public void addEXP(int gains) {
		exp = (exp + gains > 12) ? 12 : exp + gains;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public void addCoin(int gains) {
		// TODO - do I need to do something else with this?
		coin += gains;
	}

	public boolean holdStrong() {
		boolean isHoldStrong = holdStrong;

		if (atWar)
			isHoldStrong = false;
		else if (atWar && holdStrong != true)
			isHoldStrong = true;

		return isHoldStrong;
	}

	public boolean holdWeak() {
		boolean isHoldWeak = (holdStrong != true);

		if (holdStrong && atWar)
			isHoldWeak = true;
		else if (atWar && holdStrong != true)
			isHoldWeak = false;

		return isHoldWeak;
	}

	public void strengthenHold() {
		// TODO - testing
		if (holdStrong() && atPeace()) {
			++tier;
			holdStrong = false;
		} else if (atWar && holdStrong) {
			++tier;
			holdStrong = false;
		} else if (atWar && holdStrong != true) {
			holdStrong = true;
		} else if (holdStrong != true) {
			holdStrong = true;
		}
	}

	public void weakenHold() {
		// TODO - testing
		if (holdStrong) {
			holdStrong = false;
		} else if (tier == 0 && holdStrong != true) {
			// TODO - testing
			System.out.println("The faction " + this.name + " has been destroyed.");
			active = false;
		} else if (holdStrong != true) {
			--tier;
		}
	}

	public boolean isActive() {
		return active;
	}

	public boolean isInactive() {
		return (active != true);
	}

	public boolean atWar() {
		return atWar;
	}

	public boolean atPeace() {
		return (atWar != true);
	}

	public int getHeat() {
		return heat;
	}

	public void setHeat(int heat) {
		this.heat = heat;

		// TODO
		if (heat >= MAX_HEAT)
			mostWanted = true;
	}

	public int getWantedLevel() {
		return wantedLevel;
	}

	public void setWantedLevel(int wantedLevel) {
		this.wantedLevel = wantedLevel;
	}

	public void resolveHeat() {
		if (heat >= MAX_HEAT) {
			heat -= MAX_HEAT;
			mostWanted = false;

			++wantedLevel;
			// TODO - testing
			System.out.println("Wanted level rose to " + wantedLevel);
		}
	}

	public Set<Crew> getNonZeroShips() {
		HashSet<Crew> nonZeroShips = new HashSet<Crew>();

		Crew f;
		int v;
		for (Iterator<Crew> it = shipMap.keySet().iterator(); it.hasNext();) {
			f = it.next();
			v = shipMap.get(f);
			if (f.active && v != 0)
				nonZeroShips.add(f);
		}

		return nonZeroShips;
	}

	public Set<Crew> npcAllyGet() {
		return Ship.crewAllySet(this);
	}

	public Set<Crew> npcEnemyGet() {
		return Ship.crewEnemySet(this);
	}

	public Crew npcRandomEnemyGet() {
		Crew choice;
		if (npcEnemyGet().size() > 0)
			choice = Dice.randomFromSet(npcEnemyGet());
		else {
			choice = Dice.randomFromList(getActiveFactions());
			while (choice.sameAs(this)) {
				choice = Dice.randomFromList(getActiveFactions());
			}
		}

		return choice;
	}

	public HashMap<Claim, Crew> getClaims() {
		return claims;
	}

	public boolean sameAs(Crew other) {
		boolean equals = false;
		if (other.name.compareTo(this.name) == 0)
			equals = true;

		return equals;
	}

	public boolean notSameAs(Crew other) {
		boolean equals = true;
		if (other.name.compareTo(this.name) == 0)
			equals = false;

		return equals;
	}

	public EnumSet<Special> getSpecials() {
		return specials;
	}

	public Set<Upgrade> upgradeSet() {
		return upgrades.keySet();
	}

	public EnumSet<Special> containsSkillSpecials() {
		Special[] array = SKILL_SPECIALS;

		EnumSet<Special> set = EnumSet.noneOf(Special.class);
		for (int i = 0; i < array.length; ++i) {
			if (specials.contains(array[i]))
				set.add(array[i]);
		}

		return set;
	}

	@Override
	public String toString() {
		return name;
	}

	public String toStringBasic() {
		String string = String.format("name %s %s %ntier: %2d || rep: %2d (turf: %d) || coin: %3d || Strong hold: %s",
				rep.toString(), type.toString(), tier, exp, turf, coin, holdStrong());

		return string;
	}

	public String toStringDetailed() {
		Set<Crew> set = getNonZeroShips();

		String shipList = "";
		Iterator<Crew> it = set.iterator();
		Crew crew;
		int status;
		String name;
		for (int i = 0; i < set.size(); ++i) {
			crew = it.next();
			name = crew.nameOnly();
			status = shipMap.get(crew);
			name = String.format("%2d %s", status, name);
			shipList += (i + 1 < set.size()) ? name + "\n" : name;
		}

		String upgradeList = upgrades.toString();

		String string = (atWar()) ? String.format(" - AT WAR WITH: %s%n", enemiesList().toString()) : "";
		// string = String.format("name %s %s coin: %2d %n%s", rep.toString(),
		// type.toString(), coin, shipList);
		string += String.format(
				"name %s %s || Crew size: %d %n%s %ntier: %2d || rep: %2d (turf: %d) || coin: %3d || Strong hold: %s %n%s %n%s",
				rep.toString(), type.toString(), roster.size(), roster.toString(), tier, exp, turf, coin, holdStrong(),
				specials.toString(), upgradeList);

		return string;
	}

	public String simplifiedToString() {
		String string = String.format("%s (tier %d)", name, tier);

		return string;
	}

	public String nameOnly() {
		return name;
	}

	public List<Crew>[] allFactionStatus() {
		List<Crew>[] array = (List<Crew>[]) new ArrayList[7];
		for (int i = 0; i < array.length; ++i) {
			array[i] = new ArrayList<Crew>();
		}

		Set<Crew> set = new HashSet<Crew>(shipMap.keySet());
		Crew crew;
		for (Iterator<Crew> it = set.iterator(); it.hasNext();) {
			crew = it.next();
			if (crew.active)
				array[shipMap.get(crew) + 3].add(crew);
		}

		return array;
	}

	public List<Crew>[] nonNeutralStatus() {
		List<Crew>[] array = (List<Crew>[]) new ArrayList[6];
		for (int i = 0; i < array.length; ++i) {
			array[i] = new ArrayList<Crew>();
		}

		Set<Crew> set = new HashSet<Crew>(shipMap.keySet());
		Crew crew;
		int status;
		for (Iterator<Crew> it = set.iterator(); it.hasNext();) {
			crew = it.next();
			status = shipMap.get(crew);

			if (crew.active && status + 3 < 3) {
				array[status + 3].add(crew);
			} else if (crew.active && status + 3 > 3) {
				array[status + 2].add(crew);
			}
		}

		return array;
	}

	public List<Crew>[] updateShipArray() {
		// must update shipArray object in REVERSE order because a faction status equals
		// "status +3" to offset -3 "at war"
		List<Crew>[] array = allFactionStatus();

		for (int i = 0; i < shipArray.length; ++i) {
			shipArray[6 - i] = array[i].size();
		}

		return array;
	}

	public List<Crew> alliesList() {
		List<Crew> list = new ArrayList<Crew>();
		Set<Crew> set = new HashSet<Crew>(shipMap.keySet());
		Crew crew;
		for (Iterator<Crew> it = set.iterator(); it.hasNext();) {
			crew = it.next();
			if (crew.active && shipMap.get(crew) > 2)
				list.add(crew);
		}

		return list;
	}

	public List<Crew> friendliesList() {
		List<Crew> list = new ArrayList<Crew>();
		Set<Crew> set = new HashSet<Crew>(shipMap.keySet());
		Crew crew;
		for (Iterator<Crew> it = set.iterator(); it.hasNext();) {
			crew = it.next();
			if (crew.active && shipMap.get(crew) > 0)
				list.add(crew);
		}

		return list;
	}

	public List<Crew> hostilesList() {
		List<Crew> list = new ArrayList<Crew>();
		Set<Crew> set = new HashSet<Crew>(shipMap.keySet());
		Crew crew;
		for (Iterator<Crew> it = set.iterator(); it.hasNext();) {
			crew = it.next();
			if (crew.active && shipMap.get(crew) < 0)
				list.add(crew);
		}

		return list;
	}

	public List<Crew> enemiesList() {
		List<Crew> list = new ArrayList<Crew>();
		Set<Crew> set = new HashSet<Crew>(shipMap.keySet());
		Crew crew;
		for (Iterator<Crew> it = set.iterator(); it.hasNext();) {
			crew = it.next();
			if (crew.active && shipMap.get(crew) < -2)
				list.add(crew);
		}

		return list;
	}

	// static methods
	public static List<Crew> getFactions() {
		return factions;
	}

	public static List<Crew> getActiveFactions() {
		List<Crew> activeFactions = new ArrayList<Crew>();
		Crew candidate;
		for (Iterator<Crew> it = factions.iterator(); it.hasNext();) {
			candidate = it.next();
			if (candidate.active)
				activeFactions.add(candidate);
		}

		return activeFactions;
	}

	public static Crew getCrewByFaction(Faction faction) {
		int length = factions.size();

		String name;
		Crew crew = null;
		for (int i = 0; i < length; ++i) {
			crew = factions.get(i);
			name = crew.name.toString();
			if (name.endsWith(faction.toString())) {
				break;
			}
		}

		return crew;
	}

	public static Faction getFactionNameByString(String string) {
		Faction[] array = ALL_FACTIONS;
		Faction faction = null;

		for (int i = 0; i < array.length; ++i) {
			// ignore "Faction."
			if (array[i].toString().endsWith(string))
				faction = array[i];
		}

		return faction;
	}

	public static Crew randomFaction() {
		Crew choice = Dice.randomFromList(factions);
		// int length = factions.size();
		// Crew[] array = new Crew[length];
		// for (int i = 0; i < length; ++i) {
		// array[i] = factions.get(i);
		// }
		//
		// Crew choice = array[Dice.roll(array.length) - 1];
		return choice;
	}

	public static Faction randomFactionEnum() {
		return Dice.randomFromArray(ALL_FACTIONS);
	}

	public static Faction randomCitizenryEnum() {
		return Dice.randomFromArray(CITIZENRY);
	}

	public static Faction randomInstitutionEnum() {
		return Dice.randomFromArray(INSTITUTIONS);
	}

	public static Faction randomLaborTradeEnum() {
		return Dice.randomFromArray(LABOR_TRADE);
	}

	public static Faction randomFringeEnum() {
		return Dice.randomFromArray(THE_FRINGE);
	}

	public static Faction randomUnderworldEnum() {
		return Dice.randomFromArray(UNDERWORLD);
	}

	public static Type randomCrewType() {
		return Dice.randomFromArray(ALL_TYPES);
	}

	public static District randomDistrict() {
		return Dice.randomFromArray(ALL_DISTRICTS);
	}

	public static Rep randomReputation() {
		return Dice.randomFromArray(ALL_REPS);
	}

	public static Upgrade randomUpgradeByCrewType(Type type) {
		Upgrade[] array = GENERIC_UPGRADES;
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

	public static Upgrade randomUpgrade() {
		return Dice.randomFromArray(COST_ONE_UPGRADES);
	}

	public static Upgrade randomUpgradeByCrew(Type type) {
		Upgrade upgrade = null;

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

	public static Upgrade randomAssassinUpgrade() {
		return Dice.randomFromArray(COST_ONE_ASSASSIN_UPGRADES);
	}

	public static Upgrade randomBravosUpgrade() {
		return Dice.randomFromArray(COST_ONE_BRAVOS_UPGRADES);
	}

	public static Upgrade randomCultUpgrade() {
		return Dice.randomFromArray(COST_ONE_CULT_UPGRADES);
	}

	public static Upgrade randomHawkerUpgrade() {
		return Dice.randomFromArray(COST_ONE_HAWKERS_UPGRADES);
	}

	public static Upgrade randomShadowUpgrade() {
		return Dice.randomFromArray(COST_ONE_SHADOWS_UPGRADES);
	}

	public static Upgrade randomSmugglerUpgrade() {
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
		Claim turf = Claim.TURF_1;

		if (claims.containsKey(Claim.TURF_5))
			turf = Claim.TURF_6;
		else if (claims.containsKey(Claim.TURF_4))
			turf = Claim.TURF_5;
		else if (claims.containsKey(Claim.TURF_3))
			turf = Claim.TURF_4;
		else if (claims.containsKey(Claim.TURF_2))
			turf = Claim.TURF_3;
		else if (claims.containsKey(Claim.TURF_1))
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

	/*
	 * COHORTS - INNER CLASS
	 */

	private class Cohort {
		CohortType type;

		Cohort(CohortType type) {
			this.type = type;
		}

		boolean isExpert() {
			return (type.equals(CohortType.EXPERT));
		}

		boolean isGang() {
			return (type.equals(CohortType.EXPERT) != true);
		}
	}

	/*
	 * COMPARATOR - INNER CLASSES
	 */
	class TiersDescending implements Comparator<Crew> {
		@Override
		public int compare(Crew crew1, Crew crew2) {
			return crew1.tier - crew2.tier;
		}
	}
}
