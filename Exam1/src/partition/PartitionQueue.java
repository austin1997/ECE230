package partition;

import java.util.NoSuchElementException;

/**
 * A partition queue is a collection of normal queues where the enqueued objects
 * are placed in one of several possible "partitions". The partitions are
 * separate sub-queues within the main partition queue.
 * 
 * A "partitioning function" divides objects into disjoint (i.e.,
 * non-overlapping) subsets. When an object is enqueued the partitioning
 * function is used to select the appropriate sub-queue.
 * 
 * Objects can be dequeued from specific partitions. Objects can also be
 * dequeued from the highest priority non-empty partition and from the partition
 * with the greatest number of elements.
 * 
 * @param <T>
 *            the type of objects in the queues
 */
public interface PartitionQueue<T> {
	/**
	 * @return the number of elements in this queue
	 */
	int size();

	/**
	 * Checks the size of the given partition.
	 * 
	 * @param i
	 *            the partition number
	 * @return the number of elements in the given partition
	 * @throws IndexOutOfBoundsException
	 *             if i < 0 or i >= numberOfPartitions()
	 */
	int size(int i) throws IndexOutOfBoundsException;

	/**
	 * Returns the partition function for this queue. The function must be
	 * capable of partitioning some supertype of T.
	 * 
	 * @return the partition function object for this
	 */
	PartitionFunction<T> getPartitionFunction();

	/**
	 * @return the number of partitions of this queue
	 */
	int numberOfPartitions();

	/**
	 * Adds the given element to this queue.
	 * 
	 * @param element
	 */
	void enqueue(T element);

	/**
	 * Checks the empty status of the given partition.
	 * 
	 * @param i
	 *            the partition number
	 * @return true if the given partition is empty
	 * @throws IndexOutOfBoundsException
	 *             if i < 0 or i >= numberOfPartitions()
	 */
	boolean isEmpty(int i) throws IndexOutOfBoundsException;

	/**
	 * @return true if this queue is empty
	 */
	boolean isEmpty();

	/**
	 * Dequeues an element from the given partition.
	 * 
	 * @param i
	 *            the partition number
	 * @return the appropriate element
	 * @throws NoSuchElementException
	 *             if the given partition is empty
	 * @throws IndexOutOfBoundsException
	 *             if i < 0 or i >= numberOfPartitions()
	 */
	T dequeue(int i) throws NoSuchElementException, IndexOutOfBoundsException;

	/**
	 * Dequeues an element from the lowest numbered, non-empty partition of this
	 * queue.
	 * 
	 * @return the appropriate element
	 * @throws NoSuchElementException
	 *             if all partitions are empty
	 */
	T dequeueMin() throws NoSuchElementException;

	/**
	 * Dequeues an element from the longest (i.e., most full) partition of this
	 * queue. If two partitions are of the same size, then element should be
	 * dequeued from the lowest numbered partition.
	 * 
	 * @return the appropriate element
	 * @throws NoSuchElementException
	 *             if all partitions are empty
	 */
	T dequeueFromLongest() throws NoSuchElementException;

}
