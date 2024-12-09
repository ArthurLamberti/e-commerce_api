package com.arthurlamberti.ecommerce.application.item.retrieve.get;

import com.arthurlamberti.ecommerce.domain.item.Item;

import java.time.Instant;

public record GetItemOutput(
        String name,
        String imageUrl,
        Integer stockAvailable,
        Double reviewScore,
        Instant createdAt,
        Instant deletedAt
) {

    public static GetItemOutput from(Item item) {
        final var reviewScore = -1D;
        return new GetItemOutput(
                item.getName(),
                item.getImageUrl(),
                item.getQtyAvailable(),
                reviewScore,
                item.getCreatedAt(),
                item.getDeletedAt()
        );
    }

}
