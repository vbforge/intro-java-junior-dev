package com.vbforge.exam.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.vbforge.exam.practical.Task05_RecursionAndDS.IntLinkedList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task05_RecursionAndDSTest {

    // =====================================================================
    // powerSet
    // =====================================================================

    @Nested
    class PowerSet {

        @Test void emptyArrayHasOneSubset() {
            assertThat(Task05_RecursionAndDS.powerSet(new int[]{}))
                    .hasSize(1)
                    .containsExactly(List.of());
        }

        @Test void singleElement() {
            assertThat(Task05_RecursionAndDS.powerSet(new int[]{1}))
                    .containsExactlyInAnyOrder(List.of(), List.of(1));
        }

        @Test void twoElements() {
            assertThat(Task05_RecursionAndDS.powerSet(new int[]{1, 2}))
                    .containsExactlyInAnyOrder(
                            List.of(),
                            List.of(1),
                            List.of(2),
                            List.of(1, 2));
        }

        @Test void threeElements() {
            assertThat(Task05_RecursionAndDS.powerSet(new int[]{1, 2, 3}))
                    .hasSize(8)
                    .containsExactlyInAnyOrder(
                            List.of(),
                            List.of(1),
                            List.of(2),
                            List.of(3),
                            List.of(1, 2),
                            List.of(1, 3),
                            List.of(2, 3),
                            List.of(1, 2, 3));
        }

        @Test void nullInput() {
            assertThat(Task05_RecursionAndDS.powerSet(null)).isEmpty();
        }

        @Test void inputNotMutated() {
            int[] input = {1, 2, 3};
            Task05_RecursionAndDS.powerSet(input);
            assertThat(input).containsExactly(1, 2, 3);
        }
    }

    // =====================================================================
    // balancedBrackets
    // =====================================================================

    @Nested
    class BalancedBrackets {

        @Test void valid() {
            assertThat(Task05_RecursionAndDS.balancedBrackets("()")).isTrue();
            assertThat(Task05_RecursionAndDS.balancedBrackets("()[]{}")).isTrue();
            assertThat(Task05_RecursionAndDS.balancedBrackets("([{}])")).isTrue();
            assertThat(Task05_RecursionAndDS.balancedBrackets("{[]}")).isTrue();
            assertThat(Task05_RecursionAndDS.balancedBrackets("a(b)c")).isTrue();
        }

        @Test void invalid() {
            assertThat(Task05_RecursionAndDS.balancedBrackets("(]")).isFalse();
            assertThat(Task05_RecursionAndDS.balancedBrackets("([)]")).isFalse();
            assertThat(Task05_RecursionAndDS.balancedBrackets("(((")).isFalse();
            assertThat(Task05_RecursionAndDS.balancedBrackets(")")).isFalse();
            assertThat(Task05_RecursionAndDS.balancedBrackets(")(")).isFalse();
            assertThat(Task05_RecursionAndDS.balancedBrackets("())")).isFalse();
        }

        @Test void emptyAndNull() {
            assertThat(Task05_RecursionAndDS.balancedBrackets("")).isTrue();
            assertThat(Task05_RecursionAndDS.balancedBrackets(null)).isTrue();
            assertThat(Task05_RecursionAndDS.balancedBrackets("abc")).isTrue();
        }
    }

    // =====================================================================
    // evaluateRPN
    // =====================================================================

    @Nested
    class EvaluateRPN {

        @Test void singleNumber() {
            assertThat(Task05_RecursionAndDS.evaluateRPN(new String[]{"5"})).isEqualTo(5);
            assertThat(Task05_RecursionAndDS.evaluateRPN(new String[]{"-3"})).isEqualTo(-3);
        }

        @Test void addition() {
            assertThat(Task05_RecursionAndDS.evaluateRPN(new String[]{"2", "3", "+"})).isEqualTo(5);
        }

        @Test void complexExpression() {
            // (2 + 3) * 4 = 20
            assertThat(Task05_RecursionAndDS.evaluateRPN(
                    new String[]{"2", "3", "+", "4", "*"}))
                    .isEqualTo(20);
        }

        @Test void subtractionIsOrderSensitive() {
            // 10 - 3 = 7
            assertThat(Task05_RecursionAndDS.evaluateRPN(new String[]{"10", "3", "-"})).isEqualTo(7);
        }

        @Test void integerDivision() {
            assertThat(Task05_RecursionAndDS.evaluateRPN(new String[]{"10", "3", "/"})).isEqualTo(3);
        }

        @Test void nestedExpression() {
            // ((1 + 2) * (3 - 4)) = 3 * -1 = -3
            assertThat(Task05_RecursionAndDS.evaluateRPN(
                    new String[]{"1", "2", "+", "3", "4", "-", "*"}))
                    .isEqualTo(-3);
        }

        @Test void invalidTokenThrows() {
            assertThatThrownBy(() -> Task05_RecursionAndDS.evaluateRPN(new String[]{"2", "abc", "+"}))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> Task05_RecursionAndDS.evaluateRPN(null))
                    .isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> Task05_RecursionAndDS.evaluateRPN(new String[]{}))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test void malformedExpressionThrows() {
            // insufficient operands for '+'
            assertThatThrownBy(() -> Task05_RecursionAndDS.evaluateRPN(new String[]{"2", "+"}))
                    .isInstanceOf(IllegalStateException.class);
            // leftover operands
            assertThatThrownBy(() -> Task05_RecursionAndDS.evaluateRPN(new String[]{"2", "3", "4"}))
                    .isInstanceOf(IllegalStateException.class);
        }
    }

    // =====================================================================
    // IntLinkedList
    // =====================================================================

    @Nested
    class LinkedList {

        @Test void emptyList() {
            IntLinkedList l = new IntLinkedList();
            assertThat(l.isEmpty()).isTrue();
            assertThat(l.size()).isZero();
            assertThat(l.toArray()).isEmpty();
        }

        @Test void addLastInOrder() {
            IntLinkedList l = new IntLinkedList();
            l.addLast(1);
            l.addLast(2);
            l.addLast(3);
            assertThat(l.toArray()).containsExactly(1, 2, 3);
            assertThat(l.size()).isEqualTo(3);
        }

        @Test void reverse() {
            IntLinkedList l = new IntLinkedList();
            for (int i = 1; i <= 4; i++) l.addLast(i);
            l.reverse();
            assertThat(l.toArray()).containsExactly(4, 3, 2, 1);
            // addLast must still work after reverse — tail pointer updated
            l.addLast(0);
            assertThat(l.toArray()).containsExactly(4, 3, 2, 1, 0);
        }

        @Test void reverseSingleElement() {
            IntLinkedList l = new IntLinkedList();
            l.addLast(7);
            l.reverse();
            assertThat(l.toArray()).containsExactly(7);
        }

        @Test void reverseEmpty() {
            IntLinkedList l = new IntLinkedList();
            l.reverse();
            assertThat(l.toArray()).isEmpty();
            l.addLast(1);
            assertThat(l.toArray()).containsExactly(1);
        }
    }
}