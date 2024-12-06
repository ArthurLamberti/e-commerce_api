package com.arthurlamberti.ecommerce.infrastructure.seller.presenters;

import com.arthurlamberti.ecommerce.application.seller.retrieve.list.ListSellerOutput;
import com.arthurlamberti.ecommerce.infrastructure.seller.models.SellerListResponse;

public interface SellerApiPresent {

    static SellerListResponse present(ListSellerOutput output) {
        return new SellerListResponse(
                output.name(),
                output.email(),
                output.document(),
                output.description(),
                output.active(),
                output.createdAt(),
                output.deletedAt()
        );
    }
}
