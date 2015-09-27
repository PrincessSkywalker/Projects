/**
 * Simulates python's range function
 */

public class Range implements Iterable<Integer> {
	
	private SimpleLinkedList<Integer> array;

	public Range(int min, int max, int increment) {

		array = new SimpleLinkedList<Integer>();

		if(min<max){

			for(int i = min; i < max; i += increment){
				array.add(i);
			}
		}
		else{

			for(int i = min; i > max; i += increment){
				array.add(i);
			}
		}
	}

	public Range(int min, int max) {
		this(min, max, 1);
	}

	public java.util.Iterator<Integer> iterator() {
		
		return array.iterator();
	}
}
