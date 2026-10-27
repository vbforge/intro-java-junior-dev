package com.vbforge.collections.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.vbforge.collections.practical.Task06_PriorityTaskQueue.Task;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task06_PriorityTaskQueueTest {

    @Test void emptyQueue() {
        Task06_PriorityTaskQueue q = new Task06_PriorityTaskQueue();
        assertThat(q.size()).isZero();
        assertThat(q.poll()).isNull();
        assertThat(q.peek()).isNull();
        assertThat(q.drainAllInOrder()).isEmpty();
    }

    @Test void priorityOrdering() {
        Task06_PriorityTaskQueue q = new Task06_PriorityTaskQueue();
        q.add(new Task("low",  1, 100));
        q.add(new Task("high", 10, 100));
        q.add(new Task("mid",   5, 100));
        assertThat(q.poll().name()).isEqualTo("high");
        assertThat(q.poll().name()).isEqualTo("mid");
        assertThat(q.poll().name()).isEqualTo("low");
        assertThat(q.poll()).isNull();
    }

    @Test void priorityThenArrivalThenName() {
        Task06_PriorityTaskQueue q = new Task06_PriorityTaskQueue();
        // same priority, different arrival: earlier wins
        q.add(new Task("late",  5, 200));
        q.add(new Task("early", 5, 100));
        assertThat(q.poll().name()).isEqualTo("early");
        assertThat(q.poll().name()).isEqualTo("late");

        // same priority AND same arrival: name wins
        q.add(new Task("zebra", 5, 100));
        q.add(new Task("apple", 5, 100));
        assertThat(q.poll().name()).isEqualTo("apple");
        assertThat(q.poll().name()).isEqualTo("zebra");
    }

    @Test void peekDoesNotRemove() {
        Task06_PriorityTaskQueue q = new Task06_PriorityTaskQueue();
        q.add(new Task("x", 5, 100));
        assertThat(q.peek().name()).isEqualTo("x");
        assertThat(q.size()).isEqualTo(1);
    }

    @Test void drainAllInOrder() {
        Task06_PriorityTaskQueue q = new Task06_PriorityTaskQueue();
        q.add(new Task("a", 1, 100));
        q.add(new Task("b", 3, 100));
        q.add(new Task("c", 2, 100));
        List<Task> drained = q.drainAllInOrder();
        assertThat(drained).extracting(Task::name).containsExactly("b", "c", "a");
        assertThat(q.size()).isZero();
    }

    @Test void addRejectsNull() {
        Task06_PriorityTaskQueue q = new Task06_PriorityTaskQueue();
        assertThatThrownBy(() -> q.add(null)).isInstanceOf(NullPointerException.class);
    }
}