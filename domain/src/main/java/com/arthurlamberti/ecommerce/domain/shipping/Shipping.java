package com.arthurlamberti.ecommerce.domain.shipping;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;

public class Shipping extends AggregateRoot<ShippingID> {

    protected Shipping(final ShippingID shippingID) {
        super(shippingID);
    }

    @Override
    public void validate(ValidationHandler handler) {

    }
}
