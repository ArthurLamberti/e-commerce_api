package com.arthurlamberti.ecommerce.domain.address;

import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.Validator;

public class AddressValidator extends Validator {
    private final Address anAddress;

    protected AddressValidator(final Address anAddress, final ValidationHandler aHandler) {
        super(aHandler);
        this.anAddress = anAddress;
    }

    @Override
    public void validate() {

    }
}
