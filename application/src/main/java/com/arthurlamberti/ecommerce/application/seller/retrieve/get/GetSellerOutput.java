package com.arthurlamberti.ecommerce.application.seller.retrieve.get;

import com.arthurlamberti.ecommerce.application.customer.retrieve.AddressSummary;
import com.arthurlamberti.ecommerce.domain.seller.Seller;

import java.time.Instant;
import java.util.List;

public record GetSellerOutput(
        String name,
        String email,
        String document,
        String description,
        boolean active,
        List<AddressSummary> address,
        Instant createdAt,
        Instant deletedAt
) {

    public static GetSellerOutput from(Seller seller) {
        final var addressList = seller.getAddressList()
                .stream()
                .map(AddressSummary::from)
                .toList();

        return new GetSellerOutput(
                seller.getName(),
                seller.getEmail(),
                seller.getDocument(),
                seller.getDescription(),
                seller.isActive(),
                addressList,
                seller.getCreatedAt(),
                seller.getDeletedAt()
        );
    }

}
