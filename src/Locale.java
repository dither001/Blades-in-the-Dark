import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Locale {
	//
	private static final int MAXIMUM_CAPACITY;
	private static final int NUMBER_OF_ZONES;
	private static final int[][] ZONING;

	static {
		MAXIMUM_CAPACITY = 6;
		NUMBER_OF_ZONES = 19;

		ZONING = new int[][] { { 1, 2, 3, 4, 5, 6 }, { 0, 2, 6, 7, 8, 9 }, { 0, 1, 3, 9, 10, 11 },
				{ 0, 2, 4, 11, 12, 13 }, { 0, 3, 5, 13, 14, 15 }, { 0, 4, 6, 15, 16, 17 }, { 0, 1, 5, 7, 17, 18 },
				{ 1, 6, 8, 18 }, { 1, 7, 9 }, { 1, 2, 8, 10 }, { 2, 9, 11 }, { 2, 3, 10, 12 }, { 3, 11, 13 },
				{ 3, 4, 12, 14 }, { 4, 13, 15 }, { 4, 5, 14, 16 }, { 5, 15, 17 }, { 5, 6, 16, 18 }, { 6, 7, 17 } };
	}

	/*
	 * STATIC INSTANCE
	 */
	private Map<Integer, Zone> zoneMap;

	/*
	 * CONSTRUCTORS
	 */
	public Locale() {
		List<Zone> zones = new ArrayList<Zone>();

		//
		for (int i = 0; i < NUMBER_OF_ZONES; ++i) {
			zones.add(new Zone(i));
		}

		Zone current;
		for (int i = 0; i < ZONING.length; ++i) {
			current = zones.get(i);

			for (int j = 0; j < ZONING[i].length; ++j) {
				current.neighbors.add(zones.get(ZONING[i][j]));
			}
		}

		zoneMap = new HashMap<Integer, Zone>();
		for (int i = 0; i < NUMBER_OF_ZONES; ++i) {
			zoneMap.put(i, zones.get(i));
		}
	}

	/*
	 * INSTANCE METHODS
	 */
	public List<Zone> zoneListByVacancy() {
		List<Zone> list = new ArrayList<Zone>(zoneMap.values());

		// anonymous comparator
		class sort implements Comparator<Zone> {
			@Override
			public int compare(Zone left, Zone right) {
				return right.remaining() - left.remaining();
			}
		}

		Collections.sort(list, new sort());

		return list;
	}

	public void localePrint() {
		// FIXME - testing
		for (Iterator<Zone> it = zoneMap.values().iterator(); it.hasNext();) {
			System.out.println(it.next().toStringDetailed());
		}
	}

	/*
	 * INNER CLASS - ZONE
	 */
	static class Zone {
		private int id;
		private List<Building> buildings;
		private List<Zone> neighbors;

		/*
		 * CONSTRUCTORS
		 */
		public Zone(int id) {
			this.id = id;

			this.buildings = new ArrayList<Building>();
			this.neighbors = new ArrayList<Zone>();
		}

		/*
		 * INSTANCE METHODS
		 */
		public boolean addBuilding(Stakeholder owner) {
			boolean added = false;
			if (!contains(owner) && remaining() > 0) {
				buildings.add(new Building(owner, this));
				added = true;
			}

			return added;
		}

		public boolean removeBuilding(Stakeholder stakeholder) {
			boolean removed = false;

			if (contains(stakeholder)) {
				Building current = null;
				for (Iterator<Building> it = buildings.iterator(); it.hasNext();) {
					current = it.next();

					if (current.owner != null && current.owner.equals(stakeholder)) {
						removed = true;
						break;
					}
				}

				if (removed)
					buildings.remove(current);
			}

			return removed;
		}

		public boolean evict(Stakeholder owner) {
			boolean evicted = false;

			if (contains(owner)) {
				Building current = null;
				for (Iterator<Building> it = buildings.iterator(); it.hasNext();) {
					current = it.next();

					if (current.owner != null && current.owner.equals(owner)) {
						current.owner = null;
						evicted = true;
						break;
					}
				}
			}

			return evicted;
		}

		public void evictAll() {
			for (Iterator<Building> it = buildings.iterator(); it.hasNext();) {
				it.next().owner = null;
			}
		}

		public Building getBuilding(Stakeholder owner) {
			Building building = null, current;

			for (Iterator<Building> it = buildings.iterator(); it.hasNext();) {
				current = it.next();

				if (current.owner != null && current.owner.equals(owner)) {
					building = current;
					break;
				}
			}

			return building;
		}

		public boolean contains(Stakeholder stakeholder) {
			boolean contains = false;

			Building current;
			for (Iterator<Building> it = buildings.iterator(); it.hasNext();) {
				current = it.next();

				if (current.owner != null && current.owner.equals(stakeholder)) {
					contains = true;
					break;
				}
			}

			return contains;
		}

		public int totalBuildings() {
			int size = 0;

			for (Iterator<Building> it = buildings.iterator(); it.hasNext();) {
				size += it.next().size;
			}

			return size;
		}

		public int remaining() {
			return MAXIMUM_CAPACITY - totalBuildings();
		}

		@Override
		public String toString() {
			return String.format("%2d", id);
		}

		public String toStringDetailed() {
			return String.format("id: %2d %s", id, neighbors.toString());
		}
	}

	/*
	 * INNER CLASS - BUILDING
	 */
	public static class Building {
		private Stakeholder owner;
		private Zone home;

		//
		private int tier;
		private int size;

		// constructor
		public Building(Stakeholder owner, Zone home) {
			this.owner = owner;
			this.home = home;

			//
			this.tier = 0;
			this.size = 1;
		}

		public String toString() {
			return String.format("Building: (%s, %s)", home.toString(), owner.toString());
		}
	}

}
