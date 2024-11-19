package com.arthurlamberti.ecommerce.infrastructure.api;

import com.arthurlamberti.ecommerce.domain.pagination.Pagination;
import com.arthurlamberti.ecommerce.infrastructure.address.models.AddressListResponse;
import com.arthurlamberti.ecommerce.infrastructure.address.models.AddressResponse;
import com.arthurlamberti.ecommerce.infrastructure.address.models.CreateAddressRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "addresses")
@Tag(name = "Addresses")
public interface AddressAPI {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> createAddress(@RequestBody CreateAddressRequest input);

    @GetMapping
    @Operation(summary = "List all address paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    Pagination<AddressListResponse> list(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "sort", required = false, defaultValue = "city") final String sort,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") final String direction
    );

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Get a Address by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Address was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    AddressResponse getById(@PathVariable(name = "id") String id);

    @DeleteMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a address by it's identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Address was not found"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    void deleteById(@PathVariable(name = "id") String id);
}
