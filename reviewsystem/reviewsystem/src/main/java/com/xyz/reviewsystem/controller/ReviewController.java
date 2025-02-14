package com.xyz.reviewsystem.controller;

import com.xyz.reviewsystem.entity.Review;
import com.xyz.reviewsystem.model.ReviewDto;
import com.xyz.reviewsystem.service.ReviewService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    Logger log = LoggerFactory.getLogger(ReviewController.class);

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Map<String, String>>  addReview(@Valid @RequestBody ReviewDto reviewDto){
        log.info("ReviewDto {}", reviewDto);
        reviewService.addReview(reviewDto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Review added successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Review>> getReviews(@RequestParam(required = false) String store,
                                                  @RequestParam(required = false) String rating,
                                                   @RequestParam(required = false) String reviewDate){

        log.info("store {}, rating {}, reviewDate {}", store, rating, reviewDate);

        return ResponseEntity.ok(reviewService.getReviews(store,rating,reviewDate));

    }

    @GetMapping("/total-ratings")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Map<Integer, Integer>> getTotalRatingsPerCategory(){

        return ResponseEntity.ok(reviewService.findTotalRatingsPerCategory());

    }

    @GetMapping("/average-rating")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Map<String, Double> > getAverageMonthlyRating() {

        return ResponseEntity.ok(reviewService.getAverageMonthlyRating());
    }


}
