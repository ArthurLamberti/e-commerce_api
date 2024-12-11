package com.arthurlamberti.ecommerce.application.review.create;

public record CreateReviewCommand (
        String customerId,
        String itemId,
        String description,
        Double rating
) {
    public static CreateReviewCommand with(String itemId, String customerId, String description, Double rating) {
        return new CreateReviewCommand(
                customerId,
                itemId,
                description,
                rating
        );
    }
}
