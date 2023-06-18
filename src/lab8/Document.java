import java.util.Scanner;
import java.util.Iterator;
import java.util.ListIterator;
public class Document implements IWithName{
	private static final int MODVALUE=100000000;
	private static final int[] HASH_SEQUENCE={7, 11, 13, 17, 19};
	private final char[] nameChars;
	public String name;
	public BST<Link> link;
	public Document(String name) {
		this.name=name.toLowerCase();
		link=new BST<Link>();
		nameChars = this.name.toCharArray();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new BST<Link>();
		nameChars = this.name.toCharArray();
		load(scan);
	}
	public void load(Scanner scan) {
		String line = scan.nextLine().toLowerCase();
		while (!line.equals("eod")) {
			String[] words = line.split(" ");
			for (String word : words) {
				tryLink(word);
			}
			line = scan.nextLine().toLowerCase();
		}
	}

	private void tryLink(String word) {
		if (!word.toLowerCase().startsWith("link=")) {
			return;
		}
		String[] parts = word.split("=");
		if (parts.length != 2) {
			return;
		}
		char[] linkChars = parts[1].toCharArray();
		// check if link has a weight -> last n chars are of the form (x) where x is a natural number
		// defaults to 1, if link has a weight change it
		int weight = 1;
		if (linkChars[linkChars.length - 1] == ')') {
			int i = linkChars.length - 2;
			while (i >= 0 && Character.isDigit(linkChars[i])) {
				i--;
			}
			if (i == 0 || linkChars[i] != '(') {
				return;
			}
			// assign weight
			weight = Integer.parseInt(parts[1].substring(i + 1, parts[1].length() - 1));
			// remove weight from link
			parts[1] = parts[1].substring(0, i);
		}
		if (isCorrectId(parts[1])) {
			link.add(new Link(parts[1], weight));
		}
	}
	public static boolean isCorrectId(String id) {
		char[] linkChars = id.toCharArray();
		if (Character.isLetter(linkChars[0])) {
			for (int i = 1; i < linkChars.length; i++) {
				if (!Character.isLetterOrDigit(linkChars[i]) && linkChars[i] != '_') {
					return false;
				}
			}
		} else {
			return false;
		}
		return true;
	}

	public static Link createLink(String link) {
		char[] linkChars = link.toCharArray();
		// check if link has a weight -> last n chars are of the form (x) where x is a natural number
		// defaults to 1, if link has a weight change it
		int weight = 1;
		if (linkChars[linkChars.length - 1] == ')') {
			int i = linkChars.length - 2;
			while (i >= 0 && Character.isDigit(linkChars[i])) {
				i--;
			}
			if (i == 0 || linkChars[i] != '(') {
				return null;
			}
			// assign weight
			weight = Integer.parseInt(link.substring(i + 1, link.length() - 1));
			// remove weight from link
			link = link.substring(0, i);
		}
		if (isCorrectId(link)) {
			return new Link(link, weight);
		}
		return null;
	}

	@Override
	public String toString() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringInOrder();		
		return retStr;
	}

	public String toStringPreOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringPreOrder();
		return retStr;
	}

	public String toStringPostOrder() {
		String retStr="Document: "+name+"\n";
		retStr+=link.toStringPostOrder();
		return retStr;
	}

	@Override public int hashCode(){
		int hash = nameChars[0];
		int i = 0;
		for (int index = 1; index < nameChars.length; index++) {
			hash = (HASH_SEQUENCE[i] * hash + nameChars[index]) % MODVALUE;
			i = (i + 1) % HASH_SEQUENCE.length;
		}
		return hash;
	}

	@Override
	public String getName() {
		return name;
	}
}
