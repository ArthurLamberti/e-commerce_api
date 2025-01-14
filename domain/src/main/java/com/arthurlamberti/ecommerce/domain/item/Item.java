package com.arthurlamberti.ecommerce.domain.item;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.enums.ItemStatus;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.reviews.Review;
import com.arthurlamberti.ecommerce.domain.utils.InstantUtils;
import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
public class Item extends AggregateRoot<ItemID> {

    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private Integer qtyAvailable;
    List<Review> reviews;
    private String sellerId;
    private ItemStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Item(final ItemID itemID,
                   final String sellerId,
                   final String name,
                   final String description,
                   final String imageUrl,
                   final Double price,
                   final ItemStatus status,
                   final Integer qtyAvailable,
                   final List<Review> reviews,
                   final Instant createdAt,
                   final Instant updatedAt,
                   final Instant deletedAt) {
        super(itemID);
        this.sellerId = sellerId;
//        this.seller = seller;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.status = status;
        this.qtyAvailable = qtyAvailable;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.reviews = reviews;

        selfValidate();
    }

    public static Item newItem(
//            final Seller seller,
            final String sellerId,
            final String name,
            final String description,
            final String imageUrl,
            final Double price,
            final Integer stock
    ) {
        final var anId = ItemID.unique();
        final var now = InstantUtils.now();

//        return new Item(anId, seller, name, description, imageUrl, ItemStatus.ACTIVE, stock, new ArrayList<>(), now, now, null);
        return new Item(anId, sellerId, name, description, imageUrl, price, ItemStatus.INACTIVE, stock, new ArrayList<>(), now, now, null);
    }

    public static Item with(ItemID id, String name, String description, String imageUrl, Double price, Integer qtyAvailable, String seller, Instant createdAt, Instant updatedAt, Instant deletedAt) {
        return new Item(
                id,
                seller,
                name,
                description,
                imageUrl,
                price,
                ItemStatus.ACTIVE,
                qtyAvailable,
                new ArrayList<>(),
                createdAt,
                updatedAt,
                deletedAt
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new ItemValidator(this, handler).validate();
    }

    public Item activate() {
        this.deletedAt = null;
        this.status = ItemStatus.ACTIVE;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Item deactivate() {
        var now = InstantUtils.now();
        if (isNull(this.deletedAt))
            this.deletedAt = now;
        this.status = ItemStatus.INACTIVE;
        this.updatedAt = now;
        return this;
    }

    public void addStock(Integer qtyAvailable) {
        final var notification = Notification.create();
        if (qtyAvailable <= 0) {
            notification.append(new Error("Quantity should be positive"));
        }
        if (this.status == ItemStatus.INACTIVE) {
            notification.append(new Error("Item should be activate to add stock"));
        }

        if (notification.hasError()) {
            throw new NotificationException("Failed to add stock", notification);
        }

        this.qtyAvailable += qtyAvailable;
        this.updatedAt = InstantUtils.now();
    }

    public void removeStock(Integer qtyToRemove) {
        final var notification = Notification.create();
        if (qtyToRemove <= 0) {
            notification.append(new Error("Quantity should be positive"));
        }
        if (qtyToRemove > qtyAvailable) {
            notification.append(new Error("Quantity should be less or equals than qty available"));
        }
        if (this.status == ItemStatus.INACTIVE) {
            notification.append(new Error("Item should be activate to remove stock"));
        }

        if (notification.hasError()) {
            throw new NotificationException("Failed to add stock", notification);
        }

        this.qtyAvailable -= qtyToRemove;
        this.updatedAt = InstantUtils.now();
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create an Item", notification);
    }
}
