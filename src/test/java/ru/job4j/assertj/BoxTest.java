package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void whenTypeIsTetrahedron() {
        Box box = new Box(4, 2);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron")
                .containsIgnoringCase("ed")
                .doesNotContain("qwer");
    }

    @Test
    void whenTypeIsUnknown() {
        Box box = new Box(-1, 4);
        String name = box.whatsThis();
        assertThat(name).doesNotContain("Object")
                .isEqualTo("Unknown object");
    }

    @Test
    void whenVertexIs4() {
        Box box = new Box(4, 6);
        int number = box.getNumberOfVertices();
        assertThat(number).isLessThan(6)
                .isNotZero()
                .isEven()
                .isEqualTo(4);
    }

    @Test
    void whenVertexIsNegative1() {
        Box box = new Box(-1, 6);
        int number = box.getNumberOfVertices();
        assertThat(number).isLessThan(0)
                .isNotZero()
                .isOdd()
                .isEqualTo(-1);
    }

    @Test
    void whenVertexIsExistIsTrue() {
        Box box = new Box(4, 8);
        boolean number = box.isExist();
        assertThat(number).isTrue();
    }

    @Test
    void whenVertexIsExistIsFalse() {
        Box box = new Box(-1, 12);
        boolean number = box.isExist();
        assertThat(number).isFalse();
    }

    @Test
    void whenGetAreaIsCloseTo1256dot63() {
        Box box = new Box(0, 10);
        double number = box.getArea();
        assertThat(number).isEqualTo(1256.64d, withPrecision(0.007d))
                .isCloseTo(1256.63d, withPrecision(0.01d))
                .isGreaterThan(17.82d);
    }

    @Test
    void whenGetAreaIsZero() {
        Box box = new Box(-1, 10);
        double number = box.getArea();
        assertThat(number).isNotNegative()
                .isLessThan(1)
                .isEqualTo(0);
    }
}
