package lab1;
import java.io.File;
import java.util.Scanner;

public class Document {
    public static void loadDocument(String name, Scanner scan) {
//        check if file name exists in the directory
//        boolean f = new File(name).isFile();
        String line = scan.nextLine();
        while (!line.equals("eod")) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (correctLink(word))
                    {
                        String[] parts = word.split("=");
                        System.out.println(parts[1].toLowerCase());
                    }
                }
            line = scan.nextLine().strip();
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

}