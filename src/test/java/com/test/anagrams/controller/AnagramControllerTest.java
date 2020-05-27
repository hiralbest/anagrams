package com.test.anagrams.controller;
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


/**
 * Integration tests for controller
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnagramControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private HttpHeaders headers = new HttpHeaders();

    @Test
    @DisplayName("Returns true for valid anagram strings and valid payload")
    private void isAnagram_when_valid_anagram_strings_thenReturns_true() throws Exception {

        JSONObject responseBody = new JSONObject().put("areAnagrams", true);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/anagrams/abc/cab"), HttpMethod.GET, entity, String.class);
        Assertions.assertEquals(response.getStatusCodeValue(), 200, "Status code is 200");
        Assertions.assertEquals(responseBody.toString(), response.getBody(), "response body is matching");
    }

    @Test
    @DisplayName("Returns false for invalid anagram strings and valid payload")
    private void isAnagram_when_invalid_anagram_strings_thenReturns_false() throws Exception {

        JSONObject responseBody = new JSONObject().put("areAnagrams", false);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/anagrams/abc/abd"), HttpMethod.GET, entity, String.class);
        Assertions.assertEquals(response.getStatusCodeValue(), 200, "Status code is 200");
        Assertions.assertEquals(responseBody.toString(), response.getBody(), "response body is matching");
    }

    @Test
    @DisplayName("Returns 400 for bad request")
    private void isAnagram_when_bad_request_thenReturns_400() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/anagrams/a/a"), HttpMethod.GET, entity, String.class);
        Assertions.assertEquals(400, response.getStatusCodeValue(), "Status code is 400");
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
