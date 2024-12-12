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
//        checkItem();
//        checkValue();
//        checkSeller();
//        checkQty();
    }

    private void checkItem() {
//        if (isNull(this.purchasedItem.getItem())) {
//            this.validationHandler().append(new Error("'item' should not be null"));
//            return;
//        }
    }

    private void checkValue() {
        if (this.purchasedItem.getValue() <= 0) {
            this.validationHandler().append(new Error("'value' should be greater than 0"));
            return;
        }
    }

    private void checkSeller() {
//        if (isNull(this.purchasedItem.getSellerId())) {
//            this.validationHandler().append(new Error("'sellerId' should not be null"));
//            return;
//        }
//
//        if (this.purchasedItem.getSellerId().isBlank()) {
//            this.validationHandler().append(new Error("'sellerId' should not be empty"));
//        }
    }

    private void checkQty() {
        if (this.purchasedItem.getQty() <= 0) {
            this.validationHandler().append(new Error("'qty' should be greater than 0"));
        }
    }

}
