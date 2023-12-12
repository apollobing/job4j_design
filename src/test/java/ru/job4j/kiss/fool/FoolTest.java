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

        while (game.startAt < 100) {
            game.check(game);
            game.player = true;
            game.answer = iterator.next();
            game.check(game);
            game.player = false;
        }
        assertThat(game.startAt).isEqualTo(101);
        assertThat(game.answer).isEqualTo("Buzz");
    }

    @Test
    void whenInvalidInputOnFizz() {
        Fool game = new Fool();
        List<String> answers = List.of("2", "4", "Wrong");
        Iterator<String> iterator = answers.iterator();

        while (game.startAt < 6 && !iterator.hasNext()) {
            game.check(game);
            game.player = true;
            game.answer = iterator.next();
            game.check(game);
            game.player = false;
        }
        assertThat(game.startAt).isEqualTo(1);
        assertThat(game.answer).isNotEqualTo("Fizz");
    }

    @Test
    void whenInvalidInputOnBuzz() {
        Fool game = new Fool();
        List<String> answers = List.of("2", "4", "Fizz", "8", "Wrong");
        Iterator<String> iterator = answers.iterator();

        while (game.startAt < 10 && !iterator.hasNext()) {
            game.check(game);
            game.player = true;
            game.answer = iterator.next();
            game.check(game);
            game.player = false;
        }
        assertThat(game.startAt).isEqualTo(1);
        assertThat(game.answer).isNotEqualTo("Buzz");
    }

    @Test
    void whenInvalidInputOnFizzBuzz() {
        Fool game = new Fool();
        List<String> answers = List.of("2", "4", "Fizz", "8", "Buzz", "Fizz", "14", "16",
                "Fizz", "Buzz", "22", "Fizz", "26", "28", "Wrong");
        Iterator<String> iterator = answers.iterator();

        while (game.startAt < 30 && !iterator.hasNext()) {
            game.check(game);
            game.player = true;
            game.answer = iterator.next();
            game.check(game);
            game.player = false;
        }
        assertThat(game.startAt).isEqualTo(1);
        assertThat(game.answer).isNotEqualTo("FizzBuzz");
    }

    @Test
    void whenInvalidInputOnNum() {
        Fool game = new Fool();
        List<String> answers = List.of("2", "Wrong");
        Iterator<String> iterator = answers.iterator();

        while (game.startAt < 4 && !iterator.hasNext()) {
            game.check(game);
            game.player = true;
            game.answer = iterator.next();
            game.check(game);
            game.player = false;
        }
        assertThat(game.startAt).isEqualTo(1);
        assertThat(game.answer).isNotEqualTo("4");
    }
}