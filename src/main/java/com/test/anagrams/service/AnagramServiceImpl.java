package com.test.anagrams.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnagramServiceImpl implements AnagramService {

    @Override
    public boolean isAnagram(String s1, String s2) {
        return false;
    }

    @Override
    public List<String> getAllAnagramStrings(String s1) {
        return null;
    }
}
