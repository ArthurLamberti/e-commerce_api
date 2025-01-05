package com.arthurlamberti.ecommerce.domain.purchase;

import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.Validator;

import static java.util.Objects.isNull;

public class PurchaseValidator extends Validator {

    private final Purchase purchase;

    protected PurchaseValidator(final Purchase purchase, final ValidationHandler aHandler) {
        super(aHandler);
        this.purchase = purchase;
    }

    @Override
    public void validate() {
        checkSeller();
        checkCustomer();
        checkItems();
        checkValue();
        checkQty();
        checkShipping();
    }

    public void checkSeller() {
        if (isNull(this.purchase.getSellerId()))
            this.validationHandler().append(new Error("'seller' should not be null"));
    }

    public void checkCustomer() {
        if (isNull(this.purchase.getCustomerId()))
            this.validationHandler().append(new Error("'customer' should not be null"));
    }

    public void checkItems() {
        if (isNull(this.purchase.getItems())) {
            this.validationHandler().append(new Error("'items' should not be null"));
            return;
        }
        if (this.purchase.getItems().isEmpty())
            this.validationHandler().append(new Error("'items' should has at least one item"));

    }

    public void checkValue() {
        if (this.purchase.getTotalValue() <= 0)
            this.validationHandler().append(new Error("'totalValue' should be greater than 0"));
    }

    public void checkQty() {
        if (this.purchase.getTotalQty() <= 0)
            this.validationHandler().append(new Error("'totalQty' should be greater than 0"));
    }

    public void checkShipping() {
        if (isNull(this.purchase.getShipping()))
            this.validationHandler().append(new Error("'shippingId' should not be null"));
    }
}
