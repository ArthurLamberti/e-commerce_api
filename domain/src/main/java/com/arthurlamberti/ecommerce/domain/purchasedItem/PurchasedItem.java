package com.arthurlamberti.ecommerce.domain.purchasedItem;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.seller.SellerValidator;
import com.arthurlamberti.ecommerce.domain.utils.InstantUtils;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;

@Getter
public class PurchasedItem extends AggregateRoot<PurchasedItemID> {

    private String purchaseId;
    private String itemId;
    //    private Item item;
    private Integer qty;
    private Double value;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected PurchasedItem(final PurchasedItemID purchasedItemID,
                            final String purchaseId,
                            final String itemId,
                            final Integer qty,
                            final Double value,
                            final Instant createdAt,
                            final Instant updatedAt,
                            final Instant deletedAt) {
        super(purchasedItemID);
        this.purchaseId = purchaseId;
        this.itemId = itemId;
        this.qty = qty;
        this.value = value;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        selfValidate();
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);
        if (notification.hasError())
            throw new NotificationException("Failed to create a purchasedItem", notification);
    }

    public static PurchasedItem newPurchasedItem(final String purchaseId,
                                                 final String itemId,
                                                 final Integer qty,
                                                 final Double value) {
        final var anId = PurchasedItemID.unique();
        final var now = InstantUtils.now();
        return new PurchasedItem(anId, purchaseId, itemId, qty, value, now, now, null);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new PurchasedItemValidator(this, handler).validate();
    }
}
