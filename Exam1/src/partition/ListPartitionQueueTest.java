package partition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class ListPartitionQueueTest {
	private static final int DEFAULT_QUEUE_LENGTH = 6;

	private static final String[] WORDS = { "Have", "yourself", "a", "merry",
			"little", "Christmas" };

	private static final String SPACES = "                                        ";

	private PartitionQueue<Integer> integerQueue;

	private PartitionQueue<Integer> integerQueueEvenOdd;

	private PartitionQueue<String> stringQueueByLength;

	// The number of points earned for the current test case, or negative
	// to indicate that the test case is not graded.
	private static double pointsForCurrentCase;
	private static double totalPoints = 0.0;

	/*
	 * This helper method populates the three test queues.
	 */
	private void populateQueues() {
		for (int i = 0; i < DEFAULT_QUEUE_LENGTH; i++) {
			integerQueue.enqueue(i);
			integerQueueEvenOdd.enqueue(i);
		}
		for (String s : WORDS) {
			stringQueueByLength.enqueue(s);
		}
	}

	@Before
	public void setUp() throws Exception {
		integerQueue = new ListPartitionQueue<Integer>(
				new PartitionFunction<Integer>() {
					@Override
					public int getNumberOfPartitions() {
						return 1;
					}

					@Override
					public int getPartitionOf(Integer obj) {
						return 0;
					}
				});
		integerQueueEvenOdd = new ListPartitionQueue<Integer>(
				new PartitionFunction<Integer>() {
					@Override
					public int getNumberOfPartitions() {
						return 2;
					}

					@Override
					public int getPartitionOf(Integer obj) {
						return obj % 2;
					}
				});
		stringQueueByLength = new ListPartitionQueue<String>(
				new PartitionFunction<String>() {
					@Override
					public int getNumberOfPartitions() {
						return 3;
					}

					@Override
					public int getPartitionOf(String obj) {
						if (obj.length() < 5) {
							return 0;
						} else if (obj.length() < 7) {
							return 1;
						} else {
							return 2;
						}
					}
				});
		pointsForCurrentCase = -1.0;
	}

	@Test
	public void testListPartitionQueue() {
		assertNotNull(integerQueue);
		assertNotNull(integerQueueEvenOdd);
		assertNotNull(stringQueueByLength);
	}

	@Test
	public void testSize() {
		assertEquals(0, integerQueue.size());
		assertEquals(0, integerQueueEvenOdd.size());
		assertEquals(0, stringQueueByLength.size());
		populateQueues();
		assertEquals(DEFAULT_QUEUE_LENGTH, integerQueue.size());
		assertEquals(DEFAULT_QUEUE_LENGTH, integerQueueEvenOdd.size());
		assertEquals(WORDS.length, stringQueueByLength.size());
	}

	@Test
	public void testSizeInt() {
		assertEquals(0, integerQueue.size(0));
		assertEquals(0, integerQueueEvenOdd.size(0));
		assertEquals(0, integerQueueEvenOdd.size(0));
		assertEquals(0, stringQueueByLength.size(0));
		assertEquals(0, stringQueueByLength.size(1));
		assertEquals(0, stringQueueByLength.size(2));
		populateQueues();
		assertEquals(DEFAULT_QUEUE_LENGTH, integerQueue.size(0));
		assertEquals(3, integerQueueEvenOdd.size(0));
		assertEquals(3, integerQueueEvenOdd.size(0));
		assertEquals(2, stringQueueByLength.size(0));
		assertEquals(2, stringQueueByLength.size(1));
		assertEquals(2, stringQueueByLength.size(2));
	}

	@Test
	public void testSizeIntExceptions() {
		try {
			integerQueue.size(-1);
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueue.size(1);
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueueEvenOdd.size(-1);
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueueEvenOdd.size(2);
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			stringQueueByLength.size(-1);
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			stringQueueByLength.size(3);
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
	}

	@Test
	public void testGetPartitionFunction() {
		announceCase("testGetPartitionFunction");
		assertNotNull(integerQueue.getPartitionFunction());
		assertNotNull(integerQueueEvenOdd.getPartitionFunction());
		assertNotNull(stringQueueByLength.getPartitionFunction());
		pointsForCurrentCase = 3;
	}

	@Test
	public void testNumberOfPartitions() {
		announceCase("testNumberOfPartitions");
		assertEquals(1, integerQueue.numberOfPartitions());
		assertEquals(2, integerQueueEvenOdd.numberOfPartitions());
		assertEquals(3, stringQueueByLength.numberOfPartitions());
		pointsForCurrentCase = 3;
	}

	@Test
	public void testEnqueue() {
		populateQueues();
	}

	@Test
	public void testIsEmpty() {
		announceCase("testIsEmpty");
		assertTrue(integerQueue.isEmpty());
		assertTrue(integerQueueEvenOdd.isEmpty());
		assertTrue(stringQueueByLength.isEmpty());
		pointsForCurrentCase = 1.0;
		populateQueues();
		assertFalse(integerQueue.isEmpty());
		pointsForCurrentCase = 2.0;
		assertFalse(integerQueueEvenOdd.isEmpty());
		pointsForCurrentCase = 3.0;
		assertFalse(stringQueueByLength.isEmpty());
		pointsForCurrentCase = 4.0;
	}

	@Test
	public void testIsEmptyInt() {
		announceCase("testIsEmptyInt");
		assertTrue(integerQueue.isEmpty(0));
		assertTrue(integerQueueEvenOdd.isEmpty(0));
		assertTrue(integerQueueEvenOdd.isEmpty(1));
		assertTrue(stringQueueByLength.isEmpty(0));
		assertTrue(stringQueueByLength.isEmpty(1));
		assertTrue(stringQueueByLength.isEmpty(2));
		pointsForCurrentCase = 1.0;
		populateQueues();
		assertFalse(integerQueue.isEmpty(0));
		pointsForCurrentCase = 2.0;
		assertFalse(integerQueueEvenOdd.isEmpty(0));
		assertFalse(integerQueueEvenOdd.isEmpty(1));
		assertFalse(stringQueueByLength.isEmpty(0));
		assertFalse(stringQueueByLength.isEmpty(1));
		assertFalse(stringQueueByLength.isEmpty(2));
		pointsForCurrentCase = 3.0;
	}

	@Test
	public void testIsEmptyIntExceptions() {
		announceCase("testIsEmptyIntExceptions");
		try {
			integerQueue.isEmpty(-1);
			fail("Should have thrown exception");
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueue.isEmpty(1);
			fail("Should have thrown exception");
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueueEvenOdd.isEmpty(-1);
			fail("Should have thrown exception");
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueueEvenOdd.isEmpty(2);
			fail("Should have thrown exception");
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			stringQueueByLength.isEmpty(-1);
			fail("Should have thrown exception");
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			stringQueueByLength.isEmpty(3);
			fail("Should have thrown exception");
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		pointsForCurrentCase = 1.0;
	}

	@Test
	public void testDequeue() {
		announceCase("testDequeue");
		populateQueues();

		// The single-partition queue elements should come out in the order they
		// were inserted.
		for (int i = 0; i < DEFAULT_QUEUE_LENGTH; i++) {
			assertEquals(i, (int) (integerQueue.dequeue(0)));
		}
		pointsForCurrentCase = 1.0;

		// Empties the odd partition, then the even one.
		for (int i = 1; i < DEFAULT_QUEUE_LENGTH; i += 2) {
			assertEquals(i, (int) (integerQueueEvenOdd.dequeue(1)));
		}
		for (int i = 0; i < DEFAULT_QUEUE_LENGTH; i += 2) {
			assertEquals(i, (int) (integerQueueEvenOdd.dequeue(0)));
		}
		pointsForCurrentCase = 3.0;

		// The string queue elements should come out shortest first, and by
		// insertion order within the length partitions
		assertEquals("Have", stringQueueByLength.dequeue(0));
		assertEquals("a", stringQueueByLength.dequeue(0));
		assertEquals("merry", stringQueueByLength.dequeue(1));
		assertEquals("little", stringQueueByLength.dequeue(1));
		assertEquals("yourself", stringQueueByLength.dequeue(2));
		assertEquals("Christmas", stringQueueByLength.dequeue(2));
		pointsForCurrentCase = 5.0;
	}

	@Test
	public void testDequeueExceptions1() {
		announceCase("testDequeueExceptions1");
		try {
			integerQueue.dequeue(-1);
			fail("Should have thrown exception");
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueue.dequeue(1);
			fail("Should have thrown exception");
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueueEvenOdd.dequeue(-1);
			fail("Should have thrown exception");
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueueEvenOdd.dequeue(2);
			fail("Should have thrown exception");
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			stringQueueByLength.dequeue(-1);
			fail("Should have thrown exception");
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			stringQueueByLength.dequeue(3);
			fail("Should have thrown exception");
		} catch (IndexOutOfBoundsException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		pointsForCurrentCase = 1.0;
	}

	@Test
	public void testDequeueExceptions2() {
		announceCase("testDequeueExceptions2");
		try {
			integerQueue.dequeue(0);
			fail("Should have thrown exception");
		} catch (NoSuchElementException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueueEvenOdd.dequeue(0);
			fail("Should have thrown exception");
		} catch (NoSuchElementException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueueEvenOdd.dequeue(1);
			fail("Should have thrown exception");
		} catch (NoSuchElementException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			stringQueueByLength.dequeue(0);
			fail("Should have thrown exception");
		} catch (NoSuchElementException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			stringQueueByLength.dequeue(1);
			fail("Should have thrown exception");
		} catch (NoSuchElementException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			stringQueueByLength.dequeue(2);
			fail("Should have thrown exception");
		} catch (NoSuchElementException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		pointsForCurrentCase = 2.0;
	}

	@Test
	public void testDequeueMin() {
		announceCase("testDequeueMin");
		populateQueues();

		// The single-partition queue elements should come out in the order they
		// were inserted.
		for (int i = 0; i < DEFAULT_QUEUE_LENGTH; i++) {
			assertEquals(i, (int) (integerQueue.dequeueMin()));
		}
		pointsForCurrentCase = 1.0;

		// The even-odd queue elements should come out even then odd.
		for (int i = 0; i < DEFAULT_QUEUE_LENGTH; i += 2) {
			assertEquals(i, (int) (integerQueueEvenOdd.dequeueMin()));
		}
		pointsForCurrentCase++;
		for (int i = 1; i < DEFAULT_QUEUE_LENGTH; i += 2) {
			assertEquals(i, (int) (integerQueueEvenOdd.dequeueMin()));
		}
		pointsForCurrentCase += 2;

		// The string queue elements should come out shortest first, and by
		// insertion order within the length partitions
		assertEquals("Have", stringQueueByLength.dequeueMin());
		assertEquals("a", stringQueueByLength.dequeueMin());
		pointsForCurrentCase++;
		assertEquals("merry", stringQueueByLength.dequeueMin());
		assertEquals("little", stringQueueByLength.dequeueMin());
		pointsForCurrentCase++;
		assertEquals("yourself", stringQueueByLength.dequeueMin());
		assertEquals("Christmas", stringQueueByLength.dequeueMin());
		pointsForCurrentCase += 2;
	}

	@Test
	public void testDequeueMinExceptions() {
		announceCase("testDequeueMinExceptions");
		try {
			integerQueue.dequeueMin();
			fail("Should have thrown exception");
		} catch (NoSuchElementException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueueEvenOdd.dequeueMin();
			fail("Should have thrown exception");
		} catch (NoSuchElementException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			stringQueueByLength.dequeueMin();
			fail("Should have thrown exception");
		} catch (NoSuchElementException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		pointsForCurrentCase = 2.0;
	}

	@Test
	public void testDequeueFromLongest() {
		announceCase("testDequeueFromLongest");
		populateQueues();

		// The single-partition queue elements should come out in the order they
		// were inserted
		for (int i = 0; i < DEFAULT_QUEUE_LENGTH; i++) {
			assertEquals(i, (int) (integerQueue.dequeueFromLongest()));
		}
		pointsForCurrentCase = 2.0;

		// The even-odd queue elements should come out one from even, then one
		// from odd, in the order in which they were inserted.
		for (int i = 0; i < DEFAULT_QUEUE_LENGTH; i++) {
			assertEquals(i, (int) (integerQueueEvenOdd.dequeueFromLongest()));
		}
		pointsForCurrentCase = 4.0;

		// The string queue elements should come out like this:
		assertEquals("Have", stringQueueByLength.dequeueFromLongest());
		assertEquals("merry", stringQueueByLength.dequeueFromLongest());
		assertEquals("yourself", stringQueueByLength.dequeueFromLongest());
		assertEquals("a", stringQueueByLength.dequeueFromLongest());
		assertEquals("little", stringQueueByLength.dequeueFromLongest());
		assertEquals("Christmas", stringQueueByLength.dequeueFromLongest());
		pointsForCurrentCase = 6.0;
	}

	@Test
	public void testDequeueFromLongestExceptions() {
		announceCase("testDequeueFromLongestExceptions");
		try {
			integerQueue.dequeueFromLongest();
			fail("Should have thrown exception");
		} catch (NoSuchElementException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			integerQueueEvenOdd.dequeueFromLongest();
			fail("Should have thrown exception");
		} catch (NoSuchElementException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		try {
			stringQueueByLength.dequeueFromLongest();
			fail("Should have thrown exception");
		} catch (NoSuchElementException e) {
			// Success!
		} catch (Exception f) {
			fail("Unexpected exception: " + f);
		}
		pointsForCurrentCase = 2.0;
	}

	@After
	public void tearDown() throws Exception {
		if (pointsForCurrentCase >= 0) {
			System.out.println(MessageFormat.format("{0, number, 0.0}",
					pointsForCurrentCase));
			totalPoints += pointsForCurrentCase;
		}
	}

	// Prints a well formatted message to the console for accumulating points
	// and notes that the current test is graded.
	private static void announceCase(String caseName) {
		pointsForCurrentCase = 0.0;
		StringBuilder annoucement = new StringBuilder();
		annoucement.append(caseName);
		annoucement.append(SPACES);
		System.out.print(annoucement.substring(0, 40));
	}

	@AfterClass
	public static void showPoints() {
		System.out.printf("TOTAL POINTS (out of 40)                %4.1f\n",
				totalPoints);
	}

}
