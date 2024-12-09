package com.arthurlamberti.ecommerce.infrastructure.api.controllers;

import com.arthurlamberti.ecommerce.application.item.create.CreateItemCommand;
import com.arthurlamberti.ecommerce.application.item.create.CreateItemUseCase;
import com.arthurlamberti.ecommerce.application.item.retrieve.get.GetItemOutput;
import com.arthurlamberti.ecommerce.application.item.retrieve.get.GetItemUseCase;
import com.arthurlamberti.ecommerce.application.item.retrieve.list.ListItemOutput;
import com.arthurlamberti.ecommerce.application.item.retrieve.list.ListItemUseCase;
import com.arthurlamberti.ecommerce.infrastructure.api.ItemApi;
import com.arthurlamberti.ecommerce.infrastructure.item.models.CreateItemRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class ItemController implements ItemApi {

    private final CreateItemUseCase createItemUseCase;
    private final GetItemUseCase getItemUseCase;
    private final ListItemUseCase listItemUseCase;

    public ItemController(
            final CreateItemUseCase createItemUseCase,
            final GetItemUseCase getItemUseCase,
            final ListItemUseCase listItemUseCase
    ) {
        this.createItemUseCase = createItemUseCase;
        this.getItemUseCase = getItemUseCase;
        this.listItemUseCase = listItemUseCase;
    }


    @Override
    public ResponseEntity<?> createItem(CreateItemRequest input) {
        final var aCommand = CreateItemCommand.with(
                input.name(),
                input.description(),
                input.imageUrl(),
                input.stock(),
                input.sellerId()
        );

        final var output = this.createItemUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/items/" + output.id())).body(output);
    }

    @Override
    public List<ListItemOutput> list() {
        return null;
    }

    @Override
    public GetItemOutput getItemById(String customerId) {
        return null;
    }
}
