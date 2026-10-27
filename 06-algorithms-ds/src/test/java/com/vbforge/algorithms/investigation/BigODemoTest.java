package com.vbforge.algorithms.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BigODemoTest {

    @Test void constantDoesNotGrowWithN() {
        assertThat(BigODemo.constantOperations(1)).isEqualTo(1L);
        assertThat(BigODemo.constantOperations(1_000_000)).isEqualTo(1L);
    }

    @Test void linearGrowsProportionally() {
        assertThat(BigODemo.linearOperations(10)).isEqualTo(10L);
        assertThat(BigODemo.linearOperations(20)).isEqualTo(20L);         // double n → double ops
        assertThat(BigODemo.linearOperations(1_000)).isEqualTo(1_000L);
    }

    @Test void logarithmicGrowsSlowly() {
        assertThat(BigODemo.logarithmicOperations(1)).isZero();
        assertThat(BigODemo.logarithmicOperations(2)).isEqualTo(1L);
        assertThat(BigODemo.logarithmicOperations(8)).isEqualTo(3L);
        assertThat(BigODemo.logarithmicOperations(16)).isEqualTo(4L);
        assertThat(BigODemo.logarithmicOperations(1024)).isEqualTo(10L);
    }

    @Test void quadraticGrowsFasterThanLinear() {
        assertThat(BigODemo.quadraticOperations(10)).isEqualTo(100L);
        assertThat(BigODemo.quadraticOperations(20)).isEqualTo(400L);     // double n → quadruple ops
    }

    @Test void linearithmicIsSlowerThanLinearButFasterThanQuadratic() {
        // For n=100: O(n log n) ≈ 100 * 7 = 700, O(n²) = 10000
        long nlogn = BigODemo.linearithmicOperations(100);
        long quad  = BigODemo.quadraticOperations(100);
        assertThat(nlogn).isGreaterThan(BigODemo.linearOperations(100));
        assertThat(nlogn).isLessThan(quad);
    }
}