package com.arthurlamberti.ecommerce.application.item.retrieve.get;

import com.arthurlamberti.ecommerce.domain.exceptions.NotFoundException;
import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.item.ItemGateway;
import com.arthurlamberti.ecommerce.domain.item.ItemID;

public class DefaultGetItemUseCase extends GetItemUseCase {

    private final ItemGateway itemGateway;

    public DefaultGetItemUseCase(
            final ItemGateway itemGateway
    ) {
        this.itemGateway = itemGateway;
    }

    @Override
    public GetItemOutput execute(String input) {
        return this.itemGateway.findById(ItemID.from(input))
                .map(GetItemOutput::from)
                .orElseThrow(() -> NotFoundException.with(Item.class, ItemID.from(input)));
    }
}
