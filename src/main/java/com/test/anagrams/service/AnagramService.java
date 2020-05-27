package com.test.anagrams.service;

import java.util.List;

public interface AnagramService {

    boolean isAnagram(String s1, String s2);

    List<String> getAllAnagramStrings(String s1);

}
