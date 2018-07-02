public class Clock {
	public enum Length {
		FOUR, SIX, EIGHT, TEN, TWELVE
	}

	// static fields
	private static final Length[] ALL_LENGTHS = { Length.FOUR, Length.SIX, Length.EIGHT, Length.TEN, Length.TWELVE };
	private static final Length[] GOAL_LENGTHS = { Length.FOUR, Length.SIX, Length.EIGHT };

	// fields
	private int maxLength;
	private int remaining;

	// constructors
	public Clock() {
		this(randomGoalLength());
	}

	public Clock(Length length) {
		if (length.equals(Length.FOUR))
			this.maxLength = 4;
		else if (length.equals(Length.SIX))
			this.maxLength = 6;
		else if (length.equals(Length.EIGHT))
			this.maxLength = 8;
		else if (length.equals(Length.TEN))
			this.maxLength = 10;
		else if (length.equals(Length.TWELVE))
			this.maxLength = 12;

		remaining = maxLength;
	}

	public void clear() {
		remaining = 0;
	}

	public void countDown() {
		countDown(1);
	}

	public void countDown(int count) {
		remaining = (remaining - count < 0) ? 0 : remaining - count;
	}

	public boolean expired() {
		return (remaining < 1);
	}

	public int length() {
		return maxLength;
	}

	public int remaining() {
		return remaining;
	}

	public void reset() {
		remaining = maxLength;
	}

	// static methods
	public static Length randomLength() {
		Length[] array = ALL_LENGTHS;
		Length choice = array[Dice.roll(array.length) - 1];

		return choice;
	}

	public static Length randomGoalLength() {
		Length[] array = GOAL_LENGTHS;
		Length choice = array[Dice.roll(array.length) - 1];

		return choice;
	}
}
