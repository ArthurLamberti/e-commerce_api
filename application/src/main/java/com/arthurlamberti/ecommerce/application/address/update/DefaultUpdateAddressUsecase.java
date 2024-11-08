package com.arthurlamberti.ecommerce.application.address.update;

import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.address.AddressID;
import com.arthurlamberti.ecommerce.domain.exceptions.NotFoundException;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;

import static java.util.Objects.requireNonNull;

public non-sealed class DefaultUpdateAddressUsecase
        extends UpdateAddressUsecase {

    private final AddressGateway addressGateway;

    public DefaultUpdateAddressUsecase(final AddressGateway addressGateway) {
        this.addressGateway = requireNonNull(addressGateway);
    }

    @Override
    public UpdateAddressOutput execute(UpdateAddressCommand aCommand) {
        final var anId = AddressID.from(aCommand.anId());
        final var aCountry = aCommand.country();
        final var aState = aCommand.state();
        final var aCity = aCommand.city();
        final var aStreet = aCommand.street();
        final var aZipcode = aCommand.zipcode();
        final var aNumber = aCommand.number();
        final var aComplement = aCommand.complement();

        final var address = this.addressGateway.findById(anId)
                .orElseThrow(() -> NotFoundException.with(Address.class, anId));

        final var notification = Notification.create();
        notification.validate(() -> address.updateAddress(
                aCountry,
                aState,
                aCity,
                aStreet,
                aZipcode,
                aNumber,
                aComplement
        ));

        if (notification.hasError()){
            throw new NotificationException("Could not update Aggregate Address %s".formatted(anId.getValue()),notification);
        }

        return UpdateAddressOutput.from(this.addressGateway.update(address));
    }
}
