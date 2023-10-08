package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
        assertThat(config.value("key")).isEqualTo("value=1");
        assertThat(config.value("anotherKey")).isEqualTo("value=");
    }

    @Test
    void whenPairWithCommentsAndEmptyLines() {
        String path = "./data/pair_with_comments_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Idea")).isEqualTo("2023");
        assertThat(config.value("Java")).isEqualTo("17");
        assertThat(config.value("         ")).isEqualTo("         ");
    }

    @Test
    void whenPairIncorrectStartsWithEqualsSign() {
        String path = "./data/pair_incorrect_starts_with_equals_sign.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                config::load);
        assertThat(exception.getMessage()).isEqualTo("\"=value\" the template of the line isn't like \"key=value\"");
    }

    @Test
    void whenPairIncorrectEndsWithEqualsSign() {
        String path = "./data/pair_incorrect_ends_with_equals_sign.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                config::load);
        assertThat(exception.getMessage()).isEqualTo("\"key=\" the template of the line isn't like \"key=value\"");
    }

    @Test
    void whenPairIncorrectWithoutEqualsSign() {
        String path = "./data/pair_incorrect_without_equals_sign.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                config::load);
        assertThat(exception.getMessage()).isEqualTo("\"keyvalue\" the template of the line isn't like \"key=value\"");
    }

    @Test
    void whenPairIncorrectOnlyEqualsSign() {
        String path = "./data/pair_incorrect_only_equals_sign.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                config::load);
        assertThat(exception.getMessage()).isEqualTo("\"=\" the template of the line isn't like \"key=value\"");
    }
}