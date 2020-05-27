package com.test.anagrams.controller;

import com.google.common.collect.Sets;
import com.test.anagrams.dto.AllAnagramsDTO;
import com.test.anagrams.utility.TestUtility;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;


/**
 * Integration tests for controller
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnagramControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private HttpHeaders headers = new HttpHeaders();

    @Test
    @DisplayName("isAnagram Returns true for valid anagram strings and valid payload")
    void isAnagram_when_valid_anagram_strings_thenReturns_true() throws Exception {

        JSONObject expectedBody = new JSONObject().put("areAnagrams", true);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/anagrams/abc/cab"), HttpMethod.GET, entity, String.class);
        Assertions.assertEquals(200, response.getStatusCodeValue(), "Status code is 200");
        Assertions.assertEquals(expectedBody.toString(), response.getBody(), "response body is matching");
    }

    @Test
    @DisplayName("isAnagram Returns false for invalid anagram strings and valid payload")
    void isAnagram_when_invalid_anagram_strings_thenReturns_false() throws Exception {

        JSONObject expectedBody = new JSONObject().put("areAnagrams", false);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/anagrams/abc/abd"), HttpMethod.GET, entity, String.class);
        Assertions.assertEquals(200, response.getStatusCodeValue(), "Status code is 200");
        Assertions.assertEquals(expectedBody.toString(), response.getBody(), "response body is matching");
    }

    @Test
    @DisplayName("isAnagram Returns 400 for bad request when s1 is single character")
    void isAnagram_when_s1_is_bad_request_thenReturns_400() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/anagrams/a/abc"), HttpMethod.GET, entity, String.class);
        Assertions.assertEquals(400, response.getStatusCodeValue(), "Status code is 400");
    }

    @Test
    @DisplayName("isAnagram Returns 400 for bad request when s2 is single character")
    void isAnagram_when_s2_is_bad_request_thenReturns_400() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/anagrams/abc/a"), HttpMethod.GET, entity, String.class);
        Assertions.assertEquals(400, response.getStatusCodeValue(), "Status code is 400");
    }

    @Test
    @DisplayName("isAnagram Returns 400 for bad request when s1 and s2 is single character")
    void isAnagram_when_s1_and_s2_is_bad_request_thenReturns_400() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/anagrams/a/a"), HttpMethod.GET, entity, String.class);
        Assertions.assertEquals(400, response.getStatusCodeValue(), "Status code is 400");
    }

    @Test
    @DisplayName("GetAllAnagrams Returns all anagrams for a valid string")
    void getAllAnagrams_when_s1_is_valid_string() throws Exception {
        Set<String> s1_expected_anagrams = Sets.newHashSet("acb", "bac", "bca", "cab", "cba");

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<AllAnagramsDTO> response = restTemplate.exchange(createURLWithPort("/anagrams/abc"), HttpMethod.GET, entity, AllAnagramsDTO.class);
        Assertions.assertEquals(200, response.getStatusCodeValue(), "Status code is 200");
        Assertions.assertTrue(TestUtility.isEqualSet(response.getBody().getAnagrams(), s1_expected_anagrams), "List of all anagrams are equal");
    }

    @Test
    @DisplayName("GetAllAnagrams Returns 400 for bad request when s1 is single character")
    void getAllAnagrams_when_s1_is_bad_request_thenReturns_400() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<AllAnagramsDTO> response = restTemplate.exchange(createURLWithPort("/anagrams/a"), HttpMethod.GET, entity, AllAnagramsDTO.class);
        Assertions.assertEquals(400, response.getStatusCodeValue(), "Status code is 400");
    }

    String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
