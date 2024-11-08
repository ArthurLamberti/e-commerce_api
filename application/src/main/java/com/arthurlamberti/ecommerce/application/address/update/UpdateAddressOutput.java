package com.arthurlamberti.ecommerce.application.address.update;

import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.address.AddressID;

public record UpdateAddressOutput(String id) {

    public static UpdateAddressOutput from(final AddressID anId) {
        return new UpdateAddressOutput(anId.getValue());
    }

    public static UpdateAddressOutput from(final Address anAddress) {
        return new UpdateAddressOutput(anAddress.getId().getValue());
    }
}
