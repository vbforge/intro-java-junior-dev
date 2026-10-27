package com.vbforge.oop.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectContractDemoTest {

    @Test void missingHashCodeBreaksHashSetLookup() {
        assertThat(ObjectContractDemo.brokenPointFoundInHashSet()).isFalse();
    }

    @Test void correctEqualsAndHashCodeWorkInHashSet() {
        assertThat(ObjectContractDemo.correctPointFoundInHashSet()).isTrue();
    }

    @Test void equalsIsReflexive() {
        assertThat(ObjectContractDemo.equalsIsReflexive()).isTrue();
    }

    @Test void equalsHandlesNull() {
        assertThat(ObjectContractDemo.equalsIsNullSafe()).isFalse();
    }

    @Test void toStringIsReadable() {
        assertThat(ObjectContractDemo.toStringIsUseful()).isEqualTo("Point(7, 8)");
    }
}