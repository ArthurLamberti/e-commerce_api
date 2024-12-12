package com.arthurlamberti.ecommerce.application.purchased_item.create;

import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.purchase.Purchase;

import java.util.List;

public record CreatePurchasedItemCommand (

        List<Item> items,
        Purchase aPurchase) {
}
