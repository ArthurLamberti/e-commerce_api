package com.arthurlamberti.ecommerce.domain.address;

import com.arthurlamberti.ecommerce.domain.pagination.Pagination;
import com.arthurlamberti.ecommerce.domain.pagination.SearchQuery;

import java.util.Optional;

public interface AddressGateway {
    Address create(final Address anAddress);
    Optional<Address> findById(AddressID anId);
    Address update(Address address);

    void deleteById(AddressID from);

    Pagination<Address> findAll(SearchQuery aQuery);
}
