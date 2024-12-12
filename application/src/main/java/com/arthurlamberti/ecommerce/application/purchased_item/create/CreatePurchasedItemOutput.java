package com.arthurlamberti.ecommerce.application.purchased_item.create;

import com.arthurlamberti.ecommerce.application.purchase.create.CreatePurchaseOutput;
import com.arthurlamberti.ecommerce.domain.purchasedItem.PurchasedItem;

import java.util.List;

public record CreatePurchasedItemOutput(Boolean ok) {
    public static CreatePurchasedItemOutput ok(List<PurchasedItem> items) {
        return new CreatePurchasedItemOutput(Boolean.TRUE);
    }
}
