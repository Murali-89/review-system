package com.xyz.reviewsystem.service;

import com.xyz.reviewsystem.entity.Review;
import com.xyz.reviewsystem.model.ReviewDto;
import com.xyz.reviewsystem.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<Review> reviews=null;
        if(productName != null) {
            List<Review> optionalReviews = reviewRepository.findByProductName(productName);
            if (optionalReviews.isEmpty())
                throw new RuntimeException("product name not found");
            return optionalReviews;
        }
        if(rating != null){
            List<Review> optionalReviews = reviewRepository.findByRating(Integer.parseInt(rating));
            if (optionalReviews.isEmpty())
                throw new RuntimeException("No review found with the specified rating");
            return optionalReviews;
        }
        if(title != null){
            List<Review> optionalReviews = reviewRepository.findByTitle(title);
            if (optionalReviews.isEmpty())
                throw new RuntimeException("No review found with the specified title");
            return optionalReviews;
        }

        else {
            reviews = reviewRepository.findAll();
            return reviews;

        }
    }

    @Override
    public List<Object[]> findAverageRatingsPerProduct() {
        return reviewRepository.findAverageRatingsPerProduct();
    }

    @Override
    public List<Object[]> findTotalRatingsPerCategory() {
       return reviewRepository.findTotalRatingsPerCategory();

    }

    private Review mapReviewDtoToReview(ReviewDto reviewDto) {

        if (reviewDto != null) {
            Review review = new Review();
            review.setReview(reviewDto.getReview());
            review.setAuthor(reviewDto.getAuthor());
            review.setReviewSource(reviewDto.getReviewSource());
            review.setRating(reviewDto.getRating());
            review.setTitle(reviewDto.getTitle());
            review.setProductName(reviewDto.getProductName());
            review.setReviewedDate(reviewDto.getReviewedDate());
            return review;
        }
        return null;
    }
}
