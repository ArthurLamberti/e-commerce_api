package com.arthurlamberti.ecommerce.application.seller.retrieve.get;

import com.arthurlamberti.ecommerce.domain.exceptions.NotFoundException;
import com.arthurlamberti.ecommerce.domain.seller.Seller;
import com.arthurlamberti.ecommerce.domain.seller.SellerGateway;
import com.arthurlamberti.ecommerce.domain.seller.SellerID;

public class DefaultGetSellerUseCase extends GetSellerUseCase {

    private final SellerGateway sellerGateway;

    public DefaultGetSellerUseCase(
            final SellerGateway sellerGateway
    ) {
        this.sellerGateway = sellerGateway;
    }

    @Override
    public GetSellerOutput execute(String sellerId) {
        return this.sellerGateway.findById(SellerID.from(sellerId))
                .map(GetSellerOutput::from)
                .orElseThrow(() -> NotFoundException.with(Seller.class, SellerID.from(sellerId)));
    }
}
