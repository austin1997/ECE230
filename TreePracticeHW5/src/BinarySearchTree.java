/**
 * More Binary Tree practice problems. This problem creates BSTs of type
 * Integer: 1. Neither problem makes use of the BST ordering property; I just
 * found insert() to be a convenient way to build trees for testing. 2. I used
 * Integer instead of T since the makeTree method sets the data value of each
 * node to be a depth, which is an Integer.
 * 
 * @author Matt Boutell and <<<YOUR NAME HERE>>>.
 * @param <T>
 */

/*
 * TODO: 0 You are to implement the methods below. Use recursion!
 */
public class BinarySearchTree {

	private BinaryNode root;
	private int sum;
	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinarySearchTree() {
		this.root = this.NULL_NODE;
	}

	/**
	 * This constructor creates a full tree of Integers, where the value of each
	 * node is just the depth of that node in the tree.
	 * 
	 * @param maxDepth
	 *            The depth of the leaves in the tree.
	 */
	public BinarySearchTree(int maxDepth) {
		// TODO: 1 Write this.
		// Hint: You may find it easier if your recursive helper method is
		// outside of the BinaryNode class.
		this.root = this.fullTreeHelper(0, maxDepth);

	}
	private BinaryNode fullTreeHelper(int depth, int maxDepth){
		if(depth == maxDepth + 1) return this.NULL_NODE;
		BinaryNode temp = new BinaryNode(depth);
		temp.left = this.fullTreeHelper(depth + 1, maxDepth);
		temp.right = this.fullTreeHelper(depth + 1, maxDepth);
		
		return temp;
	}
	
	public int getSumOfHeights() {
		// TODO. 2 Write this.
		// Can you do it in O(n) time instead of O(n log n) by avoiding repeated
		// calls to height()?
		this.sum = 0;
		this.root.getHeight();
		
		
		return this.sum;
	}

	// These are here for testing.
	public void insert(Integer e) {
		this.root = this.root.insert(e);
	}

	/**
	 * @return A string showing an in-order traversal of nodes with extra
	 *         brackets so that the structure of the tree can be determined.
	 */
	public String toStructuredString() {
		return this.root.toStructuredString();
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Integer element) {
			this.data = element;
			this.left = BinarySearchTree.this.NULL_NODE;
			this.right = BinarySearchTree.this.NULL_NODE;
		}

		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @return
		 */
		public int getHeight() {
			// TODO Auto-generated method stub.
			if(this.left == BinarySearchTree.this.NULL_NODE && this.right == BinarySearchTree.this.NULL_NODE) return 0;
			else if(this.left == BinarySearchTree.this.NULL_NODE){
				int right = this.right.getHeight();
				BinarySearchTree.this.sum = BinarySearchTree.this.sum + right + 1;
				return right + 1;
			}else if(this.right == BinarySearchTree.this.NULL_NODE){
				int left = this.left.getHeight();
				BinarySearchTree.this.sum = BinarySearchTree.this.sum + left + 1;
				return left + 1;
			}else{
				int left = this.left.getHeight();
				int right = this.right.getHeight();
				int out = left > right? left + 1 : right + 1;
				BinarySearchTree.this.sum += out;
				return out;
			}
 		}

		public BinaryNode insert(Integer e) {
			if (this == BinarySearchTree.this.NULL_NODE) {
				return new BinaryNode(e);
			} else if (e.compareTo(this.data) < 0) {
				this.left = this.left.insert(e);
			} else if (e.compareTo(this.data) > 0) {
				this.right = this.right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		public String toStructuredString() {
			if (this == BinarySearchTree.this.NULL_NODE) {
				return "";
			}
			return "[" + this.left.toStructuredString() + this.data
					+ this.right.toStructuredString() + "]";
		}

	}
}