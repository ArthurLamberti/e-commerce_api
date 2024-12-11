package com.arthurlamberti.ecommerce.application.review.retrieve.get;

import com.arthurlamberti.ecommerce.domain.exceptions.NotFoundException;
import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.item.ItemID;
import com.arthurlamberti.ecommerce.domain.reviews.Review;
import com.arthurlamberti.ecommerce.domain.reviews.ReviewGateway;
import com.arthurlamberti.ecommerce.domain.reviews.ReviewID;

public class DefaultGetReviewUseCase extends GetReviewUseCase {

    private final ReviewGateway reviewGateway;

    public DefaultGetReviewUseCase(
            final ReviewGateway reviewGateway) {
        this.reviewGateway = reviewGateway;
    }

    @Override
    public GetReviewOutput execute(String input) {
        return this.reviewGateway.findById(ReviewID.from(input))
                .map(GetReviewOutput::from)
                .orElseThrow(() -> NotFoundException.with(Review.class, ReviewID.from(input)));
    }
}
