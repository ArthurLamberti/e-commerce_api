package com.arthurlamberti.ecommerce.application.purchased_item.create;

import com.arthurlamberti.ecommerce.domain.item.ItemGateway;
import com.arthurlamberti.ecommerce.domain.purchase.PurchaseGateway;
import com.arthurlamberti.ecommerce.domain.purchasedItem.PurchasedItem;
import com.arthurlamberti.ecommerce.domain.purchasedItem.PurchasedItemGateway;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;

import java.util.List;

public class DefaultCreatePurchasedItemUseCase extends CreatePurchasedItemUseCase {

    private final PurchasedItemGateway purchasedItemGateway;

    public DefaultCreatePurchasedItemUseCase(PurchasedItemGateway purchasedItemGateway) {
        this.purchasedItemGateway = purchasedItemGateway;
    }

    @Override
    public CreatePurchasedItemOutput execute(CreatePurchasedItemCommand input) {
        final var notification = Notification.create();
        final List<PurchasedItem> items = input.items()
                .stream()
                .map(item -> notification.validate(
                        () -> PurchasedItem.newPurchasedItem(
                                input.aPurchase().getId().getValue(),
                                item.getId().getValue(),
                                1,
                                item.getPrice()
                        )
                ))
                .toList();

        final var output = purchasedItemGateway.create(items);
//        final var output = items
//                .stream()
//                .map( item -> purchasedItemGateway.create()
//                )
//                .map(PurchasedItem::getItemId)
//                .toList();

        return CreatePurchasedItemOutput.ok(null);
    }
}
