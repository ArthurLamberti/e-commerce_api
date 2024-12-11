package com.arthurlamberti.ecommerce.infrastructure.review.models;

public record CreateReviewRequest(
        String customerId,
        String description,
        Double rating
) {
}
