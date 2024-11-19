package com.arthurlamberti.ecommerce.infrastructure.address.models;

public record CreateAddressRequest(
        String country,
        String state,
        String city,
        String street,
        String zipCode,
        String number,
        String complement
) {
}
