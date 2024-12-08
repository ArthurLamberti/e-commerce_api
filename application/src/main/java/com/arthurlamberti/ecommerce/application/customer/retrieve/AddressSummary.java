package com.arthurlamberti.ecommerce.application.customer.retrieve;

import com.arthurlamberti.ecommerce.domain.address.Address;

public record AddressSummary(
        String country,
        String state,
        String city,
        String street,
        String zipcode,
        String number,
        String complement,
        boolean isActive
) {
    public static AddressSummary from(final Address address) {
        return new AddressSummary(
                address.getCountry(),
                address.getState(),
                address.getCity(),
                address.getStreet(),
                address.getZipCode(),
                address.getNumeral(),
                address.getComplement(),
                address.isActive()
        );
    }
}
