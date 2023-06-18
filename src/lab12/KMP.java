import java.util.Arrays;
import java.util.LinkedList;

public class KMP implements IStringMatcher {

	@Override
	public LinkedList<Integer> validShifts(String pattern, String text) {
		if (pattern.length() > text.length() || pattern.length() == 0 || text.length() == 0) {
			return new LinkedList<>();
		}
		LinkedList<Integer> shifts = new LinkedList<>();
		int[] prefixFunction = buildPrefixFunction(pattern);

		int q = 0;
		for (int i = 0; i < text.length(); i++) {
			while (q > 0 && pattern.charAt(q) != text.charAt(i)) {
				q = prefixFunction[q - 1];
			}
			if (pattern.charAt(q) == text.charAt(i)) {
				q++;
			}
			if (q == pattern.length()) {
				shifts.add(i - pattern.length() + 1);
				q = prefixFunction[q - 1];
			}
		}
		return shifts;
	}

	private int[] buildPrefixFunction(String pattern) {
		int[] prefixFunction = new int[pattern.length()];
		prefixFunction[0] = 0;
		int k = 0;
		for (int q = 1; q < pattern.length(); q++) {
			while (k > 0 && pattern.charAt(k) != pattern.charAt(q)) {
				k = prefixFunction[k - 1];
			}
			if (pattern.charAt(k) == pattern.charAt(q)) {
				k++;
			}
			prefixFunction[q] = k;
		}
		return prefixFunction;
	}

	public static void main(String[] args) {
		KMP kmp = new KMP();
		String pattern = "abcdabd";
		System.out.println(Arrays.toString(kmp.buildPrefixFunction(pattern)));
	}
}
