package lab3;

import java.util.Iterator;
import java.util.ListIterator;


public class TwoWayUnorderedListWithHeadAndTail2<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object = e;
        }

        E object;
        Element next=null;
        Element prev=null;
    }

    Element head;
    Element tail;
    // can be realization with the field size or without

    private class InnerIterator implements Iterator<E>{
        Element current;

        public InnerIterator() {
            this.current = head;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E e = current.object;
            current = current.next;
            return e;
        }
    }

    private class InnerListIterator implements ListIterator<E>{
        Element current;
        // TODO maybe more fields....

        public InnerListIterator() {
            this.current = tail;
        }
        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E next() {
            E e = current.object;
            current = current.prev;
            return e;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            return null;
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
        public void set(E e) {
            throw new UnsupportedOperationException();
        }
    }

    public TwoWayUnorderedListWithHeadAndTail2() {
        // make a head and a tail
        head=null;
        tail=null;
    }

    @Override
    public boolean add(E e) {
        Element newElement=new Element(e);
        if (head==null) {
            head=newElement;
        } else {
            tail.next=newElement;
            newElement.prev=tail;
        }
        tail=newElement;
        return true;
    }

    @Override
    public void add(int index, E element) {
        Element newElement=new Element(element);
        if (index==0) {
            newElement.next=head;
            head.prev=newElement;
            head=newElement;
        } else {
            Element current=head;
            for (int i=0; i<index-1; i++) {
                current=current.next;
            }
            newElement.next=current.next;
            newElement.prev=current;
            current.next=newElement;
        }
    }

    @Override
    public void clear() {
        head=null;
        tail=null;
    }

    @Override
    public boolean contains(E element) {
        Element current=head;
        while (current!=null) {
            if (current.object.equals(element)) {
                return true;
            }
            current=current.next;
        }
        return false;
    }

    @Override
    public E get(int index) {
        Element current=head;
        for (int i=0; i<index; i++) {
            current=current.next;
        }
        return current.object;
    }

    @Override
    public E set(int index, E element) {
        Element current=head;
        for (int i=0; i<index; i++) {
            current=current.next;
        }
        E ret = current.object;
        current.object = element;
        return ret;
    }

    @Override
    public int indexOf(E element) {
        Element current=head;
        int index=0;
        while (current!=null) {
            if (current.object.equals(element)) {
                return index;
            }
            current=current.next;
            index++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
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
    public E remove(int index) {
        if (index==0) {
            E ret = head.object;
            head=head.next;
            head.prev=null;
            return ret;
        } else {
            Element current=head;
            for (int i=0; i<index-1; i++) {
                current=current.next;
            }
            E ret = current.next.object;
            current.next=current.next.next;
            current.next.prev=current;
            return ret;
        }
    }

    @Override
    public boolean remove(E e) {
        if (head.object.equals(e)) {
            head=head.next;
            head.prev=null;
            return true;
        } else {
            Element current=head;
            while (current.next!=null) {
                if (current.next.object.equals(e)) {
                    current.next=current.next.next;
                    current.next.prev=current;
                    return true;
                }
                current=current.next;
            }
            return false;
        }
    }

    @Override
    public int size() {
        int size=0;
        Element current=head;
        while (current!=null) {
            size++;
            current=current.next;
        }
        return size;
    }
    public String toStringReverse() {
        ListIterator<E> iter=new InnerListIterator();
        String retStr="";
        while(iter.hasNext())
            retStr += iter.next();
        return retStr;
    }

    public void add(TwoWayUnorderedListWithHeadAndTail2<E> other) {
        if (other.isEmpty()  || other == this) {
            return;
        }
        if (this.isEmpty()) {
            this.head = other.head;
            this.tail = other.tail;
            return;
        }
        this.tail.next = other.head;
    }
}
