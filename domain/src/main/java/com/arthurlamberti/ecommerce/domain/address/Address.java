package com.arthurlamberti.ecommerce.domain.address;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Address extends AggregateRoot<AddressID> {
    private final String country;
    private final String state;
    private final String city;
    private final String street;
    private final String zipCode;
    private final String number;
    private final String complement;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Address(
            final AddressID anId,
            final String aCountry,
            final String aState,
            final String aCity,
            final String aStreet,
            final String aZipCode,
            final String aNumber,
            final String aComplement,
            final boolean active,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(anId);
        this.country = aCountry;
        this.state = aState;
        this.city = aCity;
        this.street = aStreet;
        this.zipCode = aZipCode;
        this.number = aNumber;
        this.complement = aComplement;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Address newAddress(
            final String aCountry,
            final String aState,
            final String aCity,
            final String aStreet,
            final String aZipCode,
            final String aNumber,
            final String aComplement
    ) {
        return new Address(
                null,null,null,null,null,null,null,null,true,null,null,null);
    }

    @Override
    public void validate(ValidationHandler handler) {

    }
}
