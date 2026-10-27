package com.vbforge.collections.investigation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ListDemoTest {

    @Test void ofBuildsMutableList() {
        List<Integer> l = ListDemo.of(1, 2, 3);
        l.add(4);
        assertThat(l).containsExactly(1, 2, 3, 4);
    }

    @Test void getByIndexAndIndexOf() {
        List<Integer> l = ListDemo.of(10, 20, 30);
        assertThat(ListDemo.getByIndex(l, 1)).isEqualTo(20);
        assertThat(ListDemo.indexOf(l, 30)).isEqualTo(2);
        assertThat(ListDemo.indexOf(l, 99)).isEqualTo(-1);
    }

    @Test void sublistIsAView() {
        List<Integer> parent = ListDemo.of(1, 2, 3, 4, 5);
        List<Integer> view = ListDemo.sublist(parent, 1, 4);
        assertThat(view).containsExactly(2, 3, 4);
        assertThat(ListDemo.subListMutatesParent()).isTrue();
    }

    @Test void copyIsIndependent() {
        List<Integer> src = ListDemo.of(1, 2, 3);
        List<Integer> c = ListDemo.copy(src);
        c.add(99);
        assertThat(src).containsExactly(1, 2, 3);
    }

    @Test void linkedListAsDequeSum() {
        assertThat(ListDemo.linkedListAsDeque()).isEqualTo(4);
    }

    @Test void listOfIsImmutable() {
        assertThat(ListDemo.listOfIsImmutable()).isTrue();
    }
}