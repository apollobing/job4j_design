package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size;
    private int modCount;
    private int index;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    private void grow() {
        container = Arrays.copyOf(container, container.length == 0 ? 1 : container.length * 2);
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldItem = get(index);
        container[index] = newValue;
        return oldItem;
    }

    @Override
    public T remove(int index) {
        T removedItem = get(index);
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1
        );
        container[container.length - 1] = null;
        size--;
        modCount++;
        return removedItem;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        index = 0;

        return new Iterator<>() {

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[index++];
            }
        };
    }
}