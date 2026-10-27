package com.vbforge.collections.investigation;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IteratorDemoTest {

    @Test void collectOdd() {
        assertThat(IteratorDemo.collectOdd(6)).containsExactly(1, 3, 5);
    }

    @Test void addDuringForEachThrows() {
        assertThatThrownBy(IteratorDemo::addDuringForEachThrows)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test void removeWithIteratorIsSafe() {
        assertThat(IteratorDemo.removeEvenWithIterator(6)).containsExactly(1, 3, 5);
    }

    @Test void removeIfIsSafe() {
        assertThat(IteratorDemo.removeEvenWithRemoveIf(6)).containsExactly(1, 3, 5);
    }

    @Test void removeSecondToLastSilentlySkips() {
        // No exception is thrown — see the Javadoc for the exact reason.
        // And note: "3" was never visited (silent skip).
        assertThat(IteratorDemo.removeSecondToLastSilentlySkips()).containsExactly(1, 3);
    }
}