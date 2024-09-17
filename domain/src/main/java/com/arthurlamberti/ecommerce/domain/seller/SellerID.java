package com.arthurlamberti.ecommerce.domain.seller;

import com.arthurlamberti.ecommerce.domain.Identifier;
import com.arthurlamberti.ecommerce.domain.utils.IdUtils;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class SellerID extends Identifier {

    private final String uuid;

    public SellerID(final String value) {
        requireNonNull(value);
        this.uuid = value;
    }

    public static SellerID unique(){
        return SellerID.from(IdUtils.uuid());
    }

    public static SellerID from(final String anId) {
        return new SellerID(anId.toLowerCase());
    }
    @Override
    public String getValue() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerID sellerID = (SellerID) o;
        return Objects.equals(uuid, sellerID.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
