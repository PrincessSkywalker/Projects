import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Collections;

public class KBestCounter<T extends Comparable<T>> {
   
    PriorityQueue<T> heap_ = new PriorityQueue<>();
    int k_;

    public KBestCounter(int k) {
        k_ = k;       
    }

    public void count(T x) {
        if(heap_.size() < k_){
            heap_.add(x);
        }
        else if (heap_.peek().compareTo(x) < 0){
            heap_.poll();
            heap_.add(x);
        }
    }

    public List<T> kbest() {
        ArrayList<T> list = new ArrayList<>(heap_.size());
        PriorityQueue<T> heap = new PriorityQueue<>();

        for(int i = heap_.size(); i > 0; i--){
            T x = heap_.poll();
            list.add(x);
            heap.add(x);
        }
        heap_ = heap;
        Collections.reverse(list);
        return list;
    }

    public static void main(String[] args) {
        int k = 25;
        KBestCounter <Integer> counter = new KBestCounter<>( k );
        List<Integer> arr = Arrays.asList(new Integer[] { 1, 8, 7, 10, 18, 25, 30, 42, 3, 9, 6, 14, 12, 10, 11, 99, 61, 323, 64, 81, 2, 4, 5, 2, 12, 52, 1, 4, 52, 443 });
        Iterator<Integer> stream = arr.iterator();

        for ( int i = 0; i < 20; i ++) 
          counter.count(stream.next());

        // print k largest after 200 elements
        List<Integer> kbest = counter.kbest();
        System.out.println(Arrays.toString(kbest.toArray()));

        for ( int i = 0; i < 10; i ++) 
          counter.count(stream.next());

        // print k largest after 110 elements
        kbest = counter.kbest();
        System.out.println(Arrays.toString(kbest.toArray()));
    }

}
