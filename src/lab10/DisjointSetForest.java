public class DisjointSetForest implements DisjointSetDataStructure {
	private class Element{
		int rank;
		int parent;
	}

	Element []arr;

	public DisjointSetForest(int size) {
		arr=new Element[size];
		for (int i = 0; i < arr.length; i++)
			makeSet(i);
	}
	private int root(int item){
		if (arr[item].parent==item)
			return item;
		int root=root(arr[item].parent);
		arr[item].parent=root;
		return root;
	}
	@Override
	public void makeSet(int item) {
		arr[item]=new Element();
		arr[item].parent=item;
		arr[item].rank=0;
	}

	@Override
	public int findSet(int item) {
		return root(item);
	}

	@Override
	public boolean union(int itemA, int itemB) {
		int rootA=root(itemA);
		int rootB=root(itemB);
		if(rootA==rootB)
			return false;
		if(arr[rootA].rank>arr[rootB].rank){
			arr[rootB].parent=rootA;
		}
		else{
			arr[rootA].parent=rootB;
			if(arr[rootA].rank==arr[rootB].rank)
				arr[rootB].rank++;
		}
		return true;
	}


	@Override
	public String toString() {
		String ret="Disjoint sets as forest:\n";
		for (int i = 0; i < arr.length; i++) {
			ret+= i + " -> " + arr[i].parent + "\n";
		}
		return ret.substring(0,ret.length()-1);
	}
	public int countSets() {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].parent == i) {
				count++;
			}
		}
		return count;
	}
}
