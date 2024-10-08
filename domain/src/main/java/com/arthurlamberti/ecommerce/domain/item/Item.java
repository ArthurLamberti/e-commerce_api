package com.arthurlamberti.ecommerce.domain.item;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.enums.ItemStatus;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.utils.InstantUtils;
import com.arthurlamberti.ecommerce.domain.validation.Error;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;

import static java.util.Objects.isNull;

@Getter
public class Item extends AggregateRoot<ItemID> {

    private String sellerId;
    private String name;
    private String description;
    private String imageUrl;
    private ItemStatus status;
    private Integer qtyAvailable;
    private Integer qtySold;
    private Integer scoreReview;
    private Integer qtyReviews;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Item(final ItemID itemID,
                   final String sellerId,
                   final String name,
                   final String description,
                   final String imageUrl,
                   final ItemStatus status,
                   final Integer qtyAvailable,
                   final Integer qtySold,
                   final Integer scoreReview,
                   final Integer qtyReviews,
                   final Instant createdAt,
                   final Instant updatedAt,
                   final Instant deletedAt) {
        super(itemID);
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = status;
        this.qtyAvailable = qtyAvailable;
        this.qtySold = qtySold;
        this.scoreReview = scoreReview;
        this.qtyReviews = qtyReviews;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;

        selfValidate();
    }

    public static Item newItem(
            final String sellerId,
            final String name,
            final String description,
            final String imageUrl
    ) {
        final var anId = ItemID.unique();
        final var now = InstantUtils.now();
        return new Item(anId, sellerId, name, description, imageUrl, ItemStatus.INACTIVE, 0, 0, 0, 0, now, now, null);
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

    public void soldItem(Integer qtySold) {
        final var notification = Notification.create();
        if (qtySold <= 0) {
            notification.append(new Error("Quantity sold should be positive"));
        }
        if (qtySold > this.qtyAvailable) {
            notification.append(new Error("Item without stock"));
        }
        if (this.status == ItemStatus.INACTIVE) {
            notification.append(new Error("Item should be activate to sold item"));
        }

        if (notification.hasError()) {
            throw new NotificationException("Failed to sold item", notification);
        }

        this.qtySold += qtySold;
        this.qtyAvailable -= qtySold;
    }

    public void addReview(Integer scoreReview) {
        final var notification = Notification.create();
        if (scoreReview <= 0 || scoreReview > 5)
            notification.append(new Error("Review score must be between 1 and 5"));
        if (this.status == ItemStatus.INACTIVE) {
            notification.append(new Error("Item should be activate to add review"));
        }

        if (notification.hasError()) {
            throw new NotificationException("Failed to add review item", notification);
        }

        this.scoreReview += scoreReview;
        this.qtyReviews++;
        this.updatedAt = InstantUtils.now();
    }

    public double getReviewScore(){
        return scoreReview/Double.valueOf(qtyReviews);
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create an Item", notification);
    }
}
