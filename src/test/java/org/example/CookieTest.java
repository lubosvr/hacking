package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CookieTest {

    @Test
    void checkNextIteration() {
        final CookiesClass c = new CookiesClass(List.of(2, 7, 3, 6, 4, 6), 9);
        c.nextIteration(1);
        final var integerList = c.toList();
        assertThat(integerList).containsExactly(8, 7, 6, 4, 6);
    }

    @Test
    void checkNotFinished() {
        final CookiesClass c = new CookiesClass(List.of(2, 7, 3, 6, 4, 6), 9);
        assertThat(c.finished()).isFalse();
    }

    @Test
    void checkFinished() {
        final CookiesClass c = new CookiesClass(List.of(23, 20, 16), 9);
        assertThat(c.finished()).isTrue();
    }

    @Test
    void checkMerging() {
        final CookiesClass c = new CookiesClass(List.of(2, 7, 3, 6, 4, 6), 9);
        final int iterations = c.merge();
        assertThat(iterations).isEqualTo(4);
        final var integerList = c.toList();
        assertThat(integerList).containsExactly(40, 20);
    }

    @Test
    void checkMergingNotPossible() {
        final CookiesClass c = new CookiesClass(List.of(2, 7, 3, 6, 4, 6), 101);
        final int iterations = c.merge();
        assertThat(iterations).isEqualTo(-1);
    }
}