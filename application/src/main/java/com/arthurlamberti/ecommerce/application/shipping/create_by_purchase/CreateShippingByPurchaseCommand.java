package com.arthurlamberti.ecommerce.application.shipping.create_by_purchase;

import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.purchase.Purchase;

public record CreateShippingByPurchaseCommand(
        Address address,
        Purchase purchase
) {
}
