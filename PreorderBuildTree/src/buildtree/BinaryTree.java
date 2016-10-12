package buildtree;

import java.util.Stack;

/**
 * 
 * @author Matt Boutell and <<<YOUR NAME HERE>>>.
 * @param <T>
 */

public class BinaryTree {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinaryTree() {
		this.root = this.NULL_NODE;
	}

	/**
	 * Constructs a tree (any tree of characters, not just a BST) with the given
	 * values and number of children, given in a pre-order traversal order. See
	 * the HW spec for more details.
	 * 
	 * @param chars
	 *            One char per node.
	 * @param children
	 *            L,R, 2, or 0.
	 * @throws Exception 
	 */
	public BinaryTree(String chars, String children){
		// TODO: Implement this constructor. You may not add any other fields to
		// the BinaryTree class, but you may add local variables and helper
		// methods if you like.
		if (chars.length() <= 0){
			this.root = this.NULL_NODE;
			return;
		}
		Stack<BinaryNode> st = new Stack <BinaryNode>();
		this.root = new BinaryNode(chars.charAt(0));
		BinaryNode current = this.root;
		for (int i = 0; i < chars.length() - 1; i++){
			if (children.charAt(i) == '2'){
				current.left = new BinaryNode(chars.charAt(i + 1));
				st.push(current);
				current = current.left;
			}else if (children.charAt(i) == 'R'){
				current.right = new BinaryNode(chars.charAt(i + 1));
				current = current.right;
			}else if (children.charAt(i) == 'L'){
				current.left = new BinaryNode(chars.charAt(i + 1));
				current = current.left;
			}else if (children.charAt(i) == '0'){
				current = st.pop();
				current.right = new BinaryNode(chars.charAt(i + 1));
				current = current.right;
			}
		}
		
		
	}

	/**
	 * In-order traversal of the characters
	 */
	@Override
	public String toString() {
		return this.root.toString();
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

		public Character data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Character element) {
			this.data = element;
			this.left = BinaryTree.this.NULL_NODE;
			this.right = BinaryTree.this.NULL_NODE;
		}

		@Override
		public String toString() {
			if (this == BinaryTree.this.NULL_NODE) {
				return "";
			}
			return this.left.toString() + this.data + this.right.toString();
		}

		public String toStructuredString() {
			if (this == BinaryTree.this.NULL_NODE) {
				return "";
			}
			return "(" + this.left.toStructuredString() + this.data
					+ this.right.toStructuredString() + ")";
		}

	}
}