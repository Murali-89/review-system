package com.xyz.reviewsystem.service;

import com.xyz.reviewsystem.entity.Review;
import com.xyz.reviewsystem.model.ReviewDto;

import java.util.List;

public interface ReviewService {
    public void addReview(ReviewDto reviewDto);

    public List<Review> getReviews(String productName, String rating, String reviewSource);

    public List<Object[]> findAverageRatingsPerProduct();
    public List<Object[]> findTotalRatingsPerCategory();
}
