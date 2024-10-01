package com.arthurlamberti.ecommerce.domain.shipping;

import com.arthurlamberti.ecommerce.domain.Identifier;
import com.arthurlamberti.ecommerce.domain.utils.IdUtils;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class ShippingID extends Identifier {
    private final String uuid;

    public ShippingID(final String uuid) {
        requireNonNull(uuid);
        this.uuid = uuid;
    }

    public static ShippingID unique(){
        return ShippingID.from(IdUtils.uuid());
    }

    public static ShippingID from(final String anId) {
        return new ShippingID(anId.toLowerCase());
    }

    @Override
    public String getValue() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShippingID that = (ShippingID) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
