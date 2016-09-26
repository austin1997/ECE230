import java.util.Stack;

/**
 * Binary Tree practice problems
 * 
 * @author Matt Boutell and <<<Zhihong Zhai>>>. 2014.
 * @param <T>
 */

/*
 * TODO: 0 You are to implement the four methods below. I took most of them from
 * a CSSE230 exam given in a prior term. These can all be solved by recursion -
 * I encourage you to do so too, since most students find practicing recursion
 * to be more useful.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinarySearchTree() {
		this.root = this.NULL_NODE;
	}

	/**
	 * This method counts the number of occurrences of positive Integers in the
	 * tree that is of type Integer. Hint: You may assume this tree contains
	 * integers, so may use a cast.
	 * 
	 * @return The number of positive integers in the tree.
	 */

	public int countPositives() {
		// TODO: 1 Write this.
		return this.root.countPositive();
	}

	/**
	 * Recall that the depth of a node is number of edges in a path from this
	 * node to the root. Returns the depth of the given item in the tree. If the
	 * item isn't in the tree, then it returns -1.
	 * 
	 * @param item
	 * @return The depth, or -1 if it isn't in the tree.
	 */
	public int getDepth(T item) {
		// TODO: 2 Write this.
		if(this.root == this.NULL_NODE) return -1;
		int depth = this.root.getDepth(item);
		return depth < 0 ? -1 : depth;
	}

	/**
	 * This method visits each node of the BST in pre-order and determines the
	 * number of children of each node. It produces a string of those numbers.
	 * If the tree is empty, an empty string is to be returned. For the
	 * following tree, the method returns the string: "2200110"
	 * 
	 * 10 5 15 2 7 18 10
	 * 
	 * @return A string representing the number of children of each node when
	 *         the nodes are visited in pre-order.
	 */

	public String numChildrenOfEachNode() {
		// TODO: 3 Write this.
		if(this.root == this.NULL_NODE) return "";
		
		return this.root.numChildrenOfEachNode();
	}

	/**
	 * This method determines if a BST forms a zig-zag pattern. By this we mean
	 * that each node has exactly one child, except for the leaf. In addition,
	 * the nodes alternate between being a left and a right child. An empty tree
	 * or a tree consisting of just the root are both said to form a zigzag
	 * pattern. For example, if you insert the elements 10, 5, 9, 6, 7 into a
	 * BST in that order. , you will get a zig-zag.
	 * 
	 * @return True if the tree forms a zigzag and false otherwise.
	 */
	public boolean isZigZag() {
		// TODO: 4 Write this.
		if(this.root == this.NULL_NODE) return true;
		return this.root.isZigZag();
	}

	public void insert(T e) {
		this.root = this.root.insert(e);
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public T element;
		public BinaryNode left;
		public BinaryNode right;
		
		public BinaryNode(T element) {
			this.element = element;
			this.left = BinarySearchTree.this.NULL_NODE;
			this.right = BinarySearchTree.this.NULL_NODE;
		}
		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @return
		 */
		public boolean isZigZag() {
			// TODO Auto-generated method stub.
			if(this.left == BinarySearchTree.this.NULL_NODE && this.right == BinarySearchTree.this.NULL_NODE) return true;
			if(this.left != BinarySearchTree.this.NULL_NODE && this.right != BinarySearchTree.this.NULL_NODE) return false;
			if(this.left != BinarySearchTree.this.NULL_NODE) return this.left.isZigZagHelper(true);
			else return this.right.isZigZagHelper(false);
		}
		private boolean isZigZagHelper(boolean flag){
			if(this.left == BinarySearchTree.this.NULL_NODE && this.right == BinarySearchTree.this.NULL_NODE) return true;
			if(this.left != BinarySearchTree.this.NULL_NODE && this.right != BinarySearchTree.this.NULL_NODE) return false;
			if(this.left != BinarySearchTree.this.NULL_NODE && !flag) return this.left.isZigZagHelper(true);
			if(this.right != BinarySearchTree.this.NULL_NODE && flag) return this.right.isZigZagHelper(false);
			return false;
			
		}
		
		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @return
		 */
		public String numChildrenOfEachNode() {
			// TODO Auto-generated method stub
			return this.numChildrenOfEachNodeHelper();
		}
		private String numChildrenOfEachNodeHelper(){
			if(this == BinarySearchTree.this.NULL_NODE) return "";
			return  this.getNumofChildren() + this.left.numChildrenOfEachNodeHelper()  + this.right.numChildrenOfEachNodeHelper();
			
		}
		private int getNumofChildren(){
			int n = 0;
			if(this.left != BinarySearchTree.this.NULL_NODE) n++;
			if(this.right != BinarySearchTree.this.NULL_NODE) n++;
			return n;
		}
		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @param item
		 * @return
		 */
		public int getDepth(T item) {
			// TODO Auto-generated method stub.
			if(item.compareTo(this.element) > 0) {
				if(this.right == BinarySearchTree.this.NULL_NODE) return Integer.MIN_VALUE;
				return this.right.getDepth(item) + 1;
			}
			if(item.compareTo(this.element) < 0) {
				if(this.left == BinarySearchTree.this.NULL_NODE) return Integer.MIN_VALUE;
				return this.left.getDepth(item) + 1;
			}
			return 0;
		}
		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @return
		 */
		public int countPositive() {
			// TODO Auto-generated method stub.
			Stack<BinaryNode> st = new Stack <BinaryNode>();
			return countPositiveHelper(st);
		}
		
		private int countPositiveHelper(Stack<BinaryNode> st){
			if(this == BinarySearchTree.this.NULL_NODE) return 0;
			if(this.right != BinarySearchTree.this.NULL_NODE) st.push(this.right);
			if(this.left != BinarySearchTree.this.NULL_NODE) st.push(this.left);
			if(st.size() > 0 ) {
				if((int)this.element > 0) return st.pop().countPositiveHelper(st) + 1;
				else return st.pop().countPositiveHelper(st);
			}
			else {
				return (int)this.element > 0 ? 1 : 0;
			}
		}

		public BinaryNode insert(T e) {
			if (this == BinarySearchTree.this.NULL_NODE) {
				return new BinaryNode(e);
			} else if (e.compareTo(this.element) < 0) {
				this.left = this.left.insert(e);
			} else if (e.compareTo(this.element) > 0) {
				this.right = this.right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}
	}
}