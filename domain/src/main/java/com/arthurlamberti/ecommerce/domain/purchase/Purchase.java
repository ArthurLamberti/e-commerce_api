package com.arthurlamberti.ecommerce.domain.purchase;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.enums.PurchaseStatus;
import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.purchasedItem.PurchasedItem;
import com.arthurlamberti.ecommerce.domain.seller.Seller;
import com.arthurlamberti.ecommerce.domain.shipping.Shipping;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;

import java.util.List;

public class Purchase extends AggregateRoot<PurchaseID> {

    private Seller seller;
    private Customer customer;
    private List<PurchasedItem> items;
    private Double totalValue;
    private Integer totalQty;
    private Shipping shipping;
    private PurchaseStatus status;
    private Address address;

    protected Purchase(PurchaseID purchaseID) {
        super(purchaseID);
    }

    @Override
    public void validate(ValidationHandler handler) {

    }
}
