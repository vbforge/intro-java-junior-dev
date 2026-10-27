package com.vbforge.collections.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ImmutableCollectionsDemoTest {

    @Test void listOfRejectsMutation() {
        assertThat(ImmutableCollectionsDemo.listOfRejectsMutation()).isTrue();
    }

    @Test void listOfRejectsNull() {
        assertThat(ImmutableCollectionsDemo.listOfRejectsNull()).isTrue();
    }

    @Test void unmodifiableViewReflectsParent() {
        assertThat(ImmutableCollectionsDemo.unmodifiableViewReflectsParent())
                .containsExactly(1, 2, 3, 4);
    }

    @Test void unmodifiableViewRejectsMutation() {
        assertThat(ImmutableCollectionsDemo.unmodifiableViewRejectsMutation()).isTrue();
    }

    @Test void copyOfDetachesFromSource() {
        assertThat(ImmutableCollectionsDemo.copyOfIsDetachedFromSource())
                .containsExactly(1, 2, 3);
    }

    @Test void setOfAndMapOf() {
        assertThat(ImmutableCollectionsDemo.setOf()).containsExactlyInAnyOrder("a", "b", "c");
        assertThat(ImmutableCollectionsDemo.mapOf())
                .containsEntry("one", 1).containsEntry("two", 2).containsEntry("three", 3);
    }

    @Test void mapOfRejectsNull() {
        assertThat(ImmutableCollectionsDemo.mapOfRejectsNull()).isTrue();
    }
}