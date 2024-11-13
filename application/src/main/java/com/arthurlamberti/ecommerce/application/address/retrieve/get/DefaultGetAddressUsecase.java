package com.arthurlamberti.ecommerce.application.address.retrieve.get;

import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.address.AddressID;
import com.arthurlamberti.ecommerce.domain.exceptions.NotFoundException;

import static java.util.Objects.requireNonNull;

public class DefaultGetAddressUsecase extends GetAddressUsecase {

    private final AddressGateway addressGateway;

    public DefaultGetAddressUsecase(final AddressGateway addressGateway) {
        this.addressGateway = requireNonNull(addressGateway);
    }

    @Override
    public AddressOutput execute(String input) {
        return this.addressGateway.findById(AddressID.from(input))
                .map(AddressOutput::from)
                .orElseThrow(()-> NotFoundException.with(Address.class, AddressID.from(input)));
    }
}
