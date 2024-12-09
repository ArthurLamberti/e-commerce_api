package com.arthurlamberti.ecommerce.application.item.retrieve.list;

import com.arthurlamberti.ecommerce.domain.item.ItemGateway;

import java.util.List;

public class DefaultListItemUseCase extends ListItemUseCase{

    private final ItemGateway itemGateway;

    public DefaultListItemUseCase(
            final ItemGateway itemGateway
    ) {
        this.itemGateway = itemGateway;
    }

    @Override
    public List<ListItemOutput> execute() {
        return this.itemGateway.findAll()
                .stream()
                .map(ListItemOutput::from)
                .toList();
    }
}
