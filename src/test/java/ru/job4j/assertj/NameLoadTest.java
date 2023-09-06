package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenNamesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        String[] arr = {};
        assertThatThrownBy(() -> nameLoad.parse(arr))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining("Names");
    }

    @Test
    void whenNameDoesNotContainTheAssignmentSymbol() {
        NameLoad nameLoad = new NameLoad();
        String[] arr = {"keyvalue", "key1:value1", "key2-value2"};
        assertThatThrownBy(() -> nameLoad.parse(arr))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(arr[0])
                .hasMessageContaining("=");
    }

    @Test
    void whenNameDoesNotContainAKey() {
        NameLoad nameLoad = new NameLoad();
        String[] arr = {"key=value", "=value1", "key2=value2"};
        assertThatThrownBy(() -> nameLoad.parse(arr))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(arr[1])
                .hasMessageContaining("a key");
    }

    @Test
    void whenNameDoesNotContainAValue() {
        NameLoad nameLoad = new NameLoad();
        String[] arr = {"key=value", "key1=value1", "key2="};
        assertThatThrownBy(() -> nameLoad.parse(arr))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(arr[2])
                .hasMessageContaining("a value");
    }
}
