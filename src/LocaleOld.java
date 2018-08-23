import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class LocaleOld {
	//
	private static final int MAXIMUM_CAPACITY;
	private static final int MAXIMUM_NEIGHBORS;
	private static final int MAXIMUM_RADIUS;

	private static final Point[] ORIGIN_POINTS;

	/*
	 * INITIALIZATION
	 */
	static {
		MAXIMUM_CAPACITY = 6;
		MAXIMUM_NEIGHBORS = 6;
		MAXIMUM_RADIUS = 2;

		// ORIGIN_POINTS = new Point[] { new Point(-1, 1), new Point(2, 1), new
		// Point(-1, -1), new Point(-1, -2),
		// new Point(2, -2), new Point(-2, -1), new Point(1, -2), new Point(2, -1), new
		// Point(0, -1),
		// new Point(1, 2), new Point(-1, 0), new Point(-1, 2), new Point(1, -1), new
		// Point(0, 1),
		// new Point(0, -2), new Point(1, 1), new Point(1, 0), new Point(0, 2), new
		// Point(-2, 2), new Point(0, 0),
		// new Point(-2, 0), new Point(-2, 1), new Point(2, 0), new Point(-2, -2), new
		// Point(2, 2) };

		ORIGIN_POINTS = new Point[] { new Point(-2, 1), new Point(-2, 0), new Point(-1, 2), new Point(-1, 1),
				new Point(-1, 0), new Point(-1, -1), new Point(-1, -2), new Point(0, 2), new Point(0, 1),
				new Point(0, 0), new Point(0, -1), new Point(0, -2), new Point(1, 2), new Point(1, 1), new Point(1, 0),
				new Point(1, -1), new Point(1, -2), new Point(2, 0), new Point(2, -1) };

	}

	/*
	 * INSTANCE FIELDS
	 */
	private Cluster home;
	private int localeID;
	private Point origin;
	private Set<Building> buildings;
	private Set<LocaleOld> neighbors;
	private Set<Faction> stakes;

	public LocaleOld(Point origin, Cluster home) {
		this.home = home;
		this.localeID = home.lifetimeLocales++;
		this.origin = origin;
		this.buildings = new HashSet<Building>();
		this.neighbors = new HashSet<LocaleOld>();
		this.stakes = new HashSet<Faction>();
	}

	/*
	 * INSTANCE METHODS
	 */
	public boolean contains(Faction faction) {
		return getBuilding(faction) != null;
	}

	public int size() {
		int size = 0;
		for (Iterator<Building> it = buildings.iterator(); it.hasNext();) {
			size += it.next().size;
		}

		return size;
	}

	public int vacancy() {
		return MAXIMUM_CAPACITY - size();
	}

	public boolean occupied() {
		return buildings.size() > 0;
	}

	public Set<Faction> residents() {
		Set<Faction> set = new HashSet<Faction>();

		Building building;
		for (Iterator<Building> it = buildings.iterator(); it.hasNext();) {
			building = it.next();

			if (building != null)
				set.add(building.owner);

		}

		return set;
	}

	public boolean upgradeBuilding(Faction faction) {
		boolean upgraded = false;

		int vacancy = vacancy();
		Building building = getBuilding(faction);
		if (building == null && vacancy > 0) {
			addBuilding(faction);

		} else if (building.tier == 1 && vacancy >= building.tier * 2) {
			building.size *= 2;
			++building.tier;
			upgraded = true;

		} else if (building.tier == 0 && vacancy >= 1) {
			building.size *= 2;
			++building.tier;
			upgraded = true;

		}

		return upgraded;
	}

	public boolean addBuilding(Faction faction) {
		if (size() > 5)
			return false;

		if (contains(faction))
			return false;

		boolean add = false;
		if (buildings.add(new Building(faction, this)))
			add = true;

		return add;
	}

	public Building getBuilding(Faction faction) {
		if (buildings.isEmpty())
			return null;

		Building candidate = null;
		for (Iterator<Building> it = buildings.iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.owner.equals(faction))
				break;
			else
				candidate = null;

		}

		return candidate;
	}

	public Faction enmityClause(Faction stakeHolder) {
		Faction resident, oldest = null;

		for (Iterator<Faction> it = residents().iterator(); it.hasNext();) {
			resident = it.next();

			if (oldest == null)
				oldest = resident;
			else if (resident.factionID() < oldest.factionID())
				oldest = resident;

		}

		return oldest;
	}

	public Set<Faction> testAddStake(Faction stakeHolder) {
		Set<Faction> otherClaims = new HashSet<Faction>();

		boolean staked = false;
		if (stakes.contains(stakeHolder) != true) {
			staked = true;
		}

		if (staked) {
			for (Iterator<Faction> it = residents().iterator(); it.hasNext();) {
				otherClaims.add(it.next());
			}
		}

		return otherClaims;
	}

	public Set<Faction> addStake(Faction stakeHolder) {
		Set<Faction> otherClaims = new HashSet<Faction>();

		boolean staked = false;
		if (stakes.contains(stakeHolder) != true) {
			stakes.add(stakeHolder);
			staked = true;
		}

		if (staked) {
			for (Iterator<Faction> it = residents().iterator(); it.hasNext();) {
				otherClaims.add(it.next());
			}
		}

		return otherClaims;
	}

	public Set<LocaleOld> neighborSet() {
		return neighbors;
	}

	public String toString() {
		// String string = String.format("Locale %d (%d, %d) Vacancy: %d", localeID,
		// origin.x, origin.y, vacancy());
		String string = String.format("Locale %d", localeID);

		return string;
	}

	public String toStringDetailed() {
		String string = String.format("Locale: %2d ", localeID);
		// string = String.format("This is Locale %d (%d, %d) Neighbors: %d", localeID,
		// origin.x, origin.y,
		// neighbors.size());
		// string += "\n" + neighbors.toString();

		for (Building el : buildings)
			string += "\n" + el.toString();

		return string;
	}

	private void neighborSetup() {
		Set<Point> set = home.findPoints(origin.adjacent());

		LocaleOld locale;
		for (Iterator<Point> it = set.iterator(); it.hasNext();) {
			locale = home.pointMap.get(it.next());

			if (locale != null)
				neighbors.add(locale);
		}
	}

	/*
	 * STATIC METHODS
	 * 
	 */
	public static Cluster cluster() {
		return new Cluster();
	}

	private static List<Point> orderedPoints() {
		List<Point> list = Dice.arrayToList(ORIGIN_POINTS);

		class sort implements Comparator<Point> {
			@Override
			public int compare(Point point1, Point point2) {
				int leftX = (point1.x < 0) ? -point1.x : point1.x;
				int rightX = (point2.x < 0) ? -point2.x : point2.x;
				//
				int leftY = (point1.y < 0) ? -point1.y : point1.y;
				int rightY = (point2.y < 0) ? -point2.y : point2.y;

				//
				int left = leftX + leftY, right = rightX + rightY;

				//
				return left - right;
			}
		}

		Collections.sort(list, new sort());

		return list;
	}

	/*
	 * INNER CLASS - CLUSTER
	 * 
	 */
	public static class Cluster {
		// instance fields

		private int lifetimeLocales;
		private Map<Point, LocaleOld> pointMap;

		// constructors
		public Cluster() {
			//
			this.pointMap = new HashMap<Point, LocaleOld>();

			for (Point el : orderedPoints()) {
				// setup locations
				pointMap.put(el, new LocaleOld(el, this));
			}

			LocaleOld locale;
			for (Iterator<Point> it = pointMap.keySet().iterator(); it.hasNext();) {
				locale = pointMap.get(it.next());
				// setup neighbors
				locale.neighborSetup();
			}
		}

		// instance methods
		public LocaleOld findVacancy() {
			return findVacancy(0, null);
		}

		public LocaleOld findVacancy(int startIndex) {
			return findVacancy(startIndex, null);
		}

		public LocaleOld findVacancy(LocaleOld exclude) {
			return findVacancy(0, exclude);
		}

		public LocaleOld findVacancy(int startIndex, LocaleOld exclude) {
			LocaleOld locale = null;

			List<LocaleOld> list = localeListByVacancy();
			int counter = startIndex;
			for (Iterator<LocaleOld> it = list.iterator(); it.hasNext();) {
				locale = it.next();

				if (exclude != null && locale.equals(exclude) && it.hasNext())
					locale = it.next();

				if (counter > 0 && locale.vacancy() > 0) {
					locale = null;
					--counter;
				} else if (counter == 0 && locale.vacancy() > 0) {
					break;

				} else {
					locale = null;

				}
			}

			return locale;
		}

		public LocaleOld findStake(Set<LocaleOld> exclude) {
			LocaleOld candidate = null;

			List<LocaleOld> list = localeListByStakes();
			for (Iterator<LocaleOld> it = list.iterator(); it.hasNext();) {
				candidate = it.next();

				if (candidate.occupied() && exclude.contains(candidate) != true)
					break;
			}

			return candidate;
		}

		public LocaleOld getLocale(int localeID) {
			LocaleOld locale = null;
			for (Iterator<LocaleOld> it = localeList().iterator(); it.hasNext();) {
				locale = it.next();

				if (locale.localeID == localeID)
					break;
				else
					locale = null;
			}

			return locale;
		}

		public List<LocaleOld> localeList() {
			return localeListByID();
		}

		public List<LocaleOld> localeListByID() {
			List<LocaleOld> list = new ArrayList<LocaleOld>(pointMap.values());

			// anonymous comparator
			class sort implements Comparator<LocaleOld> {
				@Override
				public int compare(LocaleOld left, LocaleOld right) {
					return left.localeID - right.localeID;
				}
			}

			Collections.sort(list, new sort());

			return list;
		}

		public List<LocaleOld> localeListByVacancy() {
			List<LocaleOld> list = new ArrayList<LocaleOld>(pointMap.values());

			// anonymous comparator
			class sort implements Comparator<LocaleOld> {
				@Override
				public int compare(LocaleOld left, LocaleOld right) {
					return right.vacancy() - left.vacancy();
				}
			}

			Collections.sort(list, new sort());

			return list;
		}

		public List<LocaleOld> localeListByStakes() {
			List<LocaleOld> list = new ArrayList<LocaleOld>(pointMap.values());

			// anonymous comparator
			class sort implements Comparator<LocaleOld> {
				@Override
				public int compare(LocaleOld left, LocaleOld right) {
					return left.stakes.size() - right.stakes.size();
				}
			}

			Collections.sort(list, new sort());

			return list;
		}

		private Set<Point> findPoints(Set<Point> set) {
			Set<Point> workingSet = new HashSet<Point>();

			Point current, candidate;
			Iterator<Point> it1, it2;
			for (it1 = set.iterator(); it1.hasNext();) {
				current = it1.next();

				for (it2 = pointMap.keySet().iterator(); it2.hasNext();) {
					candidate = it2.next();

					if (current.x == candidate.x && current.y == candidate.y) {
						workingSet.add(candidate);
						break;
					}
				}
			}

			return workingSet;
		}

	}

	/*
	 * INNER CLASS - BUILDING
	 * 
	 */
	public static class Building {
		private Faction owner;
		private LocaleOld home;
		//
		private byte tier;
		private byte size;

		// constructor
		public Building(Faction owner, LocaleOld home) {
			this.owner = owner;
			this.home = home;
			//
			this.tier = 0;
			this.size = 1;
		}

		public String toString() {
			return String.format("(%s, %s)", home, owner);
		}
	}

	/*
	 * INNER CLASS - POINT
	 * 
	 */
	public static class Point {
		private int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			boolean equals = false;

			if (o == this)
				return true;

			if (o instanceof Point != true)
				return false;

			Point p = (Point) o;
			equals = p.x == this.x && p.y == this.y;

			return equals;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		public Set<Point> adjacent() {
			Set<Point> set = new HashSet<Point>();

			//
			Point[] points = new Point[6];
			points[0] = new Point(x + 1, y);
			points[1] = new Point(x, y - 1);
			points[2] = new Point(x - 1, y - 1);
			points[3] = new Point(x - 1, y);
			points[4] = new Point(x, y + 1);
			points[5] = new Point(x + 1, y + 1);

			//
			boolean inBounds;
			for (Point el : points) {
				inBounds = true;

				if (el.x < -MAXIMUM_RADIUS || el.x > MAXIMUM_RADIUS)
					inBounds = false;

				if (el.y < -MAXIMUM_RADIUS || el.y > MAXIMUM_RADIUS)
					inBounds = false;

				if (inBounds)
					set.add(el);
			}

			return set;
		}

		@Override
		public String toString() {
			return String.format("(%d, %d)", x, y);
		}
	}

}
