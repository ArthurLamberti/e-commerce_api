package com.arthurlamberti.ecommerce.application.customer.create;

public record CreateCustomerCommand(
        String name,
        String email,
        String document
) {
    public static CreateCustomerCommand with(
            final String name,
            final String email,
            final String document
    ) {
        return new CreateCustomerCommand(name, email, document);
    }
}
