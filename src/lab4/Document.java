//package dsaa.lab04;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Document{
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;
    public Document(String name, Scanner scan) {
        this.name=name.toLowerCase();
        link=new TwoWayCycledOrderedListWithSentinel<Link>();
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
}
