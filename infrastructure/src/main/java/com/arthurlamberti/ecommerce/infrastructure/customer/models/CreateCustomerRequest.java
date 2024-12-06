package com.arthurlamberti.ecommerce.infrastructure.customer.models;

public record CreateCustomerRequest (
        String name,
        String email,
        String document
) {
}
