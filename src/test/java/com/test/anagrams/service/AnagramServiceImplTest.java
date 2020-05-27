package com.test.anagrams.service;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnagramServiceImplTest {

    private AnagramService anagramService;

    @BeforeEach
    void setUp() {
        anagramService = new AnagramServiceImpl();
    }

    @Test
    @DisplayName("Not an anagram when string sizes are different")
    void isAnagram_when_string_size_is_different() {
        String s1 = "abc";
        String s2 = "abcd";
        assertFalse(anagramService.isAnagram(s1, s2));

    }

    @Test
    @DisplayName("Strings are anagrams")
    void isAnagram_when_strings_are_anagram() {
        String s1 = "abc";
        String s2 = "bca";
        assertTrue(anagramService.isAnagram(s1, s2));

        s2 = "cba";
        assertTrue(anagramService.isAnagram(s1, s2));
    }

    @Test
    @DisplayName("Strings are not anagrams")
    void isAnagram_when_strings_are_not_anagram() {
        String s1 = "abc";
        String s2 = "bda";
        assertFalse(anagramService.isAnagram(s1, s2));

        s2 = "uhg";
        assertFalse(anagramService.isAnagram(s1, s2));
    }

    @Test
    @DisplayName("Correct anagrams for string length of 1")
    void getAllAnagramStrings_when_string_1char() {
        String s1 = "a";
        Set<String> s1_expected_anagrams = Sets.newHashSet();
        Set<String> s1_actual_anagrams = anagramService.getAllAnagramStrings(s1);
        assertTrue(isEqualSet(s1_actual_anagrams, s1_expected_anagrams), "Correct anagrams for string length of 1");
    }

    @Test
    @DisplayName("Correct anagrams for string length of 2")
    void getAllAnagramStrings_when_string_2char() {
        String s1 = "ab";
        Set<String> s1_expected_anagrams = Sets.newHashSet("ba");
        Set<String> s1_actual_anagrams = anagramService.getAllAnagramStrings(s1);
        assertTrue(isEqualSet(s1_actual_anagrams, s1_expected_anagrams), "Correct anagrams for string length of 2");
    }

    @Test
    @DisplayName("Correct anagrams for string length of 3")
    void getAllAnagramStrings_when_string_3char() {
        String s1 = "abc";
        Set<String> s1_expected_anagrams = Sets.newHashSet("acb", "bac", "bca", "cab", "cba");
        Set<String> s1_actual_anagrams = anagramService.getAllAnagramStrings(s1);
        assertTrue(isEqualSet(s1_actual_anagrams, s1_expected_anagrams), "Correct anagrams for string length of 3");
    }

    @Test
    @DisplayName("Correct anagrams for string with duplicate character")
    void getAllAnagramStrings_when_string_with_duplicate_character() {
        String s1 = "aba";
        Set<String> s1_expected_anagrams = Sets.newHashSet("baa", "aab");
        Set<String> s1_actual_anagrams = anagramService.getAllAnagramStrings(s1);
        assertTrue(isEqualSet(s1_actual_anagrams, s1_expected_anagrams), "Correct anagrams for string with duplicate character");
    }

    /**
     * Reference: https://mkyong.com/java/java-how-to-compare-two-sets/
     *
     * @param set1
     * @param set2
     * @return
     */
    boolean isEqualSet(Set<String> set1, Set<String> set2) {
        if (set1 == null || set2 == null) {
            return false;
        }

        if (set1.size() != set2.size()) {
            return false;
        }

        return set1.containsAll(set2);
    }
}
