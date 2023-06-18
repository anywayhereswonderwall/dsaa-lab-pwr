//package dsaa.lab07;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>{

	private class Element{
		public Element(E e) {
			this.object = e;
		}
		//        public Element(E e, Element next, Element prev) {
//            this.object = e;
//            this.next = next;
//            this.prev = prev;
//        }
		// add element e after this
		public void addAfter(Element elem) {
			Element next = this.next;
			elem.next=next;
			elem.prev=this;
			this.next=elem;
			next.prev=elem;
		}
		// assert it is NOT a sentinel
		public void remove() {
			assert this != sentinel;
			Element prev=this.prev;
			Element next=this.next;
			prev.next=next;
			next.prev=prev;
		}
		E object;
		Element next=null;
		Element prev=null;
	}


	Element sentinel;
	int size;

	private class InnerIterator implements Iterator<E>{
		Element current;
		public InnerIterator() {
			current = sentinel.next;
		}
		@Override
		public boolean hasNext() {
			return current != sentinel;
		}

		@Override
		public E next() {
			if (current == sentinel)
				throw new NoSuchElementException();
			E ret = current.object;
			current = current.next;
			return ret;
		}
	}

	private class InnerListIterator implements ListIterator<E>{
		// used for reverse iteration
		// sets initial node to be the last one -> no need to call next before previous
		Element current;
		public InnerListIterator() {
			current = sentinel.prev;
		}
		@Override
		public boolean hasNext() {
			return current != sentinel;
		}

		@Override
		public E next() {
			if (current == sentinel)
				throw new NoSuchElementException();
			E ret = current.object;
			current = current.next;
			return ret;
		}
		@Override
		public boolean hasPrevious() {
			return current != sentinel;
		}
		@Override
		public E previous() {
			if (current == sentinel)
				throw new NoSuchElementException();
			E ret = current.object;
			current = current.prev;
			return ret;
		}
		@Override
		public void add(E arg0) {
			throw new UnsupportedOperationException();
		}
		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		@Override
		public void set(E arg0) {
			throw new UnsupportedOperationException();
		}
	}
	public TwoWayCycledOrderedListWithSentinel() {
		sentinel = new Element(null);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	//@SuppressWarnings("unchecked")
	@Override
	public boolean add(E e) {
		size++;
		// checks if the list is empty and puts the element in the first position
		if (isEmpty()) {
			sentinel.addAfter(new Element(e));
			return true;
		}
		Element current = sentinel.next;
		// iterates through the list to find first element greater than e and puts e before it
		while (current != sentinel && ((Comparable<E>) current.object).compareTo(e) <= 0) {
			current = current.next;
		}
		// if current == sentinel then e is the greatest element in the list, and it is put at the end
		current.prev.addAfter(new Element(e));
		return true;
	}

	private Element getElement(int index) {
		// check if index is valid
		if (index < 0 || index >= size)
			throw new NoSuchElementException();
		// iterate through the list to find the element at index
		int current_index = 0;
		Element current = sentinel.next;
		while (current_index < index) {
			current = current.next;
			current_index++;
		}
		return current;
	}
	private Element getElement(E obj) {
		Element current = sentinel.next;
		while (current != sentinel) {
			if (current.object.equals(obj))
				return current;
			current = current.next;
		}
		return null;
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		size = 0;
	}

	@Override
	public boolean contains(E element) {
		Element elem = getElement(element);
		return elem != null;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size)
			throw new NoSuchElementException();
		return getElement(index).object;
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(E element) {
		int index = 0;
		Element current = sentinel.next;
		while (current != sentinel) {
			if (current.object.equals(element))
				return index;
			current = current.next;
			index++;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return sentinel.next == sentinel;
	}

	@Override
	public Iterator<E> iterator() {
		return new InnerIterator();
	}

	@Override
	public ListIterator<E> listIterator() {
		return new InnerListIterator();
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size)
			throw new NoSuchElementException();
		Element elem = getElement(index);
		elem.remove();
		size--;
		return elem.object;
	}

	@Override
	public boolean remove(E e) {
		Element elem = getElement(e);
		if (elem != null) {
			elem.remove();
			size--;
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	//@SuppressWarnings("unchecked")
	public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
		if (other == null || other.isEmpty() || other == this)
			return;
		// implementation without creating a new list
		size += other.size;
		Element current = sentinel.next;
		Element other_current = other.sentinel.next;
		while (current != sentinel && other_current != other.sentinel) {
			if (((Comparable<E>) other_current.object).compareTo(current.object) < 0) {
				Element tmp = other_current.next;
				current.prev.addAfter(other_current);
				current = current.prev;
				other_current = tmp;
			} else {
				current = current.next;
			}
		}
		while (other_current != other.sentinel) {
			Element tmp = other_current.next;
			sentinel.prev.addAfter(other_current);
			other_current = tmp;
		}
		other.clear();
//        Element current = sentinel.next;
//        Element other_current = other.sentinel.next;
//        Element new_sentinel = new Element(null);
//        new_sentinel.next = new_sentinel;
//        new_sentinel.prev = new_sentinel;
//        Element new_current = new_sentinel;
//        // put the smaller element from the two lists in the new list,
//        // making sure to put the elements from this list first
//        // if elements are equal
//        while (current != sentinel && other_current != other.sentinel) {
//            if (((Comparable<E>) other_current.object).compareTo(current.object) < 0) {
//                new_current.addAfter(new Element(other_current.object));
//                other_current = other_current.next;
//            } else {
//                new_current.addAfter(new Element(current.object));
//                current = current.next;
//            }
//            new_current = new_current.next;
//        }
//        // put the remaining elements from the two lists in the new list
//        while (current != sentinel) {
//            new_current.addAfter(new Element(current.object));
//            current = current.next;
//            new_current = new_current.next;
//        }
//        while (other_current != other.sentinel) {
//            new_current.addAfter(new Element(other_current.object));
//            other_current = other_current.next;
//            new_current = new_current.next;
//        }
//        // replace the old list with the new one
//        sentinel = new_sentinel;
//        size += other.size;
//        other.clear();
	}

	//    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeAll(E e) {
		Element current = sentinel.next;
		while (current != sentinel) {
			if (((Comparable) current.object).compareTo(e) == 0) {
				Element to_remove = current;
				current = current.next;
				to_remove.remove();
				size--;
			} else
				current = current.next;
		}
	}
}
