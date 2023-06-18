////package dsaa.lab03;
//
//import java.util.Iterator;
//import java.util.ListIterator;
//import java.util.NoSuchElementException;
//
//
//public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{
//
//    private class Element{
//        E object;
//        Element next=null;
//        Element prev=null;
//        public Element(E e) {
//            this.object = e;
//        }
//        public Element(E e, Element next, Element prev) {
//            this.object = e;
//            this.next = next;
//            this.prev = prev;
//        }
//
//    }
//
//    Element head;
//    Element tail;
//    int size;
//
//    private class InnerIterator implements Iterator<E>{
//        Element current;
//        public InnerIterator() {
//            this.current = head;
//        }
//        @Override
//        public boolean hasNext() {
//            return current.next != tail;
//        }
//
//        @Override
//        public E next() {
//            current = current.next;
//            return current.object;
//        }
//    }
//
//    private class InnerListIterator implements ListIterator<E>{
//        Element current;
//        public InnerListIterator() {
//            this.current = head;
//        }
//        @Override
//        public void add(E e) {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public boolean hasNext() {
//            return current.next != tail;
//        }
//
//        @Override
//        public boolean hasPrevious() {
//            return current.prev != head;
//        }
//
//        @Override
//        public E next() {
//            current = current.next;
//            return current.object;
//        }
//
//        @Override
//        public int nextIndex() {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public E previous() {
//            current = current.prev;
//            return current.object;
//        }
//
//        @Override
//        public int previousIndex() {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public void remove() {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public void set(E e) {
//            throw new UnsupportedOperationException();
//        }
//    }
//
//    public TwoWayUnorderedListWithHeadAndTail() {
////        head and tail point at each other initially,
////        helps to avoid edge cases when list is empty
//        head = new Element(null);
//        tail = new Element(null);
//        head.next = tail;
//        tail.prev = head;
//        size=0;
//    }
//
//    @Override
//    public boolean add(E e) {
//        Element newElement = new Element(e);
//        Element prev = tail.prev;
//        prev.next = newElement;
//        newElement.prev = prev;
//        newElement.next = tail;
//        tail.prev = newElement;
//        size++;
//        return true;
//    }
//
//    @Override
//    public void add(int index, E element) {
//        if (index < 0 || index > size) {
//            throw new NoSuchElementException();
//        }
//        Element newElement = new Element(element);
//        Element current = head;
//        for (int i = 0; i < index; i++) {
//            current = current.next;
//        }
//        Element next = current.next;
//        Element prev = current;
//        prev.next = newElement;
//        newElement.prev = prev;
//        newElement.next = next;
//        next.prev = newElement;
//        size++;
//    }
//
//    @Override
//    public void clear() {
//        head.next = tail;
//        tail.prev = head;
//        size = 0;
//    }
//
//    @Override
//    public boolean contains(E element) {
//        Element current = head.next;
//        while (current != tail) {
//            if (current.object.equals(element)) {
//                return true;
//            }
//            current = current.next;
//        }
//        return false;
//    }
//
//    @Override
//    public E get(int index) {
//        if (index < 0 || index >= size) {
//            throw new NoSuchElementException();
//        }
//        Element current = head.next;
//        for (int i = 0; i < index; i++) {
//            current = current.next;
//        }
//        return current.object;
//    }
//
//    @Override
//    public E set(int index, E element) {
//        if (index < 0 || index >= size) {
//            throw new NoSuchElementException();
//        }
//        Element current = head.next;
//        for (int i = 0; i < index; i++) {
//            current = current.next;
//        }
//        E oldElement = current.object;
//        current.object = element;
//        return oldElement;
//    }
//
//    @Override
//    public int indexOf(E element) {
//        Element current = head.next;
//        int index = 0;
//        while (current != tail) {
//            if (current.object.equals(element)) {
//                return index;
//            }
//            current = current.next;
//            index++;
//        }
//        return -1;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return head.next == tail;
//    }
//
//    @Override
//    public Iterator<E> iterator() {
//        return new InnerIterator();
//    }
//
//    @Override
//    public ListIterator<E> listIterator() {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public E remove(int index) {
//        if (index < 0 || index >= size) {
//            throw new NoSuchElementException();
//        }
//        Element current = head.next;
//        for (int i = 0; i < index; i++) {
//            current = current.next;
//        }
//        Element next = current.next;
//        Element prev = current.prev;
//        prev.next = next;
//        next.prev = prev;
//        size--;
//        return current.object;
//    }
//
//    @Override
//    public boolean remove(E e) {
//        Element current = head.next;
//        while (current != tail) {
//            if (current.object.equals(e)) {
//                Element next = current.next;
//                Element prev = current.prev;
//                prev.next = next;
//                next.prev = prev;
//                size--;
//                return true;
//            }
//            current = current.next;
//        }
//        return false;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//    public String toStringReverse() {
//        ListIterator<E> iter = new InnerListIterator();
//        while(iter.hasNext())
//            iter.next();
//        iter.next();
//        String retStr="";
//        while(iter.hasPrevious())
//            retStr+=  "\n" + iter.previous() ;
//        return retStr;
//    }
//
//    public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
//        if (other.isEmpty() || other == null || other == this) {
//            return;
//        }
//        size += other.size;
//        Element firstOther = other.head.next;
//        Element lastOther = other.tail.prev;
//        Element lastThis = tail.prev;
//        lastThis.next = firstOther;
//        firstOther.prev = lastThis;
//        lastOther.next = tail;
//        tail.prev = lastOther;
//        other.clear();
//    }
//}
//package dsaa.lab03;
package lab3;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{

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

    public TwoWayUnorderedListWithHeadAndTail() {
        // make a head and a tail
        head=null;
        tail=null;
    }

    public void removeOddPositions() {
        if (isEmpty()) {
            return;
        }
        int position = 0;
        Element current = head;
        while (current != null) {
            if (position % 2 == 1) {
                Element next = current.next;
                Element prev = current.prev;
                prev.next = next;
                if (next != null) {
                    next.prev = prev;
                } else {
                    tail = prev;
                }
            }
            current = current.next;
            position++;
        }
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
        if (index < 0 || index > size()) {
            throw new NoSuchElementException();
        }
        Element newElement = new Element(element);
        if (isEmpty()) {
            head = newElement;
            tail = newElement;
        } else if (index == 0) {
            newElement.next = head;
            head.prev = newElement;
            head = newElement;
        } else if (index == size()) {
            newElement.prev = tail;
            tail.next = newElement;
            tail = newElement;
        } else {
            Element current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Element prev = current.prev;
            prev.next = newElement;
            newElement.prev = prev;
            newElement.next = current;
            current.prev = newElement;
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
        if (index<0 || index>=size() || isEmpty()) {
            throw new NoSuchElementException();
        }
        Element current=head;
        for (int i=0; i<index; i++) {
            current=current.next;
        }
        return current.object;
    }

    @Override
    public E set(int index, E element) {
        if (index<0 || index>=size() || isEmpty()) {
            throw new NoSuchElementException();
        }
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
        Element current = head;
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
        if (index<0 || index>=size() || isEmpty()) {
            throw new NoSuchElementException();
        }
        Element current=head;
        for (int i=0; i<index; i++) {
            current=current.next;
        }
        if (size() == 1) {
            head = null;
            tail = null;
            return current.object;
        }
        if (current == head) {
            head=head.next;
            if (head!=null) {
                head.prev=null;
            }
        } else if (current==tail) {
            tail=tail.prev;
            if (tail!=null) {
                tail.next=null;
            }
        } else {
            Element prev=current.prev;
            Element next=current.next;
            prev.next=next;
            next.prev=prev;
        }
        return current.object;
    }

    @Override
    public boolean remove(E e) {
        Element current=head;
        while (current!=null) {
            if (current.object.equals(e)) {
                if (size() == 1) {
                    head = null;
                    tail = null;
                    return true;
                }
                if (current==head) {
                    head=head.next;
                    if (head!=null) {
                        head.prev=null;
                    }
                } else if (current==tail) {
                    tail=tail.prev;
                    if (tail!=null) {
                        tail.next=null;
                    }
                } else {
                    Element prev=current.prev;
                    Element next=current.next;
                    prev.next=next;
                    next.prev=prev;
                }
                return true;
            }
            current=current.next;
        }
        return false;
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
            retStr += "\n"+ iter.next();
        return retStr;
    }

    public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
        if (other.isEmpty()  || other == this) {
            return;
        }
        if (isEmpty()) {
            head = other.head;
            tail = other.tail;
        } else {
            tail.next = other.head;
            other.head.prev = tail;
            tail = other.tail;
        }
        other.clear();
    }
}
