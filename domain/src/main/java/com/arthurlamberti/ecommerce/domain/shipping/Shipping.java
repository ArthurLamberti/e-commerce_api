package com.arthurlamberti.ecommerce.domain.shipping;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.enums.ShippingStatus;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.utils.InstantUtils;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Shipping extends AggregateRoot<ShippingID> {

    private String addressId;
    private String purchaseId;
    private String code;
    private Double price;
    private ShippingStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;


    protected Shipping(
            final ShippingID shippingID,
            final String anAddressId,
            final String purchaseId,
            final String aCode,
            final Double aPrice,
            final ShippingStatus status,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(shippingID);
        this.addressId = anAddressId;
        this.purchaseId = purchaseId;
        this.code = aCode;
        this.price = aPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        selfValidate();
    }

    public static Shipping newShipping(
            final String anAddressId,
            final String purchaseId,
            final String code,
            final Double price
    ) {
        final var anId = ShippingID.unique();
        final var now = InstantUtils.now();
        return new Shipping(anId, anAddressId, purchaseId, code, price, ShippingStatus.CREATED, now, now, null);
    }

    public static Shipping with(ShippingID anId, String addressId, String otherId, String code, Double price, ShippingStatus status, Instant createdAt, Instant updatedAt, Instant deletedAt) {
        return new Shipping(
                anId,
                addressId,
                otherId,
                code,
                price,
                status,
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create a Shipping", notification);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new ShippingValidator(this, handler).validate();
    }

    public Shipping changeStatus(final ShippingStatus aStatus) {
        this.status = aStatus;
        this.updatedAt = InstantUtils.now();
        return this;
    }
}
