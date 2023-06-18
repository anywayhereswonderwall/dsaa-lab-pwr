import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BST<T> {
	private class Node{
		T value;
		Node left,right,parent;
		public Node(T v) {
			value=v;
		}
	}		
	private Node root=null;
	private int size=0;
	public BST() {
		size=0;
	}

	public T getElement(T toFind) {
		Node current = root;
		while (current != null) {
			int cmp = ((Comparable<T>) toFind).compareTo(current.value);
			if (cmp > 0) current = current.right;
			else if (cmp < 0) current = current.left;
			else return current.value;
		}
		return null;
	}

	public T successor(T elem) {
		Node current = root;
		T successor = null;
		while (current != null) {
			int cmp = ((Comparable<T>) elem).compareTo(current.value);
			if (cmp < 0) {
				if (successor == null) {
					successor = current.value;
				}
				int cmp_successor_to_current = ((Comparable<T>) successor).compareTo(current.value);
				if (cmp_successor_to_current > 0) successor = current.value;
				current = current.left;
			}
			else {
				current = current.right;
			}
		}
		return successor;
	}

	private String toStringInOrder(Node node) {
		if (node == null) return "";
		return toStringInOrder(node.left) + node.value + ", " + toStringInOrder(node.right);
	}
	public String toStringInOrder() {
		String ret = toStringInOrder(root);
		if (ret == "") return "";
		return ret.substring(0, ret.length() - 2);
	}
	private String toStringPreOrder(Node node) {
		if (node == null) return "";
		return node.value + ", " + toStringPreOrder(node.left) + toStringPreOrder(node.right);
	}
	public String toStringPreOrder() {
		String ret = toStringPreOrder(root);
		if (ret == "") return "";
		return ret.substring(0, ret.length() - 2);
	}
	private String toStringPostOrder(Node node) {
		if (node == null) return "";
		return toStringPostOrder(node.left) + toStringPostOrder(node.right) + node.value + ", ";
	}
	public String toStringPostOrder() {
		String ret = toStringPostOrder(root);
		if (ret == "") return "";
		return ret.substring(0, ret.length() - 2);
	}

	private Node add(Node node, T value) {
		if (node == null) {
			size++;
			return new Node(value);
		}
		int cmp = ((Comparable<T>) value).compareTo(node.value);
		if (cmp > 0) {
			Node right = add(node.right, value);
			right.parent = node;
			node.right = right;
		}
		else if (cmp < 0) {
			Node left = add(node.left, value);
			left.parent = node;
			node.left = left;
		}
		else node.value = value;
		return node;
	}
	public boolean add(T elem) {
//		root = add(root, elem);
//		return true;
		// simpler approach
		Node current = root;
		Node parent = null;
		while (current != null) {
			int cmp = ((Comparable<T>) elem).compareTo(current.value);
			if (cmp > 0) {
				parent = current;
				current = current.right;
			}
			else if (cmp < 0) {
				parent = current;
				current = current.left;
			}
			else return false;
		}
		Node newNode = new Node(elem);
		if (parent == null) root = newNode;
		else {
			int cmp = ((Comparable<T>) elem).compareTo(parent.value);
			if (cmp > 0) parent.right = newNode;
			else parent.left = newNode;
		}
		size++;
		return true;
	}
	private Node getNode(T toFind) {
		Node current = root;
		while (current != null) {
			// cmp is an int returned from compareTo method
			int cmp = ((Comparable<T>) toFind).compareTo(current.value);
			if (cmp > 0) current = current.right;
			else if (cmp < 0) current = current.left;
			else return current;
		}
		return null;
	}
	private Node remove(Node node, T value) {
		// 1. no children -> remove reference from parent's node
		// 2. one child -> return reference to its child to the parent's node
		// 3. two children -> swap values of element to delete with min value in its subtree,
		// then deleting value from the new place in the tree is either case 1. or 2.
		if (node == null) return null;
		int cmp = ((Comparable<T>) value).compareTo(node.value);
		if (cmp < 0) node.left = remove(node.left, value);
		else if (cmp > 0) node.right = remove(node.right, value);
		else {
			if (node.right == null) return node.left;
			if (node.left == null) return node.right;

			Node temp = node;
			node = min(temp.right);
			node.right = removeMin(temp.right);
			node.left = temp.left;
		}
		return node;
	}
	private Node removeMin(Node node) {
		if (node.left == null) return node.right;
		node.left = removeMin(node.left);
		return node;
	}
	private Node min(Node node) {
		Node current = node;
		while (current.left != null) {
			current = current.left;
		}
		return current;
	}
	public T remove(T value) {
		// finding node with value to delete
//		Node toDelete = getNode(value);
//		if (toDelete == null) return null;
//		T toDeleteValue = toDelete.value;
//		root = remove(root, value);
//		size--;
//		return toDeleteValue;
		Node current = root;
		Node parent = null;
		while (current != null) {
			int cmp = ((Comparable<T>) value).compareTo(current.value);
			if (cmp > 0) {
				parent = current;
				current = current.right;
			}
			else if (cmp < 0) {
				parent = current;
				current = current.left;
			}
			else {
				T toDeleteValue = current.value;
				if (current.right == null) {
					if (parent == null) root = current.left;
					else {
						int cmp2 = ((Comparable<T>) value).compareTo(parent.value);
						if (cmp2 > 0) parent.right = current.left;
						else parent.left = current.left;
					}
				}
				else if (current.left == null) {
					if (parent == null) root = current.right;
					else {
						int cmp2 = ((Comparable<T>) value).compareTo(parent.value);
						if (cmp2 > 0) parent.right = current.right;
						else parent.left = current.right;
					}
				}
				else {
					Node temp = current;
					current = min(temp.right);
					current.right = removeMin(temp.right);
					current.left = temp.left;
					if (parent == null) root = current;
					else {
						int cmp2 = ((Comparable<T>) value).compareTo(parent.value);
						if (cmp2 > 0) parent.right = current;
						else parent.left = current;
					}
				}
				size--;
				return toDeleteValue;
			}
		}
		return null;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public int countNodesWithTwoChildren() {
		return countNodesWithTwoChildren(root);
	}
	private int countNodesWithTwoChildren(Node node) {
		if (node == null) return 0;
		int count = 0;
		if (node.left != null && node.right != null) count++;
		return count + countNodesWithTwoChildren(node.left) + countNodesWithTwoChildren(node.right);
	}

	// DFS
	static int answer = 0;
	public void longestPathOfNodesWithOneChild(Node node, int current_longest_path) {
		if (node.left == null ^ node.right == null) {
			current_longest_path++;
			if (node.right != null) longestPathOfNodesWithOneChild(node.right, current_longest_path);
			else longestPathOfNodesWithOneChild(node.left, current_longest_path);
		} else if (node.left == null && node.right == null) {
			answer = Math.max(answer, current_longest_path);
		} else {
			longestPathOfNodesWithOneChild(node.left, 0);
			longestPathOfNodesWithOneChild(node.right, 0);
		}
	}
	// task 8 list 5.
	private void serialize(Node root, ArrayList<T> curr) {
		if (root == null) {
			return;
		}
		curr.add(root.value);
		serialize(root.left, curr);
		serialize(root.right, curr);
	}
	public ArrayList<T> serialize(Node root) {
		ArrayList<T> curr = new ArrayList<>();
		serialize(root, curr);
		return curr;
	}
	public BST<T> deserialize(ArrayList<T> arr) {
		BST<T> bst = new BST<>();
		for (T value : arr) {
			bst.add(value);
		}
		return bst;
	}
	// end of task 8 list 5.

	// task 9 list 5.
	public void printBFS(Node root) {
		if (root == null) return;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node current = queue.remove();
			System.out.print(current.value + " ");
			if (current.left != null) queue.add(current.left);
			if (current.right != null) queue.add(current.right);
		}
	}
	// end of task 9 list 5.

	// task 11 list 5.
	// print also connections and keep correct spacing between nodes
	public void printGraphically(int[] arr) {
		int height = 4;
		int width = (int) Math.pow(2, height) - 1;
		int[] spaces = new int[height];
		int[] dashes = new int[height];
		for (int i = 0; i < height; i++) {
			spaces[i] = (int) Math.pow(2, height - i) - 1;
			dashes[i] = (int) Math.pow(2, height - i + 1) - 1;
		}
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		int current_level = 0;
		int current_space = spaces[current_level];
		int current_dash = dashes[current_level];
		while (!queue.isEmpty()) {
			Node current = queue.remove();
			for (int i = 0; i < current_space; i++) {
				System.out.print(" ");
			}
			if (current == null) {
				System.out.print(" ");
			} else {
				System.out.print(current.value);
			}
			for (int i = 0; i < current_space; i++) {
				System.out.print(" ");
			}
			if (current == null) {
				queue.add(null);
				queue.add(null);
			} else {
				queue.add(current.left);
				queue.add(current.right);
			}
			if (current_dash == 0) {
				System.out.println();
				current_level++;
				if (current_level == height) break;
				current_space = spaces[current_level];
				current_dash = dashes[current_level];
			} else {
				for (int i = 0; i < current_dash; i++) {
					System.out.print("-");
				}
				current_dash--;
			}
		}
	}
	// end of task 11 list 5.
	public static void main(String[] args) {
		BST<Integer> bst = new BST<>();
		bst.add(20);
		bst.add(7);
		bst.add(10);
		bst.add(25);
		bst.add(4);
		bst.add(1);
		bst.add(2);
		bst.add(12);
		bst.add(30);
		bst.remove(12);
		bst.remove(1);
		bst.remove(20);
	}
}
