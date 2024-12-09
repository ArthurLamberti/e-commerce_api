package com.arthurlamberti.ecommerce.infrastructure.item.models;

public record CreateItemRequest (
        String name,
        String description,
        String imageUrl,
        Integer stock,
        String sellerId
) {
}
