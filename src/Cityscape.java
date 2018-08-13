import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cityscape implements FactionLadder {
	/*
	 * INSTANCE FIELDS
	 */
	private Faction leader;

	//
	private int lifetimeMembers;
	private Set<Faction> currentMembers;
	private Set<Faction> formerMembers;
	private Map<Faction, Status> standing;

	//
	private Set<Plan.Quest> questBoard;

	/*
	 * CONSTRUCTORS
	 */
	public Cityscape() {
		this.lifetimeMembers = 0;

		//
		this.currentMembers = new HashSet<Faction>();
		this.standing = new HashMap<Faction, Status>();

		//
		this.questBoard = new HashSet<Plan.Quest>();
	}

	/*
	 * INSTANCE METHODS
	 */
	public void update() {
		// post jobs
		questBoard.clear();
		for (Iterator<Faction> it = currentMembers.iterator(); it.hasNext();) {
			quests().addAll(it.next().getPlans());

		}

		// update ladder
		updateLadder();
	}

	public Set<Plan.Quest> quests() {
		return questBoard;
	}

	/*
	 * PRIVATE METHODS
	 */
	private <T> boolean addToSet(T el, Set<T> set) {
		boolean added = false;
		if (set.add(el))
			added = true;

		return added;
	}

	private <T> boolean addAllToSet(Collection<T> el, Set<T> set) {
		boolean added = false;
		if (set.addAll(el))
			added = true;

		return added;
	}

	/*
	 * CURRENT MEMBERS
	 */
	@Override
	public boolean addCurrentMember(Faction faction) {
		Set<Faction> set = currentMembers;

		boolean added = addToSet(faction, set);
		if (added) {
			Status status = new Status(faction);
			standing.put(faction, status);
			status.setMemberID(lifetimeMembers++);
		}

		return added;
	}

	@Override
	public boolean addAllCurrentMembers(Collection<Faction> c) {
		Set<Faction> set = currentMembers;
		boolean added = addAllToSet(c, set);

		Faction faction;
		Status status;
		for (Iterator<Faction> it = c.iterator(); it.hasNext();) {
			faction = it.next();

			if (standing.containsKey(faction) != true) {
				status = new Status(faction);

				standing.put(faction, status);
				status.setMemberID(lifetimeMembers++);
			}
		}

		return added;
	}

	@Override
	public Set<Faction> currentMemberSet() {
		return currentMembers;
	}

	/*
	 * FORMER MEMBERS
	 */
	@Override
	public boolean addFormerMember(Faction faction) {
		Set<Faction> set = formerMembers;

		boolean added = addToSet(faction, set);
		if (added) {
			Status status = new Status(faction);
			standing.put(faction, status);
			status.setCooldown(0);
		}

		return added;
	}

	@Override
	public boolean addAllFormerMembers(Collection<Faction> c) {
		Set<Faction> set = formerMembers;
		boolean added = addAllToSet(c, set);

		Faction faction;
		for (Iterator<Faction> it = c.iterator(); it.hasNext();) {
			faction = it.next();

			if (standing.containsKey(faction) != true) {
				Status status = new Status(faction);
				standing.put(faction, status);
				status.setCooldown(0);
			}
		}

		return added;
	}

	@Override
	public Set<Faction> formerMemberSet() {
		return formerMembers;
	}

	@Override
	public void setCurrentMembers(Collection<Faction> c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFormerMembers(Collection<Faction> c) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> nameList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Faction getLeader() {
		return leader;
	}

	@Override
	public void setLeader(Faction leader) {
		this.leader = leader;
	}

	@Override
	public Map<Faction, Status> standings() {
		return standing;
	}

	@Override
	public Set<Faction> standingKeySet() {
		return standing.keySet();
	}

	@Override
	public Collection<Status> standingValueSet() {
		return standing.values();
	}

}
