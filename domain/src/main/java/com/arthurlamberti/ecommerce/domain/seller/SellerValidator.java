package com.arthurlamberti.ecommerce.domain.seller;

import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.Validator;

public class SellerValidator extends Validator {

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;

    public static final int DESCRIPTION_MAX_LENGTH = 3000;
    
    private final Seller seller;
    
    protected SellerValidator(final Seller aSeller, final ValidationHandler aHandler) {
        super(aHandler);
        this.seller = aSeller;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkDescriptionConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.seller.getName();

        if(name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        if (name.trim().length() < NAME_MIN_LENGTH || name.trim().length() > NAME_MAX_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between %s and %s characters".formatted(NAME_MIN_LENGTH, NAME_MAX_LENGTH)));
        }
    }

    private void checkDescriptionConstraints() {
        final var description = this.seller.getDescription();
        if (description == null) {
            return;
        }

        if (description.trim().length() > DESCRIPTION_MAX_LENGTH) {
            this.validationHandler().append(new Error("'description' must contains a maximum of %s characters".formatted(DESCRIPTION_MAX_LENGTH)));
        }

    }
}