package com.vbforge.oop.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AbstractAndInterfacesDemoTest {

    @Test void abstractMethodDispatchedFromConcreteMethod() {
        assertThat(AbstractAndInterfacesDemo.abstractPolymorphism())
                .isEqualTo("Rex says Woof | Tom says Meow");
    }

    @Test void interfaceDefaultIsInherited() {
        assertThat(AbstractAndInterfacesDemo.interfaceDefaultUsed())
                .isEqualTo("Hello, I'm a pet of Alice");
    }

    @Test void interfaceDefaultCanBeOverridden() {
        assertThat(AbstractAndInterfacesDemo.interfaceDefaultOverridden())
                .isEqualTo("Meow — I belong to Bob");
    }

    @Test void interfaceStaticHelperWorks() {
        assertThat(AbstractAndInterfacesDemo.interfaceStaticHelper()).isPositive();
    }
}