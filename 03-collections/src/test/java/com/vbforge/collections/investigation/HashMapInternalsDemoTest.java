package com.vbforge.collections.investigation;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HashMapInternalsDemoTest {

    @Test void aaAndBbCollide() {
        assertThat(HashMapInternalsDemo.collisionsOnPurpose()).isTrue();
    }

    @Test void collidingKeysBothRetrievable() {
        Map<String, Integer> m = HashMapInternalsDemo.bothKeysCoexist();
        assertThat(m).hasSize(2).containsEntry("Aa", 1).containsEntry("BB", 2);
    }

    @Test void resizeDoesNotAffectCorrectness() {
        assertThat(HashMapInternalsDemo.resizesAreInvisible(1000)).isEqualTo(1000);
    }

    @Test void badHashIsStillCorrect() {
        // correctness holds despite O(n) worst case
        assertThat(HashMapInternalsDemo.badHashMapIsStillCorrect(100)).isTrue();
    }
}