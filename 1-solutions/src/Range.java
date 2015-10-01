public class Range implements Iterable<Integer> {
	private SimpleLinkedList<Integer> list;

	public Range(int min, int max, int increment) {
		list = new SimpleLinkedList<Integer>();

		// check for invalid min max increments
		// (max - min) * increment should be > 0
		if ((max - min) * increment <= 0) {
			throw new IllegalArgumentException();
		}

		for (int i = min; (increment > 0) ? i < max : i > max; i += increment) {
			list.add(i);
		}
	}

	public Range(int min, int max) {
		this(min, max, 1);
	}

	public java.util.Iterator<Integer> iterator() {
		return list.iterator();
	}

	public static void main(String[] args) {
		for (Integer j : new Range(1, 8, 1)) {
			System.out.println(j);
		}

		for (Integer j : new Range(1, 8, 2)) {
			System.out.println(j);
		}

		// should throw exception
		for (Integer j : new Range(1, 8, -2)) {
			System.out.println(j);
		}
	}
}
