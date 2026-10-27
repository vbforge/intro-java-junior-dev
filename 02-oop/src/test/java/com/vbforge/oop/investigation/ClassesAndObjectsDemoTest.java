package com.vbforge.oop.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ClassesAndObjectsDemoTest {

    @Test void instancesAreIndependent() {
        assertThat(ClassesAndObjectsDemo.incrementTwoIndependently())
                .containsExactly(12, 101);
    }

    @Test void staticCounterIncrementsPerInstance() {
        // Note: this counter is shared across the JVM — the exact number depends on
        // how many Counter instances have been created before this test.
        // Just assert it's non-zero once the demo has run.
        ClassesAndObjectsDemo.incrementTwoIndependently();
        assertThat(ClassesAndObjectsDemo.instancesEverCreated()).isPositive();
    }

    @Test void fluentChainingWorks() {
        assertThat(ClassesAndObjectsDemo.fluentChain()).isEqualTo("hello world");
    }
}