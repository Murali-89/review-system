package com.xyz.reviewsystem.service;

import com.xyz.reviewsystem.entity.Review;
import com.xyz.reviewsystem.model.ReviewDto;

import java.util.List;
import java.util.Map;

public interface ReviewService {
    void addReview(ReviewDto reviewDto);

    List<Review> getReviews(String productName, String rating, String reviewSource);

    Map<Integer, Integer> findTotalRatingsPerCategory();

    Map<String, Double> getAverageMonthlyRating();
}
