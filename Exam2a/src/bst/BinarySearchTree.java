package bst;

import org.omg.CORBA.NO_IMPLEMENT;

/**
 *
 * Exam 2a. Tree methods.
 * 
 * @author
 */

/*
 * TODO: Directions: Implement the method below. See the paper for details.
 */
public class BinarySearchTree {

	BinaryNode root;

	// The -17 is arbitrary -any int would be fine since we never refer to it.
	final BinaryNode NULL_NODE = new BinaryNode(-17); 

	public BinarySearchTree() {
		this.root = this.NULL_NODE;
	}

	public int countLeafNodes() {
		// TODO Write this
		return this.root.countLeafNodes() ;
	}
	
	public int depthPlusHeight(Integer item) {
		// TODO Write this
		int temp[] = this.root.depthPlusHeight(item, 0);
		if (temp[1] == 1) return temp[0] - 1;
		return -1;
//		return this.root.depthPlusHeight(item, b) - 1;
//		if (b) return temp;
	}

	public Integer greatestLessThanOrNull(Integer val) {
		// TODO Write this
		if (this.root == this.NULL_NODE) return null;
		return this.root.greatestLessThanOrNull(val);
	}
		
	// The next methods are used by the unit tests
	public void insert(Integer e) {
		this.root = this.root.insert(e);
	}

	/**
	 * Feel free to call from tests to use to verify the shapes of your trees
	 * while debugging. Just remove the calls you are done so the output isn't
	 * cluttered.
	 * 
	 * @return A string showing a traversal of the nodes where children are
	 *         indented so that the structure of the tree can be determined.
	 * 
	 */
	public String toIndentString() {
		return this.root.toIndentString("");
	}

	@Override
	public String toString() {
		return this.root.toString();
	}

	// /////////////// BinaryNode
	public class BinaryNode {
		
		// TODO Write any recursive methods here
		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @return
		 */
		public int countLeafNodes() {
			// TODO Auto-generated method stub.
			if(this.left == BinarySearchTree.this.NULL_NODE && this.right == BinarySearchTree.this.NULL_NODE) return 1;
			else if (this.left == BinarySearchTree.this.NULL_NODE) return this.right.countLeafNodes();
			else if (this.right == BinarySearchTree.this.NULL_NODE) return this.left.countLeafNodes();
			return this.left.countLeafNodes() + this.right.countLeafNodes();
		}
		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @param val
		 * @param b 
		 * @return
		 */
		public Integer greatestLessThanOrNull(Integer val) {
			// TODO Auto-generated method stub.
			return this.greatestLessThanOrNullHelper(val, null);
		}
		
		private Integer greatestLessThanOrNullHelper(Integer val, Integer found){
			if (this == BinarySearchTree.this.NULL_NODE) return found;
			if (this.data >= val) return this.left.greatestLessThanOrNullHelper(val, found);
			else  return this.right.greatestLessThanOrNullHelper(val,this.data);
			
//			return null;
		}
		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @param item
		 * @param b 
		 * @return
		 */
		public int[] depthPlusHeight(Integer item, int b) {
			// TODO Auto-generated method stub.
			if (this == BinarySearchTree.this.NULL_NODE) return new int[]{0, b};
			if (b == 0) {
				if (item > this.data) {
					int temp[] = this.right.depthPlusHeight(item, b);
					return new int [] {temp[0] + 1, temp[1]};
				}
				else if (item < this.data) {
					int temp[] = this.left.depthPlusHeight(item, b);
					return new int [] {temp[0] + 1, temp[1]};
				}
				else b = 1;
			}
			if(b == 1){
				int left = this.left.depthPlusHeight(item, b)[0] + 1;
				int right = this.right.depthPlusHeight(item, b)[0] + 1;
				return left > right ? new int[] {left, 1} : new int [] {right, 1};
			}
			return null;
		}
		
		
		
		
		
		






		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		// The rest of the methods are used by the unit tests and for debugging
		public BinaryNode(Integer element) {
			this.data = element;
			this.left = BinarySearchTree.this.NULL_NODE;
			this.right = BinarySearchTree.this.NULL_NODE;
		}


		


		public BinaryNode insert(Integer e) {
			if (this == BinarySearchTree.this.NULL_NODE) {
				return new BinaryNode(e);
			} else if (e < this.data) {
				this.left = this.left.insert(e);
			} else if (e > this.data) {
				this.right = this.right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		@Override
		public String toString() {
			if (this == BinarySearchTree.this.NULL_NODE) {
				return "";
			}
			return this.left.toString() + this.data + this.right.toString();
		}

		public String toIndentString(String indent) {
			if (this == BinarySearchTree.this.NULL_NODE) {
				return indent + "NULL\n";
			}
			String myInfo = indent + String.format("%c\n", this.data);
			return myInfo + this.left.toIndentString(indent + "  ") + this.right.toIndentString(indent + "  ");
		}
	}

}