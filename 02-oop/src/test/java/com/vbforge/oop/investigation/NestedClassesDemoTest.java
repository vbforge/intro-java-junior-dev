package com.vbforge.oop.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NestedClassesDemoTest {

    @Test void staticNestedNeedsNoOuterInstance() {
        assertThat(NestedClassesDemo.staticNestedValue()).isEqualTo(99);
    }

    @Test void innerHasAccessToOuterState() {
        assertThat(NestedClassesDemo.innerReadsOuter()).isEqualTo(42);
    }

    @Test void localClassIsUsable() {
        assertThat(NestedClassesDemo.localClassDemo(5)).isEqualTo("twice(5) = 10");
    }

    @Test void anonymousClassImplementsInterface() {
        assertThat(NestedClassesDemo.anonymousClassDemo()).isEqualTo("Hello, World");
    }
}