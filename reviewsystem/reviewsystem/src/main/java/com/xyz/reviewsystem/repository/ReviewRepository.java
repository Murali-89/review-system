package com.xyz.reviewsystem.repository;

import com.xyz.reviewsystem.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByStore(String store);
    List<Review> findByRating(Integer rating);
    List<Review> findByTitle(String title);

    @Query("select r.store, avg(r.rating) from Review r group by r.store")
    List<Object[]> findAverageRatingsPerProduct();

    @Query("select r.rating, count(r.rating) from Review r group by r.rating")
    List<Object[]> findTotalRatingsPerCategory();

}
