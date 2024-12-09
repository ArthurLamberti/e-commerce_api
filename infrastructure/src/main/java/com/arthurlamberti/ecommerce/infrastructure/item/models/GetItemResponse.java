package com.arthurlamberti.ecommerce.infrastructure.item.models;

import java.time.Instant;

public record GetItemResponse(
        String id,
        String name,
        String description,
        String imageUrl,
        Integer stockAvailable,
        GetItemSellerResponse seller,
        Instant createdAt,
        Instant deletedAt
) {
}
