package com.arthurlamberti.ecommerce.application.seller.create;

import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.customer.CustomerGateway;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.seller.Seller;
import com.arthurlamberti.ecommerce.domain.seller.SellerGateway;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;

public class DefaultCreateSellerUseCase extends CreateSellerUseCase {

    private final SellerGateway sellerGateway;

    public DefaultCreateSellerUseCase(final SellerGateway sellerGateway) {
        this.sellerGateway = sellerGateway;
    }

    @Override
    public CreateSellerOutput execute(CreateSellerCommand aCommand) {
        final var notification = Notification.create();
        final var aSeller = notification.validate(
                () -> Seller.newSeller(
                        aCommand.name(),
                        aCommand.email(),
                        aCommand.description(),
                        aCommand.document()
                )
        );
        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Customer", notification);
        }
        return CreateSellerOutput.from(this.sellerGateway.create(aSeller));
    }
}
