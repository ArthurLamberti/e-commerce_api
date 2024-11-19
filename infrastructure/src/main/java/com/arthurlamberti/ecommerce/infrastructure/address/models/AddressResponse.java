package com.arthurlamberti.ecommerce.infrastructure.address.models;

import java.time.Instant;

public record AddressResponse (
        String id,
        String country,
        String state,
        String city,
        String street,
        String zipCode,
        String number,
        String complement,
        boolean active,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {
}
