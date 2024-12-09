package com.arthurlamberti.ecommerce.domain.item;

import com.arthurlamberti.ecommerce.domain.seller.Seller;
import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.Validator;

import static java.util.Objects.isNull;

public class ItemValidator extends Validator {
    public static final int NAME_MAX_LENGTH = 100;
    public static final int NAME_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MAX_LENGTH = 1000;
    public static final int DESCRIPTION_MIN_LENGTH = 50;

    private final Item item;

    protected ItemValidator(final Item anItem, ValidationHandler aHandler) {
        super(aHandler);
        this.item = anItem;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkDescriptionConstraints();
        checkImageUrlConstraints();
        checkSellerConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.item.getName();

        if (name == null) {
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
        final var description = this.item.getDescription();
        if (description == null) {
            this.validationHandler().append(new Error("'description' should not be null"));
            return;
        }

        if (description.isBlank()) {
            this.validationHandler().append(new Error("'description' should not be empty"));
            return;
        }

        if (description.trim().length() < DESCRIPTION_MIN_LENGTH || description.trim().length() > DESCRIPTION_MAX_LENGTH) {
            this.validationHandler().append(new Error("'description' must be between %s and %s characters".formatted(DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH)));
        }
    }

    private void checkImageUrlConstraints() {
        final var imageUrl = this.item.getImageUrl();
        if (imageUrl == null) {
            this.validationHandler().append(new Error("'image' should not be null"));
            return;
        }

        if (imageUrl.isBlank()) {
            this.validationHandler().append(new Error("'image' should not be empty"));
        }
    }

    private void checkSellerConstraints() {
//        final var seller = this.item.getSeller();
//        if (isNull(seller)) {
//            this.validationHandler().append(new Error("'seller' should not be null"));
//        }
    }
}
