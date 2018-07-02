import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Dice {
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

}
