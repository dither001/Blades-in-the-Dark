import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
	private static Map<NamedFaction, Gang> gangs;

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
		gangs = new HashMap<NamedFaction, Gang>();

		//
		for (NamedFaction el : FACTION_ADD_ORDER)
			gangs.put(el, new Gang(el.toString()));

		//
		for (Iterator<Gang> it = gangs.values().iterator(); it.hasNext();) {
			it.next().neighborSetup();
		}

	}

	/*
	 * STATIC METHODS
	 */
	public static Collection<Gang> getGangs() {
		return gangs.values();
	}

	public static List<Gang> orderedGangList() {
		List<Gang> list = new ArrayList<Gang>(gangs.values());

		class Sort implements Comparator<Gang> {
			@Override
			public int compare(Gang left, Gang right) {
				return left.gangID - right.gangID;
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
	private int level;
	private int coin;
	private int experience;
	private Locale lair;

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
		this.level = 1;
		this.coin = 2;
		this.experience = 0;

		//
		this.ships = new HashSet<Ship>();
		this.atWar = false;
		this.heat = 0;
		this.wantedLevel = 0;

		//
		this.lair = cluster.findVacancy(Dice.roll(6) - 1);
		lair.addBuilding(this);
	}

	@Override
	public void report(Locale locale) {
		// TODO Auto-generated method stub

	}

	private void neighborSetup() {
		for (Iterator<Faction> it = lair.residents().iterator(); it.hasNext();) {
			Ship.addShip(this, it.next());
		}

		setShips(Ship.getShips(this));
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
		String string = String.format("%s (%s)", name, type);

		return string + toStringShips();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (o instanceof Gang != true)
			return false;

		Gang g = (Gang) o;
		// System.out.println(String.format("Compare %s and %s (%s)", toString(),
		// g.toString(), g.gangID == this.gangID));

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
