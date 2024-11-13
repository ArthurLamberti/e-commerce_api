package com.arthurlamberti.ecommerce.application.address.retrieve.get;

import com.arthurlamberti.ecommerce.domain.address.Address;

import java.time.Instant;

public record AddressOutput(
        String id,
        String country,
        String state,
        String city,
        String street,
        String zipcode,
        String number,
        String complement,
        boolean isActive,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {
    public static AddressOutput from(Address anAddress) {
        return new AddressOutput(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                anAddress.getState(),
                anAddress.getCity(),
                anAddress.getStreet(),
                anAddress.getZipCode(),
                anAddress.getNumber(),
                anAddress.getComplement(),
                anAddress.isActive(),
                anAddress.getCreatedAt(),
                anAddress.getUpdatedAt(),
                anAddress.getDeletedAt()
        );
    }
}
