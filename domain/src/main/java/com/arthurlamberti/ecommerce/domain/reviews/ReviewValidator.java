package com.arthurlamberti.ecommerce.domain.reviews;

import com.arthurlamberti.ecommerce.domain.item.Item;
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

    }
}
