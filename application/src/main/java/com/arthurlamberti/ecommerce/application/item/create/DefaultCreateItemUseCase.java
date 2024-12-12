package com.arthurlamberti.ecommerce.application.item.create;

import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.item.ItemGateway;
import com.arthurlamberti.ecommerce.domain.seller.SellerGateway;
import com.arthurlamberti.ecommerce.domain.seller.SellerID;
import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;

public class DefaultCreateItemUseCase extends CreateItemUseCase {

    final ItemGateway itemGateway;
    final SellerGateway sellerGateway;

    public DefaultCreateItemUseCase(
            final ItemGateway itemGateway,
            final SellerGateway sellerGateway
    ) {
        this.itemGateway = itemGateway;
        this.sellerGateway = sellerGateway;
    }

    @Override
    public CreateItemOutput execute(CreateItemCommand aCommand) {
        final var notification = Notification.create();
        final var anItem = notification.validate(
                () -> Item.newItem(
                        aCommand.sellerId(),
                        aCommand.name(),
                        aCommand.description(),
                        aCommand.imageUrl(),
                        10D,
                        aCommand.stock()
                )
        );

        final var seller = sellerGateway.findById(SellerID.from(aCommand.sellerId()));

        if (seller.isEmpty()) {
            notification.append(new Error("Seller '%s' not exist".formatted(aCommand.sellerId())));
        }

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Seller", notification);
        }

        return CreateItemOutput.from(this.itemGateway.create(anItem));
    }
}
