package lab2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{
    private class Element{
        public Element(E e) {
            this.object = e;
        }
        E object;
        Element next = null;
    }
    Element sentinel;
    private class InnerIterator implements Iterator<E>{
        Element current;
        public InnerIterator() {
            this.current = sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E object = current.object;
            current = current.next;
            return object;
        }

    }

    public OneWayLinkedList() {
        sentinel = new Element(null);
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        Element current = sentinel;
        while(current.next != null) {
            current=current.next;
        }
        current.next = new Element(e);
        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        if (index == size()) {
            add(element);
            return;
        }
        int i = 0;
        Element current = sentinel.next;
        Element previous = sentinel;
        while(current != null) {
            if(i == index) {
                Element newElement = new Element(element);
                previous.next = newElement;
                newElement.next = current;
                return;
            }
            previous = current;
            current = current.next;
            i++;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void clear() {
        sentinel.next=null;
    }

    @Override
    public boolean contains(E element) {
        Element current = sentinel.next;
        while(current!=null) {
            if(current.object.equals(element)) {
                return true;
            }
            current=current.next;
        }
        return false;
    }

    @Override
    public E get(int index) throws NoSuchElementException {
        int i=0;
        Element current = sentinel.next;
        while(current!=null) {
            if(i==index) {
                return current.object;
            }
            current=current.next;
            i++;
        }
        throw new NoSuchElementException();
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        int i=0;
        Element current = sentinel.next;
        while(current!=null) {
            if(i==index) {
                E old = current.object;
                current.object=element;
                return old;
            }
            current=current.next;
            i++;
        }
        throw new NoSuchElementException();
    }

    @Override
    public int indexOf(E element)  {
        int i=0;
        Element current = sentinel.next;
        while(current != null) {
            if(current.object.equals(element)) {
                return i;
            }
            current = current.next;
            i++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next==null;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        int i = 0;
        Element current = sentinel.next;
        Element previous = sentinel;
        while(current != null) {
            if(i == index) {
                previous.next = current.next;
                return current.object;
            }
            previous = current;
            current = current.next;
            i++;
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean remove(E e) {
        Element current = sentinel.next;
        Element previous = sentinel;
        while(current != null) {
            if(current.object.equals(e)) {
                previous.next = current.next;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        int i=0;
        Element current = sentinel.next;
        while(current!=null) {
            current=current.next;
            i++;
        }
        return i;
    }

}
