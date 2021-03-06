package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.bladesinthedark.crew.Faction;
import com.bladesinthedark.crew.Gang;
import com.bladesinthedark.crew.NamedFaction;

public class Setting {
	/*
	 * INSTANCE FIELDS
	 */
	private LocaleOld.Cluster cluster;
	private Cityscape cityscape;

	//
	private int lifetimeGangs;
	private Set<Faction> factions;

	/*
	 * CONSTRUCTORS
	 */
	public Setting() {
		/*
		 * FIXME - this array represents will need to be replaced with the
		 * randomly-generated content when I abstract stuff for CharGen
		 */
		NamedFaction[] array = NamedFaction.factionAddOrder();

		//
		this.cluster = LocaleOld.cluster();
		this.cityscape = new Cityscape();
		this.lifetimeGangs = 0;

		//
		this.factions = new HashSet<Faction>();

		// creates the faction map
		for (int i = 0; i < array.length; ++i) {
			factions.add(new Gang(lifetimeGangs++, array[i].toString(), this, cluster));
		}

		// initializes neighbor ships
		Gang gang;
		for (Iterator<Faction> it = factions.iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.neighborSetup();
		}

		// claims turf
		for (Iterator<Faction> it = factions.iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.factionSetup();
		}

		// finds and assigns ships to each faction
		for (Iterator<Faction> it = factions.iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.setShips(Ship.getShips(gang));
		}

		// initializes upgrades
		for (Iterator<Faction> it = factions.iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.upgradeSetup();
		}

		for (Iterator<Faction> it = factions.iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.rosterSetup();
		}

		for (Iterator<Faction> it = factions.iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.obligationSetup();
		}

		for (Iterator<Faction> it = factions.iterator(); it.hasNext();) {
			gang = (Gang) it.next();
			gang.scheme();
		}

		cityscape.addAllCurrentMembers(factions);
	}

	/*
	 * INSTANCE METHODS
	 */
	public void update() {
		cityscape.update();
	}

	public Set<Faction> factions() {
		return factions;
	}
	
	public Set<Plan.Quest> quests() {
		return cityscape.quests();
	}
	
	public List<Faction> orderedFactionList() {
		List<Faction> list = new ArrayList<Faction>(factions);

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
