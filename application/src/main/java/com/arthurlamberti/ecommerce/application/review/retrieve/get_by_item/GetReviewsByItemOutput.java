package com.arthurlamberti.ecommerce.application.review.retrieve.get_by_item;

import com.arthurlamberti.ecommerce.domain.reviews.Review;

public record GetReviewsByItemOutput(
        String id,
        String itemId,
        Double rating
) {
    public static GetReviewsByItemOutput from(Review review) {
        return new GetReviewsByItemOutput(
                review.getId().getValue(),
                review.getItemId(),
                review.getRating()
        );
    }
}
