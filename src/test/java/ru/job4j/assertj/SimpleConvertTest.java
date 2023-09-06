package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("Bob", "Mark", "John", "Tom", "Sam", "Rob");
        assertThat(list).containsOnly("Mark", "Bob", "Tom", "Sam", "Rob", "John")
                .doesNotContain("John", Index.atIndex(1))
                .endsWith("Sam", "Rob")
                .containsSequence("Mark", "John")
                .allSatisfy(e -> {
                    assertThat(e.length()).isLessThan(10);
                    assertThat(e.length()).isGreaterThan(2);
                })
                .containsAnyOf("Java", "Bob")
                .last().isNotNull()
                .isEqualTo("Rob");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("Bob", "Mark", "John", "Tom", "Sam", "Rob");
        assertThat(set).containsExactlyInAnyOrder("Mark", "Bob", "Tom", "Sam", "Rob", "John")
                .anyMatch(e -> e.length() == 3)
                .filteredOn(e -> e.length() > 3).contains("Mark", "John")
                .first().isEqualTo("John");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("Bob", "Mark", "John");
        assertThat(map).hasSize(3)
                .containsKeys("Bob", "John")
                .containsValues(2, 0)
                .doesNotContainKey("Bobert")
                .doesNotContainValue(17)
                .containsEntry("Bob", 0);
    }
}