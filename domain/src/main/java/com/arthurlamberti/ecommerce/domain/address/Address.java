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
public class Address extends AggregateRoot<AddressID> implements Cloneable {
    private String country;
    private String state;
    private String city;
    private String street;
    private String zipCode;
    private String numeral;
    private String complement;
    private boolean active;
    private final String customerId;
    private final String sellerId;
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
            final String aCustomerId,
            final String aSellerId,
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
        this.numeral = aNumber;
        this.complement = aComplement;
        this.active = active;
        this.customerId = aCustomerId;
        this.sellerId = aSellerId;
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
            final String aComplement,
            final String aCustomerId,
            final String aSellerId
    ) {
        final var anId = AddressID.unique();
        final var now = InstantUtils.now();
        return new Address(anId, aCountry, aState, aCity, aStreet, aZipCode, aNumber, aComplement, true, aCustomerId, aSellerId, now, now, null);
    }

    public static Address with(Address anAddress) {
        return new Address(
                anAddress.getId(),
                anAddress.getCountry(),
                anAddress.getState(),
                anAddress.getCity(),
                anAddress.getStreet(),
                anAddress.getZipCode(),
                anAddress.getNumeral(),
                anAddress.getComplement(),
                anAddress.isActive(),
                anAddress.getCustomerId(),
                anAddress.getSellerId(),
                anAddress.createdAt,
                anAddress.updatedAt,
                anAddress.deletedAt
        );
    }

    public static Address with(
            final AddressID id,
            final String country,
            final String state,
            final String city,
            final String street,
            final String zipCode,
            final String number,
            final String complement,
            final boolean active,
            final String customerId,
            final String sellerId,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt) {
        return new Address(
                id,
                country,
                state,
                city,
                street,
                zipCode,
                number,
                complement,
                active,
                customerId,
                sellerId,
                createdAt,
                updatedAt,
                deletedAt
        );
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

    public Address updateAddress(
            final String country,
            final String state,
            final String city,
            final String street,
            final String zipCode,
            final String number,
            final String complement
    ) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
        this.numeral = number;
        this.complement = complement;
        this.updatedAt = InstantUtils.now();

        selfValidate();
        return this;
    }

    @Override
    protected Address clone() {
        try {
            return (Address) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
