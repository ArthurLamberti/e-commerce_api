package com.arthurlamberti.ecommerce.application.review.retrieve.get_by_item;

import com.arthurlamberti.ecommerce.domain.item.ItemGateway;
import com.arthurlamberti.ecommerce.domain.reviews.Review;
import com.arthurlamberti.ecommerce.domain.reviews.ReviewGateway;

public class DefaultGetReviewsByItemUseCase extends GetReviewsByItemUseCase {

    private final ReviewGateway reviewGateway;

    public DefaultGetReviewsByItemUseCase(
            final ReviewGateway reviewGateway) {
        this.reviewGateway = reviewGateway;
    }

    @Override
    public SummaryGetReviewsByItemOutput execute(String itemId) {
        final var reviews = reviewGateway.findAllByItem(itemId);
        final var reviewOutput = reviews
                .stream()
                .map(GetReviewsByItemOutput::from)
                .toList();

        final var average = reviews
                .stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0);

        return SummaryGetReviewsByItemOutput.from(
                itemId,
                average,
                reviews.size(),
                reviewOutput
        );
    }
}
