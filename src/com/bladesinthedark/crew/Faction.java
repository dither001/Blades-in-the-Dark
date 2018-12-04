package com.bladesinthedark.crew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.bladesinthedark.actor.*;
import com.bladesinthedark.rules.*;

import model.*;

public interface Faction {
	public int factionID();

	public Setting setting();

	public Set<Faction> factions();

	public String getName();

	public void setName(String name);

	public Set<CrewReputation> getReputation();

	public void setReputation(Set<CrewReputation> reputation);

	public CrewType crewType();

	public void setCrewType(CrewType type);

	public Set<Rogue> roster();

	public void setRoster(Set<Rogue> roster);

	public Set<Rogue> retired();

	public void setRetired(Set<Rogue> retired);

	public Set<Plan.Quest> getPlans();

	public void setPlans(Set<Plan.Quest> plans);

	public int getLevel();

	public void setLevel(int level);

	public int getCoin();

	public void setCoin(int coin);

	public int getExperience();

	public void setExperience(int experience);

	public EnumSet<CrewSpecial> getSpecials();

	public void setSpecials(EnumSet<CrewSpecial> specials);

	public Set<CrewUpgrade> upgradeSet();

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

	public Obligations obligations();

	public boolean eligibleForAdvancement();

	public void advance();

	/*
	 * DEFAULT METHODS
	 */
	public default Set<Faction> allies() {
		return obligations().allies();
	}

	public default Set<Faction> friends() {
		return obligations().friends();
	}

	public default Set<Faction> rivals() {
		return obligations().rivals();
	}

	public default Set<Faction> hostiles() {
		return obligations().hostiles();
	}

	public default Set<Faction> enemies() {
		return obligations().enemies();
	}

	public default void scheme() {
		obligations().updateObligations();

		Set<Plan.Quest> plans = new HashSet<Plan.Quest>(getPlans());

		if (eligibleForAdvancement())
			plans.add(new Plan.Quest(this, Plan.Goal.ASSIST));

		// ABC = Always Be Climbing
		for (Iterator<Faction> it = obligations().rivals().iterator(); it.hasNext();) {
			plans.add(new Plan.Quest(this, it.next(), Plan.Goal.CLIMB));

		}

		for (Iterator<Faction> it = obligations().enemies().iterator(); it.hasNext();) {
			plans.add(new Plan.Quest(this, it.next(), Plan.Goal.SHAKE));

		}

		setPlans(plans);
	}

	public default void action() {
		/*
		 * FIXME - this is the function called whenever a faction takes their turn; they
		 * should choose between various options such as "acquire assets" and
		 * "pull heist"; but these things are known to the implementing factions, not
		 * the ladder to which they belong; all the ladder does is CALL THEM TO ACTION
		 */

		Faction client = obligations().selectObligation();

		Plan.Quest quest;
		if (equals(client)) {
			quest = Dice.randomFromSet(getPlans());

			// System.out.println(toString() + " does a personal job");
			// System.out.println(quest);
			// System.out.println();

			execute(quest);

		} else {
			quest = Dice.randomFromSet(Plan.filterForFaction(client, this, setting().quests()));

			// System.out.println(toString() + " takes a job from " + client);
			// System.out.println(quest);
			// System.out.println();

			execute(quest);
		}

		// System.out.println("- - - - - -");
		// System.out.println();

	}

	public default void execute(Plan.Quest plan) {
		List<Rogue> roster = new ArrayList<Rogue>(roster());

		int teamSize = Dice.roll(3) + 2;
		if (teamSize > roster.size())
			teamSize = roster.size();

		Rogue.StressAscending leastStressed = new Rogue.StressAscending();
		Collections.sort(roster, leastStressed);
		roster = roster.subList(0, teamSize);

		//
		Score score = new Score(this, roster, plan);

		// TODO - testing
		// if (plan.getClient().equals(this))
		// System.out.println("Crew job. (Goal: " + plan.getGoal() + ")");
		// else
		// System.out.println("Job for " + plan.getClient().toString() + " (Goal: " +
		// plan.getGoal() + ")");

		//
		// System.out.println(score.toStringDetailed());
		// System.out.println();

		// TODO - determine engagement dice
		int dice = Dice.roll(3);
		score.action(dice);

	}

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

	public default EnumSet<CrewSpecial> skillSpecials() {
		CrewSpecial[] array = CrewSpecial.SKILL_SPECIALS;

		EnumSet<CrewSpecial> set = EnumSet.noneOf(CrewSpecial.class);
		for (int i = 0; i < array.length; ++i) {
			if (getSpecials().contains(array[i]))
				set.add(array[i]);
		}

		return set;
	}

	/*
	 * INNER CLASS - OBLIGATIONS
	 */
	public static class Obligations {
		private Faction owner;
		private Set<Faction> allies, friends, rivals, hostiles, enemies;

		public Obligations(Faction owner) {
			this.owner = owner;

			allies = new HashSet<Faction>();
			friends = new HashSet<Faction>();
			rivals = new HashSet<Faction>();
			hostiles = new HashSet<Faction>();
			enemies = new HashSet<Faction>();

			//
			updateObligations();
		}

		public String toString() {
			String string = "";

			string += "\nAllies: " + allies.toString();
			string += "\nFriends: " + friends.toString();
			string += "\nRivals: " + rivals.toString();
			string += "\nHostiles: " + hostiles.toString();
			string += "\nEnemies: " + enemies.toString();

			return string;
		}

		public Set<Faction> allies() {
			return allies;
		}

		public Set<Faction> friends() {
			return friends;
		}

		public Set<Faction> rivals() {
			return rivals;
		}

		public Set<Faction> hostiles() {
			return hostiles;
		}

		public Set<Faction> enemies() {
			return enemies;
		}

		public void clear() {
			enemies.clear();
			hostiles.clear();
			rivals.clear();
			friends.clear();
			allies.clear();
		}

		public void updateObligations() {
			clear();

			Ship ship;
			int score;
			Faction faction;
			for (Iterator<Ship> it = owner.getShips().iterator(); it.hasNext();) {
				ship = it.next();
				score = ship.getScore();
				faction = ship.getOther(owner);

				if (faction.active() && score < 2)
					enemies.add(faction);
				else if (faction.active() && score == 2)
					hostiles.add(faction);
				else if (faction.active() && (score == 3 || score == 4 || score == 5))
					rivals.add(faction);
				else if (faction.active() && score == 6)
					friends.add(faction);
				else if (faction.active() && score > 6)
					allies.add(faction);
			}
		}

		public Faction selectObligation() {
			int[] obs = new int[] { 0, 0, 0, 0, 0, 0 };

			if (enemies.size() > 0)
				obs[0] = 5;

			if (hostiles.size() > 0)
				obs[1] = 10;

			if (rivals.size() > 0)
				obs[2] = 25;

			if (friends.size() > 0)
				obs[4] = 15;

			if (allies.size() > 0)
				obs[5] = 20;

			// job board is variable
			int totalObs = obs[0] + obs[1] + obs[2] + obs[4] + obs[5];
			if (totalObs < 31)
				obs[3] = 60 - totalObs;
			else if (totalObs < 61)
				obs[3] = 75 - totalObs;

			// selection process
			Faction choice;
			int dice = Dice.roll(100);
			if (dice < obs[0])
				choice = Dice.randomFromSet(enemies);
			else if (dice < obs[0] + obs[1])
				choice = Dice.randomFromSet(hostiles);
			else if (dice < obs[0] + obs[1] + obs[2])
				choice = Dice.randomFromSet(rivals);
			else if (dice < obs[0] + obs[1] + obs[2] + obs[3])
				choice = Dice.randomFromSet(owner.factions());
			else if (dice < obs[0] + obs[1] + obs[2] + obs[3] + obs[4])
				choice = Dice.randomFromSet(friends);
			else if (dice < obs[0] + obs[1] + obs[2] + obs[3] + obs[4] + obs[5])
				choice = Dice.randomFromSet(allies);
			else
				choice = owner;

			return choice;
		}
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
		Score.Approach plan;
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
