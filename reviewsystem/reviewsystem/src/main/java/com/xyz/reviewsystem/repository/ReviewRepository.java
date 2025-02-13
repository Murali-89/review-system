package com.xyz.reviewsystem.repository;

import com.xyz.reviewsystem.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProductName(String productName);
    List<Review> findByRating(Integer rating);
    List<Review> findByTitle(String title);

    @Query("select r.productName, avg(r.rating) from Review r group by r.productName")
    List<Object[]> findAverageRatingsPerProduct();

    @Query("select r.rating, count(r.rating) from Review r group by r.rating")
    List<Object[]> findTotalRatingsPerCategory();

}
