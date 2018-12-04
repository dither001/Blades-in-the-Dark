package model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bladesinthedark.actor.Actor;

public interface ActorLadder {
	/*
	 * COMPARATOR
	 */

	/*
	 * INNER CLASS - member STATUS
	 */
	public static class Status {
		private Actor owner;
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
		public Status(Actor owner) {
			this(true, owner);
		}

		public Status(boolean active, Actor owner) {
			this.memberID = 0;
			this.owner = owner;

			//
			this.active = active;

			//
			this.rank = 0;
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
			return cooldown;
		}

		void setCooldown(int cooldown) {
			this.cooldown = cooldown;
		}
	}

	/*
	 * INSTANCE METHODS
	 */
	public <T> boolean addCurrentMember(T member);

	public boolean addAllCurrentMembers(Collection<Actor> c);

	public Set<Actor> currentMemberSet();

	public boolean addFormerMember(Actor actor);

	public boolean addAllFormerMembers(Collection<Actor> c);

	public Set<Actor> formerMemberSet();

	public void setCurrentMembers(Collection<Actor> c);

	public void setFormerMembers(Collection<Actor> c);

	public List<String> nameList();

	public Actor getLeader();

	public void setLeader(Actor leader);

	public Map<Actor, Status> standings();

	public Set<Actor> standingKeySet();

	public Collection<Status> standingValueSet();

	/*
	 * DEFAULT METHODS
	 */
	public default void updateCooldowns() {
		Status current;
		for (Iterator<Status> it = standingValueSet().iterator(); it.hasNext();) {
			current = it.next();

			int cooldown = current.getCooldown();
			if (current.active())
				current.setCooldown(cooldown + 1);
		}
	}

	public default Set<Actor> ready() {
		List<Actor> list = new ArrayList<Actor>();
		Map<Actor, Status> map = standings();
		
		class Sort implements Comparator<Actor> {
			@Override
			public int compare(Actor left, Actor right) {

				return map.get(right).cooldown - map.get(right).cooldown;
			}			
		}
		
		
		
		
		// TODO
		return null;
	}
}
