package com.vbforge.oop.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PolymorphismDemoTest {

    @Test void dynamicDispatchUsesActualType() {
        assertThat(PolymorphismDemo.allSounds())
                .containsExactly("Woof", "Meow", "...");
    }

    @Test void upcastIsImplicitAndSafe() {
        assertThat(PolymorphismDemo.upcast().sound()).isEqualTo("Woof");
    }

    @Test void safeDowncastUsesInstanceofPattern() {
        assertThat(PolymorphismDemo.safeDowncast(new PolymorphismDemo.Dog())).isEqualTo("Dog says Woof");
        assertThat(PolymorphismDemo.safeDowncast(new PolymorphismDemo.Cat())).isEqualTo("Cat says Meow");
        assertThat(PolymorphismDemo.safeDowncast(new PolymorphismDemo.Animal())).isEqualTo("Unknown animal");
    }

    @Test void overloadingResolvedAtCompileTime() {
        assertThat(PolymorphismDemo.overloaded(1)).isEqualTo("int 1");
        assertThat(PolymorphismDemo.overloaded(1L)).isEqualTo("long 1");
        assertThat(PolymorphismDemo.overloaded(1.0)).isEqualTo("double 1.0");
    }
}