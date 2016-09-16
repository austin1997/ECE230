import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * Implementation of most of the Set interface operations using a Binary Search Tree
 *
 * @author Matt Boutell and Zhihong Zhai.
 * @param <T>
 */

public class BinarySearchTree<T> implements Iterable<T>{
	class InefficientIterator {
		ArrayList<T> array = toArrayList();
		Iterator itr = this.array.iterator();
		
		
	}
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub.
		if(this.root == this.NULL_NODE) return "[]";
		StringBuilder str = new StringBuilder();
		str.append("[");
		str.append(this.root);
		
		return str.substring(0, str.length() - 2) + "]";
	}

	private BinaryNode root;

	// Most of you will prefer to use NULL NODES once you see how to use them.
	private final BinaryNode NULL_NODE = new BinaryNode();

	public BinarySearchTree() {
		this.root = this.NULL_NODE;
	}

	// For manual tests only
	void setRoot(BinaryNode n) {
		this.root = n;
	}
	
	// Not private, since we need access for manual testing.
	class BinaryNode {
		@Override
		public String toString() {
			// TODO Auto-generated method stub.
//			if(this.left == null && this.right == null) return this.data.toString() + ", ";
//			else if(this.left == null) return this.data.toString() + ", "+ this.right.toString();
//			else if(this.right == null) return this.left.toString() + this.data.toString() + ", ";
//			else return this.left.toString() + this.data.toString() + ", " + this.right.toString();
			if(this == BinarySearchTree.this.NULL_NODE) return "";
			else return this.left.toString() + this.data.toString() + ", " + this.right.toString();
		}

		private T data;
		private BinaryNode left;
		private BinaryNode right;

		public BinaryNode() {
			this.data = null;
			this.left = null;
			this.right = null;
		}

		public BinaryNode(T element) {
			this.data = element;
			this.left = BinarySearchTree.this.NULL_NODE;
			this.right = BinarySearchTree.this.NULL_NODE;
		}

		public T getData() {
			return this.data;
		}

		public BinaryNode getLeft() {
			return this.left;
		}


		public BinaryNode getRight() {
			return this.right;
		}

		// For manual testing
		public void setLeft(BinaryNode left) {
			this.left = left;
		}
		
		public void setRight(BinaryNode right) {
			this.right = right;
		}

		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @return
		 */
		public int size() {
			// TODO Auto-generated method stub.
//			if(this.left == null && this.right == null) return 1;
//			else if(this.left == null) return this.right.size() + 1;
//			else if(this.right == null) return this.left.size() + 1;
//			else return this.left.size() + this.right.size() + 1;
			if(this == BinarySearchTree.this.NULL_NODE) return 0;
			else return this.right.size() + this.left.size() + 1;
			
		}

		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @return
		 */
		public int height() {
			// TODO Auto-generated method stub.
//			if(this.left == null && this.right == null) return 1;
//			else if(this.left == null) return this.right.height() + 1;
//			else if(this.right == null) return this.left.height() + 1;
//			else return this.left.height() > this.right.height()?this.left.height() + 1 : this.right.height() + 1;
			if(this == BinarySearchTree.this.NULL_NODE) return 0;
			else return this.left.height() > this.right.height()?this.left.height() + 1 : this.right.height() + 1;

		}

		/**
		 * TODO Put here a description of what this method does.
		 * @param i 
		 *
		 * @return
		 */
		public boolean contains(int i) {
			// TODO Auto-generated method stub.
			if(this.data.equals(i)) return true;
			if(((Integer) this.data).compareTo(i) > 0)return this.left == NULL_NODE?false : this.left.contains(i);
			return this.right == NULL_NODE?false : this.right.contains(i);
			
		}

		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @param i
		 * @return
		 */
		public boolean containsNonBST(int i) {
			// TODO Auto-generated method stub.
//			if(this.data.equals(i)) return true;
//			if(this.left == null && this.right == null) return false;
//			else if(this.left == null) return this.right.containsNonBST(i);
//			else if(this.right == null) return this.left.containsNonBST(i);
//			else return this.left.containsNonBST(i) || this.right.containsNonBST(i);
			if(this == BinarySearchTree.this.NULL_NODE) return false;
			else return this.data.equals(i) || this.left.containsNonBST(i) || this.right.containsNonBST(i);
		}

		/**
		 * TODO Put here a description of what this method does.
		 * @param array 
		 *
		 * @return
		 */
		public ArrayList<T> toArrayList() {
			// TODO Auto-generated method stub.
//			if(this.left == null && this.right == null) {
//				ArrayList<T> arr = new ArrayList<T>();
//				arr.add(this.data);
//				return arr;
//			}
//			else if(this.left == null) {
//				ArrayList<T> arr = new ArrayList<T>();
//				arr.add(this.data);
//				arr.addAll(this.right.toArrayList());
//				return arr;
//			}
//			else if(this.right == null) {
//				ArrayList<T> arr = new ArrayList<T>();
//				arr.addAll(this.left.toArrayList());
//				arr.add(this.data);
//				return arr;
//			}
//			else {
//				ArrayList<T> arr = new ArrayList<T>();
//				arr.addAll(this.left.toArrayList());
//				arr.add(this.data);
//				arr.addAll(this.right.toArrayList());
//				return arr;
//			}
			if(this == BinarySearchTree.this.NULL_NODE) return new ArrayList<T>();
			else {
				ArrayList<T> arr = new ArrayList<T>();
				arr.addAll(this.left.toArrayList());
				arr.add(this.data);
				arr.addAll(this.right.toArrayList());
				return arr;
			}
			
			
			
		}
		
	}

	

	// TODO: Implement your 3 iterator classes here, plus any other inner helper classes you'd like. 
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub.
		if(this.root == this.NULL_NODE) return true;
		return false; 
				
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public int size() {
		// TODO Auto-generated method stub.
//		if(this.isEmpty()) return 0;
		return this.root.size();
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public int height() {
		// TODO Auto-generated method stub.
//		if(this.isEmpty()) return -1;
		return this.root.height() - 1;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param i
	 * @return
	 */
	public boolean containsNonBST(int i) {
		// TODO Auto-generated method stub.
//		if(this.isEmpty()) return false;
		
		return this.root.containsNonBST(i);
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public ArrayList<T> toArrayList() {
		// TODO Auto-generated method stub.
//		if(this.isEmpty()) return new ArrayList<T>();
		return this.root.toArrayList();
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public Object [] toArray() {
		// TODO Auto-generated method stub.
		
		return this.toArrayList().toArray();
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public Iterator inefficientIterator() {
		// TODO Auto-generated method stub.
		return new InefficientIterator().itr;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub.
		return null;
	}
	
	 

}
