package com.arthurlamberti.ecommerce.domain.customer;

import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.Validator;

import static java.util.Objects.isNull;

public class CustomerValidator extends Validator {


    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;

    private final Customer aCustommer;

    protected CustomerValidator(final Customer aCustommer, final ValidationHandler aHandler) {
        super(aHandler);
        this.aCustommer = aCustommer;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkEmailConstraints();
        checkDocumentConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.aCustommer.getName();

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

    private void checkEmailConstraints() {
        final var email = this.aCustommer.getEmail();

        if (isNull(email)) {
            this.validationHandler().append(new Error("'email' should not be null"));
            return;
        }

        if (email.isBlank()) {
            this.validationHandler().append(new Error("'email' should not be empty"));
        }
    }

    private void checkDocumentConstraints() {
        final var document = this.aCustommer.getDocument();

        if (isNull(document)) {
            this.validationHandler().append(new Error("'document' should not be null"));
            return;
        }

        if (document.isBlank()) {
            this.validationHandler().append(new Error("'document' should not be empty"));
        }
    }
}
