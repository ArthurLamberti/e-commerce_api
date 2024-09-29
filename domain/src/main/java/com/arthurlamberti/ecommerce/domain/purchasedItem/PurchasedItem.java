package com.arthurlamberti.ecommerce.domain.purchasedItem;

import com.arthurlamberti.ecommerce.domain.item.Item;
import lombok.Getter;

@Getter
public class PurchasedItem {

    private String sellerId;
    private Item item;
    private Integer qty;
    private Double value;

    public PurchasedItem(final String sellerId,
                         final Item item,
                         final Integer qty,
                         final Double value) {
        this.sellerId = sellerId;
        this.item = item;
        this.qty = qty;
        this.value = value;
    }
}
