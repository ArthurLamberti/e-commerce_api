package com.arthurlamberti.ecommerce.infrastructure.seller.presenters;

import com.arthurlamberti.ecommerce.application.customer.retrieve.get.GetCustomerOutput;
import com.arthurlamberti.ecommerce.application.seller.retrieve.get.GetSellerOutput;
import com.arthurlamberti.ecommerce.application.seller.retrieve.list.ListSellerOutput;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.AddressSummaryResponse;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.GetCustomerResponse;
import com.arthurlamberti.ecommerce.infrastructure.seller.models.GetSellerResponse;
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

    static GetSellerResponse present(GetSellerOutput output) {
        final var addresses = output.address()
                .stream()
                .map(AddressSummaryResponse::from)
                .toList();
        return new GetSellerResponse(
                output.name(),
                output.email(),
                output.document(),
                output.description(),
                output.active(),
                addresses,
                output.createdAt(),
                output.deletedAt()
        );
    }
}
