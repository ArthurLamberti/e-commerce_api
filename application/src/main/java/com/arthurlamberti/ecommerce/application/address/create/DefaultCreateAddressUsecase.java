package com.arthurlamberti.ecommerce.application.address.create;

import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.address.AddressID;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;

import java.util.Objects;

public class DefaultCreateAddressUsecase extends CreateAddressUsecase{

    private final AddressGateway addressGateway;

    public DefaultCreateAddressUsecase(final  AddressGateway addressGateway) {
        this.addressGateway = Objects.requireNonNull(addressGateway);
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

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Address", notification);
        }

        return CreateAddressOutput.from(this.addressGateway.create(anAddress));
    }
}
