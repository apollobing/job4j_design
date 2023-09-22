package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 4, 8))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfElLessThan5IsProcessed() {
        input.add(6);
        input.add(2);
        input.add(0);
        input.add(11);
        input.add(4);
        ListUtils.removeIf(input, i -> i < 5);
        assertThat(input).hasSize(2).containsSequence(6, 11);
    }

    @Test
    void whenRemoveIfElLessThan5IsNotProcessed() {
        input = new ArrayList<>(Arrays.asList(5, 6, 7));
        ListUtils.removeIf(input, i -> i < 5);
        assertThat(input).hasSize(3).containsSequence(5, 6, 7);
    }

    @Test
    void whenReplaceIfElHigherThan6IsProcessed() {
        input.add(11);
        input.add(7);
        input.add(2);
        input.add(4);
        input.add(6);
        ListUtils.replaceIf(input, i -> i > 6, 0);
        assertThat(input).hasSize(7).containsSequence(1, 3, 0, 0, 2, 4, 6);
    }

    @Test
    void whenReplaceIfElHigherThan6IsNotProcessed() {
        ListUtils.replaceIf(input, i -> i > 6, 0);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenRemoveAllIsProcessed() {
        input.add(5);
        input.add(6);
        List<Integer> elements = new ArrayList<>(Arrays.asList(55, 73, 1, 18, 6, 3));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(1).containsSequence(5);
    }

    @Test
    void whenRemoveAllIsNotProcessed() {
        List<Integer> elements = new ArrayList<>();
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }
}