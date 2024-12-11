package com.arthurlamberti.ecommerce.infrastructure.review.presenters;

import com.arthurlamberti.ecommerce.application.review.retrieve.get.GetReviewOutput;
import com.arthurlamberti.ecommerce.application.review.retrieve.get_by_item.GetReviewsByItemOutput;
import com.arthurlamberti.ecommerce.application.review.retrieve.get_by_item.SummaryGetReviewsByItemOutput;
import com.arthurlamberti.ecommerce.infrastructure.review.models.GetReviewByItemResponse;
import com.arthurlamberti.ecommerce.infrastructure.review.models.GetReviewResponse;

import java.util.List;

public interface ReviewApiPresent {
    static GetReviewByItemResponse present(SummaryGetReviewsByItemOutput execute) {
        return new GetReviewByItemResponse(
                execute.itemId(),
                execute.averageRating(),
                execute.amountReviews(),
                execute.reviews().stream().map(ReviewApiPresent::present).toList()
        );
    }

    static GetReviewResponse present(GetReviewsByItemOutput review) {
        return new GetReviewResponse(
                review.id(),
                review.itemId(),
                review.rating()
        );
    }

    static GetReviewResponse present(GetReviewOutput execute) {
        return new GetReviewResponse(
                execute.id(),
                execute.itemId(),
                execute.rating()
        );
    }
}
