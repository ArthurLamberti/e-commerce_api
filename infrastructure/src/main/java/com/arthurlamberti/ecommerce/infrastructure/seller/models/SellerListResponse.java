package com.arthurlamberti.ecommerce.infrastructure.seller.models;

import java.time.Instant;

public record SellerListResponse(
        String name,
        String email,
        String document,
        String description,
        boolean active,
        Instant createdAt,
        Instant deletedAt
) {
}
