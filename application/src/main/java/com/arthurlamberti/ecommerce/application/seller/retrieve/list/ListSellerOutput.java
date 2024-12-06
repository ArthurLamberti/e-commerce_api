package com.arthurlamberti.ecommerce.application.seller.retrieve.list;

import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.seller.Seller;

import java.time.Instant;

public record ListSellerOutput(
        String name,
        String email,
        String document,
        String description,
        boolean active,
        Instant createdAt,
        Instant deletedAt
) {
    public static ListSellerOutput from(Seller seller) {
        return new ListSellerOutput(
                seller.getName(),
                seller.getEmail(),
                seller.getDocument(),
                seller.getDescription(),
                seller.isActive(),
                seller.getCreatedAt(),
                seller.getDeletedAt()
        );
    }
}
