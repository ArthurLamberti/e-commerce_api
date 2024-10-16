package com.arthurlamberti.ecommerce.domain.seller;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.enums.ItemStatus;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.utils.InstantUtils;
import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;

import static java.util.Objects.isNull;

@Getter
public class Seller extends AggregateRoot<SellerID> {

    private String name;
    private String description;
    private boolean active;
    private String document;
    private Address address;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Seller(final SellerID sellerID,
                     final String name,
                     final String description,
                     final boolean active,
                     final String document,
                     final Address anAddres,
                     final Instant createdAt,
                     final Instant updatedAt,
                     final Instant deletedAt) {
        super(sellerID);
        this.name = name;
        this.description = description;
        this.active = active;
        this.document = document;
        this.address = anAddres;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        selfValidate();
    }

    public static Seller newSeller(final String name,
                                   final String description,
                                   final Boolean active,
                                   final String aDocument,
                                   final Address anAddress) {
        final var anId = SellerID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = active ? null : now;
        return new Seller(anId, name, description, active, aDocument, anAddress,now, now, deletedAt);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new SellerValidator(this, handler).validate();
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create a Seller", notification);
    }

    public Seller activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Seller deactivate() {
        var now = InstantUtils.now();
        if (isNull(this.deletedAt))
            this.deletedAt = now;
        this.active = false;
        this.updatedAt = now;
        return this;
    }

    public Seller changeAddress(final Address address) {
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
