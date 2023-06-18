//package dsaa.lab07;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Document implements IWithName{
	private static final int MODVALUE=100000000;
	private static final int[] HASH_SEQUENCE={7, 11, 13, 17, 19};
	private final char[] nameChars;
	public String name;
	public TwoWayCycledOrderedListWithSentinel<Link> link;
	public Document(String name) {
		this.name=name.toLowerCase();
		link=new TwoWayCycledOrderedListWithSentinel<Link>();
		nameChars = this.name.toCharArray();
	}

	public Document(String name, Scanner scan) {
		this.name=name.toLowerCase();
		link=new TwoWayCycledOrderedListWithSentinel<Link>();
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

	@Override
	public String toString() {
		String retStr = "Document: " + name + "\n";
		Iterator<Link> iter = link.iterator();
		// put 10 links in one line
		int i = 0;
		while (iter.hasNext()) {
			i++;
			if (i == 10) {
				retStr += iter.next().toString() + "\n";
				i = 0;
				continue;
			}
			retStr += iter.next().toString() + " ";
		}
		// remove last space or new line
		retStr = retStr.substring(0, retStr.length() - 1);
		return retStr;
	}

	public String toStringReverse() {
		String retStr = "Document: " + name + "\n";
		ListIterator<Link> iter = link.listIterator();
		// put 10 links in one line
		int i = 0;
		while (iter.hasPrevious()) {
			i++;
			if (i == 10) {
				retStr += iter.previous().toString() + "\n";
				i = 0;
				continue;
			}
			retStr += iter.previous().toString() + " ";
		}
		// remove last space or new line
		retStr = retStr.substring(0, retStr.length() - 1);
		return retStr;
	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public boolean equals(Object o){
		if(o instanceof Document){
			return name.equals(((IWithName)o).getName());
		}
		return false;
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
//	public static void main(String[] args) {
//		Document doc1 = new Document("1doc123");
//		Document doc2 = new Document("1doc123");
//		System.out.println(doc1.equals(doc2));
//
//		Document doc3 = new Document("1doc123");
//		Document doc4 = new Document("0dasdoc123");
//		System.out.println(doc3.equals(doc4));
//
//		Document doc5 = new Document("abcd");
//		System.out.println(doc5.hashCode());
//		System.out.println((int) 'd');
//	}
}

