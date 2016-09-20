package partition;

/**
 * This function object type represents the function for determine the partition
 * to which objects added to a PartitionQueue belong.
 * 
 * @param <T>
 *            the type of objects to be partitioned
 */
public interface PartitionFunction<T> {

	/**
	 * Gives the maximum number of partitions into which this function divides
	 * objects of type T. Implementations must ensure that the result is greater
	 * than 0 and is a constant. That is, for a given function object, repeated
	 * calls to this method always yield the same result.
	 * 
	 * @return the number of partitions into which this function divides objects
	 *         of type T
	 */
	int getNumberOfPartitions();

	/**
	 * Calculates the number of the partition to which the given object belongs.
	 * 
	 * @param obj
	 * @return an integer between 0 and this.getNumberOfPartitions() - 1.
	 */
	int getPartitionOf(T obj);
}
