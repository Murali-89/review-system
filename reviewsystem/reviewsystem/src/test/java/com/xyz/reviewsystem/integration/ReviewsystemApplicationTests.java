package com.xyz.reviewsystem.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReviewsystemApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/reviews";
    }

    @Test
    void testAddReview() {
        String requestJson = "{\"review\": \"Great product!\",\"author\": \"Alice\", \"reviewSource\": \"iTunes\", \"rating\": 5, \"title\": \"Awesome\", \"productName\": \"Amazon Alexa\", \"reviewedDate\": \"2025-02-07T12:00:00Z\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(getBaseUrl(), entity, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    void testGetReviews() {
        ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl(), String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testGetAverageRatingsPerProduct() {
        ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl() + "/average-rating", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testGetTotalRatingsPerCategory() {
        ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl() + "/total-ratings", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

}
