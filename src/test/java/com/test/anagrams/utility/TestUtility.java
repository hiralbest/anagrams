package com.test.anagrams.utility;

import java.util.Set;

public class TestUtility {
    /**
     * Reference: https://mkyong.com/java/java-how-to-compare-two-sets/
     *
     * @param set1
     * @param set2
     * @return
     */
    public static boolean isEqualSet(Set<String> set1, Set<String> set2) {
        if (set1 == null || set2 == null) {
            return false;
        }

        if (set1.size() != set2.size()) {
            return false;
        }

        return set1.containsAll(set2);
    }
}
