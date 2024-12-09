package com.arthurlamberti.ecommerce.infrastructure.item.models;

import java.time.Instant;

public record ListItemResponse(
        String id,
        String name,
        String imageUrl,
        Double reviewScore,
        Instant createdAt,
        Instant deletedAt
) {
}
