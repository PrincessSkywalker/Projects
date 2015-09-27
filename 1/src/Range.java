/**
 * Simulates python's range function
 */
public class Range implements Iterable<Integer> {
	
	private ArrayList<Integer> array;

	public Range(int min, int max, int increment) {

		array = new ArrayList<Integer>();
		for(int i = min; i < max; i += increment){
			array.add(i);
		}
	}

	public Range(int min, int max) {
		Range(min, max, 1);
	}

	public java.util.Iterator<Integer> iterator() {
		
		return array.iterator();
	}
}
