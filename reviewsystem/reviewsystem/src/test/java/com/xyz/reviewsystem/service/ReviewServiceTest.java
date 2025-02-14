package com.xyz.reviewsystem.service;

import com.xyz.reviewsystem.entity.Review;
import com.xyz.reviewsystem.model.ReviewDto;
import com.xyz.reviewsystem.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private ReviewDto reviewDto;
    private Review reviewEntity;


    @BeforeEach
    void setUp() {
        // Creating ReviewDto instance (data transfer object)
        reviewDto = new ReviewDto();
        reviewDto.setAuthor("John Doe");
        reviewDto.setReview("Great product!");
        reviewDto.setReviewSource("Amazon");
        reviewDto.setRating(5);
        reviewDto.setTitle("Excellent");
        reviewDto.setStore("Alexa");

        // Creating corresponding Review entity
        reviewEntity = new Review();
        reviewEntity.setAuthor(reviewDto.getAuthor());
        reviewEntity.setReview(reviewDto.getReview());
        reviewEntity.setReviewSource(reviewDto.getReviewSource());
        reviewEntity.setRating(reviewDto.getRating());
        reviewEntity.setTitle(reviewDto.getTitle());
        reviewEntity.setStore(reviewDto.getStore());
        reviewEntity.setReviewedDate(LocalDateTime.now());
    }
    @Test
    void testAddReview() {
        // Act
        reviewService.addReview(reviewDto);
        verify(reviewRepository, times(1)).save(Mockito.any());
    }
    @Test
    void testGetAllReviews() {
       //Review review = new Review();
        when(reviewRepository.findAll()).thenReturn(Arrays.asList(reviewEntity));

        List<Review> reviews = reviewService.getReviews("","","");

        assertEquals(1, reviews.size());
        assertEquals("Alexa", reviews.get(0).getStore());
        verify(reviewRepository, times(1)).findAll();
    }
    @Test
    void testGetAllReviewsWithStoreName() {
        //Review review = new Review();
        when(reviewRepository.findByStore(anyString())).thenReturn(Arrays.asList(reviewEntity));

        List<Review> reviews = reviewService.getReviews("Alexa","","");

        assertEquals(1, reviews.size());
        assertEquals("Alexa", reviews.get(0).getStore());
        verify(reviewRepository, times(1)).findByStore(anyString());
    }
    @Test
    void testGetAllReviewsWithRating() {
        //Review review = new Review();
        when(reviewRepository.findByRating(anyInt())).thenReturn(Arrays.asList(reviewEntity));

        List<Review> reviews = reviewService.getReviews("","1","");

        assertEquals(1, reviews.size());
        assertEquals("Alexa", reviews.get(0).getStore());
        verify(reviewRepository, times(1)).findByRating(anyInt());
    }

    @Test
    void testGetAverageMonthlyRating() {
        //Review review = new Review();
        List<Review>  reviewList= Arrays.asList(reviewEntity);
        when(reviewRepository.findAll()).thenReturn(reviewList);
        Map<String, Double> monthlyRating = reviewService.getAverageMonthlyRating();
        assertEquals(1, monthlyRating.size());
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void testGetTotalRatingPerCategory() {

        Object object[] = {4,2};
        List<Object[]>  reviewList= new ArrayList<>();
        reviewList.add(object);
        //List<Object[]> results = reviewRepository.findTotalRatingsPerCategory();
        when(reviewRepository.findTotalRatingsPerCategory()).thenReturn(reviewList);
        Map<Integer, Integer> totalRating = reviewService.findTotalRatingsPerCategory();
        assertEquals(2, totalRating.get(4));
        verify(reviewRepository, times(1)).findTotalRatingsPerCategory();
    }
}
