/**
 * LinkedList class implements a doubly-linked list. Adapted from Weiss, Data
 * Structures and Algorithm Analysis in Java. 3rd ed.
 * http://users.cis.fiu.edu/~weiss/dsaajava3/code/SimpleLinkedList.java
 */
public class SimpleLinkedList<T> implements Iterable<T> {

	private int size;
	private Node<T> beginMarker;
	private Node<T> endMarker;
	private boolean reversed = false;

	/**
	 * This is the doubly-linked list node class.
	 */
	private static class Node<NodeT> {
		public Node(NodeT d, Node<NodeT> p, Node<NodeT> n) {
			data = d;
			prev = p;
			next = n;
		}

		public NodeT data;
		public Node<NodeT> prev;
		public Node<NodeT> next;
	}

	public int indexOf(Object o) {
		int index = 0;
		for (T data : this) {
			if (data.equals(o)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public void reverse() {
		Node<T> current = beginMarker;

		Node<T> temp = beginMarker;
		beginMarker = endMarker;
		endMarker = temp;

		while (current != null) {
			Node<T> oldNext = current.next;
			current.next = current.prev;
			current.prev = oldNext;
			current = oldNext;
		}
	}

	public void quickReverse() {
		reversed = !reversed;
	}

	public void removeDuplicates() {
		if (size() < 2)
			return;

		Node<T> nodeI = beginMarker.next;
		while (nodeI != endMarker) {
			Node<T> nodeJ = nodeI.next;
			while (nodeJ != endMarker) {
				if (nodeI.data.equals(nodeJ.data))
					remove(nodeJ);
				// Even though we may just have removed nodeJ,
				// it is still in memory, so we can get it's next
				// reference.
				nodeJ = nodeJ.next;
			}
			nodeI = nodeI.next;
		}
	}

	public void interleave(SimpleLinkedList<T> other) {

		if (size() == 0) { // special case: this list is empty
			for (T o : other)
				add(o);
			return;
		}

		Node<T> current = beginMarker.next;
		java.util.Iterator<T> otherIter = other.iterator();

		while (current != endMarker && otherIter.hasNext()) {
			Node<T> currentNext = current.next;
			// It's not a good idea to use add(idx,e) here. If you did so,
			// the additional time required to find the element at idx before
			// the add had to be factored into your running time analysis.
			T otherItem = otherIter.next();
			Node<T> newNode = new Node<>(otherItem, current, current.next);
			currentNext.prev = newNode;
			current.next = newNode;
			current = currentNext;
		}

		// It is possible that we have reached the end of this list and
		// there are still elements in other, so we simply append each of them
		// to this list. Note that add at the end of the list takes constant
		// time.
		while (otherIter.hasNext())
			add(otherIter.next());
	}

	/**
	 * Construct an empty LinkedList.
	 */
	public SimpleLinkedList() {
		doClear();
	}

	/**
	 * Change the size of this collection to zero by initializing the beginning
	 * and end marker.
	 */
	public void doClear() {
		beginMarker = new Node<>(null, null, null);
		endMarker = new Node<>(null, beginMarker, null);
		beginMarker.next = endMarker;

		size = 0;
	}

	/**
	 * @return the number of items in this collection.
	 */
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * 
	 * @param idx
	 *          index to search at.
	 * @param lower
	 *          lowest valid index.
	 * @param upper
	 *          highest valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *           if idx is not between lower and upper, inclusive.
	 */
	private Node<T> getNode(int idx, int lower, int upper) {
		Node<T> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: "
					+ size());

		if (idx < size() / 2) { // Search through list from the beginning
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;
		} else { // serch through the list from the end
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;
		}

		return p;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * 
	 * @param idx
	 *          index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsExceptionif
	 *           idx is out of range.
	 */
	private Node<T> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Returns the item at position idx.
	 * 
	 * @param idx
	 *          the index to search in.
	 * @throws IndexOutOfBoundsException
	 *           if index is out of range.
	 */
	public T get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Changes the item at position idx.
	 * 
	 * @param idx
	 *          the index to change.
	 * @param newVal
	 *          the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException
	 *           if index is out of range.
	 */
	public T set(int idx, T newVal) {
		Node<T> p = getNode(idx);
		T oldVal = p.data;

		p.data = newVal;
		return oldVal;
	}

	/**
	 * Adds an item in front of node p, shifting p and all items after it one
	 * position to the right.
	 * 
	 * @param p
	 *          Node to add before.
	 * @param x
	 *          any object.
	 * @throws IndexOutOfBoundsException
	 *           if idx < 0 or idx > size().
	 */
	private void addBefore(Node<T> p, T x) {
		Node<T> newNode = new Node<>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		size++;
	}

	/**
	 * Adds an item to at specified index. Remaining items shift up one index.
	 * 
	 * @param x
	 *          any object.
	 * @param idx
	 *          position to add at.
	 * @throws IndexOutOfBoundsException
	 *           if idx < 0 or idx > size().
	 */
	public void add(int idx, T x) {
		addBefore(getNode(idx, 0, size()), x);
	}

	/**
	 * Adds an item to this collection, at the end.
	 * 
	 * @param x
	 *          any object.
	 */
	public void add(T x) {
		add(size(), x);
	}

	/**
	 * Removes the object contained in Node p.
	 * 
	 * @param p
	 *          the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private T remove(Node<T> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		size--;

		return p.data;
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param idx
	 *          the index of the object.
	 * @return the item was removed from the collection.
	 */
	public T remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * Returns a String representation of this collection.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");

		for (T x : this)
			sb.append(x + " ");
		sb.append("]");

		return new String(sb);
	}

	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * 
	 * @return an iterator positioned prior to the first element.
	 */
	public java.util.Iterator<T> iterator() {
		return new LinkedListIterator(reversed);
	}

	/**
	 * This is the implementation of the LinkedListIterator. It maintains a notion
	 * of a current position and of course the implicit reference to the
	 * SimpleLinkedList.
	 */
	private class LinkedListIterator implements java.util.Iterator<T> {
		private Node<T> current;
		private boolean okToRemove = false;
		private boolean reversed;

		public LinkedListIterator(boolean reversed) {
			this.reversed = reversed;
			if (reversed) {
				current = endMarker.prev;
			} else {
				current = beginMarker.next;
			}
		}

		public boolean hasNext() {
			return reversed ? current != beginMarker : current != endMarker;
		}

		public T next() {
			if (!hasNext())
				throw new java.util.NoSuchElementException();

			T nextItem = current.data;
			current = reversed ? current.prev : current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (!okToRemove)
				throw new IllegalStateException();
			SimpleLinkedList.this.remove(reversed ? current.next : current.prev);
			okToRemove = false;
		}
	}

	/**
	 * Test the linked list.
	 */
	public static void main(String[] args) {
		SimpleLinkedList<Integer> lst = new SimpleLinkedList<>();

		for (int i = 0; i < 10; i++)
			lst.add(i);
		for (int i = 20; i < 30; i++)
			lst.add(0, i);

		System.out.println(lst.indexOf(1));
		System.out.println(lst);

		lst.reverse();

		System.out.println(lst);
	}
}
