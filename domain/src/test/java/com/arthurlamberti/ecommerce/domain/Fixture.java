package com.arthurlamberti.ecommerce.domain;

import com.github.javafaker.Faker;


public final class Fixture {

    private static final Faker FAKER = new Faker();

    public static String name() {
        return FAKER.name().fullName();
    }

    public static String characters(Integer qty) {
        return FAKER.lorem().characters(qty);
    }

    public static String sellerName() {
        return FAKER.name().firstName();
    }

    public static Boolean isActive() {
        return FAKER.bool().bool();
    }
}
