package com.arthurlamberti.ecommerce.domain.item;

import com.arthurlamberti.ecommerce.domain.Identifier;
import com.arthurlamberti.ecommerce.domain.utils.IdUtils;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class ItemID extends Identifier {
    private final String uuid;

    public ItemID(final String value) {
        requireNonNull(value);
        this.uuid = value;
    }

    public static ItemID unique() {
        return ItemID.from(IdUtils.uuid());
    }
    public static ItemID from (final String anId) {
        return new ItemID(anId.toLowerCase());
    }

    @Override
    public String getValue() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemID itemID = (ItemID) o;
        return Objects.equals(uuid, itemID.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
