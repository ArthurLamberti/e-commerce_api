package com.arthurlamberti.ecommerce.domain.shipping;

import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.Validator;

import static java.util.Objects.isNull;

public class ShippingValidator extends Validator {
    private final Shipping shipping;
    private final Integer MAX_CODE_LENGTH = 20;
    protected ShippingValidator(final Shipping aShipping, final ValidationHandler aHandler) {
        super(aHandler);
        this.shipping = aShipping;
    }

    @Override
    public void validate() {
        checkAddress();
        checkCode();
        checkPrice();
    }

    private void checkAddress() {
        if (isNull(this.shipping.getAddressId())) {
            this.validationHandler().append(new Error("'item' should not be null"));
            return;
        }
    }

    private void checkCode() {
        if (isNull(this.shipping.getCode())){
            this.validationHandler().append(new Error("'code' should not be null"));
            return;
        }

        if(this.shipping.getCode().isBlank()) {
            this.validationHandler().append(new Error("'code' should not be empty"));
            return;
        }

        if(this.shipping.getCode().trim().length() >= MAX_CODE_LENGTH)
            this.validationHandler().append(new Error("'code' length should be less than 20"));
    }

    private void checkPrice(){
        if (this.shipping.getPrice() <= 0)
            this.validationHandler().append(new Error("'price' should be greater than 0"));
    }
}
