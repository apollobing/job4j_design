package ru.job4j.gc.leak;

import java.util.Random;
import java.util.Scanner;

public class Menu {

    private static final int ADD_POST = 1;
    private static final int ADD_MANY_POST = 2;
    private static final int SHOW_ALL_POSTS = 3;
    private static final int DELETE_POST = 4;

    private static final String SELECT = "Выберите меню";
    private static final String COUNT = "Выберите количество создаваемых постов";
    private static final String TEXT_OF_POST = "Введите текст";
    private static final String DELETE_ALL = "Все посты удалены";
    private static final String EXIT = "Конец работы";

    private static final String MENU = """
                Введите 1 для создание поста.
                Введите 2, чтобы создать определенное количество постов.
                Введите 3, чтобы показать все посты.
                Введите 4, чтобы удалить все посты.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Random random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        new Menu().start(commentGenerator, scanner, userGenerator, postStore);
    }

    private void start(CommentGenerator commentGenerator, Scanner scanner, UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                String text = scanner.nextLine();
                createPost(commentGenerator, userGenerator, postStore, text);
            } else if (ADD_MANY_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                String text = scanner.nextLine();
                System.out.println(COUNT);
                String count = scanner.nextLine();
                for (int i = 0; i < Integer.parseInt(count); i++) {
                    createPost(commentGenerator, userGenerator, postStore, text);
                }
            } else if (SHOW_ALL_POSTS == userChoice) {
                System.out.println(postStore.getPosts());
            } else if (DELETE_POST == userChoice) {
                System.out.println(DELETE_ALL);
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    private void createPost(CommentGenerator commentGenerator,
                                   UserGenerator userGenerator, PostStore postStore, String text) {
        userGenerator.generate();
        commentGenerator.generate();
        postStore.add(new Post(text, commentGenerator.getComments()));
    }
}
