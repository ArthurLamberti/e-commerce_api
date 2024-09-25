package com.arthurlamberti.ecommerce.domain.address;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.utils.InstantUtils;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;

import static java.util.Objects.isNull;

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
    private final Instant createdAt;
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

        selfValidate();
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
        final var anId = AddressID.unique();
        final var now = InstantUtils.now();
        return new Address(anId,aCountry,aState,aCity,aStreet,aZipCode,aNumber,aComplement,true,now,now,null);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new AddressValidator(this, handler).validate();
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create an Address", notification);
    }

    public Address activate() {
        this.deletedAt = null;
        this.updatedAt = Instant.now();
        this.active = true;
        return this;
    }

    public Address deactivate() {
        var now = InstantUtils.now();
        if (isNull(this.deletedAt))
            this.deletedAt = now;
        this.updatedAt = now;
        this.active = false;
        return this;
    }
}
