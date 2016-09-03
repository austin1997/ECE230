package list;

import java.util.NoSuchElementException;

/**
 * 
 * @author anderson
 * 
 * @param <T>
 *            Any Comparable type
 * 
 *            A linked list whose elements are kept in sorted order.
 */
public class SortedLinkedList<T extends Comparable<T>> extends
		DoublyLinkedList<T> {

	/**
	 * Create an empty list
	 * 
	 */
	public SortedLinkedList() {
		super();
	}

	/**
	 * Creates a sorted list containing the same elements as the parameter
	 * 
	 * @param list
	 *            the input list
	 */
	public SortedLinkedList(DoublyLinkedList<T> list) {
		super();
		// TODO: finish implementing this constructor
//		System.out.println(list.head.next.data);
		
		if(list.isEmpty()) return;
		this.add(list.head.next.data);
		Node current = this.head.next;
		list.removeFirst();
		while(!list.isEmpty()){
			this.add(list.removeFirst());
		}

	}

	/**
	 * Adds the given element to the list, keeping it sorted.
	 */
	@Override
	public void add(T element) {
		// TODO: implement this method
		if(this.isEmpty()) this.head.addAfter(element);
		else{
			boolean flag = false;
			Node current = this.head.next;
			if(current.data.compareTo(element) > 0) {
				this.addFirst(element);
				flag = true;
			}
			else {
				do {
					if (current.data.compareTo(element) > 0) {
						current.prev.addAfter(element);
						flag = true;
					}
					current = current.next;
				}while (current.next != null && !flag);
				
				
			}
			if (!flag) this.addLast(element);
		}
		
	}

	@Override
	public void addFirst(T element) throws UnsupportedOperationException{
		if(this.isEmpty()) throw new UnsupportedOperationException("empty list");
		// TODO: throw UnsupportedOperationException exception
		this.head.addAfter(element);
	}

	@Override
	public void addLast(T element) throws UnsupportedOperationException{
		if(this.isEmpty()) throw new UnsupportedOperationException("empty list");
		// TODO: throw UnsupportedOperationException exception
		this.tail.prev.addAfter(element);
	}
}
