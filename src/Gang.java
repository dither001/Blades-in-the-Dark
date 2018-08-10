import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Gang implements Faction, Stakeholder {
	/*
	 * STATIC FIELDS
	 */
	private static int lifetimeGangs;

	public static Locale.Cluster cluster;
	private static Map<NamedFaction, Faction> gangs;

	private static NamedFaction[] FACTION_ADD_ORDER = { NamedFaction.IMPERIAL_MILITARY, NamedFaction.CITY_COUNCIL,
			NamedFaction.LEVIATHAN_HUNTERS, NamedFaction.MINISTRY_OF_PRESERVATION, NamedFaction.WHITECROWN,
			NamedFaction.BRIGHTSTONE, NamedFaction.CHARTERHALL, NamedFaction.CHURCH_OF_ECSTASY, NamedFaction.FOUNDATION,
			NamedFaction.HIVE, NamedFaction.IRONHOOK_PRISON, NamedFaction.SPARKWRIGHTS, NamedFaction.SPIRIT_WARDENS,
			NamedFaction.UNSEEN, NamedFaction.BLUECOATS, NamedFaction.CIRCLE_OF_FLAME, NamedFaction.DOCKERS,
			NamedFaction.FORGOTTEN_GODS, NamedFaction.GONDOLIERS, NamedFaction.HORDE, NamedFaction.INSPECTORS,
			NamedFaction.IRUVIAN_CONSULATE, NamedFaction.LABORERS, NamedFaction.LORD_SCURLOCK,
			NamedFaction.PATH_OF_ECHOES, NamedFaction.RECONCILED, NamedFaction.SAILORS, NamedFaction.SILVER_NAILS,
			NamedFaction.SIX_TOWERS, NamedFaction.SKOVLAN_CONSULATE, NamedFaction.SKOVLANDER_REFUGEES,
			NamedFaction.BARROWCLEFT, NamedFaction.BILLHOOKS, NamedFaction.BRIGADE, NamedFaction.CABBIES,
			NamedFaction.COALRIDGE, NamedFaction.CROWS, NamedFaction.CROWS_FOOT, NamedFaction.CYPHERS,
			NamedFaction.DEATHLANDS_SCAVENGERS, NamedFaction.DIMMER_SISTERS, NamedFaction.DOCKS,
			NamedFaction.GRAY_CLOAKS, NamedFaction.GRINDERS, NamedFaction.INK_RAKES, NamedFaction.LAMPBLACKS,
			NamedFaction.NIGHTMARKET, NamedFaction.RAIL_JACKS, NamedFaction.RED_SASHES, NamedFaction.SERVANTS,
			NamedFaction.SILKSHORE, NamedFaction.WEEPING_LADY, NamedFaction.WRAITHS, NamedFaction.CHARHOLLOW,
			NamedFaction.DAGGER_ISLES_CONSULATE, NamedFaction.DUNSLOUGH, NamedFaction.FOG_HOUNDS, NamedFaction.LOST,
			NamedFaction.SEVEROSI_CONSULATE, NamedFaction.ULF_IRONBORN, NamedFaction.VULTURES };

	/*
	 * INITIALIZATION
	 */
	static {
		lifetimeGangs = 0;
		cluster = Locale.cluster();
		gangs = new HashMap<NamedFaction, Faction>();

		// creates the faction set
		for (NamedFaction el : FACTION_ADD_ORDER)
			gangs.put(el, new Gang(el.toString()));

		// initializes neighbor ships
		Gang gang;
		for (Iterator<Faction> it = gangs.values().iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.neighborSetup();
		}

		// claims turf
		for (Iterator<Faction> it = gangs.values().iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.factionSetup();
		}

		// finds and assigns ships to each faction
		for (Iterator<Faction> it = gangs.values().iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.setShips(Ship.getShips(gang));
		}

		// initializes upgrades
		for (Iterator<Faction> it = gangs.values().iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.upgradeSetup();
		}

		for (Iterator<Faction> it = gangs.values().iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.rosterSetup();
		}

	}

	/*
	 * STATIC METHODS
	 */
	public static Collection<Faction> getGangs() {
		return gangs.values();
	}

	public static List<Faction> orderedGangList() {
		List<Faction> list = new ArrayList<Faction>(gangs.values());

		class Sort implements Comparator<Faction> {
			@Override
			public int compare(Faction left, Faction right) {
				Gang leftGang = (Gang) left, rightGang = (Gang) right;

				return leftGang.gangID - rightGang.gangID;
			}
		}

		Collections.sort(list, new Sort());

		return list;
	}

	/*
	 * INSTANCE FIELDS
	 */
	private boolean active;
	private int gangID;
	private String name;
	private Set<Rep> reputation;
	private Type type;

	//
	private Set<Rogue> roster;

	//
	private int level;
	private int coin;
	private int experience;
	private EnumSet<Special> specials;
	private Map<Upgrade, Faction> upgrades;

	//
	private Locale lair;
	private Set<Locale> turf;

	//
	private Set<Ship> ships;
	private boolean atWar;
	private int heat;
	private int wantedLevel;

	/*
	 * CONSTRUCTORS
	 */
	public Gang(String name) {
		this.active = true;
		this.gangID = lifetimeGangs++;
		this.name = name;
		this.reputation = new HashSet<Rep>();
		this.type = Faction.randomCrewType();

		//
		this.roster = new HashSet<Rogue>();

		//
		this.level = 1;
		this.coin = 2;
		this.experience = 0;
		this.specials = EnumSet.noneOf(Special.class);
		this.upgrades = new HashMap<Upgrade, Faction>();

		//
		this.lair = cluster.findVacancy(Dice.roll(6) - 1);
		lair.addBuilding(this);
		this.turf = new HashSet<Locale>();

		//
		this.ships = new HashSet<Ship>();
		this.atWar = false;
		this.heat = 0;
		this.wantedLevel = 0;

	}

	@Override
	public void report(Locale locale) {
		// TODO Auto-generated method stub

	}

	private void neighborSetup() {
		int dice;
		for (Iterator<Faction> it = lair.residents().iterator(); it.hasNext();) {
			dice = Dice.roll(3) - 2;
			Ship.addShip(this, it.next(), dice);

		}
	}

	private void factionSetup() {
		Set<Locale> workingSet = new HashSet<Locale>();
		workingSet.add(lair);

		//
		Locale startingTurf = cluster.findStake(workingSet);

		if (startingTurf != null) {
			turf.add(startingTurf);
			startingTurf.addStake(this);

			Faction localBoss = startingTurf.enmityClause(this);
			Ship ship = Ship.get(this, localBoss);

			int dice = Dice.roll(4), score = ship.getScore();
			if (dice == 1 && coin > 1) {
				transferCoinTo(2, localBoss);
				ship.setScore(score + 1);

			} else if (dice == 2 && coin > 0) {
				transferCoinTo(1, localBoss);
				ship.setScore(score);

			} else {
				ship.setScore(score - 1);

			}
		}

		//
	}

	private void upgradeSetup() {
		Faction faction = Dice.randomFromSet(ships).getOther(this);

		if (type.equals(Type.ASSASSINS)) {
			specials.add(Dice.randomFromArray(ASSASSIN_SPECIALS));
			upgrades.put(Faction.Upgrade.TRAINING_INSIGHT, faction);
			upgrades.put(Faction.Upgrade.TRAINING_PROWESS, faction);

		} else if (type.equals(Type.BRAVOS)) {
			// TODO - additional cohort details
			specials.add(Dice.randomFromArray(BRAVOS_SPECIALS));
			upgrades.put(Faction.Upgrade.C2_COHORT_1, faction);
			upgrades.put(Faction.Upgrade.TRAINING_PROWESS, faction);

		} else if (type.equals(Type.CULT)) {
			// TODO - additional cohort details
			specials.add(Dice.randomFromArray(CULT_SPECIALS));
			upgrades.put(Faction.Upgrade.C2_COHORT_1, faction);
			upgrades.put(Faction.Upgrade.TRAINING_RESOLVE, faction);

		} else if (type.equals(Type.HAWKERS)) {
			specials.add(Dice.randomFromArray(HAWKERS_SPECIALS));
			upgrades.put(Faction.Upgrade.SECURE_LAIR_1, faction);
			upgrades.put(Faction.Upgrade.TRAINING_RESOLVE, faction);

		} else if (type.equals(Type.SHADOWS)) {
			specials.add(Dice.randomFromArray(SHADOWS_SPECIALS));
			upgrades.put(Faction.Upgrade.HIDDEN_LAIR, faction);
			upgrades.put(Faction.Upgrade.TRAINING_PROWESS, faction);

		} else if (type.equals(Type.SMUGGLERS)) {
			specials.add(Dice.randomFromArray(SMUGGLERS_SPECIALS));
			upgrades.put(Faction.Upgrade.BOAT_HOUSE_1, faction);
			upgrades.put(Faction.Upgrade.TRAINING_PROWESS, faction);

		}

		// upgrade one
		faction = Dice.randomFromSet(ships).getOther(this);
		Ship ship = Ship.get(this, faction);
		int score = ship.getScore();

		Upgrade upgrade = Faction.randomUpgradeByCrewType(type);
		while (upgrades.containsKey(upgrade)) {
			upgrade = Faction.randomUpgradeByCrewType(type);
		}

		upgrades.put(upgrade, faction);
		if (coin > 0 && score < 2 && Dice.roll(2) == 1) {
			transferCoinTo(1, faction);
			ship.setScore(score + 2);

		} else {
			ship.setScore(score + 1);

		}

		// upgrade two
		faction = Dice.randomFromSet(ships).getOther(this);
		ship = Ship.get(this, faction);
		score = ship.getScore();

		while (upgrades.containsKey(upgrade)) {
			upgrade = Faction.randomUpgradeByCrewType(type);
		}

		upgrades.put(upgrade, faction);
		if (coin > 0 && score > -2 && Dice.roll(2) == 1) {
			transferCoinTo(1, faction);
			ship.setScore(score - 1);

		} else {
			ship.setScore(score - 2);

		}
	}

	public void rosterSetup() {
		for (int i = Dice.roll(3, 3); i > 0; --i) {

			// starting roster is "3d3" or 3-9 rogues
			roster.add(new Rogue(this));
		}
	}

	@Override
	public String toString() {
		// String string = String.format("%s (%2d)", name, gangID);

		return name;
	}

	private String toStringShips() {
		String string = "\n";

		for (Iterator<Ship> it = ships.iterator(); it.hasNext();) {
			string += it.next().toString(this);

			if (it.hasNext())
				string += ", ";
		}

		return string;
	}

	public String toStringDetailed() {
		String string = String.format("%s (%s) || Coin: %d", name, type, coin);

		String holding = String.format("\nLair: %s || Turf: %s", lair, turf.toString());
		String rogues = String.format("\n%s", roster.toString());
		String special = String.format("\n%s", specials.toString());
		String upgrade = String.format("\n%s", upgrades.toString());

		// String locals = "\nLocals: ";
		// locals += lair.residents().toString();

		// String residents = "\nResidents: ";
		// for (Locale el : turf)
		// residents += el.residents().toString();

		// return string + holding + special + upgrade + toStringShips();
		return string + rogues + toStringShips();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o instanceof Gang != true)
			return false;

		Gang g = (Gang) o;
		return g.gangID == this.gangID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gangID);
	}

	/*
	 * 
	 */
	@Override
	public int factionID() {
		return gangID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Set<Rep> getReputation() {
		return reputation;
	}

	@Override
	public void setReputation(Set<Rep> reputation) {
		this.reputation = reputation;
	}

	@Override
	public Type crewType() {
		return type;
	}

	@Override
	public void setCrewType(Type type) {
		this.type = type;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public int getCoin() {
		return coin;
	}

	@Override
	public void setCoin(int coin) {
		this.coin = coin;
	}

	@Override
	public int getExperience() {
		return experience;
	}

	@Override
	public void setExperience(int experience) {
		this.experience = experience;
	}

	@Override
	public EnumSet<Special> getSpecials() {
		return specials;
	}

	@Override
	public void setSpecials(EnumSet<Special> specials) {
		this.specials = specials;
	}

	@Override
	public Set<Upgrade> upgradeSet() {
		return upgrades.keySet();
	}

	@Override
	public int getHeat() {
		return heat;
	}

	@Override
	public void setHeat(int heat) {
		this.heat = heat;
	}

	@Override
	public int getWantedLevel() {
		return wantedLevel;
	}

	@Override
	public void setWantedLevel(int wantedLevel) {
		this.wantedLevel = wantedLevel;
	}

	@Override
	public boolean active() {
		return active;
	}

	@Override
	public boolean inactive() {
		return active != true;
	}

	@Override
	public void activate() {
		this.active = true;
	}

	@Override
	public void deactivate() {
		this.active = false;
	}

	@Override
	public boolean atWar() {
		return atWar;
	}

	@Override
	public boolean atPeace() {
		return atWar != true;
	}

	@Override
	public Set<Ship> getShips() {
		return ships;
	}

	@Override
	public void setShips(Set<Ship> ships) {
		this.ships = ships;
	}

}
