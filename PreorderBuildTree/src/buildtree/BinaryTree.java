package buildtree;

/**
 * 
 * @author Matt Boutell and <<<YOUR NAME HERE>>>.
 * @param <T>
 */

public class BinaryTree {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinaryTree() {
		root = NULL_NODE;
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
	 */
	public BinaryTree(String chars, String children) {
		// TODO: Implement this constructor. You may not add any other fields to
		// the BinaryTree class, but you may add local variables and helper
		// methods if you like.
	}

	/**
	 * In-order traversal of the characters
	 */
	@Override
	public String toString() {
		return root.toString();
	}

	/**
	 * @return A string showing an in-order traversal of nodes with extra
	 *         brackets so that the structure of the tree can be determined.
	 */
	public String toStructuredString() {
		return root.toStructuredString();
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public Character data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Character element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + data + right.toString();
		}

		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "(" + left.toStructuredString() + this.data
					+ right.toStructuredString() + ")";
		}

	}
}