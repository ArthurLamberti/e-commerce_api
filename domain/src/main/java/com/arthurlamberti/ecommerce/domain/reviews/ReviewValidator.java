package com.arthurlamberti.ecommerce.domain.reviews;

import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.Validator;

public class ReviewValidator extends Validator {

    private final Review review;

    protected ReviewValidator(final Review aReview, ValidationHandler aHandler) {
        super(aHandler);
        this.review = aReview;
    }

    @Override
    public void validate() {
        checkCustomerIdConstraints();
        checkItemIdConstraints();
        checkDescriptionConstraints();
        checkRatingConstraints();
    }

    public void checkCustomerIdConstraints() {
        final var customerId = review.getCustomerId();

        if (customerId == null) {
            this.validationHandler().append(new Error("customerId should not be null"));
            return;
        }

        if (customerId.isBlank()) {
            this.validationHandler().append(new Error("customerId should not be empty"));
        }
    }

    public void checkItemIdConstraints() {
        final var itemId = review.getItemId();
        if (itemId == null) {
            this.validationHandler().append(new Error("itemId should not be null"));
            return;
        }
        if (itemId.isBlank()) {
            this.validationHandler().append(new Error("itemId should not be empty"));
        }
    }

    public void checkDescriptionConstraints() {
        final var description = review.getDescription();
        if (description == null) {
            this.validationHandler().append(new Error("description should not be null"));
            return;
        }
        if (description.isBlank()) {
            this.validationHandler().append(new Error("description should not be empty"));
        }
    }

    public void checkRatingConstraints() {
        final var rating = review.getRating();
        if (rating == null) {
            this.validationHandler().append(new Error("rating should not be null"));
            return;
        }
        if (rating.isInfinite() || rating.isNaN()) {
            this.validationHandler().append(new Error("rating should not be infinite or NaN"));
            return;
        }
        if (rating < 0 || rating > 5) {
            this.validationHandler().append(new Error("rating should be between 0 and 5"));
        }
    }
}
