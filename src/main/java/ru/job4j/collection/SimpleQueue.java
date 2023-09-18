package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int countIn;
    private int countOut;

    public T poll() {
        if (countOut == 0) {
            while (countIn > 0) {
                out.push(in.pop());
                countIn--;
                countOut++;
            }
        }
        countOut--;
        try {
            return out.pop();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Queue is empty", e);
        }
    }

    public void push(T value) {
        in.push(value);
        countIn++;
    }
}