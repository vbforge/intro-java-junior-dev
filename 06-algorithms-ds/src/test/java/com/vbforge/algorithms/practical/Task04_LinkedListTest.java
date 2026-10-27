package com.vbforge.algorithms.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class Task04_LinkedListTest {

    @Test void emptyList() {
        var l = new Task04_LinkedList<Integer>();
        assertThat(l.isEmpty()).isTrue();
        assertThat(l.size()).isZero();
        assertThat(l.toList()).isEmpty();
        assertThat(l.contains(1)).isFalse();
        assertThat(l.remove(1)).isFalse();
    }

    @Test void addFirst() {
        var l = new Task04_LinkedList<Integer>();
        l.addFirst(1);
        l.addFirst(2);
        l.addFirst(3);
        assertThat(l.toList()).containsExactly(3, 2, 1);
        assertThat(l.size()).isEqualTo(3);
    }

    @Test void addLast() {
        var l = new Task04_LinkedList<Integer>();
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        assertThat(l.toList()).containsExactly(1, 2, 3);
        assertThat(l.size()).isEqualTo(3);
    }

    @Test void containsWithNullSafety() {
        var l = new Task04_LinkedList<String>();
        l.addLast("a");
        l.addLast(null);
        assertThat(l.contains("a")).isTrue();
        assertThat(l.contains(null)).isTrue();
        assertThat(l.contains("z")).isFalse();
    }

    @Test void removeHeadMiddleTail() {
        var l = new Task04_LinkedList<Integer>();
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        assertThat(l.remove(1)).isTrue();       // head
        assertThat(l.toList()).containsExactly(2, 3);
        assertThat(l.remove(3)).isTrue();       // tail
        assertThat(l.toList()).containsExactly(2);
        assertThat(l.remove(2)).isTrue();       // only element
        assertThat(l.isEmpty()).isTrue();
    }

    @Test void reverse() {
        var l = new Task04_LinkedList<Integer>();
        for (int i = 1; i <= 4; i++) l.addLast(i);
        l.reverse();
        assertThat(l.toList()).containsExactly(4, 3, 2, 1);
        // addLast must still work after reverse (tail pointer updated)
        l.addLast(0);
        assertThat(l.toList()).containsExactly(4, 3, 2, 1, 0);
    }

    @Test void reverseSingleElement() {
        var l = new Task04_LinkedList<Integer>();
        l.addLast(7);
        l.reverse();
        assertThat(l.toList()).containsExactly(7);
    }

    @Test void toListReturnsCopy() {
        var l = new Task04_LinkedList<Integer>();
        l.addLast(1);
        l.addLast(2);
        var snapshot = l.toList();
        snapshot.add(99);
        assertThat(l.toList()).containsExactly(1, 2);
    }
}