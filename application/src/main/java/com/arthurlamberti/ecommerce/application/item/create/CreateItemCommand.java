package com.arthurlamberti.ecommerce.application.item.create;

public record CreateItemCommand(
        String name,
        String description,
        String imageUrl,
        Integer stock,
        String sellerId
) {
    public static CreateItemCommand with(
            String name,
            String description,
            String imageUrl,
            Integer stock,
            String sellerId
    ) {
        return new CreateItemCommand(name, description, imageUrl, stock, sellerId);
    }
}
