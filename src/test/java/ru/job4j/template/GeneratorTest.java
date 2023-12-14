package ru.job4j.template;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeneratorTest {

    @Test
    void whenTemplateAndMapHaveEqualKeys() {
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        List<String> keys = Arrays.stream(template.split("\\$"))
                .filter(s -> s.contains("{"))
                .map(s -> s.substring(s.indexOf("{") + 1, s.indexOf("}")))
                .toList();
        String expected = "I am Petr Arsentev, Who are you?";
        String result = "I am " + map.get(keys.get(0)) + ", Who are " + map.get(keys.get(1)) + "?";
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenTemplateHasWrongFirstKey() {
        String template = "I am ${Name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        List<String> keys = Arrays.stream(template.split("\\$"))
                .filter(s -> s.contains("{"))
                .map(s -> s.substring(s.indexOf("{") + 1, s.indexOf("}")))
                .toList();
        Exception exception = assertThrows(
                Exception.class,
                () -> {
                    for (String key : keys) {
                        if (!map.containsKey(key)) {
                            throw new Exception("Template has wrong key: " + "\"" + key + "\"" + " but must only \"name\" or \"subject\"");
                        }
                    }
                });
        assertThat(exception.getMessage()).isEqualTo("Template has wrong key: \"Name\" but must only \"name\" or \"subject\"");
    }

    @Test
    void whenTemplateHasWrongSecondKey() {
        String template = "I am ${name}, Who are ${Subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        List<String> keys = Arrays.stream(template.split("\\$"))
                .filter(s -> s.contains("{"))
                .map(s -> s.substring(s.indexOf("{") + 1, s.indexOf("}")))
                .toList();
        Exception exception = assertThrows(
                Exception.class,
                () -> {
                    for (String key : keys) {
                        if (!map.containsKey(key)) {
                            throw new Exception("Template has wrong key: " + "\"" + key + "\"" + " but must only \"name\" or \"subject\"");
                        }
                    }
                });
        assertThat(exception.getMessage()).isEqualTo("Template has wrong key: \"Subject\" but must only \"name\" or \"subject\"");
    }

    @Test
    void whenMapHasMoreKeys() {
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("position", "Developer");
        map.put("subject", "you");
        List<String> keys = Arrays.stream(template.split("\\$"))
                .filter(s -> s.contains("{"))
                .map(s -> s.substring(s.indexOf("{") + 1, s.indexOf("}")))
                .toList();
        Exception exception = assertThrows(
                Exception.class,
                () -> {
                    if (map.size() > keys.size()) {
                        throw new Exception("Map has more keys: " + map.size() + " but must only 2 \"name\" and \"subject\"");
                    }
                });
        assertThat(exception.getMessage()).isEqualTo("Map has more keys: 3 but must only 2 \"name\" and \"subject\"");
    }

    @Test
    void whenTemplateHasMoreKeys() {
        String template = "I am ${name}, my position is ${position}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Petr Arsentev");
        map.put("subject", "you");
        List<String> keys = Arrays.stream(template.split("\\$"))
                .filter(s -> s.contains("{"))
                .map(s -> s.substring(s.indexOf("{") + 1, s.indexOf("}")))
                .toList();
        Exception exception = assertThrows(
                Exception.class,
                () -> {
                    if (map.size() < keys.size()) {
                        throw new Exception("Template has more keys: " + keys.size() + " but must only 2 \"name\" and \"subject\"");
                    }
                });
        assertThat(exception.getMessage()).isEqualTo("Template has more keys: 3 but must only 2 \"name\" and \"subject\"");
    }

}