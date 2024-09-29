package com.arthurlamberti.ecommerce.domain.shipping;

import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.Validator;

public class ShippingValidator extends Validator {
    protected ShippingValidator(final Shipping aShipping, final ValidationHandler aHandler) {
        super(aHandler);
    }

    @Override
    public void validate() {

    }
}
