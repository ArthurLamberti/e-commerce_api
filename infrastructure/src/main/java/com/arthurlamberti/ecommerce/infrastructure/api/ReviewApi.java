package com.arthurlamberti.ecommerce.infrastructure.api;

import com.arthurlamberti.ecommerce.infrastructure.review.models.CreateReviewRequest;
import com.arthurlamberti.ecommerce.infrastructure.review.models.GetReviewByItemResponse;
import com.arthurlamberti.ecommerce.infrastructure.review.models.GetReviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "reviews")
@Tag(name = "Reviews")
public interface ReviewApi {


    @PostMapping(
            value = "/{itemId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Create a new review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created successfully"),
            @ApiResponse(responseCode = "422", description = "A validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    ResponseEntity<?> createReview(@RequestBody CreateReviewRequest input, @PathVariable String itemId);

    @GetMapping("/item/{itemId}")
    @Operation(summary = "List all reviews from an Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listed successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    GetReviewByItemResponse getByItemId(@PathVariable String itemId);


    @GetMapping("/{reviewId}")
    @Operation(summary = "get reviews=")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "get customer successfully"),
            @ApiResponse(responseCode = "422", description = "A invalid parameter was received"),
            @ApiResponse(responseCode = "500", description = "An internal server error was thrown"),
    })
    GetReviewResponse getById(@PathVariable String reviewId);
}
