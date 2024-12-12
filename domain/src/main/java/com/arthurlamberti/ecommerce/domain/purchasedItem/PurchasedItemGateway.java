package com.arthurlamberti.ecommerce.domain.purchasedItem;

import java.util.List;

public interface PurchasedItemGateway {
    Boolean create(final List<PurchasedItem> aPurchasedItem);
}
