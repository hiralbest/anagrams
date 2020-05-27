package com.test.anagrams.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    void isAnagram_when_strings_are_anagram(){
        String s1 = "abc";
        String s2 = "bca";
        assertTrue(anagramService.isAnagram(s1, s2));

        s2 = "cba";
        assertTrue(anagramService.isAnagram(s1, s2));
    }

    @Test
    @DisplayName("Strings are not anagrams")
    void isAnagram_when_strings_are_not_anagram(){
        String s1 = "abc";
        String s2 = "bda";
        assertFalse(anagramService.isAnagram(s1, s2));

        s2 = "uhg";
        assertFalse(anagramService.isAnagram(s1, s2));
    }
}
