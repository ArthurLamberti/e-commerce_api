package com.arthurlamberti.ecommerce.infrastructure.api;

import com.arthurlamberti.ecommerce.infrastructure.address.models.CreateAddressRequest;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.CreateCustomerRequest;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.CustomerListResponse;
import com.arthurlamberti.ecommerce.infrastructure.customer.models.GetCustomerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "customers")
@Tag(name = "Customers")
public interface CustomerAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> createCustomer(@RequestBody CreateCustomerRequest input);

    @GetMapping
    @Operation(summary = "List all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    List<CustomerListResponse> list();

    @GetMapping("/{customerId}")
    @Operation(summary = "get customers by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get customer successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    GetCustomerResponse getCustomerById(@PathVariable(name = "customerId") String customerId);

}
