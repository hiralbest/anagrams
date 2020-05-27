package com.test.anagrams.service;

import java.util.Set;

public interface AnagramService {

    boolean isAnagram(String s1, String s2);

    Set<String> getAllAnagramStrings(String s1);

}
