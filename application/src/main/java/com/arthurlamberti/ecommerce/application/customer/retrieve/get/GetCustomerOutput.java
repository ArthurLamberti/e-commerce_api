package com.arthurlamberti.ecommerce.application.customer.retrieve.get;

import com.arthurlamberti.ecommerce.application.customer.retrieve.AddressSummary;
import com.arthurlamberti.ecommerce.domain.customer.Customer;

import java.time.Instant;
import java.util.List;

public record GetCustomerOutput(
        String name,
        String email,
        String document,
        boolean active,
        List<AddressSummary> address,
        Instant createdAt,
        Instant deletedAt
) {
    public static GetCustomerOutput from(Customer customer) {
        final var addressList = customer.getAddressList()
                .stream()
                .map(AddressSummary::from)
                .toList();
        return new GetCustomerOutput(
                customer.getName(),
                customer.getEmail(),
                customer.getDocument(),
                customer.isActive(),
                addressList,
                customer.getCreatedAt(),
                customer.getDeletedAt()
        );
    }
}
