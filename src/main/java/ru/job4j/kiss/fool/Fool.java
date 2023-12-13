package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    private int startAt = 1;
    private final Scanner io = new Scanner(System.in);
    private String answer;

    public String check(Fool game) {
        boolean isFizz = game.startAt % 3 == 0;
        boolean isBuzz = game.startAt % 5 == 0;
        String rsl;

        if (isFizz && isBuzz) {
            rsl = "FizzBuzz";
        } else if (isFizz) {
            rsl = "Fizz";
        } else if (isBuzz) {
            rsl = "Buzz";
        } else {
            rsl = Integer.toString(game.startAt);
        }

        game.startAt++;
        return rsl;
    }

    public static void main(String[] args) {
        Fool game = new Fool();
        System.out.println("Игра FizzBuzz.");
        while (game.startAt < 100) {
            String rsl = game.check(game);
            if (game.startAt % 2 == 0) {
                System.out.println(rsl);
            }
            game.answer = game.io.nextLine();
            rsl = game.check(game);
            if (!rsl.equals(game.answer)) {
                System.out.println("Ошибка. Начинай снова.");
                game.startAt = 1;
            }
        }
    }

    public int getStartAt() {
        return startAt;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}