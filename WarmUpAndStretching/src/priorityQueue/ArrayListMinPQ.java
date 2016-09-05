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
		for(int i = 0; i < this.items.size() - 1; i++){
			for(int j = i; j < this.items.size() - 1; j++){
				if(this.items.get(j).compareTo(this.items.get(j + 1)) > 0){
					T temp = this.items.get(j);
					this.items.set(j,this.items.get(j + 1));
					this.items.set(j + 1, temp);
				}
			}
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
