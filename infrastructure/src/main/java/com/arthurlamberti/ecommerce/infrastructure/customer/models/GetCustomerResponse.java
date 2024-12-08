package com.arthurlamberti.ecommerce.infrastructure.customer.models;

import com.arthurlamberti.ecommerce.application.customer.retrieve.AddressSummary;

import java.time.Instant;
import java.util.List;

public record GetCustomerResponse (
        String name,
        String email,
        String document,
        boolean active,
        List<AddressSummaryResponse> address,
        Instant createdAt,
        Instant deletedAt
) {
}
