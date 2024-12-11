package com.arthurlamberti.ecommerce.infrastructure.review.models;

public record GetReviewResponse(
        String id,
        String itemId,
        Double rating
) {
}
