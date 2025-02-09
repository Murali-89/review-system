package com.xyz.reviewsystem.controller;

import com.xyz.reviewsystem.entity.Review;
import com.xyz.reviewsystem.model.ReviewDto;
import com.xyz.reviewsystem.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    Logger log = LoggerFactory.getLogger(ReviewController.class);

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping
    public ResponseEntity<String>  addReview(@RequestBody ReviewDto reviewDto){
        log.info("ReviewDto {}", reviewDto);
        reviewService.addReview(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review added successfully");

    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam(required = false) String productName,
                                                  @RequestParam(required = false) String rating,
                                                   @RequestParam(required = false) String reviewDate){

        log.info("productName {}, rating {}, reviewDate {}", productName, rating, reviewDate);

        return ResponseEntity.ok(reviewService.getReviews(productName,rating,reviewDate));

    }

    @GetMapping("/average-ratings")
    public ResponseEntity<List<Object[]>> getAverageRatingsPerProduct(){

        return ResponseEntity.ok(reviewService.findAverageRatingsPerProduct());
    }

    @GetMapping("/total-ratings")
    public ResponseEntity<List<Object[]>> getTotalRatingsPerCategory(){

        return ResponseEntity.ok(reviewService.findTotalRatingsPerCategory());


    }

}
