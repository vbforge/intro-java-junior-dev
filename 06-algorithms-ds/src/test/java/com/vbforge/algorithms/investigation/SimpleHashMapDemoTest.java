package com.vbforge.algorithms.investigation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleHashMapDemoTest {

    @Test void putAndGet() {
        var m = new SimpleHashMapDemo.SimpleHashMap<String, Integer>();
        m.put("a", 1);
        m.put("b", 2);
        assertThat(m.get("a")).isEqualTo(1);
        assertThat(m.get("b")).isEqualTo(2);
        assertThat(m.get("missing")).isNull();
        assertThat(m.size()).isEqualTo(2);
    }

    @Test void putOverwritesExistingKey() {
        var m = new SimpleHashMapDemo.SimpleHashMap<String, Integer>();
        m.put("a", 1);
        m.put("a", 99);
        assertThat(m.get("a")).isEqualTo(99);
        assertThat(m.size()).isEqualTo(1);
    }

    @Test void remove() {
        var m = new SimpleHashMapDemo.SimpleHashMap<String, Integer>();
        m.put("a", 1);
        m.put("b", 2);
        assertThat(m.remove("a")).isEqualTo(1);
        assertThat(m.get("a")).isNull();
        assertThat(m.remove("missing")).isNull();
        assertThat(m.size()).isEqualTo(1);
    }

    @Test void containsKeyAndNullValues() {
        var m = new SimpleHashMapDemo.SimpleHashMap<String, Integer>();
        m.put("present", null);                         // null VALUES are allowed
        assertThat(m.containsKey("present")).isFalse(); // get returns null → containsKey false
        // ^ This is a documented limitation of the tiny implementation:
        //   it can't distinguish "present-but-null" from "absent".
    }

    @Test void nullKeyRejected() {
        var m = new SimpleHashMapDemo.SimpleHashMap<String, Integer>();
        assertThatThrownBy(() -> m.put(null, 1)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> m.get(null)).isInstanceOf(NullPointerException.class);
    }

    @Test void resizeHappensTransparently() {
        var m = new SimpleHashMapDemo.SimpleHashMap<Integer, Integer>();
        for (int i = 0; i < 100; i++) m.put(i, i * 10);
        assertThat(m.size()).isEqualTo(100);
        for (int i = 0; i < 100; i++) assertThat(m.get(i)).isEqualTo(i * 10);
    }
}