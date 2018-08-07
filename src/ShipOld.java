import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class ShipOld {
	// static fields
	private static ShipSet ships;

	static {
		ships = new ShipSet();
		ships.add(new ShipOld(Crew.NamedFaction.BLUECOATS, Crew.NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(Crew.NamedFaction.BLUECOATS, Crew.NamedFaction.BILLHOOKS, true));
		ships.add(new ShipOld(Crew.NamedFaction.BLUECOATS, Crew.NamedFaction.CROWS, true));
		ships.add(new ShipOld(Crew.NamedFaction.BLUECOATS, Crew.NamedFaction.IRONHOOK_PRISON, true));
		ships.add(new ShipOld(Crew.NamedFaction.BLUECOATS, Crew.NamedFaction.LORD_SCURLOCK, true));
		ships.add(new ShipOld(Crew.NamedFaction.BLUECOATS, Crew.NamedFaction.UNSEEN, true));
		ships.add(new ShipOld(Crew.NamedFaction.BLUECOATS, Crew.NamedFaction.IMPERIAL_MILITARY, false));
		ships.add(new ShipOld(Crew.NamedFaction.CITY_COUNCIL, Crew.NamedFaction.BLUECOATS, true));
		ships.add(new ShipOld(Crew.NamedFaction.CITY_COUNCIL, Crew.NamedFaction.CHURCH_OF_ECSTASY, true));
		ships.add(new ShipOld(Crew.NamedFaction.CITY_COUNCIL, Crew.NamedFaction.CIRCLE_OF_FLAME, true));
		ships.add(new ShipOld(Crew.NamedFaction.CITY_COUNCIL, Crew.NamedFaction.LORD_SCURLOCK, true));
		ships.add(new ShipOld(Crew.NamedFaction.CITY_COUNCIL, Crew.NamedFaction.BRIGADE, true));
		ships.add(new ShipOld(Crew.NamedFaction.CITY_COUNCIL, Crew.NamedFaction.CABBIES, true));
		ships.add(new ShipOld(Crew.NamedFaction.CITY_COUNCIL, Crew.NamedFaction.SPARKWRIGHTS, true));
		ships.add(new ShipOld(Crew.NamedFaction.CITY_COUNCIL, Crew.NamedFaction.FOUNDATION, true));
		ships.add(new ShipOld(Crew.NamedFaction.CITY_COUNCIL, Crew.NamedFaction.IMPERIAL_MILITARY, false));
		ships.add(new ShipOld(Crew.NamedFaction.CITY_COUNCIL, Crew.NamedFaction.INSPECTORS, false));
		ships.add(new ShipOld(Crew.NamedFaction.CITY_COUNCIL, Crew.NamedFaction.MINISTRY_OF_PRESERVATION, false));
		ships.add(new ShipOld(Crew.NamedFaction.CITY_COUNCIL, Crew.NamedFaction.RECONCILED, false));
		ships.add(new ShipOld(Crew.NamedFaction.LEVIATHAN_HUNTERS, Crew.NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(Crew.NamedFaction.LEVIATHAN_HUNTERS, Crew.NamedFaction.CHURCH_OF_ECSTASY, true));
		ships.add(new ShipOld(Crew.NamedFaction.LEVIATHAN_HUNTERS, Crew.NamedFaction.SAILORS, true));
		ships.add(new ShipOld(Crew.NamedFaction.LEVIATHAN_HUNTERS, Crew.NamedFaction.DOCKERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.LEVIATHAN_HUNTERS, Crew.NamedFaction.SPARKWRIGHTS, true));
		ships.add(new ShipOld(Crew.NamedFaction.LEVIATHAN_HUNTERS, Crew.NamedFaction.GRINDERS, false));
		ships.add(new ShipOld(Crew.NamedFaction.LEVIATHAN_HUNTERS, Crew.NamedFaction.MINISTRY_OF_PRESERVATION, false));
		ships.add(new ShipOld(Crew.NamedFaction.LEVIATHAN_HUNTERS, Crew.NamedFaction.PATH_OF_ECHOES, false));
		ships.add(new ShipOld(Crew.NamedFaction.MINISTRY_OF_PRESERVATION, Crew.NamedFaction.BILLHOOKS, true));
		ships.add(new ShipOld(Crew.NamedFaction.MINISTRY_OF_PRESERVATION, Crew.NamedFaction.IMPERIAL_MILITARY, true));
		ships.add(new ShipOld(Crew.NamedFaction.MINISTRY_OF_PRESERVATION, Crew.NamedFaction.RAIL_JACKS, true));
		ships.add(new ShipOld(Crew.NamedFaction.MINISTRY_OF_PRESERVATION, Crew.NamedFaction.SPARKWRIGHTS, true));
		ships.add(new ShipOld(Crew.NamedFaction.MINISTRY_OF_PRESERVATION, Crew.NamedFaction.LEVIATHAN_HUNTERS, false));
		ships.add(new ShipOld(Crew.NamedFaction.SPARKWRIGHTS, Crew.NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(Crew.NamedFaction.SPARKWRIGHTS, Crew.NamedFaction.LEVIATHAN_HUNTERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.SPARKWRIGHTS, Crew.NamedFaction.MINISTRY_OF_PRESERVATION, true));
		ships.add(new ShipOld(Crew.NamedFaction.SPARKWRIGHTS, Crew.NamedFaction.PATH_OF_ECHOES, false));
		ships.add(new ShipOld(Crew.NamedFaction.SPARKWRIGHTS, Crew.NamedFaction.RECONCILED, false));
		ships.add(new ShipOld(Crew.NamedFaction.SPARKWRIGHTS, Crew.NamedFaction.FOUNDATION, false));
		ships.add(new ShipOld(Crew.NamedFaction.SPIRIT_WARDENS, Crew.NamedFaction.CHURCH_OF_ECSTASY, true));
		ships.add(new ShipOld(Crew.NamedFaction.SPIRIT_WARDENS, Crew.NamedFaction.DEATHLANDS_SCAVENGERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.SPIRIT_WARDENS, Crew.NamedFaction.DIMMER_SISTERS, false));
		ships.add(new ShipOld(Crew.NamedFaction.SPIRIT_WARDENS, Crew.NamedFaction.GONDOLIERS, false));
		ships.add(new ShipOld(Crew.NamedFaction.SPIRIT_WARDENS, Crew.NamedFaction.LORD_SCURLOCK, false));
		ships.add(new ShipOld(Crew.NamedFaction.SPIRIT_WARDENS, Crew.NamedFaction.SILVER_NAILS, false));
		ships.add(new ShipOld(Crew.NamedFaction.SPIRIT_WARDENS, Crew.NamedFaction.UNSEEN, false));
		ships.add(new ShipOld(Crew.NamedFaction.SPIRIT_WARDENS, Crew.NamedFaction.PATH_OF_ECHOES, false));
		ships.add(new ShipOld(Crew.NamedFaction.SPIRIT_WARDENS, Crew.NamedFaction.RECONCILED, false));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.LAMPBLACKS, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.BARROWCLEFT, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.BRIGHTSTONE, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.CHARHOLLOW, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.CHARTERHALL, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.COALRIDGE, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.CROWS_FOOT, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.DOCKS, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.DUNSLOUGH, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.NIGHTMARKET, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.SILKSHORE, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.SIX_TOWERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.WHITECROWN, true));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.RED_SASHES, false));
		ships.add(new ShipOld(Crew.NamedFaction.GONDOLIERS, Crew.NamedFaction.SPIRIT_WARDENS, false));
		ships.add(new ShipOld(Crew.NamedFaction.CHURCH_OF_ECSTASY, Crew.NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(Crew.NamedFaction.CHURCH_OF_ECSTASY, Crew.NamedFaction.LEVIATHAN_HUNTERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.CHURCH_OF_ECSTASY, Crew.NamedFaction.SPIRIT_WARDENS, true));
		ships.add(new ShipOld(Crew.NamedFaction.CHURCH_OF_ECSTASY, Crew.NamedFaction.PATH_OF_ECHOES, false));
		ships.add(new ShipOld(Crew.NamedFaction.CHURCH_OF_ECSTASY, Crew.NamedFaction.RECONCILED, false));
		ships.add(new ShipOld(Crew.NamedFaction.DEATHLANDS_SCAVENGERS, Crew.NamedFaction.FORGOTTEN_GODS, true));
		ships.add(new ShipOld(Crew.NamedFaction.DEATHLANDS_SCAVENGERS, Crew.NamedFaction.GONDOLIERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.DEATHLANDS_SCAVENGERS, Crew.NamedFaction.SPIRIT_WARDENS, true));
		ships.add(new ShipOld(Crew.NamedFaction.DEATHLANDS_SCAVENGERS, Crew.NamedFaction.IRONHOOK_PRISON, false));
		ships.add(new ShipOld(Crew.NamedFaction.RECONCILED, Crew.NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(Crew.NamedFaction.RECONCILED, Crew.NamedFaction.GONDOLIERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.RECONCILED, Crew.NamedFaction.CHURCH_OF_ECSTASY, false));
		ships.add(new ShipOld(Crew.NamedFaction.RECONCILED, Crew.NamedFaction.SPIRIT_WARDENS, false));
		ships.add(new ShipOld(Crew.NamedFaction.RECONCILED, Crew.NamedFaction.SPARKWRIGHTS, false));
		ships.add(new ShipOld(Crew.NamedFaction.BILLHOOKS, Crew.NamedFaction.BLUECOATS, true));
		ships.add(new ShipOld(Crew.NamedFaction.BILLHOOKS, Crew.NamedFaction.MINISTRY_OF_PRESERVATION, true));
		ships.add(new ShipOld(Crew.NamedFaction.BILLHOOKS, Crew.NamedFaction.ULF_IRONBORN, false));
		ships.add(new ShipOld(Crew.NamedFaction.BILLHOOKS, Crew.NamedFaction.CROWS_FOOT, false));
		ships.add(new ShipOld(Crew.NamedFaction.BILLHOOKS, Crew.NamedFaction.DOCKS, false));
		ships.add(new ShipOld(Crew.NamedFaction.CIRCLE_OF_FLAME, Crew.NamedFaction.FORGOTTEN_GODS, true));
		ships.add(new ShipOld(Crew.NamedFaction.CIRCLE_OF_FLAME, Crew.NamedFaction.PATH_OF_ECHOES, true));
		ships.add(new ShipOld(Crew.NamedFaction.CIRCLE_OF_FLAME, Crew.NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(Crew.NamedFaction.CIRCLE_OF_FLAME, Crew.NamedFaction.FOUNDATION, true));
		ships.add(new ShipOld(Crew.NamedFaction.CIRCLE_OF_FLAME, Crew.NamedFaction.HIVE, false));
		ships.add(new ShipOld(Crew.NamedFaction.CIRCLE_OF_FLAME, Crew.NamedFaction.SILVER_NAILS, false));
		ships.add(new ShipOld(Crew.NamedFaction.CROWS, Crew.NamedFaction.BLUECOATS, true));
		ships.add(new ShipOld(Crew.NamedFaction.CROWS, Crew.NamedFaction.SAILORS, true));
		ships.add(new ShipOld(Crew.NamedFaction.CROWS, Crew.NamedFaction.LOST, true));
		ships.add(new ShipOld(Crew.NamedFaction.CROWS, Crew.NamedFaction.CROWS_FOOT, true));
		ships.add(new ShipOld(Crew.NamedFaction.CROWS, Crew.NamedFaction.HIVE, false));
		ships.add(new ShipOld(Crew.NamedFaction.CROWS, Crew.NamedFaction.INSPECTORS, false));
		ships.add(new ShipOld(Crew.NamedFaction.CROWS, Crew.NamedFaction.DOCKERS, false));
		ships.add(new ShipOld(Crew.NamedFaction.DIMMER_SISTERS, Crew.NamedFaction.FORGOTTEN_GODS, true));
		ships.add(new ShipOld(Crew.NamedFaction.DIMMER_SISTERS, Crew.NamedFaction.FOUNDATION, true));
		ships.add(new ShipOld(Crew.NamedFaction.DIMMER_SISTERS, Crew.NamedFaction.SPIRIT_WARDENS, false));
		ships.add(new ShipOld(Crew.NamedFaction.DIMMER_SISTERS, Crew.NamedFaction.RECONCILED, false));
		ships.add(new ShipOld(Crew.NamedFaction.FOG_HOUNDS, Crew.NamedFaction.DOCKERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.FOG_HOUNDS, Crew.NamedFaction.LAMPBLACKS, true));
		ships.add(new ShipOld(Crew.NamedFaction.FOG_HOUNDS, Crew.NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(Crew.NamedFaction.FOG_HOUNDS, Crew.NamedFaction.VULTURES, false));
		ships.add(new ShipOld(Crew.NamedFaction.GRAY_CLOAKS, Crew.NamedFaction.INSPECTORS, true));
		ships.add(new ShipOld(Crew.NamedFaction.GRAY_CLOAKS, Crew.NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(Crew.NamedFaction.GRAY_CLOAKS, Crew.NamedFaction.LEVIATHAN_HUNTERS, false));
		ships.add(new ShipOld(Crew.NamedFaction.GRINDERS, Crew.NamedFaction.ULF_IRONBORN, true));
		ships.add(new ShipOld(Crew.NamedFaction.GRINDERS, Crew.NamedFaction.DOCKERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.GRINDERS, Crew.NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(Crew.NamedFaction.GRINDERS, Crew.NamedFaction.IMPERIAL_MILITARY, false));
		ships.add(new ShipOld(Crew.NamedFaction.GRINDERS, Crew.NamedFaction.LEVIATHAN_HUNTERS, false));
		ships.add(new ShipOld(Crew.NamedFaction.GRINDERS, Crew.NamedFaction.SAILORS, false));
		ships.add(new ShipOld(Crew.NamedFaction.GRINDERS, Crew.NamedFaction.SILVER_NAILS, false));
		ships.add(new ShipOld(Crew.NamedFaction.HIVE, Crew.NamedFaction.MINISTRY_OF_PRESERVATION, true));
		ships.add(new ShipOld(Crew.NamedFaction.HIVE, Crew.NamedFaction.DAGGER_ISLES_CONSULATE, true));
		ships.add(new ShipOld(Crew.NamedFaction.HIVE, Crew.NamedFaction.CIRCLE_OF_FLAME, false));
		ships.add(new ShipOld(Crew.NamedFaction.HIVE, Crew.NamedFaction.UNSEEN, false));
		ships.add(new ShipOld(Crew.NamedFaction.HIVE, Crew.NamedFaction.CROWS, false));
		ships.add(new ShipOld(Crew.NamedFaction.HIVE, Crew.NamedFaction.WRAITHS, false));
		ships.add(new ShipOld(Crew.NamedFaction.LAMPBLACKS, Crew.NamedFaction.FOG_HOUNDS, true));
		ships.add(new ShipOld(Crew.NamedFaction.LAMPBLACKS, Crew.NamedFaction.GONDOLIERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.LAMPBLACKS, Crew.NamedFaction.IRONHOOK_PRISON, true));
		ships.add(new ShipOld(Crew.NamedFaction.LAMPBLACKS, Crew.NamedFaction.RED_SASHES, false));
		ships.add(new ShipOld(Crew.NamedFaction.LAMPBLACKS, Crew.NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(Crew.NamedFaction.LAMPBLACKS, Crew.NamedFaction.CABBIES, false));
		ships.add(new ShipOld(Crew.NamedFaction.LORD_SCURLOCK, Crew.NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(Crew.NamedFaction.LORD_SCURLOCK, Crew.NamedFaction.BLUECOATS, true));
		ships.add(new ShipOld(Crew.NamedFaction.LORD_SCURLOCK, Crew.NamedFaction.INSPECTORS, true));
		ships.add(new ShipOld(Crew.NamedFaction.LORD_SCURLOCK, Crew.NamedFaction.FORGOTTEN_GODS, true));
		ships.add(new ShipOld(Crew.NamedFaction.LORD_SCURLOCK, Crew.NamedFaction.SPIRIT_WARDENS, false));
		ships.add(new ShipOld(Crew.NamedFaction.LOST, Crew.NamedFaction.LABORERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.LOST, Crew.NamedFaction.COALRIDGE, true));
		ships.add(new ShipOld(Crew.NamedFaction.LOST, Crew.NamedFaction.DUNSLOUGH, true));
		ships.add(new ShipOld(Crew.NamedFaction.LOST, Crew.NamedFaction.CROWS, true));
		ships.add(new ShipOld(Crew.NamedFaction.LOST, Crew.NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(Crew.NamedFaction.LOST, Crew.NamedFaction.BILLHOOKS, false));
		ships.add(new ShipOld(Crew.NamedFaction.RED_SASHES, Crew.NamedFaction.IRUVIAN_CONSULATE, true));
		ships.add(new ShipOld(Crew.NamedFaction.RED_SASHES, Crew.NamedFaction.PATH_OF_ECHOES, true));
		ships.add(new ShipOld(Crew.NamedFaction.RED_SASHES, Crew.NamedFaction.DOCKERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.RED_SASHES, Crew.NamedFaction.CABBIES, true));
		ships.add(new ShipOld(Crew.NamedFaction.RED_SASHES, Crew.NamedFaction.INSPECTORS, true));
		ships.add(new ShipOld(Crew.NamedFaction.RED_SASHES, Crew.NamedFaction.LAMPBLACKS, false));
		ships.add(new ShipOld(Crew.NamedFaction.RED_SASHES, Crew.NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(Crew.NamedFaction.RED_SASHES, Crew.NamedFaction.GONDOLIERS, false));
		ships.add(new ShipOld(Crew.NamedFaction.SILVER_NAILS, Crew.NamedFaction.IMPERIAL_MILITARY, true));
		ships.add(new ShipOld(Crew.NamedFaction.SILVER_NAILS, Crew.NamedFaction.SAILORS, true));
		ships.add(new ShipOld(Crew.NamedFaction.SILVER_NAILS, Crew.NamedFaction.SEVEROSI_CONSULATE, true));
		ships.add(new ShipOld(Crew.NamedFaction.SILVER_NAILS, Crew.NamedFaction.CIRCLE_OF_FLAME, false));
		ships.add(new ShipOld(Crew.NamedFaction.SILVER_NAILS, Crew.NamedFaction.GRINDERS, false));
		ships.add(new ShipOld(Crew.NamedFaction.SILVER_NAILS, Crew.NamedFaction.SKOVLAN_CONSULATE, false));
		ships.add(new ShipOld(Crew.NamedFaction.SILVER_NAILS, Crew.NamedFaction.SKOVLANDER_REFUGEES, false));
		ships.add(new ShipOld(Crew.NamedFaction.SILVER_NAILS, Crew.NamedFaction.SPIRIT_WARDENS, false));
		ships.add(new ShipOld(Crew.NamedFaction.ULF_IRONBORN, Crew.NamedFaction.GRINDERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.ULF_IRONBORN, Crew.NamedFaction.COALRIDGE, false));
		ships.add(new ShipOld(Crew.NamedFaction.ULF_IRONBORN, Crew.NamedFaction.BILLHOOKS, false));
		ships.add(new ShipOld(Crew.NamedFaction.UNSEEN, Crew.NamedFaction.BLUECOATS, true));
		ships.add(new ShipOld(Crew.NamedFaction.UNSEEN, Crew.NamedFaction.IRONHOOK_PRISON, true));
		ships.add(new ShipOld(Crew.NamedFaction.UNSEEN, Crew.NamedFaction.FORGOTTEN_GODS, true));
		ships.add(new ShipOld(Crew.NamedFaction.UNSEEN, Crew.NamedFaction.CYPHERS, true));
		ships.add(new ShipOld(Crew.NamedFaction.UNSEEN, Crew.NamedFaction.INK_RAKES, false));
		ships.add(new ShipOld(Crew.NamedFaction.UNSEEN, Crew.NamedFaction.SPIRIT_WARDENS, false));
		ships.add(new ShipOld(Crew.NamedFaction.WRAITHS, Crew.NamedFaction.CABBIES, true));
		ships.add(new ShipOld(Crew.NamedFaction.WRAITHS, Crew.NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(Crew.NamedFaction.WRAITHS, Crew.NamedFaction.INSPECTORS, false));
		ships.add(new ShipOld(Crew.NamedFaction.WRAITHS, Crew.NamedFaction.HIVE, false));

	}

	// instance fields
	private Crew crew1, crew2;
	private boolean allies;

	// constructors
	public ShipOld(Crew crew1, Crew crew2, boolean allies) {
		this.crew1 = crew1;
		this.crew2 = crew2;
		this.allies = allies;
	}

	public ShipOld(Crew.NamedFaction faction1, Crew.NamedFaction faction2, boolean allies) {
		Crew crew1 = Crew.getCrewByFaction(faction1);
		Crew crew2 = Crew.getCrewByFaction(faction2);

		this.crew1 = crew1;
		this.crew2 = crew2;
		this.allies = allies;
	}

	public Crew getOtherCrew(Crew crew) {
		Crew other = (crew1.equals(crew)) ? crew2 : crew1;
		return other;
	}

	public boolean contains(Crew other) {
		boolean contains = false;
		if (crew1.equals(other) || crew2.equals(other))
			contains = true;

		return contains;
	}

	public boolean equals(ShipOld other) {
		boolean equals = true;

		if (other.crew1.notSameAs(crew1) && other.crew1.notSameAs(crew2))
			equals = false;

		if (other.crew2.notSameAs(crew1) && other.crew2.notSameAs(crew2))
			equals = false;

		return equals;
	}

	public boolean allies() {
		return allies;
	}

	public boolean enemies() {
		return (allies != true);
	}

	@Override
	public String toString() {
		String status = (allies()) ? "Allies" : "Enemies";
		return String.format("%s [%s, %s]", status, crew1, crew2);
	}

	// static methods
	public static Set<Crew> crewShipSet(Crew crew) {
		// TODO - testing
		HashSet<Crew> subset = new HashSet<Crew>();

		ShipOld ship;
		for (Iterator<ShipOld> it = ships.iterator(); it.hasNext();) {
			ship = it.next();
			if (ship.contains(crew))
				subset.add(ship.getOtherCrew(crew));
		}

		return subset;
	}

	public static Set<Crew> crewAllySet(Crew crew) {
		// TODO - testing
		HashSet<Crew> subset = new HashSet<Crew>();

		ShipOld ship;
		for (Iterator<ShipOld> it = ships.iterator(); it.hasNext();) {
			ship = it.next();
			Crew other;
			if (ship.contains(crew) && ship.allies()) {
				other = ship.getOtherCrew(crew);
				if (other.active())
					subset.add(other);
			}
		}

		return subset;
	}

	public static Set<Crew> crewEnemySet(Crew crew) {
		// TODO - testing
		HashSet<Crew> subset = new HashSet<Crew>();

		ShipOld ship;
		for (Iterator<ShipOld> it = ships.iterator(); it.hasNext();) {
			ship = it.next();
			Crew other;
			if (ship.contains(crew) && ship.enemies()) {
				other = ship.getOtherCrew(crew);
				if (other.active())
					subset.add(other);
				
			}
		}

		return subset;
	}

	public static Set<ShipOld> shipSet() {
		return ShipSet.set;
	}

	/*
	 * SHIP SET - INNER CLASS
	 */
	private static class ShipSet {
		static HashSet<ShipOld> set;

		static {
			set = new HashSet<ShipOld>();
		}

		public boolean add(ShipOld e) {
			boolean add = false;
			if (contains(e) != true) {
				set.add(e);
			}
			// else {
			// System.out.println("Already contains " + e);
			// }

			return add;
		}

		public boolean contains(ShipOld e) {
			boolean contains = false;

			ShipOld ship;
			for (Iterator<ShipOld> it = set.iterator(); it.hasNext();) {
				ship = it.next();
				if (ship.equals(e)) {
					contains = true;
					break;
				}
			}

			return contains;
		}

		public Iterator<ShipOld> iterator() {
			return set.iterator();
		}

		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}

		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

	}
}