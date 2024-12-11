package com.arthurlamberti.ecommerce.infrastructure.review.models;

import com.arthurlamberti.ecommerce.application.review.retrieve.get_by_item.GetReviewsByItemOutput;

import java.util.List;

public record GetReviewByItemResponse(
        String itemId,
        Double averageRating,
        Integer amountReviews,
        List<GetReviewResponse> reviews
) {
}
