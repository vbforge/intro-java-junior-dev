package com.vbforge.algorithms.investigation;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StackQueueDemoTest {

    @Test void stackLifo() {
        var s = new StackQueueDemo.IntStack();
        s.push(1); s.push(2); s.push(3);
        assertThat(s.peek()).isEqualTo(3);
        assertThat(s.pop()).isEqualTo(3);
        assertThat(s.pop()).isEqualTo(2);
        assertThat(s.pop()).isEqualTo(1);
        assertThat(s.isEmpty()).isTrue();
    }

    @Test void stackPopOnEmptyThrows() {
        var s = new StackQueueDemo.IntStack();
        assertThatThrownBy(s::pop).isInstanceOf(EmptyStackException.class);
        assertThatThrownBy(s::peek).isInstanceOf(EmptyStackException.class);
    }

    @Test void stackGrows() {
        var s = new StackQueueDemo.IntStack();
        for (int i = 0; i < 100; i++) s.push(i);
        assertThat(s.size()).isEqualTo(100);
        assertThat(s.peek()).isEqualTo(99);
    }

    @Test void queueFifo() {
        var q = new StackQueueDemo.IntQueue();
        q.enqueue(1); q.enqueue(2); q.enqueue(3);
        assertThat(q.peek()).isEqualTo(1);
        assertThat(q.dequeue()).isEqualTo(1);
        assertThat(q.dequeue()).isEqualTo(2);
        assertThat(q.dequeue()).isEqualTo(3);
        assertThat(q.isEmpty()).isTrue();
    }

    @Test void queueDequeueOnEmptyThrows() {
        var q = new StackQueueDemo.IntQueue();
        assertThatThrownBy(q::dequeue).isInstanceOf(NoSuchElementException.class);
    }

    @Test void queueWrapAroundCircularBuffer() {
        var q = new StackQueueDemo.IntQueue();
        for (int i = 0; i < 6; i++) q.enqueue(i);       // fill
        for (int i = 0; i < 6; i++) assertThat(q.dequeue()).isEqualTo(i);
        // now head has "wrapped" — enqueue more to exercise the wrap
        for (int i = 100; i < 110; i++) q.enqueue(i);
        for (int i = 100; i < 110; i++) assertThat(q.dequeue()).isEqualTo(i);
    }
}