package com.xyz.reviewsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyz.reviewsystem.entity.Review;
import com.xyz.reviewsystem.model.ReviewDto;
import com.xyz.reviewsystem.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ReviewControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private ReviewController reviewController;

    private ReviewDto reviewDto;

    private Review reviewEntity;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();

        reviewDto = new ReviewDto();
        reviewDto.setAuthor("John Doe");
        reviewDto.setReview("Great product!");
        reviewDto.setReviewSource("Amazon");
        reviewDto.setRating(5);
        reviewDto.setTitle("Excellent");
        reviewDto.setStore("Alexa");
        //reviewDto.setReviewedDate("2024-01-15T12:00:00");

        reviewEntity = new Review();
        reviewEntity.setAuthor(reviewDto.getAuthor());
        reviewEntity.setReview(reviewDto.getReview());
        reviewEntity.setReviewSource(reviewDto.getReviewSource());
        reviewEntity.setRating(reviewDto.getRating());
        reviewEntity.setTitle(reviewDto.getTitle());
        reviewEntity.setStore(reviewDto.getStore());
    }

    @Test
    void testAddReview() throws Exception {
        doNothing().when(reviewService).addReview(any(ReviewDto.class));

        mockMvc.perform(post("/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reviewDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"message\":\"Review added successfully\"}"));

        verify(reviewService, times(1)).addReview(any(ReviewDto.class));
    }

    @Test
    void testGetAllReviews() throws Exception {
        List<Review> reviews = Arrays.asList(reviewEntity);
        when(reviewService.getReviews(anyString(),anyString(),anyString())).thenReturn(reviews);
        

        mockMvc.perform(get("/reviews")
                        .param("store", "Alexa")
                        .param("rating", "5")
                        .param("reviewDate", "2024-02-10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].store").value("Alexa"))
                .andExpect(jsonPath("$[0].rating").value(5));

        verify(reviewService, times(1)).getReviews(anyString(),anyString(),anyString());
    }

    @Test
    void testGetAverageMonthlyRatings() throws Exception {
        Map<String, Double> totalRatings = new HashMap<>();
        totalRatings.put("apple", 4.5);
        when(reviewService.getAverageMonthlyRating()).thenReturn(totalRatings);

        mockMvc.perform(get("/reviews/average-rating"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"apple\":4.5}"));

        verify(reviewService, times(1)).getAverageMonthlyRating();
    }

    @Test
    void testGetTotalRatings() throws Exception {
        Map<Integer, Integer> totalRatings = new HashMap<>();
        totalRatings.put(5, 10);
        totalRatings.put(4, 5);

        when(reviewService.findTotalRatingsPerCategory()).thenReturn(totalRatings);

        mockMvc.perform(get("/reviews/total-ratings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.5").value(10))
                .andExpect(jsonPath("$.4").value(5));

        verify(reviewService, times(1)).findTotalRatingsPerCategory();
    }
}
