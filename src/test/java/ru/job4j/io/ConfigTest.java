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
    }

    @Test
    void whenPairIncorrect() {
        String path = "./data/pair_incorrect.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                config::load);
        assertThat(exception.getMessage()).isEqualTo("Property file doesn't contain correct keys=values");
    }
}