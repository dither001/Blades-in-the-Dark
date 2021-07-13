package model;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.bladesinthedark.crew.*;

public class ShipOld {
	// static fields
	private static ShipSet ships;

	static {
		ships = new ShipSet();
		ships.add(new ShipOld(NamedFaction.BLUECOATS, NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(NamedFaction.BLUECOATS, NamedFaction.BILLHOOKS, true));
		ships.add(new ShipOld(NamedFaction.BLUECOATS, NamedFaction.CROWS, true));
		ships.add(new ShipOld(NamedFaction.BLUECOATS, NamedFaction.IRONHOOK_PRISON, true));
		ships.add(new ShipOld(NamedFaction.BLUECOATS, NamedFaction.LORD_SCURLOCK, true));
		ships.add(new ShipOld(NamedFaction.BLUECOATS, NamedFaction.UNSEEN, true));
		ships.add(new ShipOld(NamedFaction.BLUECOATS, NamedFaction.IMPERIAL_MILITARY, false));
		ships.add(new ShipOld(NamedFaction.CITY_COUNCIL, NamedFaction.BLUECOATS, true));
		ships.add(new ShipOld(NamedFaction.CITY_COUNCIL, NamedFaction.CHURCH_OF_ECSTASY, true));
		ships.add(new ShipOld(NamedFaction.CITY_COUNCIL, NamedFaction.CIRCLE_OF_FLAME, true));
		ships.add(new ShipOld(NamedFaction.CITY_COUNCIL, NamedFaction.LORD_SCURLOCK, true));
		ships.add(new ShipOld(NamedFaction.CITY_COUNCIL, NamedFaction.BRIGADE, true));
		ships.add(new ShipOld(NamedFaction.CITY_COUNCIL, NamedFaction.CABBIES, true));
		ships.add(new ShipOld(NamedFaction.CITY_COUNCIL, NamedFaction.SPARKWRIGHTS, true));
		ships.add(new ShipOld(NamedFaction.CITY_COUNCIL, NamedFaction.FOUNDATION, true));
		ships.add(new ShipOld(NamedFaction.CITY_COUNCIL, NamedFaction.IMPERIAL_MILITARY, false));
		ships.add(new ShipOld(NamedFaction.CITY_COUNCIL, NamedFaction.INSPECTORS, false));
		ships.add(new ShipOld(NamedFaction.CITY_COUNCIL, NamedFaction.MINISTRY_OF_PRESERVATION, false));
		ships.add(new ShipOld(NamedFaction.CITY_COUNCIL, NamedFaction.RECONCILED, false));
		ships.add(new ShipOld(NamedFaction.LEVIATHAN_HUNTERS, NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(NamedFaction.LEVIATHAN_HUNTERS, NamedFaction.CHURCH_OF_ECSTASY, true));
		ships.add(new ShipOld(NamedFaction.LEVIATHAN_HUNTERS, NamedFaction.SAILORS, true));
		ships.add(new ShipOld(NamedFaction.LEVIATHAN_HUNTERS, NamedFaction.DOCKERS, true));
		ships.add(new ShipOld(NamedFaction.LEVIATHAN_HUNTERS, NamedFaction.SPARKWRIGHTS, true));
		ships.add(new ShipOld(NamedFaction.LEVIATHAN_HUNTERS, NamedFaction.GRINDERS, false));
		ships.add(new ShipOld(NamedFaction.LEVIATHAN_HUNTERS, NamedFaction.MINISTRY_OF_PRESERVATION, false));
		ships.add(new ShipOld(NamedFaction.LEVIATHAN_HUNTERS, NamedFaction.PATH_OF_ECHOES, false));
		ships.add(new ShipOld(NamedFaction.MINISTRY_OF_PRESERVATION, NamedFaction.BILLHOOKS, true));
		ships.add(new ShipOld(NamedFaction.MINISTRY_OF_PRESERVATION, NamedFaction.IMPERIAL_MILITARY, true));
		ships.add(new ShipOld(NamedFaction.MINISTRY_OF_PRESERVATION, NamedFaction.RAIL_JACKS, true));
		ships.add(new ShipOld(NamedFaction.MINISTRY_OF_PRESERVATION, NamedFaction.SPARKWRIGHTS, true));
		ships.add(new ShipOld(NamedFaction.MINISTRY_OF_PRESERVATION, NamedFaction.LEVIATHAN_HUNTERS, false));
		ships.add(new ShipOld(NamedFaction.SPARKWRIGHTS, NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(NamedFaction.SPARKWRIGHTS, NamedFaction.LEVIATHAN_HUNTERS, true));
		ships.add(new ShipOld(NamedFaction.SPARKWRIGHTS, NamedFaction.MINISTRY_OF_PRESERVATION, true));
		ships.add(new ShipOld(NamedFaction.SPARKWRIGHTS, NamedFaction.PATH_OF_ECHOES, false));
		ships.add(new ShipOld(NamedFaction.SPARKWRIGHTS, NamedFaction.RECONCILED, false));
		ships.add(new ShipOld(NamedFaction.SPARKWRIGHTS, NamedFaction.FOUNDATION, false));
		ships.add(new ShipOld(NamedFaction.SPIRIT_WARDENS, NamedFaction.CHURCH_OF_ECSTASY, true));
		ships.add(new ShipOld(NamedFaction.SPIRIT_WARDENS, NamedFaction.DEATHLANDS_SCAVENGERS, true));
		ships.add(new ShipOld(NamedFaction.SPIRIT_WARDENS, NamedFaction.DIMMER_SISTERS, false));
		ships.add(new ShipOld(NamedFaction.SPIRIT_WARDENS, NamedFaction.GONDOLIERS, false));
		ships.add(new ShipOld(NamedFaction.SPIRIT_WARDENS, NamedFaction.LORD_SCURLOCK, false));
		ships.add(new ShipOld(NamedFaction.SPIRIT_WARDENS, NamedFaction.SILVER_NAILS, false));
		ships.add(new ShipOld(NamedFaction.SPIRIT_WARDENS, NamedFaction.UNSEEN, false));
		ships.add(new ShipOld(NamedFaction.SPIRIT_WARDENS, NamedFaction.PATH_OF_ECHOES, false));
		ships.add(new ShipOld(NamedFaction.SPIRIT_WARDENS, NamedFaction.RECONCILED, false));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.LAMPBLACKS, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.BARROWCLEFT, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.BRIGHTSTONE, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.CHARHOLLOW, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.CHARTERHALL, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.COALRIDGE, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.CROWS_FOOT, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.DOCKS, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.DUNSLOUGH, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.NIGHTMARKET, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.SILKSHORE, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.SIX_TOWERS, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.WHITECROWN, true));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.RED_SASHES, false));
		ships.add(new ShipOld(NamedFaction.GONDOLIERS, NamedFaction.SPIRIT_WARDENS, false));
		ships.add(new ShipOld(NamedFaction.CHURCH_OF_ECSTASY, NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(NamedFaction.CHURCH_OF_ECSTASY, NamedFaction.LEVIATHAN_HUNTERS, true));
		ships.add(new ShipOld(NamedFaction.CHURCH_OF_ECSTASY, NamedFaction.SPIRIT_WARDENS, true));
		ships.add(new ShipOld(NamedFaction.CHURCH_OF_ECSTASY, NamedFaction.PATH_OF_ECHOES, false));
		ships.add(new ShipOld(NamedFaction.CHURCH_OF_ECSTASY, NamedFaction.RECONCILED, false));
		ships.add(new ShipOld(NamedFaction.DEATHLANDS_SCAVENGERS, NamedFaction.FORGOTTEN_GODS, true));
		ships.add(new ShipOld(NamedFaction.DEATHLANDS_SCAVENGERS, NamedFaction.GONDOLIERS, true));
		ships.add(new ShipOld(NamedFaction.DEATHLANDS_SCAVENGERS, NamedFaction.SPIRIT_WARDENS, true));
		ships.add(new ShipOld(NamedFaction.DEATHLANDS_SCAVENGERS, NamedFaction.IRONHOOK_PRISON, false));
		ships.add(new ShipOld(NamedFaction.RECONCILED, NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(NamedFaction.RECONCILED, NamedFaction.GONDOLIERS, true));
		ships.add(new ShipOld(NamedFaction.RECONCILED, NamedFaction.CHURCH_OF_ECSTASY, false));
		ships.add(new ShipOld(NamedFaction.RECONCILED, NamedFaction.SPIRIT_WARDENS, false));
		ships.add(new ShipOld(NamedFaction.RECONCILED, NamedFaction.SPARKWRIGHTS, false));
		ships.add(new ShipOld(NamedFaction.BILLHOOKS, NamedFaction.BLUECOATS, true));
		ships.add(new ShipOld(NamedFaction.BILLHOOKS, NamedFaction.MINISTRY_OF_PRESERVATION, true));
		ships.add(new ShipOld(NamedFaction.BILLHOOKS, NamedFaction.ULF_IRONBORN, false));
		ships.add(new ShipOld(NamedFaction.BILLHOOKS, NamedFaction.CROWS_FOOT, false));
		ships.add(new ShipOld(NamedFaction.BILLHOOKS, NamedFaction.DOCKS, false));
		ships.add(new ShipOld(NamedFaction.CIRCLE_OF_FLAME, NamedFaction.FORGOTTEN_GODS, true));
		ships.add(new ShipOld(NamedFaction.CIRCLE_OF_FLAME, NamedFaction.PATH_OF_ECHOES, true));
		ships.add(new ShipOld(NamedFaction.CIRCLE_OF_FLAME, NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(NamedFaction.CIRCLE_OF_FLAME, NamedFaction.FOUNDATION, true));
		ships.add(new ShipOld(NamedFaction.CIRCLE_OF_FLAME, NamedFaction.HIVE, false));
		ships.add(new ShipOld(NamedFaction.CIRCLE_OF_FLAME, NamedFaction.SILVER_NAILS, false));
		ships.add(new ShipOld(NamedFaction.CROWS, NamedFaction.BLUECOATS, true));
		ships.add(new ShipOld(NamedFaction.CROWS, NamedFaction.SAILORS, true));
		ships.add(new ShipOld(NamedFaction.CROWS, NamedFaction.LOST, true));
		ships.add(new ShipOld(NamedFaction.CROWS, NamedFaction.CROWS_FOOT, true));
		ships.add(new ShipOld(NamedFaction.CROWS, NamedFaction.HIVE, false));
		ships.add(new ShipOld(NamedFaction.CROWS, NamedFaction.INSPECTORS, false));
		ships.add(new ShipOld(NamedFaction.CROWS, NamedFaction.DOCKERS, false));
		ships.add(new ShipOld(NamedFaction.DIMMER_SISTERS, NamedFaction.FORGOTTEN_GODS, true));
		ships.add(new ShipOld(NamedFaction.DIMMER_SISTERS, NamedFaction.FOUNDATION, true));
		ships.add(new ShipOld(NamedFaction.DIMMER_SISTERS, NamedFaction.SPIRIT_WARDENS, false));
		ships.add(new ShipOld(NamedFaction.DIMMER_SISTERS, NamedFaction.RECONCILED, false));
		ships.add(new ShipOld(NamedFaction.FOG_HOUNDS, NamedFaction.DOCKERS, true));
		ships.add(new ShipOld(NamedFaction.FOG_HOUNDS, NamedFaction.LAMPBLACKS, true));
		ships.add(new ShipOld(NamedFaction.FOG_HOUNDS, NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(NamedFaction.FOG_HOUNDS, NamedFaction.VULTURES, false));
		ships.add(new ShipOld(NamedFaction.GRAY_CLOAKS, NamedFaction.INSPECTORS, true));
		ships.add(new ShipOld(NamedFaction.GRAY_CLOAKS, NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(NamedFaction.GRAY_CLOAKS, NamedFaction.LEVIATHAN_HUNTERS, false));
		ships.add(new ShipOld(NamedFaction.GRINDERS, NamedFaction.ULF_IRONBORN, true));
		ships.add(new ShipOld(NamedFaction.GRINDERS, NamedFaction.DOCKERS, true));
		ships.add(new ShipOld(NamedFaction.GRINDERS, NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(NamedFaction.GRINDERS, NamedFaction.IMPERIAL_MILITARY, false));
		ships.add(new ShipOld(NamedFaction.GRINDERS, NamedFaction.LEVIATHAN_HUNTERS, false));
		ships.add(new ShipOld(NamedFaction.GRINDERS, NamedFaction.SAILORS, false));
		ships.add(new ShipOld(NamedFaction.GRINDERS, NamedFaction.SILVER_NAILS, false));
		ships.add(new ShipOld(NamedFaction.HIVE, NamedFaction.MINISTRY_OF_PRESERVATION, true));
		ships.add(new ShipOld(NamedFaction.HIVE, NamedFaction.DAGGER_ISLES_CONSULATE, true));
		ships.add(new ShipOld(NamedFaction.HIVE, NamedFaction.CIRCLE_OF_FLAME, false));
		ships.add(new ShipOld(NamedFaction.HIVE, NamedFaction.UNSEEN, false));
		ships.add(new ShipOld(NamedFaction.HIVE, NamedFaction.CROWS, false));
		ships.add(new ShipOld(NamedFaction.HIVE, NamedFaction.WRAITHS, false));
		ships.add(new ShipOld(NamedFaction.LAMPBLACKS, NamedFaction.FOG_HOUNDS, true));
		ships.add(new ShipOld(NamedFaction.LAMPBLACKS, NamedFaction.GONDOLIERS, true));
		ships.add(new ShipOld(NamedFaction.LAMPBLACKS, NamedFaction.IRONHOOK_PRISON, true));
		ships.add(new ShipOld(NamedFaction.LAMPBLACKS, NamedFaction.RED_SASHES, false));
		ships.add(new ShipOld(NamedFaction.LAMPBLACKS, NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(NamedFaction.LAMPBLACKS, NamedFaction.CABBIES, false));
		ships.add(new ShipOld(NamedFaction.LORD_SCURLOCK, NamedFaction.CITY_COUNCIL, true));
		ships.add(new ShipOld(NamedFaction.LORD_SCURLOCK, NamedFaction.BLUECOATS, true));
		ships.add(new ShipOld(NamedFaction.LORD_SCURLOCK, NamedFaction.INSPECTORS, true));
		ships.add(new ShipOld(NamedFaction.LORD_SCURLOCK, NamedFaction.FORGOTTEN_GODS, true));
		ships.add(new ShipOld(NamedFaction.LORD_SCURLOCK, NamedFaction.SPIRIT_WARDENS, false));
		ships.add(new ShipOld(NamedFaction.LOST, NamedFaction.LABORERS, true));
		ships.add(new ShipOld(NamedFaction.LOST, NamedFaction.COALRIDGE, true));
		ships.add(new ShipOld(NamedFaction.LOST, NamedFaction.DUNSLOUGH, true));
		ships.add(new ShipOld(NamedFaction.LOST, NamedFaction.CROWS, true));
		ships.add(new ShipOld(NamedFaction.LOST, NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(NamedFaction.LOST, NamedFaction.BILLHOOKS, false));
		ships.add(new ShipOld(NamedFaction.RED_SASHES, NamedFaction.IRUVIAN_CONSULATE, true));
		ships.add(new ShipOld(NamedFaction.RED_SASHES, NamedFaction.PATH_OF_ECHOES, true));
		ships.add(new ShipOld(NamedFaction.RED_SASHES, NamedFaction.DOCKERS, true));
		ships.add(new ShipOld(NamedFaction.RED_SASHES, NamedFaction.CABBIES, true));
		ships.add(new ShipOld(NamedFaction.RED_SASHES, NamedFaction.INSPECTORS, true));
		ships.add(new ShipOld(NamedFaction.RED_SASHES, NamedFaction.LAMPBLACKS, false));
		ships.add(new ShipOld(NamedFaction.RED_SASHES, NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(NamedFaction.RED_SASHES, NamedFaction.GONDOLIERS, false));
		ships.add(new ShipOld(NamedFaction.SILVER_NAILS, NamedFaction.IMPERIAL_MILITARY, true));
		ships.add(new ShipOld(NamedFaction.SILVER_NAILS, NamedFaction.SAILORS, true));
		ships.add(new ShipOld(NamedFaction.SILVER_NAILS, NamedFaction.SEVEROSI_CONSULATE, true));
		ships.add(new ShipOld(NamedFaction.SILVER_NAILS, NamedFaction.CIRCLE_OF_FLAME, false));
		ships.add(new ShipOld(NamedFaction.SILVER_NAILS, NamedFaction.GRINDERS, false));
		ships.add(new ShipOld(NamedFaction.SILVER_NAILS, NamedFaction.SKOVLAN_CONSULATE, false));
		ships.add(new ShipOld(NamedFaction.SILVER_NAILS, NamedFaction.SKOVLANDER_REFUGEES, false));
		ships.add(new ShipOld(NamedFaction.SILVER_NAILS, NamedFaction.SPIRIT_WARDENS, false));
		ships.add(new ShipOld(NamedFaction.ULF_IRONBORN, NamedFaction.GRINDERS, true));
		ships.add(new ShipOld(NamedFaction.ULF_IRONBORN, NamedFaction.COALRIDGE, false));
		ships.add(new ShipOld(NamedFaction.ULF_IRONBORN, NamedFaction.BILLHOOKS, false));
		ships.add(new ShipOld(NamedFaction.UNSEEN, NamedFaction.BLUECOATS, true));
		ships.add(new ShipOld(NamedFaction.UNSEEN, NamedFaction.IRONHOOK_PRISON, true));
		ships.add(new ShipOld(NamedFaction.UNSEEN, NamedFaction.FORGOTTEN_GODS, true));
		ships.add(new ShipOld(NamedFaction.UNSEEN, NamedFaction.CYPHERS, true));
		ships.add(new ShipOld(NamedFaction.UNSEEN, NamedFaction.INK_RAKES, false));
		ships.add(new ShipOld(NamedFaction.UNSEEN, NamedFaction.SPIRIT_WARDENS, false));
		ships.add(new ShipOld(NamedFaction.WRAITHS, NamedFaction.CABBIES, true));
		ships.add(new ShipOld(NamedFaction.WRAITHS, NamedFaction.BLUECOATS, false));
		ships.add(new ShipOld(NamedFaction.WRAITHS, NamedFaction.INSPECTORS, false));
		ships.add(new ShipOld(NamedFaction.WRAITHS, NamedFaction.HIVE, false));

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

	public ShipOld(NamedFaction faction1, NamedFaction faction2, boolean allies) {
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