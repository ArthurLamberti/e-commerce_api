package com.arthurlamberti.ecommerce.application.review.retrieve.get;

import com.arthurlamberti.ecommerce.domain.reviews.Review;

public record GetReviewOutput(
        String id,
        String itemId,
        Double rating
) {
    public static GetReviewOutput from(Review review) {
        return new GetReviewOutput(
                review.getId().getValue(),
                review.getItemId(),
                review.getRating()
        );
    }
}
