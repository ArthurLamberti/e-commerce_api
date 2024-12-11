package com.arthurlamberti.ecommerce.infrastructure.review.persistence;

import com.arthurlamberti.ecommerce.domain.reviews.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewJpaEntity, String> {
    List<ReviewJpaEntity> findAllByItemId(String item);
}
