package com.arthurlamberti.ecommerce.infrastructure.purchase.models;

import java.util.List;

public record CreatePurchaseRequest(

        String sellerId,
        String customerId,
        List<String> itemsId,
        Double totalValue,
        Integer totalQty,
        String addressId
) {
}
