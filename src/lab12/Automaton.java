import java.util.*;

public class Automaton implements IStringMatcher {

	@Override
	public LinkedList<Integer> validShifts(String pattern, String text) {
		if (pattern.length() > text.length() || pattern.length() == 0 || text.length() == 0) {
			return new LinkedList<>();
		}
		LinkedList<Integer> shifts = new LinkedList<>();
		char[] alphabet = getAlphabet(pattern);
		Map<Character, Integer> charToIndexMap = getCharToIndexMap(alphabet);
		int[][] transitionFunction = computeTransitionFunction(pattern, alphabet, charToIndexMap);
		int q = 0;
		for (int i = 0; i < text.length(); i++) {
			if (charToIndexMap.containsKey(text.charAt(i))) {
				q = transitionFunction[q][charToIndexMap.get(text.charAt(i))];
			} else {
				q = 0;
			}
			if (q == pattern.length()) {
				shifts.add(i - pattern.length() + 1);
			}
		}
		return shifts;
	}
	private int[][] computeTransitionFunction(String pattern, char[] alphabet, Map<Character, Integer> charToIndexMap) {
		int[][] transitionFunction = new int[pattern.length() + 1][alphabet.length];
		char[] patternChars = pattern.toCharArray();
		for (int i = 0; i < alphabet.length; i++) {
			transitionFunction[0][i] = 0;
		}
		transitionFunction[0][charToIndexMap.get(patternChars[0])] = 1;
		int x = 0;
		for (int i = 1; i <= pattern.length(); i++) {
			System.arraycopy(transitionFunction[x], 0, transitionFunction[i], 0, alphabet.length);
			if (i < pattern.length()) {
				transitionFunction[i][charToIndexMap.get(patternChars[i])] = i + 1;
				x = transitionFunction[x][charToIndexMap.get(patternChars[i])];
			}
		}
		System.out.println(Arrays.deepToString(transitionFunction));
		return transitionFunction;
	}
	private char[] getAlphabet(String pattern) {
		Set<Character> alphabetSet = new HashSet<>();
		for (char c : pattern.toCharArray()) {
			alphabetSet.add(c);
		}
		char[] alphabet = new char[alphabetSet.size()];
		int i = 0;
		for (char c : alphabetSet) {
			alphabet[i++] = c;
		}
		return alphabet;
	}
	private Map<Character, Integer> getCharToIndexMap(char[] alphabet) {
		Map<Character, Integer> charToIndexMap = new HashMap<>();
		for (int i = 0; i < alphabet.length; i++) {
			charToIndexMap.put(alphabet[i], i);
		}
		return charToIndexMap;
	}

	public static void main(String[] args) {
		Automaton automaton = new Automaton();
		String pattern = "aabab";
		automaton.validShifts(pattern, "sdasdsdasdsadasd");
	}
}
