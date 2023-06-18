package lab3;//package dsaa.lab03;

import java.util.Scanner;

public class Document{
    public String name;
    public TwoWayUnorderedListWithHeadAndTail<Link> link;
    public Document(String name, Scanner scan) {
        this.name = name;
        link = new TwoWayUnorderedListWithHeadAndTail<Link>();
        load(scan);
    }
    public void load(Scanner scan) {
        String line = scan.nextLine().toLowerCase();
        while (!line.equals("eod")) {
            String[] words = line.split(" ");
            for (String word : words) {
                if (correctLink(word))
                {
                    String[] parts = word.split("=");
                    link.add(new Link(parts[1]));
                }
            }
            line = scan.nextLine().toLowerCase();
        }
    }
    public static boolean correctLink(String link) {
        if (!link.toLowerCase().startsWith("link=")) {
            return false;
        }
        String[] parts = link.split("=");
        if (parts.length != 2) {
            return false;
        }
        char[] linkChars = parts[1].toCharArray();
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
        String out = "Document: " + name;
        for (Link e : link) {
            out += "\n" + e;
        }
        return out;
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        return retStr+link.toStringReverse();
    }

}