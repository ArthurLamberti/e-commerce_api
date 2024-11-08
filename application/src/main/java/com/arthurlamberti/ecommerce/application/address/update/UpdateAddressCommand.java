package com.arthurlamberti.ecommerce.application.address.update;

import com.arthurlamberti.ecommerce.domain.address.AddressID;

public record UpdateAddressCommand(
        String anId,
        String country,
        String state,
        String city,
        String street,
        String zipcode,
        String number,
        String complement
) {

    public static UpdateAddressCommand with(
            final String anId,
            final String country,
            final String state,
            final String city,
            final String street,
            final String zipcode,
            final String number,
            final String complement
    ) {
        return new UpdateAddressCommand(anId,country,state,city,street,zipcode,number,complement);
    }
}
