package com.vbforge.oop.practical;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled
class Task04_MoneyTest {

    @Test void constructorValidation() {
        assertThatThrownBy(() -> new Task04_Money(100, null))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Task04_Money(100, "  "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test void currencyIsNormalizedToUpperCase() {
        assertThat(new Task04_Money(100, "usd").currency()).isEqualTo("USD");
    }

    @Test void equalsIsValueBased() {
        assertThat(new Task04_Money(100, "USD")).isEqualTo(new Task04_Money(100, "usd"));
        assertThat(new Task04_Money(100, "USD")).isNotEqualTo(new Task04_Money(100, "EUR"));
        assertThat(new Task04_Money(100, "USD")).isNotEqualTo(new Task04_Money(101, "USD"));
    }

    @Test void hashCodeIsConsistentWithEquals() {
        Set<Task04_Money> set = new HashSet<>();
        set.add(new Task04_Money(100, "USD"));
        assertThat(set).contains(new Task04_Money(100, "usd"));
    }

    @Test void toStringFormat() {
        assertThat(new Task04_Money(1234, "USD").toString()).isEqualTo("1234 USD");
        assertThat(new Task04_Money(-50, "EUR").toString()).isEqualTo("-50 EUR");
    }

    @Test void plus() {
        assertThat(new Task04_Money(100, "USD").plus(new Task04_Money(50, "USD")))
                .isEqualTo(new Task04_Money(150, "USD"));
        assertThatThrownBy(() -> new Task04_Money(100, "USD").plus(new Task04_Money(50, "EUR")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("currency");
    }

    @Test void negate() {
        assertThat(new Task04_Money(100, "USD").negate())
                .isEqualTo(new Task04_Money(-100, "USD"));
    }

    @Test void compareToSortsByCurrencyThenCents() {
        Set<Task04_Money> sorted = new TreeSet<>();
        sorted.add(new Task04_Money(100, "USD"));
        sorted.add(new Task04_Money(50, "USD"));
        sorted.add(new Task04_Money(200, "EUR"));

        // Expected order: EUR(200), USD(50), USD(100)
        assertThat(sorted).containsExactly(
                new Task04_Money(200, "EUR"),
                new Task04_Money(50, "USD"),
                new Task04_Money(100, "USD")
        );
    }
}