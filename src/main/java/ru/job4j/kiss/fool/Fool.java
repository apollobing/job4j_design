package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    private int startAt = 1;
    private final Scanner io = new Scanner(System.in);
    private String answer;

    public String check(Fool game) {
        boolean isFizz = game.startAt % 3 == 0;
        boolean isBuzz = game.startAt % 5 == 0;
        String rsl = Integer.toString(game.startAt);
        if (isFizz && isBuzz) {
            rsl = "FizzBuzz";
        } else if (isFizz) {
            rsl = "Fizz";
        } else if (isBuzz) {
            rsl = "Buzz";
        }
        return rsl;
    }

    public static void main(String[] args) {
        Fool game = new Fool();
        System.out.println("Игра FizzBuzz.");
        while (game.startAt < 100) {
            System.out.println(game.check(game));
            game.setAnswer(game.io.nextLine());
            game.startAt++;
            if (!game.getAnswer().equals(game.check(game))) {
                System.out.println("Ошибка. Начинай снова.");
                game.startAt = 0;
            }
            game.startAt++;
        }
    }

    public int getStartAt() {
        return startAt;
    }

    public void setStartAt(int startAt) {
        this.startAt = startAt;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}