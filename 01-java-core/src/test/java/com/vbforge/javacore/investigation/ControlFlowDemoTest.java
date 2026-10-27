package com.vbforge.javacore.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ControlFlowDemoTest {

    @Test void classifyHandlesAllBranches() {
        assertThat(ControlFlowDemo.classify(-1)).isEqualTo("negative");
        assertThat(ControlFlowDemo.classify(0)).isEqualTo("zero");
        assertThat(ControlFlowDemo.classify(1)).isEqualTo("positive");
    }

    @Test void classicSwitchWorks() {
        assertThat(ControlFlowDemo.dayTypeClassic(1)).isEqualTo("weekday");
        assertThat(ControlFlowDemo.dayTypeClassic(6)).isEqualTo("weekend");
        assertThat(ControlFlowDemo.dayTypeClassic(99)).isEqualTo("invalid");
    }

    @Test void arrowSwitchBehavesLikeClassicSwitch() {
        for (int d = 0; d <= 8; d++) {
            assertThat(ControlFlowDemo.dayTypeArrow(d))
                    .isEqualTo(ControlFlowDemo.dayTypeClassic(d));
        }
    }

    @Test void sumForAndSumWhileAgree() {
        for (int n = 0; n <= 10; n++) {
            assertThat(ControlFlowDemo.sumFor(n)).isEqualTo(ControlFlowDemo.sumWhile(n));
        }
        assertThat(ControlFlowDemo.sumFor(10)).isEqualTo(55);
    }

    @Test void doWhileRunsOnceEvenWhenConditionFalse() {
        assertThat(ControlFlowDemo.doWhileRunsAtLeastOnce()).isEqualTo(1);
    }

    @Test void enhancedForSums() {
        assertThat(ControlFlowDemo.sumEnhancedFor(new int[]{1, 2, 3})).isEqualTo(6);
    }

    @Test void labeledBreakExitsBothLoops() {
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        assertThat(ControlFlowDemo.findPair(grid, 5)).containsExactly(1, 1);
        assertThat(ControlFlowDemo.findPair(grid, 42)).containsExactly(-1, -1);
    }
}