/**
 * 
 */
package edu.unl.raikes.linkedlistlab;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * linked list.
 * 
 * @author downey
 * @param <E> a
 */
public class MyLinkedList<E> implements List<E> {

    /**
     * Node is identical to ListNode from the example, but parameterized with T.
     * 
     * @author downey
     *
     */
    private class Node {
        public E cargo;
        public Node next;

        public Node() {
            this.cargo = null;
            this.next = null;
        }

        public Node(E cargo) {
            this.cargo = cargo;
            this.next = null;
        }

        public Node(E cargo, Node next) {
            this.cargo = cargo;
            this.next = next;
        }

        public String toString() {
            return "Node(" + this.toString() + ")";
        }
    }

    private int size; // keeps track of the number of elements
    private Node head; // reference to the first node

    /**
     * .
     */
    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * main.
     * 
     * @param args args
     */
    public static void main(String[] args) {
        // run a few simple tests
        List<Integer> mll = new MyLinkedList<Integer>();
        mll.add(1);
        mll.add(2);
        mll.add(3);
        System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());

        mll.remove(new Integer(2));
        System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());
    }

    @Override
    public boolean add(E element) {
        if (this.head == null) {
            this.head = new Node(element);
        } else {
            Node node = this.head;
            // loop until the last node
            for (; node.next != null; node = node.next) {
            }
            node.next = new Node(element);
        }
        this.size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        Node node = this.head;

        if (index == 0) {
            Node nodeToAdd = new Node(element, node);
            this.head = nodeToAdd;
            this.size++;
            return;
        }

        Node previous = node;
        for (int i = 0; i < index; i++) {
            if (node == null) {
                throw new IndexOutOfBoundsException();
            }
            previous = node;
            node = node.next;
        }
        Node nodeToAdd = new Node(element, node);
        previous.next = nodeToAdd;
        this.size++;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        boolean flag = true;
        for (E element : collection) {
            flag &= this.add(element);
        }
        return flag;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public boolean contains(Object obj) {
        return this.indexOf(obj) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object obj : collection) {
            if (!this.contains(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public E get(int index) {
        Node node = this.getNode(index);
        return node.cargo;
    }

    /**
     * Returns the node at the given index.
     * 
     * @param index
     * @return
     */
    private Node getNode(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = this.head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public int indexOf(Object target) {
        Node node = this.head;
        int index = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.equals(target, node.cargo)) {
                index = i;
            }
            node = node.next;
        }
        return index;
    }

    /**
     * Checks whether an element of the array is the target.
     * 
     * Handles the special case that the target is null.
     * 
     * @param target
     * @param object
     */
    private boolean equals(Object target, Object element) {
        if (target == null) {
            return element == null;
        } else {
            return target.equals(element);
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        E[] array = (E[]) this.toArray();
        return Arrays.asList(array).iterator();
    }

    @Override
    public int lastIndexOf(Object target) {
        Node node = this.head;
        int index = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.equals(target, node.cargo)) {
                index = i;
            }
            node = node.next;
        }
        return index;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public boolean remove(Object obj) {
        Node node = this.head;
        if (this.equals(obj, node.cargo)) {
            this.head = node.next;
            this.size--;
            return true;
        }
        Node previous = node;

        for (int i = 0; i < this.size - 1; i++) {
            previous = node;
            node = node.next;
            if (this.equals(obj, node.cargo)) {
                previous.next = node.next;
                this.size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public E remove(int index) {
        Node node = this.head;
        if (index == 0) {
            E cargo = node.cargo;
            this.head = node.next;
            this.size--;
            return cargo;
        }
        
        Node previous = node;
        for (int i = 0; i < index - 1; i++) {
            previous = node;
            node = node.next;
            if (i == index) {
                E cargo = node.cargo;
                previous.next = node.next;
                this.size--;
                return cargo;
            }
        }

        return null;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean flag = true;
        for (Object obj : collection) {
            flag &= this.remove(obj);
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E set(int index, E element) {
        Node node = this.getNode(index);
        E old = node.cargo;
        node.cargo = element;
        return old;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= this.size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        // TODO: classify this and improve it.
        int i = 0;
        MyLinkedList<E> list = new MyLinkedList<E>();
        for (Node node = this.head; node != null; node = node.next) {
            if (i >= fromIndex && i <= toIndex) {
                list.add(node.cargo);
            }
            i++;
        }
        return list;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[this.size];
        int i = 0;
        for (Node node = this.head; node != null; node = node.next) {
            // System.out.println(node);
            array[i] = node.cargo;
            i++;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }
}
