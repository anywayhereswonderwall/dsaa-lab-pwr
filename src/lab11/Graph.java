import java.util.*;
import java.util.Map.Entry;

public class Graph {
	int matrix[][];
	private final int size;
	HashMap<String,Integer> nameToInt;
	String[] intToName;
	DisjointSetForest quickUnion;
	public Graph(SortedMap<String,Document> nodes){
		size = nodes.size();
		matrix = new int[size][size];
		nameToInt = new HashMap<>();
		intToName = new String[size];
		quickUnion = new DisjointSetForest(size);
		int i = 0;
		for (Entry<String, Document> entry : nodes.entrySet()) {
			nameToInt.put(entry.getKey(), i);
			intToName[i] = entry.getKey();
			i++;
		}
		for (Entry<String, Document> entry : nodes.entrySet()) {
			int from = nameToInt.get(entry.getKey());
			for (Link to : entry.getValue().getLinks()) {
				if (!nameToInt.containsKey(to.ref)) {
					continue;
				}
				int toInt = nameToInt.get(to.ref);
				int weight = to.weight;
				if (toInt != from && toInt < size) {
					matrix[from][toInt] = weight;
					quickUnion.union(from, toInt);
				}
			}
		}
	}
	
	public String bfs(String start) {
		if (!nameToInt.containsKey(start)) {
			return null;
		}
		String ret = "";
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		int startInt = nameToInt.get(start);
		q.add(startInt);
		visited.add(startInt);
		while (!q.isEmpty()) {
			int current = q.poll();
			ret += intToName[current] + ", ";
			for (int i = 0; i < size; i++) {
				if (matrix[current][i] > 0 && !visited.contains(i)) {
					q.add(i);
					visited.add(i);
				}
			}
		}
		if (ret.length() == 0) {
			return null;
		}
		return ret.substring(0, ret.length() - 2);
	}
	
	public String dfs(String start) {
		if (!nameToInt.containsKey(start)) {
			return null;
		}
		String ret = "";
		Stack<Integer> s = new Stack<>();
		Set<Integer> visited = new HashSet<>();
		int startInt = nameToInt.get(start);
		s.push(startInt);

		while (!s.isEmpty()) {
			int current = s.pop();
			if (!visited.contains(current)) {
				ret += intToName[current] + ", ";
				visited.add(current);
				for (int i = size - 1; i >= 0; i--) {
					if (matrix[current][i] > 0 && !visited.contains(i)) {
						s.push(i);
					}
				}
			}
		}
		if (ret.length() == 0) {
			return null;
		}
		return ret.substring(0, ret.length() - 2);
	}
	public int connectedComponents() {
		return quickUnion.countSets();
	}

    public String DijkstraSSSP(String s) {
		if (!nameToInt.containsKey(s)) return null;

		int[] shortestDistFromS = new int[size];
		int[] previousVertex = new int[size];
		Set<Integer> visited = new HashSet<>();
		int startVertex = nameToInt.get(s);

		for (int i = 0; i < size; i++) {
			if (i == startVertex) shortestDistFromS[i] = 0;
			else shortestDistFromS[i] = Integer.MAX_VALUE;
		}

		PriorityQueue<Integer> q = new PriorityQueue<>(size, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return shortestDistFromS[o1] - shortestDistFromS[o2];
			}
		});
		q.add(startVertex);
		while (!q.isEmpty()) {
			int currentVertex = q.poll();
			if (visited.contains(currentVertex)) continue;
			for (int i = 0; i < size; i++) {
				if (matrix[currentVertex][i] > 0) {
					if (shortestDistFromS[currentVertex] + matrix[currentVertex][i] < shortestDistFromS[i]) {
						shortestDistFromS[i] = shortestDistFromS[currentVertex] + matrix[currentVertex][i];
						previousVertex[i] = currentVertex;
						q.add(i);
					}
				}
			}

			visited.add(currentVertex);
		}

		String out = "";
		for (int i = 0; i < size; i++) {
			if (shortestDistFromS[i] == Integer.MAX_VALUE) out += "no path to " + intToName[i] + "\n";
			else {
				Stack<String> path = new Stack<>();
				int currentVertex = i;
				while (currentVertex != startVertex) {
					path.push(intToName[currentVertex]);
					currentVertex = previousVertex[currentVertex];
				}
				path.push(intToName[startVertex]);
				while (!path.isEmpty()) {
					out += path.pop();
					if (!path.isEmpty()) out += "->";
				}
				out += "=" + shortestDistFromS[i] + "\n";
			}
		}
		return out;
    }
}
