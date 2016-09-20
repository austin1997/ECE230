package partition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class implements a PartitionQueue using linked lists and array lists.
 * 
 * @author TODO: Your name here.
 * @param <T>
 *            the type of elements in this queue
 */
public class ListPartitionQueue<T> implements PartitionQueue<T> {
	private PartitionFunction<T> function;

	private ArrayList<LinkedList<T>> queues;

	/**
	 * Constructs a partition queue using the given partition function.
	 * 
	 * @param f
	 */
	public ListPartitionQueue(PartitionFunction<T> f) {
		this.function = f;
		int numberOfPartitions = f.getNumberOfPartitions();
		this.queues = new ArrayList<LinkedList<T>>(numberOfPartitions);
		for (int i = 0; i < numberOfPartitions; i++) {
			queues.add(new LinkedList<T>());
		}
	}

	public int size() {
		int size = 0;
		for (LinkedList<T> q : queues) {
			size += q.size();
		}
		return size;
	}

	public int size(int i) throws IndexOutOfBoundsException {
		return queues.get(i).size();
	}

	public PartitionFunction<T> getPartitionFunction() {
		// TODO: implement this method
		return null;
	}

	public int numberOfPartitions() {
		// TODO: implement this method
		return -1;
	}

	public void enqueue(T element) {
		int partition = function.getPartitionOf(element);
		queues.get(partition).offer(element);
	}

	public boolean isEmpty(int i) {
		// TODO: implement this method
		return false;
	}

	public boolean isEmpty() {
		// TODO: implement this method
		return false;
	}

	public T dequeue(int i) throws NoSuchElementException,
			IndexOutOfBoundsException {
		// TODO: implement this method
		return null;
	}

	public T dequeueMin() {
		// TODO: implement this method
		return null;
	}

	public T dequeueFromLongest() throws NoSuchElementException {
		// TODO: implement this method
		return null;
	}

}
