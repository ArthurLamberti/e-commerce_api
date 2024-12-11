package com.arthurlamberti.ecommerce.domain.reviews;

import com.arthurlamberti.ecommerce.domain.Identifier;
import com.arthurlamberti.ecommerce.domain.utils.IdUtils;

import java.util.Objects;

public class ReviewID extends Identifier {

    private final String uuid;

    public ReviewID(final String value) {
        this.uuid = value;
    }

    public static ReviewID unique(){
        return ReviewID.from(IdUtils.uuid());
    }

    public static ReviewID from(final String anId) {
        return new ReviewID(anId.toLowerCase());
    }

    @Override
    public String getValue() {
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewID reviewID = (ReviewID) o;
        return Objects.equals(uuid, reviewID.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
