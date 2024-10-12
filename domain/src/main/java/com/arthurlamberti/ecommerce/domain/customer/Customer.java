package com.arthurlamberti.ecommerce.domain.customer;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.seller.Seller;
import com.arthurlamberti.ecommerce.domain.utils.InstantUtils;
import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;

import static java.util.Objects.isNull;

@Getter
public class Customer extends AggregateRoot<CustomerID> {

    private final String name;
    private final String email;
    private final String document;
    private Address address; //opcional
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Customer(final CustomerID anId,
                       final String aName,
                       final String anEmail,
                       final String aDocument,
                       final Address anAddress,
                       final boolean active,
                       final Instant createdAt,
                       final Instant updatedAt,
                       final Instant deletedAt) {
        super(anId);
        this.name = aName;
        this.email = anEmail;
        this.document = aDocument;
        this.address = anAddress;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        selfValidate();
    }

    public static Customer newCustomer(
            final String aName,
            final String anEmail,
            final String aDocument,
            final Address anAddress
    ) {
        final var anId = CustomerID.unique();
        final var now = InstantUtils.now();
        return new Customer(anId,aName,anEmail,aDocument,anAddress,true,now,now,null);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new CustomerValidator(this, handler).validate();
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create a Customer", notification);
    }


    public Customer activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Customer deactivate() {
        var now = InstantUtils.now();
        if (isNull(this.deletedAt))
            this.deletedAt = now;
        this.active = false;
        this.updatedAt = now;
        return this;
    }

    public Customer changeAddress(final Address address) {
        final var notification = Notification.create();
        if (isNull(address)) {
            notification.append(new Error("'address' should not be null"));
        }

        if (notification.hasError()) {
            throw new NotificationException("Failed to sold item", notification);
        }
        this.address = address;
        this.updatedAt = InstantUtils.now();
        return this;
    }
}
