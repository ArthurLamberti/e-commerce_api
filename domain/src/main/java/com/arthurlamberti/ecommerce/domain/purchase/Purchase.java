package com.arthurlamberti.ecommerce.domain.purchase;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.enums.PurchaseStatus;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.purchasedItem.PurchasedItem;
import com.arthurlamberti.ecommerce.domain.seller.Seller;
import com.arthurlamberti.ecommerce.domain.shipping.Shipping;
import com.arthurlamberti.ecommerce.domain.utils.InstantUtils;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class Purchase extends AggregateRoot<PurchaseID> {

    private String sellerId;
    private String customerId;
    private List<String> items;
    private Double totalValue;
    private Integer totalQty;
    private String shipping;
    private PurchaseStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Purchase(final PurchaseID purchaseID,
                       final String aSeller,
                       final String aCustomer,
                       final List<String> listItems,
                       final Double aTotalValue,
                       final Integer totalQty,
                       final String aShipping,
                       final PurchaseStatus aStatus,
                       final Instant createdAt,
                       final Instant updatedAt,
                       final Instant deletedAt) {
        super(purchaseID);
        this.sellerId = aSeller;
        this.customerId = aCustomer;
        this.items = listItems;
        this.totalQty = totalQty;
        this.totalValue = aTotalValue;
        this.shipping = aShipping;
        this.status = aStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        selfValidate();
    }

    public static Purchase newPurchase(
            final String aSellerId,
            final String aCustomerId,
            final List<String> listItems,
            final Double aTotalValue,
            final Integer totalQty,
            final String aShipping,
            final String anAddress
    ) {
        final var anId = PurchaseID.unique();
        final var now = InstantUtils.now();
        return new Purchase(
                anId,
                aSellerId,
                aCustomerId,
                listItems,
                aTotalValue,
                totalQty,
                aShipping,
                PurchaseStatus.NEW,
                now,
                now,
                null
        );
    }

    public static Purchase with(PurchaseID from, String customerId, String sellerId, List<String> items, String shippingId, Double totalValue, PurchaseStatus status, Instant createdAt, Instant updatedAt, Instant deletedAt) {
        return new Purchase(
                from,
                customerId,
                sellerId,
                items,
                totalValue,
                items.size(),
                shippingId,
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
            throw new NotificationException("Failed to create a purchase", notification);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new PurchaseValidator(this, handler).validate();
    }
}
