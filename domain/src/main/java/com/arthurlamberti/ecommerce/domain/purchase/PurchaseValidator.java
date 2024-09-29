package com.arthurlamberti.ecommerce.domain.purchase;

import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.Validator;

public class PurchaseValidator extends Validator {

    protected PurchaseValidator(final Purchase purchase, final ValidationHandler aHandler) {
        super(aHandler);
    }

    @Override
    public void validate() {

    }
}
