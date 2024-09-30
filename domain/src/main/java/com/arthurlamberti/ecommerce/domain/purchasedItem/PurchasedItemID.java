package com.arthurlamberti.ecommerce.domain.purchasedItem;

import com.arthurlamberti.ecommerce.domain.Identifier;
import com.arthurlamberti.ecommerce.domain.utils.IdUtils;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class PurchasedItemID extends Identifier {
    private final String uuid;

    public PurchasedItemID(final String value) {
        requireNonNull(value);
        this.uuid = value;
    }

    public static PurchasedItemID unique(){
        return PurchasedItemID.from(IdUtils.uuid());
    }

    public static PurchasedItemID from(final String anId) {
        return new PurchasedItemID(anId.toLowerCase());
    }
    @Override
    public String getValue() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchasedItemID that = (PurchasedItemID) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
