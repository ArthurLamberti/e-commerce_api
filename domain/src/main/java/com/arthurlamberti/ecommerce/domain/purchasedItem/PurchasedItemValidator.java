package com.arthurlamberti.ecommerce.domain.purchasedItem;

import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.Validator;

import static java.util.Objects.isNull;

public class PurchasedItemValidator extends Validator {

    private final PurchasedItem purchasedItem;

    protected PurchasedItemValidator(final PurchasedItem purchasedItem, final ValidationHandler aHandler) {
        super(aHandler);
        this.purchasedItem = purchasedItem;
    }

    @Override
    public void validate() {
        checkItem();
        checkValue();
        checkQty();
        checkPurchaseId();
    }

    private void checkItem() {
        if (isNull(this.purchasedItem.getItemId())) {
            this.validationHandler().append(new Error("'item' should not be null"));
        }
    }

    private void checkValue() {
        if (this.purchasedItem.getValue() <= 0) {
            this.validationHandler().append(new Error("'value' should be greater than 0"));
        }
    }

    private void checkQty() {
        if (this.purchasedItem.getQty() <= 0) {
            this.validationHandler().append(new Error("'qty' should be greater than 0"));
        }
    }

    private void checkPurchaseId() {
        if (isNull(this.purchasedItem.getPurchaseId())) {
            this.validationHandler().append(new Error("'purchaseId' should not be null"));
            return;
        }

        if (this.purchasedItem.getPurchaseId().isBlank()) {
            this.validationHandler().append(new Error("'purchaseId' should not be empty"));
        }
    }

}
