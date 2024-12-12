package com.arthurlamberti.ecommerce.application.shipping.create_by_purchase;

import com.arthurlamberti.ecommerce.domain.shipping.Shipping;

public record CreateShippingOutput(String id) {
    public static CreateShippingOutput from(Shipping shipping) {
        return new CreateShippingOutput(shipping.getId().getValue());
    }
}
