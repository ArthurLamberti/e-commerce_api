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

    private Address address;
    private String code;
    private Double price;
    private ShippingStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;


    protected Shipping(
            final ShippingID shippingID,
            final Address anAddress,
            final String aCode,
            final Double aPrice,
            final ShippingStatus status,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
            ) {
        super(shippingID);
        this.address = anAddress;
        this.code = aCode;
        this.price = aPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        selfValidate();
    }

    public static Shipping newShipping(
            final Address anAddress,
            final String code,
            final Double price
    ){
        final var anId = ShippingID.unique();
        final var now = InstantUtils.now();
        return new Shipping(anId,anAddress,code,price,ShippingStatus.CREATED,now,now,null);
    }

    private void selfValidate(){
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create a Shipping", notification);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new ShippingValidator(this,handler).validate();
    }
}
