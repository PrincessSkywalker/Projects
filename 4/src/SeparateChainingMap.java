import java.util.LinkedList;

public class SeparateChainingMap<K extends Comparable<? super K>, V> implements Map<K, V> {

  public static final int SCALE_FACTOR = 2;
  public static final int INITIAL_TABLE_SIZE = 8;
  public static final double MAX_LOAD_FACTOR = 1.0;

  private LinkedList<Pair<K,V>>[] map = (LinkedList<Pair<K,V>>[])new LinkedList[INITIAL_TABLE_SIZE];
  private int size = 0;

  public SeparateChainingMap() {
    for(int i = 0; i < map.length; i++)
      map[i] = new LinkedList<>();
  }

  public int getSize() {
    // returns number of pairs in map
    return size;
  }

  public int getTableSize() {
    // returns size of table
    return map.length;
  }

  public void put(K key, V value) {
    int i = Math.abs(key.hashCode()) % getTableSize();
    for(Pair<K, V> p : map[i]){
      if(p.key.equals(key)){
        p.value = value;
        return;
      }
    }
    map[i].add(new Pair<K, V>(key, value));
    size++;
    if((double)size / getTableSize() > MAX_LOAD_FACTOR){
      upsize();
    }
  }

  public V get(K key) {
    int i = Math.abs(key.hashCode()) % getTableSize();
    for(Pair<K, V> p : map[i]){
      if(p.key.equals(key)){
        return p.value;
      }
    }
    return null;
  }

  public void upsize() {
    LinkedList<Pair<K,V>>[] oldMap = map;
    map = (LinkedList<Pair<K,V>>[])new LinkedList[getTableSize() * SCALE_FACTOR];
    for(int i = 0; i < map.length; i++)
      map[i] = new LinkedList<>();
    size = 0;

    for(LinkedList<Pair<K, V>> list : oldMap) {
      for(Pair<K, V> pair : list){
        put(pair.key, pair.value);
      }
    }
  }
}
