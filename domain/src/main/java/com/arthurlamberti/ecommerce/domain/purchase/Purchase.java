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

    private Seller seller;
    private Customer customer;
    private List<PurchasedItem> items;
    private Double totalValue;
    private Integer totalQty;
    private Shipping shipping;
    private PurchaseStatus status;
    private Address address;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Purchase(final PurchaseID purchaseID,
                       final Seller aSeller,
                       final Customer aCustomer,
                       final List<PurchasedItem> listItems,
                       final Double aTotalValue,
                       final Integer totalQty,
                       final Shipping aShipping,
                       final Address anAddress,
                       final PurchaseStatus aStatus,
                       final Instant createdAt,
                       final Instant updatedAt,
                       final Instant deletedAt) {
        super(purchaseID);
        this.seller = aSeller;
        this.customer = aCustomer;
        this.items = listItems;
        this.totalQty = totalQty;
        this.totalValue = aTotalValue;
        this.shipping = aShipping;
        this.address = anAddress;
        this.status = aStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        selfValidate();
    }

    public static Purchase newPurchase(
            final Seller aSeller,
            final Customer aCustomer,
            final List<PurchasedItem> listItems,
            final Double aTotalValue,
            final Integer totalQty,
            final Shipping aShipping,
            final Address anAddress
    ){
        final var anId = PurchaseID.unique();
        final var now = InstantUtils.now();
        return new Purchase(
                anId,
                aSeller,
                aCustomer,
                listItems,
                aTotalValue,
                totalQty,
                aShipping,
                anAddress,
                PurchaseStatus.NEW,
                now,
                now,
                null
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
