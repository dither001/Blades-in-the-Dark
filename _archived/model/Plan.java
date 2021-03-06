package model;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.bladesinthedark.crew.CrewType;
import com.bladesinthedark.crew.Faction;
import com.bladesinthedark.rules.Dice;

public interface Plan {

	public enum Goal {
		// CLIMB from "social climb"
		// SHAKE from "shaking someone else"
		ASSIST, CLIMB, CLAIM, RECRUIT, SHAKE
	}

	public enum Approach {
		ASSAULT, DECEPTION, STEALTH, OCCULT, SOCIAL, TRANSPORT
	}

	public enum Activity {
		ACCIDENT, DISAPPEARANCE, MURDER, RANSOM, //
		BATTLE, EXTORTION, SABOTAGE, SMASH_N_GRAB, //
		ACQUISITION, AUGURY, CONSECRATION, SACRIFICE, //
		SALES, SUPPLY, SHOW_OF_FORCE, SOCIALIZE, //
		BURGLARY, ESPIONAGE, ROBBERY, // also SABOTAGE
		CARGO_ARMS, CARGO_CONTRABAND, CARGO_PEOPLE, CARGO_WEIRD
	}

	/*
	 * STATIC FIELDS
	 */
	public static final Approach[] ALL_PLANS = { Approach.ASSAULT, Approach.DECEPTION, Approach.STEALTH,
			Approach.OCCULT, Approach.SOCIAL, Approach.TRANSPORT };
	public static final String[] DETAILS = { "Point of attack.", "Method of deception.", "Point of infiltration.",
			"Arcane method.", "Social connection.", "Route & means." };

	public static final Activity[] ALL_ACTIVITIES = { Activity.ACCIDENT, Activity.DISAPPEARANCE, Activity.MURDER,
			Activity.RANSOM, Activity.BATTLE, Activity.EXTORTION, Activity.SABOTAGE, Activity.SMASH_N_GRAB,
			Activity.ACQUISITION, Activity.AUGURY, Activity.CONSECRATION, Activity.SACRIFICE, Activity.SALES,
			Activity.SUPPLY, Activity.SHOW_OF_FORCE, Activity.SOCIALIZE, Activity.BURGLARY, Activity.ESPIONAGE,
			Activity.ROBBERY, Activity.CARGO_ARMS, Activity.CARGO_CONTRABAND, Activity.CARGO_PEOPLE,
			Activity.CARGO_WEIRD };

	public static final Activity[] ASSASSIN_FLAVORS = { Activity.ACCIDENT, Activity.DISAPPEARANCE, Activity.MURDER,
			Activity.RANSOM };
	public static final Activity[] BRAVO_FLAVORS = { Activity.BATTLE, Activity.EXTORTION, Activity.SABOTAGE,
			Activity.SMASH_N_GRAB };
	public static final Activity[] CULT_FLAVORS = { Activity.ACQUISITION, Activity.AUGURY, Activity.CONSECRATION,
			Activity.SACRIFICE };
	public static final Activity[] HAWKER_FLAVORS = { Activity.SALES, Activity.SUPPLY, Activity.SHOW_OF_FORCE,
			Activity.SOCIALIZE };
	public static final Activity[] SHADOW_FLAVORS = { Activity.BURGLARY, Activity.ESPIONAGE, Activity.ROBBERY,
			Activity.SABOTAGE };
	public static final Activity[] SMUGGLER_FLAVORS = { Activity.CARGO_ARMS, Activity.CARGO_CONTRABAND,
			Activity.CARGO_PEOPLE, Activity.CARGO_WEIRD };

	/*
	 * STATIC METHODS
	 */
	public static Approach randomPlan() {
		return Dice.randomFromArray(ALL_PLANS);
	}

	public static String detail(Approach plan) {
		String string = "";

		if (plan.equals(Approach.ASSAULT))
			string = DETAILS[0];
		else if (plan.equals(Approach.DECEPTION))
			string = DETAILS[1];
		else if (plan.equals(Approach.STEALTH))
			string = DETAILS[2];
		else if (plan.equals(Approach.OCCULT))
			string = DETAILS[3];
		else if (plan.equals(Approach.SOCIAL))
			string = DETAILS[4];
		else if (plan.equals(Approach.TRANSPORT))
			string = DETAILS[5];

		return string;
	}

	public static Activity randomActivity() {
		return Dice.randomFromArray(ALL_ACTIVITIES);
	}

	public static Activity randomActivity(CrewType crew) {
		Activity[] array = ALL_ACTIVITIES;

		if (crew.equals(CrewType.ASSASSINS))
			array = ASSASSIN_FLAVORS;
		else if (crew.equals(CrewType.BRAVOS))
			array = BRAVO_FLAVORS;
		else if (crew.equals(CrewType.CULT))
			array = CULT_FLAVORS;
		else if (crew.equals(CrewType.HAWKERS))
			array = HAWKER_FLAVORS;
		else if (crew.equals(CrewType.SHADOWS))
			array = SHADOW_FLAVORS;
		else if (crew.equals(CrewType.SMUGGLERS))
			array = SMUGGLER_FLAVORS;

		return Dice.randomFromArray(array);
	}

	public static Set<Quest> filterForFaction(Faction faction, Faction exception, Set<Quest> quests) {
		Set<Plan.Quest> set = new HashSet<Plan.Quest>();

		Plan.Quest candidate;
		for (Iterator<Plan.Quest> it = quests.iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.getClient().equals(faction) && candidate.getTarget() != exception)
				set.add(candidate);
		}

		return set;
	}

	/*
	 * INNER CLASS - MOTIVE
	 */
	public static class Quest {
		private Faction client;

		//
		private Faction target;
		private Goal goal;
		private Approach approach;
		private Activity activity;

		public Quest(Faction client, Object... objects) {
			this.setClient(client);
			this.setTarget(null);
			this.setGoal(null);
			this.setApproach(null);
			this.setActivity(null);

			for (Object o : objects) {
				// target
				if (o instanceof Faction)
					setTarget((Faction) o);

				// goal
				if (o instanceof Goal)
					setGoal((Goal) o);

				// approach
				if (o instanceof Approach)
					setApproach((Approach) o);

				// activity
				if (o instanceof Activity)
					setActivity((Activity) o);

			}

		}

		public int points() {
			int points = 0;

			points += (target != null) ? 1 : 0;
			points += (goal != null) ? 1 : 0;
			points += (approach != null) ? 1 : 0;
			points += (activity != null) ? 1 : 0;

			return points;
		}

		public Faction getClient() {
			return client;
		}

		public void setClient(Faction client) {
			this.client = client;
		}

		public Faction getTarget() {
			return target;
		}

		public void setTarget(Faction target) {
			this.target = target;
		}

		public Goal getGoal() {
			return goal;
		}

		public void setGoal(Goal goal) {
			this.goal = goal;
		}

		public Approach getApproach() {
			return approach;
		}

		public void setApproach(Approach approach) {
			this.approach = approach;
		}

		public Activity getActivity() {
			return activity;
		}

		public void setActivity(Activity activity) {
			this.activity = activity;
		}

		public String toString() {
			// String string = String.format("Client: %s || Target: %s || Goal: %s", client,
			// target, goal);

			return String.format("%s|%s|%s", client, target, goal);
		}

		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;

			if (o instanceof Quest != true)
				return false;

			Quest q = (Quest) o;
			boolean sameClient = false, sameTarget = false, sameGoal = false;

			if (q.client.equals(this.client))
				sameClient = true;

			if (q.target.equals(this.target))
				sameTarget = true;

			if (q.goal.equals(this.goal))
				sameGoal = true;

			return sameClient && sameTarget && sameGoal;
		}

		@Override
		public int hashCode() {
			int result = 13, c = client.hashCode();

			if (target != null)
				c += target.hashCode();

			if (goal != null)
				c += goal.hashCode();

			return 37 * result + c;
		}

	}

}
