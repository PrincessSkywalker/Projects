public class AvlMap<K extends Comparable<? super K>, V> implements Map<K, V> {

  private AvlTree<Pair<K, V>> tree = new AvlTree<>();

  @Override
  public void put(K key, V value) {
    Pair<K, V> pair = new Pair<>(key, value);
    tree.insert(pair);
    return;
  }

  @Override
  public V get(K key) {
    Pair<K, V> pair = new Pair<>(key, null);
    Pair<K, V> returnPair = tree.get(pair);
    if(returnPair == null)
      return null;
    return returnPair.value;
  }
}
