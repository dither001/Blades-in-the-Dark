import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class Ship {
	// static fields
	private static ShipSet ships;

	static {
		ships = new ShipSet();
		ships.add(new Ship(Crew.Faction.BLUECOATS, Crew.Faction.CITY_COUNCIL, true));
		ships.add(new Ship(Crew.Faction.BLUECOATS, Crew.Faction.BILLHOOKS, true));
		ships.add(new Ship(Crew.Faction.BLUECOATS, Crew.Faction.CROWS, true));
		ships.add(new Ship(Crew.Faction.BLUECOATS, Crew.Faction.IRONHOOK_PRISON, true));
		ships.add(new Ship(Crew.Faction.BLUECOATS, Crew.Faction.LORD_SCURLOCK, true));
		ships.add(new Ship(Crew.Faction.BLUECOATS, Crew.Faction.UNSEEN, true));
		ships.add(new Ship(Crew.Faction.BLUECOATS, Crew.Faction.IMPERIAL_MILITARY, false));
		ships.add(new Ship(Crew.Faction.CITY_COUNCIL, Crew.Faction.BLUECOATS, true));
		ships.add(new Ship(Crew.Faction.CITY_COUNCIL, Crew.Faction.CHURCH_OF_ECSTASY, true));
		ships.add(new Ship(Crew.Faction.CITY_COUNCIL, Crew.Faction.CIRCLE_OF_FLAME, true));
		ships.add(new Ship(Crew.Faction.CITY_COUNCIL, Crew.Faction.LORD_SCURLOCK, true));
		ships.add(new Ship(Crew.Faction.CITY_COUNCIL, Crew.Faction.BRIGADE, true));
		ships.add(new Ship(Crew.Faction.CITY_COUNCIL, Crew.Faction.CABBIES, true));
		ships.add(new Ship(Crew.Faction.CITY_COUNCIL, Crew.Faction.SPARKWRIGHTS, true));
		ships.add(new Ship(Crew.Faction.CITY_COUNCIL, Crew.Faction.FOUNDATION, true));
		ships.add(new Ship(Crew.Faction.CITY_COUNCIL, Crew.Faction.IMPERIAL_MILITARY, false));
		ships.add(new Ship(Crew.Faction.CITY_COUNCIL, Crew.Faction.INSPECTORS, false));
		ships.add(new Ship(Crew.Faction.CITY_COUNCIL, Crew.Faction.MINISTRY_OF_PRESERVATION, false));
		ships.add(new Ship(Crew.Faction.CITY_COUNCIL, Crew.Faction.RECONCILED, false));
		ships.add(new Ship(Crew.Faction.LEVIATHAN_HUNTERS, Crew.Faction.CITY_COUNCIL, true));
		ships.add(new Ship(Crew.Faction.LEVIATHAN_HUNTERS, Crew.Faction.CHURCH_OF_ECSTASY, true));
		ships.add(new Ship(Crew.Faction.LEVIATHAN_HUNTERS, Crew.Faction.SAILORS, true));
		ships.add(new Ship(Crew.Faction.LEVIATHAN_HUNTERS, Crew.Faction.DOCKERS, true));
		ships.add(new Ship(Crew.Faction.LEVIATHAN_HUNTERS, Crew.Faction.SPARKWRIGHTS, true));
		ships.add(new Ship(Crew.Faction.LEVIATHAN_HUNTERS, Crew.Faction.GRINDERS, false));
		ships.add(new Ship(Crew.Faction.LEVIATHAN_HUNTERS, Crew.Faction.MINISTRY_OF_PRESERVATION, false));
		ships.add(new Ship(Crew.Faction.LEVIATHAN_HUNTERS, Crew.Faction.PATH_OF_ECHOES, false));
		ships.add(new Ship(Crew.Faction.MINISTRY_OF_PRESERVATION, Crew.Faction.BILLHOOKS, true));
		ships.add(new Ship(Crew.Faction.MINISTRY_OF_PRESERVATION, Crew.Faction.IMPERIAL_MILITARY, true));
		ships.add(new Ship(Crew.Faction.MINISTRY_OF_PRESERVATION, Crew.Faction.RAIL_JACKS, true));
		ships.add(new Ship(Crew.Faction.MINISTRY_OF_PRESERVATION, Crew.Faction.SPARKWRIGHTS, true));
		ships.add(new Ship(Crew.Faction.MINISTRY_OF_PRESERVATION, Crew.Faction.LEVIATHAN_HUNTERS, false));
		ships.add(new Ship(Crew.Faction.SPARKWRIGHTS, Crew.Faction.CITY_COUNCIL, true));
		ships.add(new Ship(Crew.Faction.SPARKWRIGHTS, Crew.Faction.LEVIATHAN_HUNTERS, true));
		ships.add(new Ship(Crew.Faction.SPARKWRIGHTS, Crew.Faction.MINISTRY_OF_PRESERVATION, true));
		ships.add(new Ship(Crew.Faction.SPARKWRIGHTS, Crew.Faction.PATH_OF_ECHOES, false));
		ships.add(new Ship(Crew.Faction.SPARKWRIGHTS, Crew.Faction.RECONCILED, false));
		ships.add(new Ship(Crew.Faction.SPARKWRIGHTS, Crew.Faction.FOUNDATION, false));
		ships.add(new Ship(Crew.Faction.SPIRIT_WARDENS, Crew.Faction.CHURCH_OF_ECSTASY, true));
		ships.add(new Ship(Crew.Faction.SPIRIT_WARDENS, Crew.Faction.DEATHLANDS_SCAVENGERS, true));
		ships.add(new Ship(Crew.Faction.SPIRIT_WARDENS, Crew.Faction.DIMMER_SISTERS, false));
		ships.add(new Ship(Crew.Faction.SPIRIT_WARDENS, Crew.Faction.GONDOLIERS, false));
		ships.add(new Ship(Crew.Faction.SPIRIT_WARDENS, Crew.Faction.LORD_SCURLOCK, false));
		ships.add(new Ship(Crew.Faction.SPIRIT_WARDENS, Crew.Faction.SILVER_NAILS, false));
		ships.add(new Ship(Crew.Faction.SPIRIT_WARDENS, Crew.Faction.UNSEEN, false));
		ships.add(new Ship(Crew.Faction.SPIRIT_WARDENS, Crew.Faction.PATH_OF_ECHOES, false));
		ships.add(new Ship(Crew.Faction.SPIRIT_WARDENS, Crew.Faction.RECONCILED, false));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.LAMPBLACKS, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.BARROWCLEFT, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.BRIGHTSTONE, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.CHARHOLLOW, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.CHARTERHALL, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.COALRIDGE, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.CROWS_FOOT, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.DOCKS, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.DUNSLOUGH, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.NIGHTMARKET, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.SILKSHORE, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.SIX_TOWERS, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.WHITECROWN, true));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.RED_SASHES, false));
		ships.add(new Ship(Crew.Faction.GONDOLIERS, Crew.Faction.SPIRIT_WARDENS, false));
		ships.add(new Ship(Crew.Faction.CHURCH_OF_ECSTASY, Crew.Faction.CITY_COUNCIL, true));
		ships.add(new Ship(Crew.Faction.CHURCH_OF_ECSTASY, Crew.Faction.LEVIATHAN_HUNTERS, true));
		ships.add(new Ship(Crew.Faction.CHURCH_OF_ECSTASY, Crew.Faction.SPIRIT_WARDENS, true));
		ships.add(new Ship(Crew.Faction.CHURCH_OF_ECSTASY, Crew.Faction.PATH_OF_ECHOES, false));
		ships.add(new Ship(Crew.Faction.CHURCH_OF_ECSTASY, Crew.Faction.RECONCILED, false));
		ships.add(new Ship(Crew.Faction.DEATHLANDS_SCAVENGERS, Crew.Faction.FORGOTTEN_GODS, true));
		ships.add(new Ship(Crew.Faction.DEATHLANDS_SCAVENGERS, Crew.Faction.GONDOLIERS, true));
		ships.add(new Ship(Crew.Faction.DEATHLANDS_SCAVENGERS, Crew.Faction.SPIRIT_WARDENS, true));
		ships.add(new Ship(Crew.Faction.DEATHLANDS_SCAVENGERS, Crew.Faction.IRONHOOK_PRISON, false));
		ships.add(new Ship(Crew.Faction.RECONCILED, Crew.Faction.CITY_COUNCIL, true));
		ships.add(new Ship(Crew.Faction.RECONCILED, Crew.Faction.GONDOLIERS, true));
		ships.add(new Ship(Crew.Faction.RECONCILED, Crew.Faction.CHURCH_OF_ECSTASY, false));
		ships.add(new Ship(Crew.Faction.RECONCILED, Crew.Faction.SPIRIT_WARDENS, false));
		ships.add(new Ship(Crew.Faction.RECONCILED, Crew.Faction.SPARKWRIGHTS, false));
		ships.add(new Ship(Crew.Faction.BILLHOOKS, Crew.Faction.BLUECOATS, true));
		ships.add(new Ship(Crew.Faction.BILLHOOKS, Crew.Faction.MINISTRY_OF_PRESERVATION, true));
		ships.add(new Ship(Crew.Faction.BILLHOOKS, Crew.Faction.ULF_IRONBORN, false));
		ships.add(new Ship(Crew.Faction.BILLHOOKS, Crew.Faction.CROWS_FOOT, false));
		ships.add(new Ship(Crew.Faction.BILLHOOKS, Crew.Faction.DOCKS, false));
		ships.add(new Ship(Crew.Faction.CIRCLE_OF_FLAME, Crew.Faction.FORGOTTEN_GODS, true));
		ships.add(new Ship(Crew.Faction.CIRCLE_OF_FLAME, Crew.Faction.PATH_OF_ECHOES, true));
		ships.add(new Ship(Crew.Faction.CIRCLE_OF_FLAME, Crew.Faction.CITY_COUNCIL, true));
		ships.add(new Ship(Crew.Faction.CIRCLE_OF_FLAME, Crew.Faction.FOUNDATION, true));
		ships.add(new Ship(Crew.Faction.CIRCLE_OF_FLAME, Crew.Faction.HIVE, false));
		ships.add(new Ship(Crew.Faction.CIRCLE_OF_FLAME, Crew.Faction.SILVER_NAILS, false));
		ships.add(new Ship(Crew.Faction.CROWS, Crew.Faction.BLUECOATS, true));
		ships.add(new Ship(Crew.Faction.CROWS, Crew.Faction.SAILORS, true));
		ships.add(new Ship(Crew.Faction.CROWS, Crew.Faction.LOST, true));
		ships.add(new Ship(Crew.Faction.CROWS, Crew.Faction.CROWS_FOOT, true));
		ships.add(new Ship(Crew.Faction.CROWS, Crew.Faction.HIVE, false));
		ships.add(new Ship(Crew.Faction.CROWS, Crew.Faction.INSPECTORS, false));
		ships.add(new Ship(Crew.Faction.CROWS, Crew.Faction.DOCKERS, false));
		ships.add(new Ship(Crew.Faction.DIMMER_SISTERS, Crew.Faction.FORGOTTEN_GODS, true));
		ships.add(new Ship(Crew.Faction.DIMMER_SISTERS, Crew.Faction.FOUNDATION, true));
		ships.add(new Ship(Crew.Faction.DIMMER_SISTERS, Crew.Faction.SPIRIT_WARDENS, false));
		ships.add(new Ship(Crew.Faction.DIMMER_SISTERS, Crew.Faction.RECONCILED, false));
		ships.add(new Ship(Crew.Faction.FOG_HOUNDS, Crew.Faction.DOCKERS, true));
		ships.add(new Ship(Crew.Faction.FOG_HOUNDS, Crew.Faction.LAMPBLACKS, true));
		ships.add(new Ship(Crew.Faction.FOG_HOUNDS, Crew.Faction.BLUECOATS, false));
		ships.add(new Ship(Crew.Faction.FOG_HOUNDS, Crew.Faction.VULTURES, false));
		ships.add(new Ship(Crew.Faction.GRAY_CLOAKS, Crew.Faction.INSPECTORS, true));
		ships.add(new Ship(Crew.Faction.GRAY_CLOAKS, Crew.Faction.BLUECOATS, false));
		ships.add(new Ship(Crew.Faction.GRAY_CLOAKS, Crew.Faction.LEVIATHAN_HUNTERS, false));
		ships.add(new Ship(Crew.Faction.GRINDERS, Crew.Faction.ULF_IRONBORN, true));
		ships.add(new Ship(Crew.Faction.GRINDERS, Crew.Faction.DOCKERS, true));
		ships.add(new Ship(Crew.Faction.GRINDERS, Crew.Faction.BLUECOATS, false));
		ships.add(new Ship(Crew.Faction.GRINDERS, Crew.Faction.IMPERIAL_MILITARY, false));
		ships.add(new Ship(Crew.Faction.GRINDERS, Crew.Faction.LEVIATHAN_HUNTERS, false));
		ships.add(new Ship(Crew.Faction.GRINDERS, Crew.Faction.SAILORS, false));
		ships.add(new Ship(Crew.Faction.GRINDERS, Crew.Faction.SILVER_NAILS, false));
		ships.add(new Ship(Crew.Faction.HIVE, Crew.Faction.MINISTRY_OF_PRESERVATION, true));
		ships.add(new Ship(Crew.Faction.HIVE, Crew.Faction.DAGGER_ISLES_CONSULATE, true));
		ships.add(new Ship(Crew.Faction.HIVE, Crew.Faction.CIRCLE_OF_FLAME, false));
		ships.add(new Ship(Crew.Faction.HIVE, Crew.Faction.UNSEEN, false));
		ships.add(new Ship(Crew.Faction.HIVE, Crew.Faction.CROWS, false));
		ships.add(new Ship(Crew.Faction.HIVE, Crew.Faction.WRAITHS, false));
		ships.add(new Ship(Crew.Faction.LAMPBLACKS, Crew.Faction.FOG_HOUNDS, true));
		ships.add(new Ship(Crew.Faction.LAMPBLACKS, Crew.Faction.GONDOLIERS, true));
		ships.add(new Ship(Crew.Faction.LAMPBLACKS, Crew.Faction.IRONHOOK_PRISON, true));
		ships.add(new Ship(Crew.Faction.LAMPBLACKS, Crew.Faction.RED_SASHES, false));
		ships.add(new Ship(Crew.Faction.LAMPBLACKS, Crew.Faction.BLUECOATS, false));
		ships.add(new Ship(Crew.Faction.LAMPBLACKS, Crew.Faction.CABBIES, false));
		ships.add(new Ship(Crew.Faction.LORD_SCURLOCK, Crew.Faction.CITY_COUNCIL, true));
		ships.add(new Ship(Crew.Faction.LORD_SCURLOCK, Crew.Faction.BLUECOATS, true));
		ships.add(new Ship(Crew.Faction.LORD_SCURLOCK, Crew.Faction.INSPECTORS, true));
		ships.add(new Ship(Crew.Faction.LORD_SCURLOCK, Crew.Faction.FORGOTTEN_GODS, true));
		ships.add(new Ship(Crew.Faction.LORD_SCURLOCK, Crew.Faction.SPIRIT_WARDENS, false));
		ships.add(new Ship(Crew.Faction.LOST, Crew.Faction.LABORERS, true));
		ships.add(new Ship(Crew.Faction.LOST, Crew.Faction.COALRIDGE, true));
		ships.add(new Ship(Crew.Faction.LOST, Crew.Faction.DUNSLOUGH, true));
		ships.add(new Ship(Crew.Faction.LOST, Crew.Faction.CROWS, true));
		ships.add(new Ship(Crew.Faction.LOST, Crew.Faction.BLUECOATS, false));
		ships.add(new Ship(Crew.Faction.LOST, Crew.Faction.BILLHOOKS, false));
		ships.add(new Ship(Crew.Faction.RED_SASHES, Crew.Faction.IRUVIAN_CONSULATE, true));
		ships.add(new Ship(Crew.Faction.RED_SASHES, Crew.Faction.PATH_OF_ECHOES, true));
		ships.add(new Ship(Crew.Faction.RED_SASHES, Crew.Faction.DOCKERS, true));
		ships.add(new Ship(Crew.Faction.RED_SASHES, Crew.Faction.CABBIES, true));
		ships.add(new Ship(Crew.Faction.RED_SASHES, Crew.Faction.INSPECTORS, true));
		ships.add(new Ship(Crew.Faction.RED_SASHES, Crew.Faction.LAMPBLACKS, false));
		ships.add(new Ship(Crew.Faction.RED_SASHES, Crew.Faction.BLUECOATS, false));
		ships.add(new Ship(Crew.Faction.RED_SASHES, Crew.Faction.GONDOLIERS, false));
		ships.add(new Ship(Crew.Faction.SILVER_NAILS, Crew.Faction.IMPERIAL_MILITARY, true));
		ships.add(new Ship(Crew.Faction.SILVER_NAILS, Crew.Faction.SAILORS, true));
		ships.add(new Ship(Crew.Faction.SILVER_NAILS, Crew.Faction.SEVEROSI_CONSULATE, true));
		ships.add(new Ship(Crew.Faction.SILVER_NAILS, Crew.Faction.CIRCLE_OF_FLAME, false));
		ships.add(new Ship(Crew.Faction.SILVER_NAILS, Crew.Faction.GRINDERS, false));
		ships.add(new Ship(Crew.Faction.SILVER_NAILS, Crew.Faction.SKOVLAN_CONSULATE, false));
		ships.add(new Ship(Crew.Faction.SILVER_NAILS, Crew.Faction.SKOVLANDER_REFUGEES, false));
		ships.add(new Ship(Crew.Faction.SILVER_NAILS, Crew.Faction.SPIRIT_WARDENS, false));
		ships.add(new Ship(Crew.Faction.ULF_IRONBORN, Crew.Faction.GRINDERS, true));
		ships.add(new Ship(Crew.Faction.ULF_IRONBORN, Crew.Faction.COALRIDGE, false));
		ships.add(new Ship(Crew.Faction.ULF_IRONBORN, Crew.Faction.BILLHOOKS, false));
		ships.add(new Ship(Crew.Faction.UNSEEN, Crew.Faction.BLUECOATS, true));
		ships.add(new Ship(Crew.Faction.UNSEEN, Crew.Faction.IRONHOOK_PRISON, true));
		ships.add(new Ship(Crew.Faction.UNSEEN, Crew.Faction.FORGOTTEN_GODS, true));
		ships.add(new Ship(Crew.Faction.UNSEEN, Crew.Faction.CYPHERS, true));
		ships.add(new Ship(Crew.Faction.UNSEEN, Crew.Faction.INK_RAKES, false));
		ships.add(new Ship(Crew.Faction.UNSEEN, Crew.Faction.SPIRIT_WARDENS, false));
		ships.add(new Ship(Crew.Faction.WRAITHS, Crew.Faction.CABBIES, true));
		ships.add(new Ship(Crew.Faction.WRAITHS, Crew.Faction.BLUECOATS, false));
		ships.add(new Ship(Crew.Faction.WRAITHS, Crew.Faction.INSPECTORS, false));
		ships.add(new Ship(Crew.Faction.WRAITHS, Crew.Faction.HIVE, false));

	}

	// instance fields
	private Crew crew1, crew2;
	private boolean allies;

	// constructors
	public Ship(Crew crew1, Crew crew2, boolean allies) {
		this.crew1 = crew1;
		this.crew2 = crew2;
		this.allies = allies;
	}

	public Ship(Crew.Faction faction1, Crew.Faction faction2, boolean allies) {
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

	public boolean equals(Ship other) {
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

		Ship ship;
		for (Iterator<Ship> it = ships.iterator(); it.hasNext();) {
			ship = it.next();
			if (ship.contains(crew))
				subset.add(ship.getOtherCrew(crew));
		}

		return subset;
	}

	public static Set<Crew> crewAllySet(Crew crew) {
		// TODO - testing
		HashSet<Crew> subset = new HashSet<Crew>();

		Ship ship;
		for (Iterator<Ship> it = ships.iterator(); it.hasNext();) {
			ship = it.next();
			Crew other;
			if (ship.contains(crew) && ship.allies()) {
				other = ship.getOtherCrew(crew);
				if (other.isActive())
					subset.add(other);
			}
		}

		return subset;
	}

	public static Set<Crew> crewEnemySet(Crew crew) {
		// TODO - testing
		HashSet<Crew> subset = new HashSet<Crew>();

		Ship ship;
		for (Iterator<Ship> it = ships.iterator(); it.hasNext();) {
			ship = it.next();
			Crew other;
			if (ship.contains(crew) && ship.enemies()) {
				other = ship.getOtherCrew(crew);
				if (other.isActive())
					subset.add(other);
				
			}
		}

		return subset;
	}

	public static Set<Ship> shipSet() {
		return ShipSet.set;
	}

	/*
	 * SHIP SET - INNER CLASS
	 */
	private static class ShipSet {
		static HashSet<Ship> set;

		static {
			set = new HashSet<Ship>();
		}

		public boolean add(Ship e) {
			boolean add = false;
			if (contains(e) != true) {
				set.add(e);
			}
			// else {
			// System.out.println("Already contains " + e);
			// }

			return add;
		}

		public boolean contains(Ship e) {
			boolean contains = false;

			Ship ship;
			for (Iterator<Ship> it = set.iterator(); it.hasNext();) {
				ship = it.next();
				if (ship.equals(e)) {
					contains = true;
					break;
				}
			}

			return contains;
		}

		public Iterator<Ship> iterator() {
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