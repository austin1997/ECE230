import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 
 * Implementation of most of the Set interface operations using a Binary Search Tree
 *
 * @author Matt Boutell and Zhihong Zhai.
 * @param <T>
 */

public class BinarySearchTree<T extends Comparable<T>> implements Iterable<T>{
	
	private int change;
	
	
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
		this.change = 0;
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
			if(((Integer) this.data).compareTo(i) > 0)return this.left == BinarySearchTree.this.NULL_NODE?false : this.left.contains(i);
			return this.right == BinarySearchTree.this.NULL_NODE?false : this.right.contains(i);
			
		}

		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @param i
		 * @return
		 */
		public boolean containsNonBST(T i) {
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
		public void toArrayList(ArrayList<T> arr) {
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
//			if(this == BinarySearchTree.this.NULL_NODE) return new ArrayList<T>();
//			else {
//				ArrayList<T> arr = new ArrayList<T>();
//				arr.addAll(this.left.toArrayList());
//				arr.add(this.data);
//				arr.addAll(this.right.toArrayList());
//				return arr;
//			}
			if(this == BinarySearchTree.this.NULL_NODE) return;
			else{
				this.left.toArrayList(arr);
				arr.add(this.data);
				this.right.toArrayList(arr);
			}
		}

		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @param o
		 * @return
		 */
		public BinarySearchTree<T>.BinaryNode insert(T o) {
			// TODO Auto-generated method stub.
			if(this == BinarySearchTree.this.NULL_NODE) return new BinaryNode(o);
			else if(o.compareTo(this.data) < 0) this.left = this.left.insert(o);
			else if(o.compareTo(this.data) > 0) this.right = this.right.insert(o);
			
			return this;
		}

		/**
		 * TODO Put here a description of what this method does.
		 *
		 * @param element
		 * @return
		 */
		public BinarySearchTree<T>.BinaryNode remove(T element) {
			// TODO Auto-generated method stub.
			if (this.data.compareTo(element) > 0) {
				this.left = this.left.remove(element);
			} else if (this.data.compareTo(element) < 0) {
				this.right = this.right.remove(element);
			} else {
				if (this.left == BinarySearchTree.this.NULL_NODE && this.right == BinarySearchTree.this.NULL_NODE)
					return BinarySearchTree.this.NULL_NODE;
				else if (this.left == BinarySearchTree.this.NULL_NODE)
					return this.right;
				else if (this.right == BinarySearchTree.this.NULL_NODE)
					return this.left;
				else {
					BinaryNode temp = this.left;
					while (temp.right != BinarySearchTree.this.NULL_NODE)
						temp = temp.right;
					T t = this.data;
					this.data = temp.data;
					temp.data = t;
					this.left = this.left.remove(t);
				}
			}
			return this;

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
	public boolean containsNonBST(T i) {
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
		ArrayList<T> arr = new ArrayList<T>();
		this.root.toArrayList(arr);
		return arr;
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
	public Iterator<T> inefficientIterator() {
		// TODO Auto-generated method stub.
//		return this.toArrayList().iterator();
		return new InefficientIterator();
	}
	
	public class InefficientIterator implements Iterator<T>{
		private ArrayList<T> list;
		private int index;
		InefficientIterator(){
			this.list = toArrayList();
			this.index = 0;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub.
			return this.index < this.list.size();
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub.
			
			if(!this.hasNext()) throw new NoSuchElementException();
			return this.list.get(this.index++);
		}
		
		
		
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub.
		return new InOrderIterator();
	}
	
	public class InOrderIterator implements Iterator<T>{
		@Override
		public void remove() {
			// TODO Auto-generated method stub.
			if(BinarySearchTree.this.isEmpty() || this.nxt < 1) throw new IllegalStateException();
//			Iterator.super.remove();
			BinarySearchTree.this.remove(this.current.data);
			this.nxt = 0;
		}
		
		private BinaryNode current;
		private int nxt;
		private Stack<BinaryNode> st;
		private int n;
		private final int change;
		public InOrderIterator(){
			this.st = new Stack<BinaryNode>();
			this.st.push(BinarySearchTree.this.root);
			this.n = 0;
			this.change = BinarySearchTree.this.change;
			this.nxt = 0;
			this.current = BinarySearchTree.this.NULL_NODE;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub.
			if(BinarySearchTree.this.change != this.change) throw new ConcurrentModificationException();
			return !(this.st.isEmpty() || this.st.peek() == BinarySearchTree.this.NULL_NODE);
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub.
			if(BinarySearchTree.this.change != this.change) throw new ConcurrentModificationException();
			if(this.st.isEmpty()) throw new NoSuchElementException();
			if(this.st.peek() == BinarySearchTree.this.root && this.n == 0){
				this.n++;
				while(this.st.peek().left != BinarySearchTree.this.NULL_NODE){
					this.st.push(this.st.peek().left);
				}
			}
			this.current = this.st.pop();
			T out = this.current.data;
			if(this.current.right != BinarySearchTree.this.NULL_NODE){
				BinaryNode temp = this.current.right;
				this.st.push(temp);
				while(temp.left != BinarySearchTree.this.NULL_NODE){
					temp = this.current.left;
					this.st.push(temp);
				}
			}
			this.nxt = 1;
			return out;
		}
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public Iterator<T> preOrderIterator() {
		// TODO Auto-generated method stub.
		
		return new PreOrderIterator();
	}
	
	public class PreOrderIterator implements Iterator<T>{
		@Override
		public void remove() {
			// TODO Auto-generated method stub.
			if(BinarySearchTree.this.isEmpty() || this.nxt < 1) throw new IllegalStateException();
//			Iterator.super.remove();
			BinarySearchTree.this.remove(this.current.data);
			this.nxt = 0;
		}
		private BinaryNode current;
		private Stack<BinaryNode> st;
		private final int change;
		private int nxt;
		
		public PreOrderIterator(){
			this.current = BinarySearchTree.this.NULL_NODE;
			this.st = new Stack<BinaryNode>();
			this.st.push(BinarySearchTree.this.root);
			this.change = BinarySearchTree.this.change;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub.
			if(BinarySearchTree.this.change != this.change) throw new ConcurrentModificationException();
			return !(this.st.isEmpty() || this.st.peek() == BinarySearchTree.this.NULL_NODE);
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub.
			if(BinarySearchTree.this.change != this.change) throw new ConcurrentModificationException();
			if(this.st.isEmpty()) throw new NoSuchElementException();
			this.current = this.st.pop();
			if(this.current.right != BinarySearchTree.this.NULL_NODE) this.st.push(this.current.right);
			if(this.current.left != BinarySearchTree.this.NULL_NODE)this.st.push(this.current.left);
			this.nxt = 1;
			return this.current.data;
		}
		
	}
	public boolean insert(T o){
		if(o == null) throw new IllegalArgumentException();
//		if(this.contains(o)) return false;
		this.change ++;
		this.root = this.root.insert(o);
		return true;
	}
	
	public boolean remove(T element){
		if(element == null) throw new IllegalArgumentException();
		if(!this.contains(element)) return false;
		this.change ++;
		this.root = this.root.remove(element);
		return true;
	}

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param i
	 * @return
	 */
	public boolean contains(T i) {
		// TODO Auto-generated method stub.
//		if(this.root.data.equals(i)) return true;
		BinaryNode current = this.root;
		
		while(current != this.NULL_NODE){
			if(current.data.compareTo(i) > 0) current = current.left;
			else if (current.data.compareTo(i) < 0) current = current.right;
			else return true;
		}
		
		
		return false;
	}
	 

}
