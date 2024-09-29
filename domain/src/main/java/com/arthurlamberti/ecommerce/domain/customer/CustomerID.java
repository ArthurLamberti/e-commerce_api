package com.arthurlamberti.ecommerce.domain.customer;

import com.arthurlamberti.ecommerce.domain.Identifier;
import com.arthurlamberti.ecommerce.domain.utils.IdUtils;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class CustomerID extends Identifier {
    private final String uuid;

    public CustomerID(final String uuid) {
        requireNonNull(uuid);
        this.uuid = uuid;
    }

    public static CustomerID unique() {
        return CustomerID.from(IdUtils.uuid());
    }
    public static CustomerID from (final String anId) {
        return new CustomerID(anId.toLowerCase());
    }

    @Override
    public String getValue() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerID that = (CustomerID) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
