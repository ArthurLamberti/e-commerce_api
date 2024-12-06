package com.arthurlamberti.ecommerce.application.customer.retrieve.list;

import com.arthurlamberti.ecommerce.domain.customer.Customer;

import java.time.Instant;
import java.util.List;

public record ListCustomerOutput(
        String name,
        String email,
        String document,
        boolean active,
        Instant createdAt,
        Instant deletedAt
) {
    public static ListCustomerOutput from(Customer customer) {
        return new ListCustomerOutput(
                customer.getName(),
                customer.getEmail(),
                customer.getDocument(),
                customer.isActive(),
                customer.getCreatedAt(),
                customer.getDeletedAt()
        );
    }
}
