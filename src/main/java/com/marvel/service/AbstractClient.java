package com.marvel.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class AbstractClient {
    @Value("${marvel.base-url}")
    protected String baseUrl;
    @Value("${marvel.url-ts}")
    protected String ts;
    @Value("${marvel.url-apiKey}")
    protected String apiKey;
    @Value("${marvel.url-hash}")
    protected String hash;

    protected final RestTemplate restTemplate;

    protected AbstractClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected HttpHeaders buildAuthToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }
}
