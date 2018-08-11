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

	/*
	 * DEFAULT METHODS
	 */
	public default void updateLadder() {
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
		Faction client;
		for (Faction el : actors) {
			System.out.println(el.getObligations().toString());
//			System.out.println(el.toString() + " does a job for " + client);
			
		}

		// update cooldowns
		updateCooldown();

		// release actors
		for (Faction el : actors) {
			status = standings().get(el);
			status.setCooldown(0);
			status.release();
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

		// Faction faction;
		// int cooldown;
		// String string;
		// for (Iterator<Faction> it = list.iterator(); it.hasNext();) {
		// faction = it.next();
		// cooldown = map.get(faction).cooldown;
		// string = String.format("(%2d) %s", cooldown, faction.toString());
		// System.out.println(string);
		// }

		//
		for (int i = 0; i < MAX_ACTIVE; ++i) {
			set.add(list.get(i));
		}

		// for (Iterator<Faction> it = set.iterator(); it.hasNext();) {
		// System.out.println(it.next());
		// }

		return set;
	}
}
