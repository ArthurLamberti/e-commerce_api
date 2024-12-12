package com.arthurlamberti.ecommerce.application.purchase.create;

import com.arthurlamberti.ecommerce.domain.purchase.Purchase;

public record CreatePurchaseOutput(String id) {
    public static CreatePurchaseOutput from(Purchase aPurchase) {
        return new CreatePurchaseOutput(aPurchase.getId().getValue());
    }
}
