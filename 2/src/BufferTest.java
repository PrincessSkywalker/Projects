public class BufferTest{
	public static void main(String[] args) {
		FastBuffer buffer = new FastBuffer();
		buffer.load(new char[]{'a', 'b', 'c', 'd'}, 3);
		System.out.println("Size: " + buffer.size());
		System.out.println(buffer.toArray());
		System.out.println(buffer.getCursor());

		buffer.setCursor(2);
		System.out.println("Size: " + buffer.size());
		System.out.println(buffer.toArray());
		System.out.println(buffer.getCursor());

		buffer.moveRight();
		System.out.println("Size: " + buffer.size());
		System.out.println(buffer.toArray());
		System.out.println(buffer.getCursor());

		buffer.moveLeft();
		System.out.println("Size: " + buffer.size());
		System.out.println(buffer.toArray());
		System.out.println(buffer.getCursor());

		buffer.insertLeft('z');
		System.out.println("Size: " + buffer.size());
		System.out.println(buffer.toArray());
		System.out.println(buffer.getCursor());

		buffer.deleteRight();
		System.out.println("Size: " + buffer.size());
		System.out.println(buffer.toArray());
		System.out.println(buffer.getCursor());

		buffer.deleteLeft();
		System.out.println("Size: " + buffer.size());
		System.out.println(buffer.toArray());
		System.out.println(buffer.getCursor());
	}
}