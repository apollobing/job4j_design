package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    int startAt = 1;
    final Scanner io = new Scanner(System.in);
    String answer;
    boolean player = false;

    void check(Fool game) {
        boolean isFizz = game.startAt % 3 == 0;
        boolean isBuzz = game.startAt % 5 == 0;

        if (!game.player) {
            if (isFizz && isBuzz) {
                System.out.println("FizzBuzz");
            } else if (isFizz) {
                System.out.println("Fizz");
            } else if (isBuzz) {
                System.out.println("Buzz");
            } else {
                System.out.println(game.startAt);
            }
        }

        if (game.player) {
            if (((isFizz && !"Fizz".equals(game.answer) || isBuzz && !"Buzz".equals(game.answer))
                    && !"FizzBuzz".equals(game.answer))
            || !isFizz && !isBuzz && !String.valueOf(game.startAt).equals(game.answer)) {
                System.out.println("Ошибка. Начинай снова.");
                game.startAt = 0;
            }
        }
        game.startAt++;
    }

    public static void main(String[] args) {
        Fool game = new Fool();
        System.out.println("Игра FizzBuzz.");
        while (game.startAt < 100) {
            game.check(game);
            game.player = true;
            game.answer = game.io.nextLine();
            game.check(game);
            game.player = false;
        }
    }
}