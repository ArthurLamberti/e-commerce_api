package com.arthurlamberti.ecommerce.domain.purchase;

import com.arthurlamberti.ecommerce.domain.Identifier;
import com.arthurlamberti.ecommerce.domain.utils.IdUtils;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class PurchaseID extends Identifier {
    private final String uuid;

    public PurchaseID(final String uuid) {
        requireNonNull(uuid);
        this.uuid = uuid;
    }

    public static PurchaseID unique() {
        return PurchaseID.from(IdUtils.uuid());
    }

    public static PurchaseID from(final String anID){
        return new PurchaseID(anID.toLowerCase());
    }

    @Override
    public String getValue() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseID that = (PurchaseID) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
