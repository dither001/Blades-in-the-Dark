import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface FactionLadder {
	static final int MAX_ACTIVE = 6;

	/*
	 * INNER CLASS - member STATUS
	 */
	public static class Status {
		private Faction owner;
		private int memberID;

		//
		private boolean active;
		private boolean busy;

		//
		private int rank;
		private int cooldown;
		private int fame;
		private int infamy;

		// constructors
		public Status(Faction owner) {
			this(true, owner);
		}

		public Status(boolean active, Faction owner) {
			this.memberID = 0;
			this.owner = owner;

			//
			this.active = active;

			//
			this.rank = 1;
			this.cooldown = 0;
			this.fame = 0;
			this.infamy = 0;
		}

		// methods
		@Override
		public String toString() {
			String string;
			//
			String name = owner.toString();
			String active = (this.active) ? "(active)" : "(inactive)";

			string = String.format("%s %s || Age: %d", name, active, memberID);
			return string;
		}

		int getMemberID() {
			return memberID;
		}

		void setMemberID(int memberID) {
			this.memberID = memberID;
		}

		boolean active() {
			return active;
		}

		boolean inactive() {
			return active != true;
		}

		void activate() {
			this.active = true;
		}

		void deactivate() {
			this.active = false;
		}

		boolean available() {
			return busy != true;
		}

		void makeBusy() {
			this.busy = true;
		}

		void release() {
			this.busy = false;
		}

		int getCooldown() {
			// turnsSinceLastAction / ((lowestRank - rank > 0) ? lowestRank - rank : 1)
			return cooldown / rank;
		}

		void setCooldown(int cooldown) {
			this.cooldown = cooldown;
		}
	}

	/*
	 * INSTANCE METHODS
	 */
	public boolean addCurrentMember(Faction faction);

	public boolean addAllCurrentMembers(Collection<Faction> c);

	public Set<Faction> currentMemberSet();

	public boolean addFormerMember(Faction faction);

	public boolean addAllFormerMembers(Collection<Faction> c);

	public Set<Faction> formerMemberSet();

	public void setCurrentMembers(Collection<Faction> c);

	public void setFormerMembers(Collection<Faction> c);

	public List<String> nameList();

	public Faction getLeader();

	public void setLeader(Faction leader);

	public Map<Faction, Status> standings();

	public Set<Faction> standingKeySet();

	public Collection<Status> standingValueSet();

	public Set<Plan.Quest> quests();

	/*
	 * DEFAULT METHODS
	 */
	public default void updateLadder() {
		// post jobs
		quests().clear();
		for (Iterator<Faction> it = currentMemberSet().iterator(); it.hasNext();) {
			quests().addAll(it.next().getPlans());

		}

		// turns taken

		// update time

		// done! -> get ready
		Set<Faction> actors = ready();

		// done! -> set busy
		Status status;
		for (Faction el : actors) {
			status = standings().get(el);
			status.makeBusy();

		}

		// select action
		Set<Plan.Quest> quests;
		Plan.Quest quest;
		Faction client;
		for (Faction el : actors) {
			// System.out.println("Plans: " + el.getPlans().size());
			// System.out.println(el.getObligations().toString());
			client = el.obligations().selectObligation();

			if (el.equals(client)) {
				quest = Dice.randomFromSet(el.getPlans());

				System.out.println(el.toString() + " does a personal job");
				System.out.println(quest);
				System.out.println();

			} else {
				quests = filterForFaction(client, el);
				quest = Dice.randomFromSet(quests);

				System.out.println(el.toString() + " takes a job from " + client);
				System.out.println(quest);
				System.out.println();

			}
		}

		// update cooldowns
		updateCooldown();

		// release actors
		for (Faction el : actors) {
			status = standings().get(el);
			status.setCooldown(0);
			status.release();
		}

		// update plans for the next round
		for (Iterator<Faction> it = currentMemberSet().iterator(); it.hasNext();) {
			it.next().makePlans();
		}

		System.out.println("- - - - - -");
	}

	public default void updateCooldown() {
		Status current;
		for (Iterator<Status> it = standingValueSet().iterator(); it.hasNext();) {
			current = it.next();

			int cooldown = current.getCooldown();
			if (current.active())
				current.setCooldown(cooldown + 1);
		}
	}

	public default Set<Faction> ready() {
		Set<Faction> set = new HashSet<Faction>();

		List<Faction> list = new ArrayList<Faction>(currentMemberSet());
		Map<Faction, Status> map = standings();

		class Sort implements Comparator<Faction> {
			@Override
			public int compare(Faction left, Faction right) {

				return map.get(right).cooldown - map.get(left).cooldown;
			}
		}

		Collections.sort(list, new Sort());

		//
		for (int i = 0; i < MAX_ACTIVE; ++i) {
			set.add(list.get(i));
		}

		return set;
	}

	public default Set<Plan.Quest> filterForFaction(Faction faction, Faction exception) {
		Set<Plan.Quest> set = new HashSet<Plan.Quest>();

		Plan.Quest candidate;
		for (Iterator<Plan.Quest> it = quests().iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.getClient().equals(faction) && candidate.getTarget() != exception)
				set.add(candidate);
		}

		return set;
	}
}
