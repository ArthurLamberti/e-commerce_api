package com.arthurlamberti.ecommerce.application.review.create;

import com.arthurlamberti.ecommerce.domain.customer.CustomerGateway;
import com.arthurlamberti.ecommerce.domain.customer.CustomerID;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.item.ItemGateway;
import com.arthurlamberti.ecommerce.domain.item.ItemID;
import com.arthurlamberti.ecommerce.domain.reviews.Review;
import com.arthurlamberti.ecommerce.domain.reviews.ReviewGateway;
import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;

public class DefaultCreateReviewUseCase extends CreateReviewUseCase {

    private ReviewGateway reviewGateway;
    private CustomerGateway customerGateway;
    private ItemGateway itemGateway;

    public DefaultCreateReviewUseCase(
            final ReviewGateway reviewGateway,
            final CustomerGateway customerGateway,
            final ItemGateway itemGateway
    ) {
        this.reviewGateway = reviewGateway;
        this.customerGateway = customerGateway;
        this.itemGateway = itemGateway;
    }

    @Override
    public CreateReviewOutput execute(CreateReviewCommand input) {
        final var notification = Notification.create();
        final var aReview = notification.validate(
                () -> Review.newReview(
                        input.customerId(),
                        input.itemId(),
                        input.description(),
                        input.rating()
                )
        );
        final var customer = customerGateway.findById(CustomerID.from(aReview.getCustomerId()));
        if (customer.isEmpty()) {
            notification.append(new Error("Customer %s not found".formatted(aReview.getCustomerId())));
        }

        final var item = itemGateway.findById(ItemID.from(input.itemId()));
        if (item.isEmpty()) {
            notification.append(new Error("Item %s not found".formatted(aReview.getItemId())));
        }

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Review", notification);
        }

        return CreateReviewOutput.from(this.reviewGateway.create(aReview));
    }
}
