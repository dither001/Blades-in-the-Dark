import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public abstract class Dice {
	// static fields
	private static final Random RAND = new Random();

	// static methods
	public static int roll(int faces) {
		return roll(1, faces);
	}

	public static int roll(int dice, int faces) {
		int result = 0;

		dice = (dice < 1) ? 1 : dice;
		faces = (faces < 1) ? 1 : faces;

		for (int i = 0; i < dice; ++i) {
			result += RAND.nextInt(faces) + 1;
		}

		return result;
	}

	public static byte[] rollAbilities() {
		byte[] array = new byte[] { 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < array.length; ++i) {
			array[i] += roll(3, 6);
		}

		return array;
	}

	public static int advantage(int faces) {
		int firstRoll = roll(1, faces);
		int secondRoll = roll(1, faces);

		return (firstRoll > secondRoll) ? firstRoll : secondRoll;
	}

	public static int disadvantage(int faces) {
		int firstRoll = roll(1, faces);
		int secondRoll = roll(1, faces);

		return (firstRoll < secondRoll) ? firstRoll : secondRoll;
	}

//	public static byte[] rollHitDice(Class job) {
//		int hitDieSize = Class.getHitDie(job);
//
//		byte[] hitDice = new byte[20];
//		for (int i = 0; i < hitDice.length; ++i) {
//			hitDice[i] = (byte) roll(hitDieSize);
//		}
//
//		return hitDice;
//	}

	public static int[] fortune() {
		return fortune(0, 6);
	}

	public static int[] fortune(int dice) {
		return fortune(dice, 6);
	}

	public static int[] fortune(int dice, int faces) {
		int[] fortune = new int[faces];

		if (dice > 0) {
			for (int i = 0; i < dice; ++i) {
				++fortune[roll(faces) - 1];
			}
		} else {
			++fortune[disadvantage(faces) - 1];
		}

		return fortune;
	}

	public static <T> T randomFromArray(T[] array) {
		T choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	@SuppressWarnings("unchecked")
	public static <T> T randomFromList(List<T> list) {
		T[] array = (T[]) new Object[list.size()];
		list.toArray(array);
		T choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	@SuppressWarnings("unchecked")
	public static <T> T randomFromSet(Set<T> set) {
		T[] array = (T[]) new Object[set.size()];
		set.toArray(array);
		T choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static <T> List<T> arrayToList(T[] array) {
		List<T> list = new ArrayList<T>();

		for (T el : array)
			list.add(el);

		return list;
	}

	public static <T> List<T> mapToList(Map<T, Integer> map) {
		List<T> list = new ArrayList<T>();

		for (Iterator<T> it = map.keySet().iterator(); it.hasNext();) {
			list.add(it.next());
		}

		// Anonymous Comparator class to sort the list by map values
		class sort implements Comparator<T> {
			@Override
			public int compare(T left, T right) {
				return map.get(left) - map.get(right);
			}

		}

		Collections.sort(list, new sort());

		return list;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] setToArray(Set<T> set) {
		T[] array = (T[]) new Object[set.size()];

		Iterator<T> it = set.iterator();
		for (int i = 0; i < array.length; ++i) {
			array[i] = it.next();
		}

		return array;
	}

	public static <T> List<T> setToList(Set<T> set) {
		return new ArrayList<T>(set);
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> sublistFromList(int length, List<T> list) {
		T[] array = (T[]) new Object[list.size()];
		list.toArray(array);

		List<T> sublist = new ArrayList<T>();
		T choice;
		while (sublist.size() < length) {
			choice = array[Dice.roll(array.length) - 1];

			if (sublist.contains(choice) != true)
				sublist.add(choice);
		}

		return sublist;
	}

	/*
	 * addToSet is one of my most important and useful methods which I should use
	 * basically all the time to avoid code repetition
	 */
	public static <T> Set<T> randomAddToSet(T[] array, Set<T> set) {
		return randomAddToSet(1, array, set);
	}

	/*
	 * addToSet is one of my most important and useful methods which I should use
	 * basically all the time to avoid code repetition
	 */
	public static <T> Set<T> randomAddToSet(int toAdd, T[] array, Set<T> set) {
		int added = 0;
		T candidate;

		while (added < toAdd) {
			candidate = randomFromArray(array);

			if (set.add(candidate))
				++added;
		}

		return set;
	}

	public static <T> Set<T> randomAddToSet(Set<T> fromSet, Set<T> toSet) {
		return randomAddToSet(1, fromSet, toSet);
	}

	public static <T> Set<T> randomAddToSet(int toAdd, Set<T> fromSet, Set<T> toSet) {
		int added = 0;
		T candidate;

		while (added < toAdd) {
			candidate = randomFromSet(fromSet);

			if (toSet.add(candidate))
				++added;
		}

		return toSet;
	}

	public static <T> Set<T> addAllToSet(T[] array, Set<T> set) {
		for (T el : array)
			set.add(el);

		return set;
	}

	public static <T> Set<T> addAllToSetOrElse(T[] array1, T[] array2, Set<T> set) {
		int toAdd = array1.length, added = 0;

		for (int i = 0; i < array1.length; ++i) {
			if (set.add(array1[i]))
				++added;
		}

		T candidate;
		while (added < toAdd) {
			candidate = randomFromArray(array2);

			if (set.contains(candidate) != true) {
				set.add(candidate);
				++added;
			}

		}

		return set;
	}

	public static boolean containsIntegerOrGreater(int integer, List<Integer> list) {
		boolean contains = false;

		for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
			if (it.next() >= integer) {
				contains = true;
				break;
			}
		}

		return contains;
	}

	public static boolean containsLessThanInteger(int integer, List<Integer> list) {
		boolean contains = false;

		for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
			if (it.next() < integer) {
				contains = true;
				break;
			}
		}

		return contains;
	}
}
