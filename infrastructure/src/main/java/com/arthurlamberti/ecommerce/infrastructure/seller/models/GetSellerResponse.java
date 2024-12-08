package com.arthurlamberti.ecommerce.infrastructure.seller.models;

import com.arthurlamberti.ecommerce.infrastructure.customer.models.AddressSummaryResponse;

import java.time.Instant;

public record GetSellerResponse(
        String name,
        String email,
        String document,
        String description,
        boolean active,
        java.util.List<AddressSummaryResponse> addresses,
        Instant createdAt,
        Instant deletedAt
) {
}
