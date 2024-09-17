package com.arthurlamberti.ecommerce.domain.seller;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.utils.InstantUtils;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Seller extends AggregateRoot<SellerID> {

    private String name;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Seller(final SellerID sellerID,
                     final String name,
                     final boolean active,
                     final Instant createdAt,
                     final Instant updatedAt,
                     final Instant deletedAt) {
        super(sellerID);
        this.name = name;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        selfValidate();
    }

    public static Seller newSeller(final String name, final Boolean active) {
        final var anId = SellerID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = active ? null : now;
        return new Seller(anId, name, active, now, now, deletedAt);
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
}
