package com.test.anagrams.controller;

import com.google.common.base.Strings;
import com.test.anagrams.service.AnagramService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/anagrams/{s1}/{s2}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> isAnagram(@PathVariable String s1, @PathVariable String s2){
        if(Strings.isNullOrEmpty(s1) || Strings.isNullOrEmpty(s2) || s1.length() <= 1 || s2.length() <= 1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new JSONObject().put("areAnagrams", anagramService.isAnagram(s1, s2)).toString());
    }

    @GetMapping(path = "/anagrams/{s1}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllAnagrams(@PathVariable String s1){
        if(Strings.isNullOrEmpty(s1) || s1.length() <= 1)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(new JSONObject().put("anagrams", anagramService.getAllAnagramStrings(s1)).toString());
    }

}
