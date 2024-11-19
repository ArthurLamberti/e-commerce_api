package com.arthurlamberti.ecommerce.application.address.delete;

import com.arthurlamberti.ecommerce.application.address.update.DefaultUpdateAddressUsecase;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.address.AddressID;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class DefaultDeleteAddressUsecase extends DeleteAddressUsecase {

    private final AddressGateway addressGateway;

    public DefaultDeleteAddressUsecase(final AddressGateway addressGateway) {
        this.addressGateway = requireNonNull(addressGateway);
    }

    @Override
    public void execute(final String anId) {
        this.addressGateway.deleteById(AddressID.from(anId));
    }
}
