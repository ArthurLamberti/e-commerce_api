package com.arthurlamberti.ecommerce.domain.purchase;

import com.arthurlamberti.ecommerce.domain.shipping.Shipping;

import java.util.Optional;

public interface PurchaseGateway {


    Purchase create(final Purchase aPurchase, Shipping shipping);

    Optional<Purchase> findById(final PurchaseID anId);
}
