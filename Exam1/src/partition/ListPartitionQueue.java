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
			this.queues.add(new LinkedList<T>());
		}
	}

	@Override
	public int size() {
		int size = 0;
		for (LinkedList<T> q : this.queues) {
			size += q.size();
		}
		return size;
	}

	@Override
	public int size(int i) throws IndexOutOfBoundsException {
		if(i >= this.function.getNumberOfPartitions() || i < 0) throw new IndexOutOfBoundsException();
		return this.queues.get(i).size();
	}

	@Override
	public PartitionFunction<T> getPartitionFunction() {
		// TODO: implement this method
		return this.function;
	}

	@Override
	public int numberOfPartitions() {
		// TODO: implement this method
		return this.function.getNumberOfPartitions();
	}

	@Override
	public void enqueue(T element) {
		int partition = this.function.getPartitionOf(element);
		this.queues.get(partition).offer(element);
	}

	@Override
	public boolean isEmpty(int i) {
		// TODO: implement this method
		return this.queues.get(i).isEmpty();
	}

	@Override
	public boolean isEmpty() {
		// TODO: implement this method
		return this.size() == 0;
	}

	@Override
	public T dequeue(int i) throws NoSuchElementException,
			IndexOutOfBoundsException {
		// TODO: implement this method
		if(i >= this.function.getNumberOfPartitions() || i < 0) throw new IndexOutOfBoundsException();
		if(this.isEmpty(i)) throw new NoSuchElementException();
		return this.queues.get(i).poll();
	}

	@Override
	public T dequeueMin() throws NoSuchElementException{
		// TODO: implement this method
		if(this.isEmpty()) throw new NoSuchElementException();
		for(int i = 0; i < this.function.getNumberOfPartitions(); i++){
			if(this.isEmpty(i))continue;
			return this.queues.get(i).poll();
		}
		throw new NoSuchElementException();
	}

	@Override
	public T dequeueFromLongest() throws NoSuchElementException {
		// TODO: implement this method
		if(this.isEmpty()) throw new NoSuchElementException();
		int max = -1;
		int index = -1;
		for(int i = 0; i < this.function.getNumberOfPartitions(); i++){
			if(this.size(i) > max) {
				max = this.size(i);
				index = i;
			}
		}
		return this.queues.get(index).poll();
	}

}
