package com.test.anagrams.service;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnagramServiceImpl implements AnagramService {

    /**
     * Check if 2 strings are anagrams
     * Reference: https://www.baeldung.com/java-strings-anagrams
     * @param s1
     * @param s2
     * @return
     */
    @Override
    public boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        Multiset<Character> multiset1 = HashMultiset.create();
        Multiset<Character> multiset2 = HashMultiset.create();
        for (int i = 0; i < s1.length(); i++) {
            multiset1.add(s1.charAt(i));
            multiset2.add(s2.charAt(i));
        }
        return multiset1.equals(multiset2);
    }

    @Override
    public List<String> getAllAnagramStrings(String s1) {
        return null;
    }
}
