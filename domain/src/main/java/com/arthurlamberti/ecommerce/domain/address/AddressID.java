package com.arthurlamberti.ecommerce.domain.address;

import com.arthurlamberti.ecommerce.domain.Identifier;
import com.arthurlamberti.ecommerce.domain.utils.IdUtils;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class AddressID extends Identifier {
    private final String uuid;

    public AddressID(final String uuid) {
        requireNonNull(uuid);
        this.uuid = uuid;
    }

    public static AddressID from(final String anId) {
        return new AddressID(anId.toLowerCase());
    }

    public static AddressID unique() {
        return AddressID.from(IdUtils.uuid());
    }

    @Override
    public String getValue() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressID addressID = (AddressID) o;
        return Objects.equals(uuid, addressID.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
