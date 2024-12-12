package com.arthurlamberti.ecommerce.application.shipping.create_by_purchase;

import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.address.AddressID;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.purchase.PurchaseGateway;
import com.arthurlamberti.ecommerce.domain.purchase.PurchaseID;
import com.arthurlamberti.ecommerce.domain.shipping.Shipping;
import com.arthurlamberti.ecommerce.domain.shipping.ShippingGateway;
import com.arthurlamberti.ecommerce.domain.shipping.ShippingID;
import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;

import java.util.Random;

public class DefaultCreateShippingUseCase extends CreateShippingUseCase {

    private final ShippingGateway shippingGateway;
    private final AddressGateway addressGateway;
    private final PurchaseGateway purchaseGateway;

    public DefaultCreateShippingUseCase(ShippingGateway shippingGateway, AddressGateway addressGateway, PurchaseGateway purchaseGateway) {
        this.shippingGateway = shippingGateway;
        this.addressGateway = addressGateway;
        this.purchaseGateway = purchaseGateway;
    }

    @Override
    public Shipping execute(CreateShippingByPurchaseCommand input) {
        final var notification = Notification.create();
        final var code = ShippingID.unique().getValue().toUpperCase().substring(0,10);
        final var price = new Random().nextDouble(10D, 90D);

        final var aShipping = notification.validate(
                () -> Shipping.newShipping(
                        input.address().getId().getValue(),
                        input.purchase().getId().getValue(),
                        code,
                        price
                )
        );

        final var address = this.addressGateway.findById(AddressID.from(aShipping.getAddressId()));
        if (address.isEmpty()) {
            notification.append(new Error("Address %s does not exist".formatted(aShipping.getAddressId())));
        }

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Shipping", notification);
        }

        return this.shippingGateway.create(aShipping);
    }
}
