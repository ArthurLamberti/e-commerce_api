package com.arthurlamberti.ecommerce.domain;

import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.purchasedItem.PurchasedItem;
import com.arthurlamberti.ecommerce.domain.seller.Seller;
import com.arthurlamberti.ecommerce.domain.seller.SellerID;
import com.arthurlamberti.ecommerce.domain.shipping.Shipping;
import com.arthurlamberti.ecommerce.domain.utils.IdUtils;
import com.github.javafaker.Faker;

import java.util.UUID;


public final class Fixture {

    private static final Faker FAKER = new Faker();

    public static String name() {
        return FAKER.name().fullName();
    }

    public static String email() {
        return FAKER.internet().emailAddress();
    }

    public static String document() {
        return FAKER.idNumber().valid();
    }

    public static String characters(Integer qty) {
        return FAKER.lorem().characters(qty);
    }

    public static String characters(Integer qtyMin, Integer qtyMax) {
        return FAKER.lorem().characters(qtyMin, qtyMax);
    }

    public static String imageUrl() {
        return characters(80);
    }

    public static String uuid() {
        return IdUtils.uuid();
    }

    public static String sellerName() {
        return FAKER.name().firstName();
    }

    public static String sellerDescription() {
        return FAKER.lorem().characters(0, 2999);
    }

    public static Boolean isActive() {
        return FAKER.bool().bool();
    }

    public static Integer positiveNumber() {
        return FAKER.number().numberBetween(1, Integer.MAX_VALUE);
    }

    public static Integer negativeNumber() {
        return FAKER.number().numberBetween(Integer.MIN_VALUE, -1);
    }

    public static final class SellerFixture {
        public static String name() {
            return FAKER.name().firstName();
        }

        public static String description() {
            return FAKER.lorem().characters(0, 2999);
        }

        public static Seller validSeller(){
            return Seller.newSeller(
                    name(),
                    description(),
                    true,
                    document(),
                    AddressFixture.validAddress()
            );
        }
    }

    public static final class CustomerFixture{
        public static Customer validCustomer(){
            return Customer.newCustomer(
                    name(),
                    email(),
                    document(),
                    AddressFixture.validAddress()
            );
        }
    }

    public static final class ShippingFixture {
        public static Shipping validShipping(){
            return Shipping.newShipping(AddressFixture.validAddress(), characters(10), ItemFixture.price());
        }
    }

    public static final class ItemFixture {

        public static String name() {
            return characters(11, 99);
        }

        public static String description() {
            return characters(51, 999);
        }

        public static Double price () {
            return FAKER.number().randomDouble(2, 1, 5000);
        }

        public static Item validItem(String sellerId){
            if (sellerId.isBlank()){
                sellerId = SellerID.unique().getValue();
            }
            return Item.newItem(
                    sellerId,
                    name(),
                    description(),
                    imageUrl()
            );
        }
    }

    public static final class AddressFixture {
        public static String country() {
            return FAKER.address().country();
        }

        public static String state() {
            return FAKER.address().state();
        }

        public static String city() {
            return FAKER.address().city();
        }

        public static String street() {
            return FAKER.address().streetName();
        }

        public static String zipCode() {
            return FAKER.address().zipCode();
        }

        public static String number() {
            return FAKER.address().buildingNumber();
        }

        public static Address validAddress() {
            return Address.newAddress(
                    country(),
                    state(),
                    city(),
                    street(),
                    zipCode(),
                    number(),
                    null
            );
        }

        public static Address invalidAddress(final String field){
            return Address.newAddress(
                    field.equals("country") ? null : country(),
                    field.equals("state") ? null : state(),
                    field.equals("city") ? null : city(),
                    field.equals("street") ? null : street(),
                    field.equals("zipCode") ? null : zipCode(),
                    field.equals("number") ? null : number(),
                    null
            );
        }
    }

    public static final class PurchaseItemFixture{
        public static PurchasedItem validPurchaseItem(String sellerId, Item item){
            return PurchasedItem.newPurchasedItem(
                    sellerId,
                    item,
                    positiveNumber(),
                    positiveNumber().doubleValue()
            );
        }
    }
}
