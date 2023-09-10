package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        if (row < data.length) {
            while (column == data[row].length && row < data.length - 1) {
                row++;
                column = 0;
            }
            rsl = data[row].length > 0;
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row].length > 1 ? data[row][column++] : data[row++][column];
    }
}