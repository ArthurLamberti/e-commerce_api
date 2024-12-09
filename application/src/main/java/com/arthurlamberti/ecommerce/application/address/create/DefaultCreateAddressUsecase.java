package com.arthurlamberti.ecommerce.application.address.create;

import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.address.AddressID;
import com.arthurlamberti.ecommerce.domain.customer.CustomerGateway;
import com.arthurlamberti.ecommerce.domain.customer.CustomerID;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.seller.SellerGateway;
import com.arthurlamberti.ecommerce.domain.seller.SellerID;
import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;

import java.util.Objects;

public class DefaultCreateAddressUsecase extends CreateAddressUsecase {

    private final AddressGateway addressGateway;
    private final CustomerGateway customerGateway;
    private final SellerGateway sellerGateway;

    public DefaultCreateAddressUsecase(
            final AddressGateway addressGateway,
            final CustomerGateway customerGateway,
            final SellerGateway sellerGateway
    ) {
        this.addressGateway = Objects.requireNonNull(addressGateway);
        this.customerGateway = customerGateway;
        this.sellerGateway = sellerGateway;
    }

    @Override
    public CreateAddressOutput execute(CreateAddressCommand aCommand) {
        final var aCountry = aCommand.country();
        final var aState = aCommand.state();
        final var aCity = aCommand.city();
        final var aStreet = aCommand.street();
        final var aZipcode = aCommand.zipcode();
        final var aNumber = aCommand.number();
        final var aComplement = aCommand.complement();
        final var aCustomerId = aCommand.customerId();
        final var aSellerId = aCommand.sellerId();

        final var notification = Notification.create();
        final var anAddress = notification.validate(
                () -> Address.newAddress(
                        aCountry,
                        aState,
                        aCity,
                        aStreet,
                        aZipcode,
                        aNumber,
                        aComplement,
                        aCustomerId,
                        aSellerId
                )
        );

        final var customer = customerGateway.findById(CustomerID.from(aCustomerId));
        final var seller = sellerGateway.findById(SellerID.from(aSellerId));

        if (customer.isEmpty() && seller.isEmpty()) {
            notification.append(new Error("id must exist"));
        }

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Address", notification);
        }

        return CreateAddressOutput.from(this.addressGateway.create(anAddress));
    }
}
