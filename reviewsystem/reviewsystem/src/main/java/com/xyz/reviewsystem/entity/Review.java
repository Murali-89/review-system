package com.xyz.reviewsystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String review;
    private String author;

    private String reviewSource;

    private int rating;

    private String title;

    private String store;
    @Column(name = "reviewed_date")
    private LocalDateTime reviewedDate;

    public Review(Long id, String review, String author, String reviewSource, int rating, String title, String store, LocalDateTime reviewedDate) {
        this.id = id;
        this.review = review;
        this.author = author;
        this.reviewSource = reviewSource;
        this.rating = rating;
        this.title = title;
        this.store = store;
        this.reviewedDate = reviewedDate;
    }

    public Review() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getReviewedDate() {
        return reviewedDate;
    }

    public void setReviewedDate(LocalDateTime reviewedDate) {
        this.reviewedDate = reviewedDate;
    }
}
