import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Setting {

	/*
	 * INSTANCE FIELDS
	 */
	private Locale.Cluster cluster;
	private Cityscape cityscape;

	//
	private int lifetimeGangs;
	private Map<Faction.NamedFaction, Faction> factions;

	/*
	 * CONSTRUCTORS
	 */
	public Setting() {
		this.cluster = Locale.cluster();
		this.cityscape = new Cityscape();
		this.lifetimeGangs = 0;

		//
		this.factions = new HashMap<Faction.NamedFaction, Faction>();

		// creates the faction set
		Faction.NamedFaction[] array = Faction.factionAddOrder();

		for (int i = 0; i < array.length; ++i) {
			factions.put(array[i], new Gang(lifetimeGangs++, array[i].toString(), cluster));
		}

		// initializes neighbor ships
		Gang gang;
		for (Iterator<Faction> it = factions.values().iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.neighborSetup();
		}

		// claims turf
		for (Iterator<Faction> it = factions.values().iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.factionSetup();
		}

		// finds and assigns ships to each faction
		for (Iterator<Faction> it = factions.values().iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.setShips(Ship.getShips(gang));
		}

		// initializes upgrades
		for (Iterator<Faction> it = factions.values().iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.upgradeSetup();
		}

		for (Iterator<Faction> it = factions.values().iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.rosterSetup();
		}

		Collection<Faction> c = factions.values();
		cityscape.addAllCurrentMembers(c);
	}

	/*
	 * INSTANCE METHODS
	 */
	public void update() {
		cityscape.update();
	}

	public List<Faction> orderedFactionList() {
		List<Faction> list = new ArrayList<Faction>(factions.values());

		class Sort implements Comparator<Faction> {
			@Override
			public int compare(Faction left, Faction right) {
				Gang leftGang = (Gang) left, rightGang = (Gang) right;

				return leftGang.factionID() - rightGang.factionID();
			}
		}

		Collections.sort(list, new Sort());

		return list;
	}

}
