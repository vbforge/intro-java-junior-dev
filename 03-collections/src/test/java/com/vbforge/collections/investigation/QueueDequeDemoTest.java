package com.vbforge.collections.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueueDequeDemoTest {

    @Test void fifoOrder() {
        assertThat(QueueDequeDemo.fifoOrder()).containsExactly(1, 2, 3);
    }

    @Test void lifoOrder() {
        assertThat(QueueDequeDemo.lifoOrder()).containsExactly(3, 2, 1);
    }

    @Test void naturalPriorityOrder() {
        assertThat(QueueDequeDemo.naturalOrderPoll()).containsExactly(1, 2, 3);
    }

    @Test void reversePriorityOrder() {
        assertThat(QueueDequeDemo.customComparatorPoll()).containsExactly(3, 2, 1);
    }

    @Test void iterationIsNotSorted() {
        Integer[] iterOrder = QueueDequeDemo.iterationIsNotSorted();
        // heap order: first element is the smallest, but the rest are not sorted
        assertThat(iterOrder[0]).isEqualTo(1);
        assertThat(iterOrder).doesNotContainSequence(1, 2, 3, 4, 5);
    }
}