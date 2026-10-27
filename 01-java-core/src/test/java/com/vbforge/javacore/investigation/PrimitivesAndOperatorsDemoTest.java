package com.vbforge.javacore.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PrimitivesAndOperatorsDemoTest {

    @Test void intDivisionTruncates() {
        assertThat(PrimitivesAndOperatorsDemo.intDivision()).isEqualTo(3);
    }

    @Test void realDivisionKeepsFraction() {
        assertThat(PrimitivesAndOperatorsDemo.realDivision()).isEqualTo(3.5);
    }

    @Test void moduloFollowsDividendSign() {
        assertThat(PrimitivesAndOperatorsDemo.negativeModulo()).isEqualTo(-1);
    }

    @Test void integerOverflowWrapsSilently() {
        assertThat(PrimitivesAndOperatorsDemo.integerOverflow()).isEqualTo(Integer.MIN_VALUE);
    }

    @Test void narrowCastTruncatesNotClamps() {
        assertThat(PrimitivesAndOperatorsDemo.narrowingCastTruncates()).isEqualTo((byte) -126);
    }

    @Test void compoundAssignmentHidesCast() {
        assertThat(PrimitivesAndOperatorsDemo.compoundAssignmentTruncates()).isEqualTo(13);
    }

    @Test void logicalAndShortCircuits() {
        assertThat(PrimitivesAndOperatorsDemo.logicalAndShortCircuits()).isFalse();
    }

    @Test void bitwiseAndEvaluatesBoth() {
        assertThat(PrimitivesAndOperatorsDemo.bitwiseAndEvaluatesBoth()).isFalse();
    }

    @Test void wrappersAreNotEqualOutsideCache() {
        assertThat(PrimitivesAndOperatorsDemo.wrapperEqualityOutsideCache()).isFalse();
    }

    @Test void wrappersAreEqualInsideCache() {
        assertThat(PrimitivesAndOperatorsDemo.wrapperEqualityInsideCache()).isTrue();
    }
}