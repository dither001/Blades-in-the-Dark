package com.bladesinthedark.crew;

import java.util.ArrayList;
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

import com.bladesinthedark.actor.*;
import com.bladesinthedark.rules.*;

import model.*;

public class Gang implements Faction, Stakeholder {
	/*
	 * INSTANCE FIELDS
	 */
	private int gangID;
	private Setting setting;
	private LocaleOld.Cluster home;
	private Set<Faction> gangs;

	//
	private boolean active;
	private String name;
	private Set<CrewReputation> reputation;
	private CrewType type;

	//
	private Set<Rogue> roster;
	private Set<Rogue> retired;
	private Set<Plan.Quest> plans;

	//
	private int level;
	private int coin;
	private int experience;
	private EnumSet<CrewSpecial> specials;
	private Map<CrewUpgrade, Faction> upgrades;

	//
	private LocaleOld lair;
	private Set<LocaleOld> turf;

	//
	private Set<Ship> ships;
	private Obligations obligations;
	//
	private boolean atWar;
	private int heat;
	private int wantedLevel;

	/*
	 * CONSTRUCTORS
	 */
	public Gang(int gangID, String name, Setting setting, LocaleOld.Cluster home) {
		this.setting = setting;
		this.gangs = setting.factions();
		this.home = home;

		//
		this.active = true;
		this.gangID = gangID;
		this.name = name;
		this.reputation = new HashSet<CrewReputation>();
		this.type = CrewType.randomCrewType();

		//
		this.roster = new HashSet<Rogue>();
		this.retired = new HashSet<Rogue>();
		this.plans = new HashSet<Plan.Quest>();

		//
		this.level = 2;
		this.coin = 2;
		this.experience = 0;
		this.specials = EnumSet.noneOf(CrewSpecial.class);
		this.upgrades = new HashMap<CrewUpgrade, Faction>();

		//
		this.lair = home.findVacancy(Dice.roll(6) - 1);
		lair.addBuilding(this);
		this.turf = new HashSet<LocaleOld>();

		//
		this.ships = new HashSet<Ship>();

		//
		this.atWar = false;
		this.heat = 0;
		this.wantedLevel = 0;

	}

	@Override
	public void report(LocaleOld locale) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean eligibleForAdvancement() {
		if (experience < 12 - turf.size())
			return false;

		// create list of contributors
		List<Rogue> rogues = new ArrayList<Rogue>(roguesWithCoin());
		class Sort implements Comparator<Rogue> {

			@Override
			public int compare(Rogue left, Rogue right) {
				return right.getCoin() - left.getCoin();
			}
		}

		// sort contributors by wealth
		Collections.sort(rogues, new Sort());
		int availableCoin = coin;
		for (Iterator<Rogue> it = rogues.iterator(); it.hasNext();) {
			availableCoin += it.next().getCoin();
		}

		// second gate
		int costToAdvance = ((level + 1) / 2) * 8;
		if (availableCoin < costToAdvance)
			return false;

		return true;
	}

	@Override
	public void advance() {
		// first gate
		if (experience < 12 - turf.size())
			return;

		// create list of contributors
		List<Rogue> rogues = new ArrayList<Rogue>(roguesWithCoin());
		class Sort implements Comparator<Rogue> {

			@Override
			public int compare(Rogue left, Rogue right) {
				return right.getCoin() - left.getCoin();
			}
		}

		// sort contributors by wealth
		Collections.sort(rogues, new Sort());
		int availableCoin = coin;
		for (Iterator<Rogue> it = rogues.iterator(); it.hasNext();) {
			availableCoin += it.next().getCoin();
		}

		// second gate
		int costToAdvance = ((level + 1) / 2) * 8;
		if (availableCoin < costToAdvance)
			return;

		// FIXME - TESTING
		for (Iterator<Rogue> it = rogues.iterator(); it.hasNext();) {
			Rogue rogue = it.next();
			System.out.println(rogue.toString() + " || Coin: " + rogue.getCoin());
		}

		// everyone contributes
		Rogue current;
		int rogueCoin;
		while (costToAdvance > 0) {
			for (Iterator<Rogue> it = rogues.iterator(); it.hasNext();) {
				current = it.next();

				rogueCoin = current.getCoin();
				if (rogueCoin > 0) {
					--costToAdvance;
					current.setCoin(rogueCoin - 1);
				}

			}

			// pay remainder from vault if possible
			if (coin >= costToAdvance) {
				coin -= costToAdvance;
				costToAdvance = 0;
			}
		}

		// TODO - testing
		String report = String.format("%s (level %d) has advanced.", toString(), level);
		System.out.println(report);

		// final steps
		if (costToAdvance == 0) {
			++level;
			experience = 12 - turf.size();
		}

		// TODO - testing
		System.out.println("New level: " + level);
		System.out.println();

	}

	private Set<Rogue> roguesWithCoin() {
		Set<Rogue> set = new HashSet<Rogue>();

		Rogue candidate;
		for (Iterator<Rogue> it = roster.iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.getCoin() > 0)
				set.add(candidate);
		}

		return set;
	}

	public void neighborSetup() {
		int dice;
		for (Iterator<Faction> it = lair.residents().iterator(); it.hasNext();) {
			dice = Dice.roll(3) + 2;
			Ship.addShip(this, it.next(), dice);

		}
	}

	public void factionSetup() {
		Set<LocaleOld> workingSet = new HashSet<LocaleOld>();
		workingSet.add(lair);

		//
		LocaleOld startingTurf = null;
		while (startingTurf == null || startingTurf.testAddStake(this).size() < 1) {
			startingTurf = home.findStake(workingSet);
		}

		//
		turf.add(startingTurf);

		Set<Faction> rivals = new HashSet<Faction>(startingTurf.addStake(this));

		for (Iterator<Faction> it = rivals.iterator(); it.hasNext();) {
			Ship.addShip(this, it.next(), Dice.roll(3) + 2);
		}

		//
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

		//
	}

	public void upgradeSetup() {
		Faction faction = Dice.randomFromSet(ships).getOther(this);

		if (type.equals(CrewType.ASSASSINS)) {
			specials.add(Dice.randomFromArray(CrewSpecial.ASSASSIN_SPECIALS));
			upgrades.put(CrewUpgrade.TRAINING_INSIGHT, faction);
			upgrades.put(CrewUpgrade.TRAINING_PROWESS, faction);

		} else if (type.equals(CrewType.BRAVOS)) {
			// TODO - additional cohort details
			specials.add(Dice.randomFromArray(CrewSpecial.BRAVOS_SPECIALS));
			upgrades.put(CrewUpgrade.C2_COHORT_1, faction);
			upgrades.put(CrewUpgrade.TRAINING_PROWESS, faction);

		} else if (type.equals(CrewType.CULT)) {
			// TODO - additional cohort details
			specials.add(Dice.randomFromArray(CrewSpecial.CULT_SPECIALS));
			upgrades.put(CrewUpgrade.C2_COHORT_1, faction);
			upgrades.put(CrewUpgrade.TRAINING_RESOLVE, faction);

		} else if (type.equals(CrewType.HAWKERS)) {
			specials.add(Dice.randomFromArray(CrewSpecial.HAWKERS_SPECIALS));
			upgrades.put(CrewUpgrade.SECURE_LAIR_1, faction);
			upgrades.put(CrewUpgrade.TRAINING_RESOLVE, faction);

		} else if (type.equals(CrewType.SHADOWS)) {
			specials.add(Dice.randomFromArray(CrewSpecial.SHADOWS_SPECIALS));
			upgrades.put(CrewUpgrade.HIDDEN_LAIR, faction);
			upgrades.put(CrewUpgrade.TRAINING_PROWESS, faction);

		} else if (type.equals(CrewType.SMUGGLERS)) {
			specials.add(Dice.randomFromArray(CrewSpecial.SMUGGLERS_SPECIALS));
			upgrades.put(CrewUpgrade.BOAT_HOUSE_1, faction);
			upgrades.put(CrewUpgrade.TRAINING_PROWESS, faction);

		}

		// upgrade one
		faction = Dice.randomFromSet(ships).getOther(this);
		Ship ship = Ship.get(this, faction);
		int score = ship.getScore();

		CrewUpgrade upgrade = CrewUpgrade.randomUpgradeByCrewType(type);
		while (upgrades.containsKey(upgrade)) {
			upgrade = CrewUpgrade.randomUpgradeByCrewType(type);
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
			upgrade = CrewUpgrade.randomUpgradeByCrewType(type);
		}

		upgrades.put(upgrade, faction);
		if (coin > 0 && score > -2 && Dice.roll(2) == 1) {
			transferCoinTo(1, faction);
			ship.setScore(score - 1);

		} else {
			ship.setScore(score - 2);

		}

		setShips(ships);
	}

	public void rosterSetup() {
		for (int i = Dice.roll(3, 3); i > 0; --i) {

			// starting roster is "3d3" or 3-9 rogues
			roster.add(new Rogue(this));
		}
	}

	public void obligationSetup() {
		this.obligations = new Obligations(this);
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
	public Setting setting() {
		return setting;
	}

	@Override
	public Set<Faction> factions() {
		return gangs;
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
	public Set<CrewReputation> getReputation() {
		return reputation;
	}

	@Override
	public void setReputation(Set<CrewReputation> reputation) {
		this.reputation = reputation;
	}

	@Override
	public CrewType crewType() {
		return type;
	}

	@Override
	public void setCrewType(CrewType type) {
		this.type = type;
	}

	@Override
	public Set<Rogue> roster() {
		return roster;
	}

	@Override
	public void setRoster(Set<Rogue> roster) {
		this.roster = roster;
	}

	@Override
	public Set<Rogue> retired() {
		return retired;
	}

	@Override
	public void setRetired(Set<Rogue> retired) {
		this.retired = retired;
	}

	@Override
	public Set<Plan.Quest> getPlans() {
		return plans;
	}

	@Override
	public void setPlans(Set<Plan.Quest> plans) {
		this.plans = plans;
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
	public EnumSet<CrewSpecial> getSpecials() {
		return specials;
	}

	@Override
	public void setSpecials(EnumSet<CrewSpecial> specials) {
		this.specials = specials;
	}

	@Override
	public Set<CrewUpgrade> upgradeSet() {
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

	@Override
	public Obligations obligations() {
		return obligations;
	};
}
