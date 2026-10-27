package com.vbforge.oop.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InheritanceDemoTest {

    @Test void describeIsOverridden() {
        assertThat(InheritanceDemo.describeHierarchy())
                .isEqualTo("Vehicle[Generic] | Car[Toyota, 4 doors]");
    }

    @Test void subclassCanCallSuperStart() {
        assertThat(InheritanceDemo.startChain()).isEqualTo("Starting vehicle (car)");
    }

    @Test void overrideCanFullyReplaceParent() {
        assertThat(InheritanceDemo.overriddenStart()).isEqualTo("Silent start (electric)");
    }

    @Test void subclassIsInstanceOfSuperclass() {
        assertThat(InheritanceDemo.subclassIsInstanceOfSuperclass()).isTrue();
    }
}