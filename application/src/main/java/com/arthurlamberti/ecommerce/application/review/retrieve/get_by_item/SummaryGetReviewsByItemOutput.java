package com.arthurlamberti.ecommerce.application.review.retrieve.get_by_item;

import java.util.List;

public record SummaryGetReviewsByItemOutput (
        String itemId,
        Double averageRating,
        Integer amountReviews,
        List<GetReviewsByItemOutput> reviews
){
    public static SummaryGetReviewsByItemOutput from(
            final String itemId,
            final Double rating,
            final Integer amountReviews,
            final List<GetReviewsByItemOutput> reviews
    ) {
        return new SummaryGetReviewsByItemOutput(
                itemId,
                rating,
                amountReviews,
                reviews
        );
    }
}
