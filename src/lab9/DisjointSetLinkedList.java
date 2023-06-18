public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private class Element{
		int representant;
		int next;
		int length;
		int last;
	}
	
	private static final int NULL=-1;
	
	Element arr[];
	
	public DisjointSetLinkedList(int size) {
		arr=new Element[size];
		for (int i = 0; i < arr.length; i++)
			makeSet(i);
	}
	
	@Override
	public void makeSet(int item) {
		arr[item]=new Element();
		arr[item].representant=item;
		arr[item].next=NULL;
		arr[item].length=1;
		arr[item].last=item;
	}

	@Override
	public int findSet(int item) {
		return arr[item].representant;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		if (arr[itemA].representant == arr[itemB].representant)
			return false;
		int headA = arr[itemA].representant;
		int headB = arr[itemB].representant;
		if (arr[headA].length < arr[headB].length) {
			// B is bigger

			arr[headB].length += arr[headA].length;
			arr[arr[headB].last].next=headA;
			arr[headB].last=arr[headA].last;

			int current=headA;
			while(current!=NULL){
				arr[current].representant=headB;
				current=arr[current].next;
			}
		} else {
			// A is bigger

			arr[headA].length += arr[headB].length;
			arr[arr[headA].last].next=headB;
			arr[headA].last=arr[headB].last;

			int current=headB;
			while(current!=NULL){
				arr[current].representant=headA;
				current=arr[current].next;
			}
		}
		return true;
	}

	
	@Override
	public String toString() {
		String ret="Disjoint sets as linked list:\n";
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].representant == i) {
				ret+=i;
				int current = arr[i].next;
				while (current != NULL) {
					ret+= ", " + current;
					current = arr[current].next;
				}
				ret+="\n";
			}
		}
		return ret.substring(0, ret.length()-1);
	}
}
