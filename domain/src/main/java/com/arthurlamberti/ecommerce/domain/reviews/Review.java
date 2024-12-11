package com.arthurlamberti.ecommerce.domain.reviews;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.utils.InstantUtils;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Review extends AggregateRoot<ReviewID> {

    private String customerId;
    private String itemId;
    private String description;
    private Double rating;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Review(
            final ReviewID reviewID,
            final String aCustomerId,
            final String itemId,
            final String aDescription,
            final Double aRating,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(reviewID);
        this.customerId = aCustomerId;
        this.itemId = itemId;
        this.description = aDescription;
        this.rating = aRating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;

        selfValidate();
    }

    public static Review with(ReviewID id, String customerId, String itemID, String description, Double rating, Instant createdAt, Instant updatedAt, Instant deletedAt) {
        return new Review(
                id,
                customerId,
                itemID,
                description,
                rating,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new ReviewValidator(this, handler).validate();
    }

    public static Review newReview(
            final String customerId,
            final String itemId,
            final String description,
            final Double rating
    ) {
        final var anId = ReviewID.unique();
        final var now = InstantUtils.now();

        return new Review(anId, customerId, itemId, description, rating, now, now, null);
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create a Review", notification);
    }
}
