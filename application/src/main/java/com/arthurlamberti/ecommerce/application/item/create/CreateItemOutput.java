package com.arthurlamberti.ecommerce.application.item.create;

import com.arthurlamberti.ecommerce.domain.item.Item;

public record CreateItemOutput(
        String id,
        Integer stock
) {

    public static CreateItemOutput from(Item item) {
        return new CreateItemOutput(
                item.getId().getValue(),
                item.getQtyAvailable()
        );
    }

}
