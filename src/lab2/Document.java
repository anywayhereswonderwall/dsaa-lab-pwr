package lab2;

import java.util.Scanner;

public class Document{
    public String name;
    public OneWayLinkedList<Link> links = new OneWayLinkedList<>();;
    public Document(String name, Scanner scan) {
        this.name = name;
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
                    links.add(new Link(parts[1]));
                }
            }
            line = scan.nextLine().toLowerCase();
        }
    }
    private static boolean correctLink(String link) {
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
        for (Link link : links) {
            out += "\n" + link;
        }
        return out;
    }
}
