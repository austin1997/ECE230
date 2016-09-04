package priorityQueue;
import java.util.ArrayList;

/**
 * An implementation of the Priority Queue interface using an array list.
 * 
 * @author Matt Boutell and <<TODO: Zhihong Zhai>>>. Created Mar 29, 2014.
 * 
 * @param <T>
 *            Generic type of PQ elements
 */
public class ArrayListMinPQ<T extends Comparable<T>> {
	// Requirement: You must use this instance variable without changes.
	private ArrayList<T> items;

	public ArrayListMinPQ() {
		// TODO: implement
		this.items = new ArrayList<T>();
	}

	public T findMin() {
		// This is also known as peekMin
		// TODO: implement
		
		
		return this.isEmpty()? null : this.items.get(0);
	}

	public T deleteMin() {
		// TODO: implement
		return this.isEmpty()? null : this.items.remove(0);
	}

	public void insert(T item) {
		// TODO: implement
		this.items.add(item);
		if (this.isEmpty()) return;
		Object [] array = this.items.toArray();
//		T minimum = (T) array[0];
		for(int i = 0; i < array.length - 1; i++){
			for(int j = i; j < array.length - 1; j++){
				if(((T)array[j]).compareTo((T)array[j + 1]) > 0){
					T temp = (T) array[j];
					array[j] = array [j + 1];
					array[j + 1] = temp;
				}
			}
		}
		items.clear();
		for(Object obj : array){
			items.add((T) obj);
		}
		
		
	}

	public int size() {
		// TODO: implement
		return this.items.size();
	}

	public boolean isEmpty() {
		// TODO: implement
		return this.items.isEmpty();
	}

	public void clear() {
		// TODO: implement
		this.items.clear();
	}
}
