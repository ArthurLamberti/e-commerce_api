package com.arthurlamberti.ecommerce.application.item.retrieve.list;

import com.arthurlamberti.ecommerce.domain.item.Item;

import java.time.Instant;

public record ListItemOutput (
        String name,
        String imageUrl,
        Integer stockAvailable,
        Instant createdAt,
        Instant updatedAt,
        Instant deletedAt
) {
    public static ListItemOutput from(Item item) {
        return new ListItemOutput(
                item.getName(),
                item.getImageUrl(),
                item.getQtyAvailable(),
                item.getCreatedAt(),
                item.getUpdatedAt(),
                item.getDeletedAt()
        );
    }
}
