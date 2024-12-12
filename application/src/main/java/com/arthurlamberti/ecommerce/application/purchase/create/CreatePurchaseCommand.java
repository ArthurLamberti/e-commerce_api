package com.arthurlamberti.ecommerce.application.purchase.create;

import java.util.List;

public record CreatePurchaseCommand(
        String sellerId,
        String customerId,
        List<String> itemsId,
        Double totalValue,
        Integer totalQty,
        String addressId
) {
    public static CreatePurchaseCommand with(
            String sellerId,
            String customerId,
            List<String> itemsId,
            Double totalValue,
            Integer totalQty,
            String addressId
    ) {
        return new CreatePurchaseCommand(sellerId, customerId, itemsId, totalValue, totalQty, addressId);
    }
}
