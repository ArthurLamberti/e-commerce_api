package com.arthurlamberti.ecommerce.infrastructure.customer.models;

import java.time.Instant;

public record CustomerListResponse(

        String name,
        String email,
        String document,
        boolean active,
        Instant createdAt,
        Instant deletedAt
) {
}
