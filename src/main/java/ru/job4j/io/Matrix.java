package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Matrix {
    public static int[][] multiple(int size) {
        int[][] arr = new int[size][size];
        try (FileOutputStream out = new FileOutputStream("data/matrix.txt")) {
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = (i + 1) * (j + 1);
                    out.write((arr[i][j] + " ").getBytes());
                    count++;
                    if (count == size) {
                        out.write(System.lineSeparator().getBytes());
                        count = 0;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    public static void main(String[] args) {
        multiple(10);
        System.out.println("Done!");
    }
}
