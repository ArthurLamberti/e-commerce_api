package com.arthurlamberti.ecommerce.infrastructure.api;

import com.arthurlamberti.ecommerce.application.item.retrieve.get.GetItemOutput;
import com.arthurlamberti.ecommerce.application.item.retrieve.list.ListItemOutput;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.CreateCustomerRequest;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.CustomerListResponse;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.GetCustomerResponse;
import com.arthurlamberti.ecommerce.infrastructure.item.models.CreateItemRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "items")
@Tag(name = "Items")
public interface ItemApi {


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> createItem(@RequestBody CreateItemRequest input);

    @GetMapping
    @Operation(summary = "List all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    List<ListItemOutput> list();

    @GetMapping("/{customerId}")
    @Operation(summary = "get customers by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get customer successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    GetItemOutput getItemById(@PathVariable(name = "customerId") String customerId);

}
