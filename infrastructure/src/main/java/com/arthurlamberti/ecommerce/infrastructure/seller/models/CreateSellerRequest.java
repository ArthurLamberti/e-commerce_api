package com.arthurlamberti.ecommerce.infrastructure.seller.models;

public record CreateSellerRequest (

        String name,
        String email,
        String document,
        String description
){
}
