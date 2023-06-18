import java.util.Scanner;

public class LinesReader {
		String concatLines(int howMany, Scanner scanner) {
			// int howMany indicates how many lines of string input will be provided,
			// using stringBuffer and input from scanner build a continuous string from these lines
			// return this string
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < howMany; i++) {
				sb.append(scanner.nextLine());
			}
			return sb.toString();
		}
}
