package com.arthurlamberti.ecommerce.infrastructure.api.controllers;

import com.arthurlamberti.ecommerce.application.review.create.CreateReviewCommand;
import com.arthurlamberti.ecommerce.application.review.create.CreateReviewUseCase;
import com.arthurlamberti.ecommerce.application.review.retrieve.get.GetReviewUseCase;
import com.arthurlamberti.ecommerce.application.review.retrieve.get_by_item.GetReviewsByItemUseCase;
import com.arthurlamberti.ecommerce.infrastructure.api.ReviewApi;
import com.arthurlamberti.ecommerce.infrastructure.review.models.CreateReviewRequest;
import com.arthurlamberti.ecommerce.infrastructure.review.models.GetReviewByItemResponse;
import com.arthurlamberti.ecommerce.infrastructure.review.models.GetReviewResponse;
import com.arthurlamberti.ecommerce.infrastructure.review.presenters.ReviewApiPresent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class ReviewController implements ReviewApi {

    private final CreateReviewUseCase createReviewUseCase;
    private final GetReviewUseCase getReviewUseCase;
    private final GetReviewsByItemUseCase getReviewsByItemUseCase;

    public ReviewController(CreateReviewUseCase createReviewUseCase, GetReviewUseCase getReviewUseCase, GetReviewsByItemUseCase getReviewsByItemUseCase) {
        this.createReviewUseCase = createReviewUseCase;
        this.getReviewUseCase = getReviewUseCase;
        this.getReviewsByItemUseCase = getReviewsByItemUseCase;
    }

    @Override
    public ResponseEntity<?> createReview(CreateReviewRequest input, String itemId) {
        final var aCommand = CreateReviewCommand.with(
                itemId,
                input.customerId(),
                input.description(),
                input.rating()
        );

        final var output = this.createReviewUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/reviews/" + output.id())).body(output);
    }

    @Override
    public GetReviewByItemResponse getByItemId(String itemId) {
        return ReviewApiPresent.present(this.getReviewsByItemUseCase.execute(itemId));
    }

    @Override
    public GetReviewResponse getById(String reviewId) {
        return ReviewApiPresent.present(this.getReviewUseCase.execute(reviewId));
    }
}
