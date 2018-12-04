package model;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.bladesinthedark.crew.Faction;

public class Ship {
	public enum Nature {
		ALLIES, CONTACTS, RIVALS, ENEMIES
	}

	private static int lifetimeShips;
	private static Set<Ship> ships;

	/*
	 * INITIALIZATION
	 */
	static {
		lifetimeShips = 0;
		ships = new HashSet<Ship>();
	}

	/*
	 * INSTANCE FIELDS
	 */
	private int shipID;
	private Faction faction1, faction2;
	private Nature nature;
	private int score;

	/*
	 * CONSTRUCTORS
	 */
	public Ship(Faction faction1, Faction faction2, int score) {
		this.shipID = lifetimeShips++;

		//
		this.faction1 = faction1;
		this.faction2 = faction2;

		//
		this.score = score;
	}

	/*
	 * INSTANCE METHODS
	 */
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean contains(Faction faction) {
		boolean contains = false;

		if (faction1.equals(faction) || faction2.equals(faction))
			contains = true;

		return contains;
	}

	public Faction getOther(Faction faction) {
		Faction other;

		if (faction1.equals(faction))
			other = faction2;
		else
			other = faction1;

		return other;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s]", faction1, faction2);
	}

	public String toString(Faction faction) {
		return String.format("%s (%d)", getOther(faction), score);
	}

	/*
	 * STATIC METHODS
	 */
	// public static boolean addShip(Faction faction1, Faction faction2) {
	// return addShip(faction1, faction2, 0);
	// }

	public static boolean addShip(Faction faction1, Faction faction2, int score) {
		if (faction1.equals(faction2))
			return false;

		if (contains(faction1, faction2))
			return false;

		Ship ship = new Ship(faction1, faction2, score);
		ships.add(ship);
		// System.out.println("Added" + ship.toString());
		return true;
	}

	public static Ship get(Faction faction1, Faction faction2) {
		Ship candidate = null;
		boolean got1 = false, got2 = false;

		for (Iterator<Ship> it = ships.iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.faction1.equals(faction1) || candidate.faction2.equals(faction1))
				got1 = true;

			if (candidate.faction1.equals(faction2) || candidate.faction2.equals(faction2))
				got2 = true;

			if (got1 && got2) {
				break;

			} else {
				got1 = false;
				got2 = false;

			}

		}

		return candidate;
	}

	public static Set<Ship> getShips(Faction faction) {
		Set<Ship> set = new HashSet<Ship>();

		for (Ship el : ships) {
			if (el.contains(faction))
				set.add(el);
		}

		return set;
	}

	public static boolean contains(Faction faction1, Faction faction2) {
		boolean contains = false;

		boolean got1 = false, got2 = false;
		Ship candidate = null;
		for (Iterator<Ship> it = iterator(); it.hasNext();) {
			candidate = it.next();

			if (candidate.faction1.equals(faction1) || candidate.faction2.equals(faction1))
				got1 = true;

			if (candidate.faction1.equals(faction2) || candidate.faction2.equals(faction2))
				got2 = true;

			if (got1 && got2) {
				contains = true;
				break;

			} else {
				got1 = false;
				got2 = false;

			}

		}

		return contains;

	}

	public static Iterator<Ship> iterator() {
		return ships.iterator();
	}

	public static int size() {
		return ships.size();
	}

}
