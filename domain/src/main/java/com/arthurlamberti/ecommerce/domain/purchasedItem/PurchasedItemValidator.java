package com.arthurlamberti.ecommerce.domain.purchasedItem;

import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.Validator;

public class PurchasedItemValidator extends Validator {
    protected PurchasedItemValidator(final PurchasedItem purchasedItem, final ValidationHandler aHandler) {
        super(aHandler);
    }

    @Override
    public void validate() {

    }
}
