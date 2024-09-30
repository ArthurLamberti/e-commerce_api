package com.arthurlamberti.ecommerce.domain;

import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.seller.SellerID;
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
    }
}
