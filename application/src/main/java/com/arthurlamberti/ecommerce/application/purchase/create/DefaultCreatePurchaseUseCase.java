package com.arthurlamberti.ecommerce.application.purchase.create;

import com.arthurlamberti.ecommerce.application.purchased_item.create.CreatePurchasedItemCommand;
import com.arthurlamberti.ecommerce.application.purchased_item.create.CreatePurchasedItemUseCase;
import com.arthurlamberti.ecommerce.application.shipping.create_by_purchase.CreateShippingByPurchaseCommand;
import com.arthurlamberti.ecommerce.application.shipping.create_by_purchase.CreateShippingUseCase;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.customer.CustomerGateway;
import com.arthurlamberti.ecommerce.domain.customer.CustomerID;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.item.ItemGateway;
import com.arthurlamberti.ecommerce.domain.item.ItemID;
import com.arthurlamberti.ecommerce.domain.purchase.Purchase;
import com.arthurlamberti.ecommerce.domain.purchase.PurchaseGateway;
import com.arthurlamberti.ecommerce.domain.seller.SellerGateway;
import com.arthurlamberti.ecommerce.domain.seller.SellerID;
import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;

import java.util.Optional;

public class DefaultCreatePurchaseUseCase extends CreatePurchaseUseCase {

    private final PurchaseGateway purchaseGateway;
    private final SellerGateway sellerGateway;
    private final CustomerGateway customerGateway;
    private final AddressGateway addressGateway;
    private final ItemGateway itemGateway;

    private final CreateShippingUseCase createShippingUseCase;
    private final CreatePurchasedItemUseCase createPurchasedItemUseCase;

    public DefaultCreatePurchaseUseCase(final PurchaseGateway purchaseGateway, SellerGateway sellerGateway, CustomerGateway customerGateway, AddressGateway addressGateway, ItemGateway itemGateway, CreateShippingUseCase createShippingUseCase, CreatePurchasedItemUseCase createPurchasedItemUseCase) {
        this.purchaseGateway = purchaseGateway;
        this.sellerGateway = sellerGateway;
        this.customerGateway = customerGateway;
        this.addressGateway = addressGateway;
        this.itemGateway = itemGateway;
        this.createShippingUseCase = createShippingUseCase;
        this.createPurchasedItemUseCase = createPurchasedItemUseCase;
    }

    @Override
    public CreatePurchaseOutput execute(CreatePurchaseCommand input) {
        final var notification = Notification.create();
        final var aPurchase = notification.validate(
                () -> Purchase.newPurchase(
                        input.sellerId(),
                        input.customerId(),
                        input.itemsId(),
                        input.totalValue(),
                        input.totalQty(),
                        null,
                        input.addressId()
                )
        );

        final var seller = sellerGateway.findById(SellerID.from(aPurchase.getSellerId()))
                .or(() -> {
                    notification.append(new Error("Seller %s not found".formatted(aPurchase.getSellerId())));
                    return Optional.empty();
                });

        final var customer = customerGateway.findById(CustomerID.from(aPurchase.getCustomerId()))
                .or(() -> {
                    notification.append(new Error("Customer %s not found".formatted(aPurchase.getCustomerId())));
                    return Optional.empty();
                });

        final var items = aPurchase.getItems()
                .stream()
                .map(id -> itemGateway.findById(ItemID.from(id))
                        .or(() -> {
                            notification.append(new Error("Item %s not found".formatted(id)));
                            return Optional.empty();
                        }))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Purchase", notification);
        }

        final var shippingCommand = new CreateShippingByPurchaseCommand(customer.get().getAddressList().get(0), aPurchase);
        final var shipping = createShippingUseCase.execute(shippingCommand);

        //TODO create purchasedItems and send to purchaseGateway
        final var createPurchasedItemsCommand = new CreatePurchasedItemCommand(items, aPurchase);
        final var output = this.purchaseGateway.create(aPurchase, shipping);
        final var res = createPurchasedItemUseCase.execute(createPurchasedItemsCommand);

        return CreatePurchaseOutput.from(output);
    }
}
