package bst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.AfterClass;
import org.junit.Test;


public class Testing {

	private static int leafCountPoints = 0;
	private static int depthPlusHeightPoints = 0;
	private static int greatestLessThanOrNullPoints = 0;
	
	private BinarySearchTree buildTree() {
		BinarySearchTree t = new BinarySearchTree();
		t.insert(10);
		t.insert(5);
		t.insert(50);
		t.insert(30);
		t.insert(20);
		t.insert(40);
		t.insert(90);
		t.insert(80);
		t.insert(70);
		t.insert(60);
		t.insert(95);
		t.insert(97);
		return t;
	}
	
	@Test
	public void testLeafCountSingleNode() {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(10);
		assertEquals(1, b.countLeafNodes());
		leafCountPoints += 1;
	}

	@Test
	public void testLeafCountFull() {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(20);
		b.insert(10);
		b.insert(30);
		assertEquals(2, b.countLeafNodes());
		leafCountPoints += 1;
		b.insert(40);
		b.insert(38);
		b.insert(15);
		assertEquals(2, b.countLeafNodes());
		leafCountPoints += 1;
		b.insert(5);
		assertEquals(3, b.countLeafNodes());
		leafCountPoints += 1;
		b.insert(2);
		assertEquals(3, b.countLeafNodes());
		leafCountPoints += 1;
		b.insert(25);
		assertEquals(4, b.countLeafNodes());
		leafCountPoints += 1;
		b.insert(50);
		assertEquals(5, b.countLeafNodes());
		leafCountPoints += 2;
	}

	@Test
	public void testLeafCountSmallTreeWithNonLeaf() {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(10);
		b.insert(5);
		b.insert(15);
		assertEquals(2, b.countLeafNodes());

		b = new BinarySearchTree();
		b.insert(10);
		b.insert(5);
		b.insert(15);
		b.insert(2);
		assertEquals(2, b.countLeafNodes());

		b = new BinarySearchTree();
		b.insert(10);
		b.insert(5);
		b.insert(15);
		b.insert(2);
		b.insert(7);
		assertEquals(3, b.countLeafNodes());

		leafCountPoints += 3;
	}

	@Test
	public void testLeafCountOnWorstCaseTree() {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(10);
		b.insert(5);
		b.insert(9);
		b.insert(6);
		b.insert(7);
		assertEquals(1, b.countLeafNodes());
		leafCountPoints += 1;
	}

	@Test
	public void testLeafCountOnBestCaseTree() {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(20);
		b.insert(10);
		b.insert(5);
		b.insert(15);
		b.insert(30);
		b.insert(25);
		b.insert(35);
		assertEquals(4, b.countLeafNodes());
		leafCountPoints += 1;
	}

	@Test
	public void testLeafCountOnTreeFromSpec() {
		BinarySearchTree t = buildTree();
		assertEquals(5, t.countLeafNodes());
		leafCountPoints += 2;
	}

	@Test
	public void testLeafCountOnLargeRandomTrees() {
		// TODO: Read this test case AFTER you submit the exam. Interesting,
		// huh? I left in a bunch of
		// commented out experiments I did in case you want to explore what
		// happens when random elements are inserted.
		//
		// Feel free to change n.
		int n = 100;
		// Generate a random permutation of n ints (just so there are no
		// duplicates).
		ArrayList<Integer> nums = new ArrayList<Integer>(n);
		for (int i = 1; i <= n; i++) {
			nums.add(i);
		}

		int min = n, max = 1;

		// Run on random trees a bunch of times. Feel free to change the number
		// of times it runs
		int nSimulations = 100;
		for (int i = 0; i < nSimulations; i++) {
			// Shuffle them
			Collections.shuffle(nums);

			BinarySearchTree b = new BinarySearchTree();
			for (int num : nums) {
				b.insert(num);
			}
			int leaves = b.countLeafNodes();
			// System.out.printf("Random tree has %d leaves\n", leaves);
			if (leaves < min)
				min = leaves;
			if (leaves > max)
				max = leaves;

			// Can you see why this must be true? In practice, you'll find that
			// the typical range of leaf counts is much smaller than this.
			assertTrue(leaves >= 1 && leaves <= Math.ceil(n / 2));
		}
		// System.out.printf("Random trees had leaf counts in range [%d,%d]\n",
		// min, max);
		leafCountPoints += 1;
	}
	
	
	@Test
	public void testDepthPlusHeightRoot() {
		BinarySearchTree t = buildTree();
		assertEquals(5, t.depthPlusHeight(10));
		depthPlusHeightPoints += 2;
	}
	
	@Test
	public void testDepthPlusHeightLeaf() {
		BinarySearchTree t = buildTree();
		assertEquals(1, t.depthPlusHeight(5));
		depthPlusHeightPoints += 1;
	}
	
	@Test
	public void testDepthPlusHeightOnLongPath() {
		BinarySearchTree t = buildTree();
		assertEquals(5, t.depthPlusHeight(50));
		depthPlusHeightPoints += 2;
	}

	@Test
	public void testDepthPlusHeightOnLongPath2() {
		BinarySearchTree t = buildTree();
		assertEquals(5, t.depthPlusHeight(90));
		depthPlusHeightPoints += 1;
	}

	@Test
	public void testDepthPlusHeightOnLongPath3() {
		BinarySearchTree t = buildTree();
		assertEquals(5, t.depthPlusHeight(80));
		depthPlusHeightPoints += 1;
	}

	@Test
	public void testDepthPlusHeightOnLongPath4() {
		BinarySearchTree t = buildTree();
		assertEquals(5, t.depthPlusHeight(70));
		depthPlusHeightPoints += 1;
	}

	@Test
	public void testDepthPlusHeightOnLongPathLeaf() {
		BinarySearchTree t = buildTree();
		assertEquals(5, t.depthPlusHeight(60));
		depthPlusHeightPoints += 1;
	}

	@Test
	public void testDepthPlusHeightOther() {
		BinarySearchTree t = buildTree();
		assertEquals(3, t.depthPlusHeight(30));
		assertEquals(3, t.depthPlusHeight(20));
		assertEquals(3, t.depthPlusHeight(40));
		depthPlusHeightPoints += 2;
	}

	@Test
	public void testDepthPlusHeightOther2() {
		BinarySearchTree t = buildTree();
		assertEquals(4, t.depthPlusHeight(95));
		assertEquals(4, t.depthPlusHeight(97));
		depthPlusHeightPoints += 2;
	}
	
	@Test
	public void testDepthPlusHeightItemNotFound() {
		BinarySearchTree t = buildTree();
		assertEquals(-1, t.depthPlusHeight(17));
		assertEquals(-1, t.depthPlusHeight(49));
		assertEquals(-1, t.depthPlusHeight(2));
		assertEquals(-1, t.depthPlusHeight(99));
		depthPlusHeightPoints += 2;
	}

	@Test
	public void testGreatestLessThanOrNullLeftChild() {
		BinarySearchTree t = buildTree();
		assertEquals(new Integer(80), t.greatestLessThanOrNull(90));
		assertEquals(new Integer(60), t.greatestLessThanOrNull(70));
		assertEquals(new Integer(5), t.greatestLessThanOrNull(10));
		greatestLessThanOrNullPoints += 2;
	}

	@Test
	public void testGreatestLessThanOrNullParentOfRightChild() {
		BinarySearchTree t = buildTree();
		assertEquals(new Integer(95), t.greatestLessThanOrNull(97));
		assertEquals(new Integer(30), t.greatestLessThanOrNull(40));
		assertEquals(new Integer(90), t.greatestLessThanOrNull(95));
		greatestLessThanOrNullPoints += 2;
	}
	

	@Test
	public void testGreatestLessThanOrNullAncestor() {
		BinarySearchTree t = buildTree();
		assertEquals(new Integer(10), t.greatestLessThanOrNull(20));
		assertEquals(new Integer(50), t.greatestLessThanOrNull(60));
		greatestLessThanOrNullPoints += 2;
	}
	

	@Test
	public void testGreatestLessThanOrNullDescendant() {
		BinarySearchTree t = buildTree();
		t.insert(8);
		assertEquals(new Integer(40), t.greatestLessThanOrNull(50));
		assertEquals(new Integer(8), t.greatestLessThanOrNull(10));
		greatestLessThanOrNullPoints += 2;
	}
	
	@Test
	public void testGreatestLessThanOrNullItemNotInTree() {
		BinarySearchTree t = buildTree();
		assertEquals(new Integer(10), t.greatestLessThanOrNull(12));
		assertEquals(new Integer(40), t.greatestLessThanOrNull(45));
		assertEquals(new Integer(80), t.greatestLessThanOrNull(85));
		assertEquals(new Integer(90), t.greatestLessThanOrNull(93));
		assertEquals(new Integer(97), t.greatestLessThanOrNull(100));
		greatestLessThanOrNullPoints += 4;
	}

	@Test
	public void testGreatestLessThanOrNullAllGreaterOrEqual() {
		BinarySearchTree t = buildTree();
		assertEquals(null, t.greatestLessThanOrNull(0));
		assertEquals(null, t.greatestLessThanOrNull(2));
		assertEquals(null, t.greatestLessThanOrNull(5));
		greatestLessThanOrNullPoints += 3;
	}


	
	
	@AfterClass
	public static void displayPoints() {
		System.out.printf("%2d/16 leafCount correctness points\n", leafCountPoints);
		System.out.printf("%2d/15 depthPlusHeight correctness points\n", depthPlusHeightPoints);
		System.out.printf(" _/5  depthPlusHeight efficiency will be checked by the instructor\n");
		System.out.printf("%2d/15 greatestLessThanOrNull correctness points\n", greatestLessThanOrNullPoints);
		System.out.printf(" _/5  greatestLessThanOrNull efficiency will be checked by the instructor\n");
		System.out.printf(" _/4  elegance will be checked by the instructor\n");

	}
}
