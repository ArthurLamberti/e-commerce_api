package com.arthurlamberti.ecommerce.application.address.create;

import com.arthurlamberti.ecommerce.domain.address.Address;

public record CreateAddressOutput (String id) {
    public static CreateAddressOutput from(final Address anAddress) {
        return new CreateAddressOutput(anAddress.getId().getValue());
    }
}
