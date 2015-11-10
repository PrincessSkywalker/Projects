public class Test {
	public static void main(String[] args) {
		Map<String, String> m = new AvlMap<>();

		m.put("hello", "world");
		m.put("icecream", "sandwich");

		System.out.println(m.get("hello"));
		System.out.println(m.get("bye"));

		m.put("hello", "turtle");
		System.out.println(m.get("hello"));

		SeparateChainingMap m2 = new SeparateChainingMap<>();
		m2.put("hello", "world");
		m2.put("icecream", "sandwich");
		m2.put("a", "b");
		m2.put("c", "d");
		m2.put("e", "f");
		m2.put("g", "h");
		m2.put("i", "j");
		m2.put("k", "l");

		System.out.println(m2.getTableSize());
		m2.put("m", "n");
		System.out.println(m2.getTableSize());
		System.out.println(m2.get("hello"));
		System.out.println(m2.get("bye"));

		m2.put("hello", "turtle");
		System.out.println(m2.get("hello"));
	}
}