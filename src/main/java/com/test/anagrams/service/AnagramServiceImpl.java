package com.test.anagrams.service;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AnagramServiceImpl implements AnagramService {

    /**
     * Check if 2 strings are anagrams
     * Reference: https://www.baeldung.com/java-strings-anagrams
     *
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
    public Set<String> getAllAnagramStrings(String s1) {
        Set<String> allAnagrams = new HashSet<>();
        permutation("", s1, allAnagrams);
        // Remove original string
        allAnagrams.remove(s1);
        return allAnagrams;
    }

    /**
     * We are using Set data structure to avoid duplicates
     * Reference: https://stackoverflow.com/questions/4240080/generating-all-permutations-of-a-given-string
     *
     * @param prefix
     * @param str
     * @param allAnagrams
     */
    private void permutation(String prefix, String str, Set<String> allAnagrams) {
        int n = str.length();
        if (n == 0) allAnagrams.add(prefix);
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), allAnagrams);
        }
    }
}
