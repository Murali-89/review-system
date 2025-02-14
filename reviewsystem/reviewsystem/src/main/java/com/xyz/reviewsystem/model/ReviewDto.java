package com.xyz.reviewsystem.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class ReviewDto {

    @NotBlank(message = "Review content cannot be blank")
    private String review;
    @NotBlank(message = "author content cannot be blank")
    private String author;

    @NotBlank(message = "reviewSource content cannot be blank")
    private String reviewSource;

    @Min(1) @Max(5)
    private int rating;

    @NotBlank(message = "title content cannot be blank")
    private String title;
    @NotBlank(message = "store content cannot be blank")
    private String store;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReviewSource() {
        return reviewSource;
    }

    public void setReviewSource(String reviewSource) {
        this.reviewSource = reviewSource;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }
}
