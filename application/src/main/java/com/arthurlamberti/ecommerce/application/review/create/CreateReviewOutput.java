package com.arthurlamberti.ecommerce.application.review.create;

import com.arthurlamberti.ecommerce.domain.reviews.Review;

public record CreateReviewOutput(String id) {
    public static CreateReviewOutput from(Review aReview) {
        return new CreateReviewOutput(aReview.getId().getValue());
    }
}
