package com.arthurlamberti.ecommerce.domain.seller;

import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.pagination.Pagination;

import java.util.List;
import java.util.Optional;

public interface SellerGateway {
    Seller create(final Seller customer);

    List<Seller> findAll();

    Optional<Seller> findById(SellerID from);
}
