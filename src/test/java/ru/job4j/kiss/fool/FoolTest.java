package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FoolTest {

    @Test
    void whenValidInput() {
        Fool game = new Fool();
        List<String> answers = List.of("2", "4", "Fizz", "8", "Buzz", "Fizz", "14", "16",
                "Fizz", "Buzz", "22", "Fizz", "26", "28", "FizzBuzz", "32", "34", "Fizz", "38",
                "Buzz", "Fizz", "44", "46", "Fizz", "Buzz", "52", "Fizz", "56", "58", "FizzBuzz",
                "62", "64", "Fizz", "68", "Buzz", "Fizz", "74", "76", "Fizz", "Buzz", "82", "Fizz",
                "86", "88", "FizzBuzz", "92", "94", "Fizz", "98", "Buzz");
        Iterator<String> iterator = answers.iterator();

        while (game.getStartAt() < 100 && iterator.hasNext()) {
            game.check(game);
            game.setStartAt(game.getStartAt() + 1);
            game.setAnswer(iterator.next());
            game.check(game);
            game.setStartAt(game.getStartAt() + 1);
        }
        assertThat(game.getStartAt()).isEqualTo(101);
        assertThat(game.getAnswer()).isEqualTo("Buzz");
    }

    @Test
    void whenInvalidInputOnFizz() {
        Fool game = new Fool();
        List<String> answers = List.of("2", "4", "Wrong");
        Iterator<String> iterator = answers.iterator();

        while (game.getStartAt() < 6 && iterator.hasNext()) {
            game.check(game);
            game.setStartAt(game.getStartAt() + 1);
            game.setAnswer(iterator.next());
            game.check(game);
            game.setStartAt(game.getStartAt() + 1);
        }
        assertThat(game.getStartAt()).isEqualTo(7);
        assertThat(game.getAnswer()).isNotEqualTo("Fizz");
    }

    @Test
    void whenInvalidInputOnBuzz() {
        Fool game = new Fool();
        List<String> answers = List.of("2", "4", "Fizz", "8", "Wrong");
        Iterator<String> iterator = answers.iterator();

        while (game.getStartAt() < 10 && iterator.hasNext()) {
            game.check(game);
            game.setStartAt(game.getStartAt() + 1);
            game.setAnswer(iterator.next());
            game.check(game);
            game.setStartAt(game.getStartAt() + 1);
        }
        assertThat(game.getStartAt()).isEqualTo(11);
        assertThat(game.getAnswer()).isNotEqualTo("Buzz");
    }

    @Test
    void whenInvalidInputOnFizzBuzz() {
        Fool game = new Fool();
        List<String> answers = List.of("2", "4", "Fizz", "8", "Buzz", "Fizz", "14", "16",
                "Fizz", "Buzz", "22", "Fizz", "26", "28", "Wrong");
        Iterator<String> iterator = answers.iterator();

        while (game.getStartAt() < 30 && iterator.hasNext()) {
            game.check(game);
            game.setStartAt(game.getStartAt() + 1);
            game.setAnswer(iterator.next());
            game.check(game);
            game.setStartAt(game.getStartAt() + 1);
        }
        assertThat(game.getStartAt()).isEqualTo(31);
        assertThat(game.getAnswer()).isNotEqualTo("FizzBuzz");
    }

    @Test
    void whenInvalidInputOnNum() {
        Fool game = new Fool();
        List<String> answers = List.of("2", "Wrong");
        Iterator<String> iterator = answers.iterator();

        while (game.getStartAt() < 4 && iterator.hasNext()) {
            game.check(game);
            game.setStartAt(game.getStartAt() + 1);
            game.setAnswer(iterator.next());
            game.check(game);
            game.setStartAt(game.getStartAt() + 1);
        }
        assertThat(game.getStartAt()).isEqualTo(5);
        assertThat(game.getAnswer()).isNotEqualTo("4");
    }
}