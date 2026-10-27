package com.vbforge.javacore.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MethodsDemoTest {

    @Test void overloadingPicksMostSpecificParameter() {
        assertThat(MethodsDemo.describe(1)).isEqualTo("int: 1");
        assertThat(MethodsDemo.describe(1L)).isEqualTo("long: 1");
        assertThat(MethodsDemo.describe(1.0)).isEqualTo("double: 1.0");
        assertThat(MethodsDemo.describe("x")).isEqualTo("String: x");
    }

    @Test void varargsAcceptZeroToMany() {
        assertThat(MethodsDemo.sum()).isZero();
        assertThat(MethodsDemo.sum(1)).isEqualTo(1);
        assertThat(MethodsDemo.sum(1, 2, 3)).isEqualTo(6);
        assertThat(MethodsDemo.sum(new int[]{1, 2, 3, 4})).isEqualTo(10);
    }

    @Test void primitivesAreNotAffectedByModification() {
        int x = 1;
        MethodsDemo.tryToChangeInt(x);
        assertThat(x).isEqualTo(1);
    }

    @Test void referencesCanBeUsedToMutateObjects() {
        int[] a = {1, 2, 3};
        MethodsDemo.mutateArray(a);
        assertThat(a).containsExactly(42, 2, 3);
    }

    @Test void reassigningReferenceDoesNotChangeCaller() {
        int[] b = {1, 2, 3};
        MethodsDemo.reassignArrayLocal(b);
        assertThat(b).containsExactly(1, 2, 3);
    }
}