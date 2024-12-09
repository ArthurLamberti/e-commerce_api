package com.arthurlamberti.ecommerce.domain.reviews;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Review extends AggregateRoot<ReviewID> {

    private Customer customer;
    private String description;
    private Double rating;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Review(
            final ReviewID reviewID,
            final Customer aCustomer,
            final String aDescription,
            final Double aRating,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(reviewID);
        this.customer = aCustomer;
        this.description = aDescription;
        this.rating = aRating;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;

        selfValidate();
    }

    @Override
    public void validate(ValidationHandler handler) {

    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create a Review", notification);
    }
}
