/**
 * Simulates python's range function
 */
import java.util.ArrayList;

public class Range implements Iterable<Integer> {
	
	private ArrayList<Integer> array;

	public Range(int min, int max, int increment) {

		array = new ArrayList<Integer>();
		for(int i = min; i < max; i += increment){
			array.add(i);
		}
	}

	public Range(int min, int max) {
		this(min, max, 1);
	}

	public java.util.Iterator<Integer> iterator() {
		
		return array.iterator();
	}
}
