package com.vbforge.collections.investigation;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MapDemoTest {

    @Test void basicMap() {
        Map<String, Integer> m = MapDemo.basicMap();
        assertThat(m).containsEntry("a", 1).containsEntry("b", 2).containsEntry("c", 3);
    }

    @Test void getOrDefault() {
        Map<String, Integer> m = Map.of("x", 10);
        assertThat(MapDemo.getOrDefaultDemo(m, "x")).isEqualTo(10);
        assertThat(MapDemo.getOrDefaultDemo(m, "y")).isEqualTo(-1);
    }

    @Test void putIfAbsent() {
        Map<String, Integer> m = MapDemo.putIfAbsentDemo();
        assertThat(m).containsEntry("x", 1).containsEntry("y", 2);
    }

    @Test void computeIfAbsentGroups() {
        Map<String, java.util.List<Integer>> groups =
                MapDemo.groupByModulus(new int[]{2, 3, 4, 5, 6}, 2);
        assertThat(groups.get("divisible")).containsExactly(2, 4, 6);
        assertThat(groups.get("not-divisible")).containsExactly(3, 5);
    }

    @Test void mergeCountsWords() {
        Map<String, Integer> counts =
                MapDemo.countWords(new String[]{"a", "b", "a", "c", "a"});
        assertThat(counts).containsEntry("a", 3).containsEntry("b", 1).containsEntry("c", 1);
    }

    @Test void entrySetIteration() {
        Map<String, Integer> m = new java.util.LinkedHashMap<>();
        m.put("a", 1);
        m.put("b", 2);
        assertThat(MapDemo.joinEntries(m)).isEqualTo("a=1, b=2");
    }

    @Test void linkedHashMapInsertionOrder() {
        assertThat(MapDemo.linkedHashMapOrder()).containsExactly("c", "a", "b");
    }

    @Test void treeMapSortedOrder() {
        assertThat(MapDemo.treeMapOrder()).containsExactly("a", "b", "c");
    }

    @Test void lruCacheEvictsOldestAccessed() {
        MapDemo.LruCache<Integer, String> cache = new MapDemo.LruCache<>(2);
        cache.put(1, "a");
        cache.put(2, "b");
        cache.get(1);                                   // 1 becomes "most recently used"
        cache.put(3, "c");                              // evicts 2 (least recently used)
        assertThat(cache).containsKey(1).containsKey(3).doesNotContainKey(2);
    }
}