package com.vbforge.algorithms.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedListDemoTest {

    @Test void addFirstBuildsReversedList() {
        var l = new LinkedListDemo.SinglyLinkedList<Integer>();
        l.addFirst(1);
        l.addFirst(2);
        l.addFirst(3);
        assertThat(l.toList()).containsExactly(3, 2, 1);
        assertThat(l.size()).isEqualTo(3);
    }

    @Test void addLastBuildsInOrder() {
        var l = new LinkedListDemo.SinglyLinkedList<Integer>();
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        assertThat(l.toList()).containsExactly(1, 2, 3);
    }

    @Test void containsAndRemove() {
        var l = new LinkedListDemo.SinglyLinkedList<String>();
        l.addLast("a");
        l.addLast("b");
        l.addLast("c");
        assertThat(l.contains("b")).isTrue();
        assertThat(l.contains("z")).isFalse();
        assertThat(l.remove("b")).isTrue();
        assertThat(l.remove("z")).isFalse();
        assertThat(l.toList()).containsExactly("a", "c");
        assertThat(l.size()).isEqualTo(2);
    }

    @Test void removeHeadAndTail() {
        var l = new LinkedListDemo.SinglyLinkedList<Integer>();
        l.addLast(1);
        l.addLast(2);
        l.addLast(3);
        l.remove(1);                                    // head
        assertThat(l.toList()).containsExactly(2, 3);
        l.remove(3);                                    // tail
        assertThat(l.toList()).containsExactly(2);
        assertThat(l.size()).isEqualTo(1);
    }

    @Test void reverse() {
        var l = new LinkedListDemo.SinglyLinkedList<Integer>();
        for (int i = 1; i <= 4; i++) l.addLast(i);
        l.reverse();
        assertThat(l.toList()).containsExactly(4, 3, 2, 1);
        // addLast must still work after reverse (tail pointer updated)
        l.addLast(0);
        assertThat(l.toList()).containsExactly(4, 3, 2, 1, 0);
    }

    @Test void emptyList() {
        var l = new LinkedListDemo.SinglyLinkedList<Integer>();
        assertThat(l.isEmpty()).isTrue();
        assertThat(l.size()).isZero();
        assertThat(l.toList()).isEmpty();
        assertThat(l.remove(1)).isFalse();
    }
}