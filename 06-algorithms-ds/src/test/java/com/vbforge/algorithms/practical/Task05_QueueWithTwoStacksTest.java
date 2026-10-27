package com.vbforge.algorithms.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task05_QueueWithTwoStacksTest {

    @Test void emptyQueue() {
        var q = new Task05_QueueWithTwoStacks<Integer>();
        assertThat(q.isEmpty()).isTrue();
        assertThat(q.size()).isZero();
        assertThatThrownBy(q::dequeue).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(q::peek).isInstanceOf(NoSuchElementException.class);
    }

    @Test void fifoOrder() {
        var q = new Task05_QueueWithTwoStacks<Integer>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertThat(q.dequeue()).isEqualTo(1);
        assertThat(q.dequeue()).isEqualTo(2);
        assertThat(q.dequeue()).isEqualTo(3);
        assertThat(q.isEmpty()).isTrue();
    }

    @Test void peekDoesNotRemove() {
        var q = new Task05_QueueWithTwoStacks<String>();
        q.enqueue("a");
        assertThat(q.peek()).isEqualTo("a");
        assertThat(q.size()).isEqualTo(1);
    }

    @Test void interleavedEnqueueDequeue() {
        var q = new Task05_QueueWithTwoStacks<Integer>();
        q.enqueue(1);
        q.enqueue(2);
        assertThat(q.dequeue()).isEqualTo(1);
        q.enqueue(3);                            // hits the tricky transition
        assertThat(q.dequeue()).isEqualTo(2);
        assertThat(q.dequeue()).isEqualTo(3);
    }

    @Test void largeQueue() {
        var q = new Task05_QueueWithTwoStacks<Integer>();
        for (int i = 0; i < 1000; i++) q.enqueue(i);
        assertThat(q.size()).isEqualTo(1000);
        for (int i = 0; i < 1000; i++) assertThat(q.dequeue()).isEqualTo(i);
        assertThat(q.isEmpty()).isTrue();
    }
}