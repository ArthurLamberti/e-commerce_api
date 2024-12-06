package com.arthurlamberti.ecommerce.application.seller.create;

import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.seller.Seller;

public record CreateSellerOutput(String id) {
    public static CreateSellerOutput from(Seller seller) {
        return new CreateSellerOutput(seller.getId().getValue());
    }
}
