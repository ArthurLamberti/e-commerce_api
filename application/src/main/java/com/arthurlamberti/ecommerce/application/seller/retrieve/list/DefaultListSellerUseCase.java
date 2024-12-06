package com.arthurlamberti.ecommerce.application.seller.retrieve.list;

import com.arthurlamberti.ecommerce.domain.seller.SellerGateway;

import java.util.List;

public class DefaultListSellerUseCase extends ListSellerUseCase {

    private final SellerGateway sellerGateway;

    public DefaultListSellerUseCase(final SellerGateway sellerGateway) {
        this.sellerGateway = sellerGateway;
    }

    @Override
    public List<ListSellerOutput> execute() {
        return this.sellerGateway.findAll()
                .stream()
                .map(ListSellerOutput::from)
                .toList();
    }
}
