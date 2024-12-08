package com.arthurlamberti.ecommerce.domain.customer;

import com.arthurlamberti.ecommerce.domain.pagination.Pagination;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CustomerGateway {
    Customer create(final Customer customer);

    List<Customer> findAll();

    Optional<Customer> findById(CustomerID from);
}
