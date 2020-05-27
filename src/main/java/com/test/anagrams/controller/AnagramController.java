package com.test.anagrams.controller;

import com.test.anagrams.service.AnagramService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AnagramController {

    private AnagramService anagramService;

    @Autowired
    public AnagramController(AnagramService anagramService) {
        this.anagramService = anagramService;
    }

    @GetMapping(path = "/anagrams/{s1}/{s2}")
    public String isAnagram(@PathVariable String s1, @PathVariable String s2){
        return new JSONObject().put("areAnagrams", anagramService.isAnagram(s1, s2)).toString();
    }

    @GetMapping(path = "/anagrams/{s1}")
    public String getAllAnagrams(@PathVariable String s1){
        return new JSONObject().put("anagrams", anagramService.getAllAnagramStrings(s1)).toString();
    }

}
