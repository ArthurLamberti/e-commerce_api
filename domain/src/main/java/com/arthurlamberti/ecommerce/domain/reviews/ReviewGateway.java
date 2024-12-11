package com.arthurlamberti.ecommerce.domain.reviews;

import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.item.ItemID;

import java.util.List;
import java.util.Optional;

public interface ReviewGateway {
    Review create(final Review aReview);

    Optional<Review> findById(final ReviewID anId);

    List<Review> findAllByItem(final String item);
}
