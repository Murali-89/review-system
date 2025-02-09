package com.xyz.reviewsystem.repository;

import com.xyz.reviewsystem.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findByProductName(String productName);
    public List<Review> findByRating(Integer rating);
    public List<Review> findByTitle(String title);

    @Query("select r.productName, avg(r.rating) from Review r group by r.productName")
    public List<Object[]> findAverageRatingsPerProduct();

    @Query("select r.rating, count(r.rating) from Review r group by r.rating")
    public List<Object[]> findTotalRatingsPerCategory();

}
