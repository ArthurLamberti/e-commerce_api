package com.arthurlamberti.ecommerce.domain.address;

import java.util.Optional;

public interface AddressGateway {
    Address create(final Address anAddress);
    Optional<Address> findById(AddressID anId);
}
