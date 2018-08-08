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

	public int getLevel();

	public void setLevel(int level);

	public int getCoin();

	public void setCoin(int coin);

	public int getExperience();

	public void setExperience(int experience);

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
		Score.Plan plan;
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
