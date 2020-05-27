package com.test.anagrams.dto;

import java.util.Set;

public class AllAnagramsDTO {

    private Set<String> anagrams;

    public Set<String> getAnagrams() {
        return anagrams;
    }

    public void setAnagrams(Set<String> anagrams) {
        this.anagrams = anagrams;
    }
}
