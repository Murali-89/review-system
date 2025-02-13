package com.xyz.reviewsystem.service;

import com.xyz.reviewsystem.entity.Review;
import com.xyz.reviewsystem.model.ReviewDto;
import com.xyz.reviewsystem.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void addReview(ReviewDto reviewDto) {
        Review review = mapReviewDtoToReview(reviewDto);
        if (review == null) {
            throw new RuntimeException("Review is null");
        }
        reviewRepository.save(review);

    }

    @Override
    public List<Review> getReviews(String productName, String rating, String title) {
        List<Review> reviews = null;
        if (!StringUtils.isEmpty(productName)) {
            List<Review> optionalReviews = reviewRepository.findByProductName(productName);
            if (optionalReviews.isEmpty())
                throw new RuntimeException("product name not found");
            return optionalReviews;
        }
        if (!StringUtils.isEmpty(rating)) {
            List<Review> optionalReviews = reviewRepository.findByRating(Integer.parseInt(rating));
            if (optionalReviews.isEmpty())
                throw new RuntimeException("No review found with the specified rating");
            return optionalReviews;
        }
        if (!StringUtils.isEmpty(title)) {
            List<Review> optionalReviews = reviewRepository.findByTitle(title);
            if (optionalReviews.isEmpty())
                throw new RuntimeException("No review found with the specified title");
            return optionalReviews;
        } else {
            reviews = reviewRepository.findAll();
            return reviews;

        }
    }

    @Override
    public Map<String, Double> getAverageMonthlyRating() {
        List<Review> reviews = reviewRepository.findAll();

        Map<String, List<Integer>> storeRatings = new HashMap<>();

        for (Review review : reviews) {
            String key = review.getProductName() + "-" + review.getReviewedDate().getYear() + "-" + review.getReviewedDate().getMonthValue();
            storeRatings.computeIfAbsent(key, k -> new ArrayList<>()).add(review.getRating());
        }

        Map<String, Double> avgRatings = new HashMap<>();
        for (Map.Entry<String, List<Integer>> entry : storeRatings.entrySet()) {
            double avg = entry.getValue().stream().mapToInt(Integer::intValue).average().orElse(0);
            avgRatings.put(entry.getKey(), avg);
        }
        return avgRatings;

    }

    @Override
    public Map<Integer, Integer> findTotalRatingsPerCategory() {
        //return reviewRepository.findTotalRatingsPerCategory();
        List<Object[]> results = reviewRepository.findTotalRatingsPerCategory();
        Map<Integer, Integer> ratingMap = new HashMap<>();

        for (Object[] row : results) {
            Integer rating = (Integer) row[0];  // Assuming first column is rating
            Integer count = ((Number) row[1]).intValue();  // Convert count to Integer
            ratingMap.put(rating, count);
        }

        return ratingMap;

    }

    public Review mapReviewDtoToReview(ReviewDto reviewDto) {

        if (reviewDto != null) {
            Review review = new Review();
            review.setReview(reviewDto.getReview());
            review.setAuthor(reviewDto.getAuthor());
            review.setReviewSource(reviewDto.getReviewSource());
            review.setRating(reviewDto.getRating());
            review.setTitle(reviewDto.getTitle());
            review.setProductName(reviewDto.getProductName());
            review.setReviewedDate(LocalDateTime.now());
            return review;
        }
        return null;
    }

}
