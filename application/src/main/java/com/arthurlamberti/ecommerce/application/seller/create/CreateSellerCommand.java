package com.arthurlamberti.ecommerce.application.seller.create;

public record CreateSellerCommand(
        String name,
        String email,
        String description,
        String document
) {
    public static CreateSellerCommand with(
            final String name,
            final String email,
            final String description,
            final String document
    ) {
        return new CreateSellerCommand(name, email, description, document);
    }
}
