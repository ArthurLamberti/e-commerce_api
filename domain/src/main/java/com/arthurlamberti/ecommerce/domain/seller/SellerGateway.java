package com.arthurlamberti.ecommerce.domain.seller;

import com.arthurlamberti.ecommerce.domain.customer.Customer;

import java.util.List;

public interface SellerGateway {
    Seller create(final Seller customer);

    List<Seller> findAll();
}
