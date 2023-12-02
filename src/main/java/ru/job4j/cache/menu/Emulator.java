package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    private static final int ENTER_DIR = 1;
    private static final int LOAD_TO_CACHE = 2;
    private static final int GET_FROM_CACHE = 3;
    private static final String MENU = """
                Menu:
                Press 1 to enter relative path to directory in job4j_design project, example - data.
                Press 2 to enter filename you want add to cache (example: Names.txt).
                Press 3 to get data from cache using previous filename.
                Press 4 to exit program.
            """;

    public static void main(String[] args) {
        AbstractCache<String, String> abstractCache = null;
        boolean run = true;
        String directory = null;
        String filename = null;
        Scanner scanner = new Scanner(System.in);
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ENTER_DIR == userChoice) {
                System.out.println("Enter relative directory path:");
                directory = scanner.nextLine();
                abstractCache = new DirFileCache(directory);
                System.out.println("Done! You enter directory - " + directory);
            } else if (LOAD_TO_CACHE == userChoice && directory != null) {
                System.out.println("Enter filename you want add to cache:");
                filename = scanner.nextLine();
                System.out.println(abstractCache.get(filename));
                System.out.println("Done! You enter filename - " + filename);
            } else if (GET_FROM_CACHE == userChoice && directory != null && filename != null) {
                System.out.println("This is your data from cache:");
                System.out.println(abstractCache.get(filename));
            } else if (4 == userChoice) {
                run = false;
                System.out.println("Exit program");
            } else {
                System.out.println("First you must enter relative path to directory AND filename");
            }
        }
    }
}
