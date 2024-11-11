package com.arthurlamberti.ecommerce.application.address.retrieve.list;

import com.arthurlamberti.ecommerce.domain.address.Address;

import java.time.Instant;

public record ListAddressOutput(
        String id,
        String country,
        String state,
        String city,
        String street,
        String zipcode,
        String number,
        String complement,
        Instant createdAt,
        Instant updatedAt
) {
    public static ListAddressOutput from(Address anAddress) {
        return new ListAddressOutput(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                anAddress.getState(),
                anAddress.getCity(),
                anAddress.getStreet(),
                anAddress.getZipCode(),
                anAddress.getNumber(),
                anAddress.getComplement(),
                anAddress.getCreatedAt(),
                anAddress.getUpdatedAt()
        );
    }
}
