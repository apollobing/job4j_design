package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;
    private Node<E> item;
    private int nodeCount;

    @Override
    public void add(E value) {
        Node<E> l = head;
        final Node<E> newNode = new Node<>(value, null);
        if (size == 0) {
            head = newNode;
        } else {
            while (l.next != null) {
                l = l.next;
            }
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> x = head;
        for (int i = 0; i < index; i++) {
            x = x.next;
        }
        return x.item;
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        item = head;
        nodeCount = size;

        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return size > 0 && nodeCount > 0;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E el = item.item;
                item = item.next;
                nodeCount--;
                return el;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}