package com.arthurlamberti.ecommerce.infrastructure.review;

import com.arthurlamberti.ecommerce.domain.reviews.Review;
import com.arthurlamberti.ecommerce.domain.reviews.ReviewGateway;
import com.arthurlamberti.ecommerce.domain.reviews.ReviewID;
import com.arthurlamberti.ecommerce.infrastructure.customer.persistence.CustomerRepository;
import com.arthurlamberti.ecommerce.infrastructure.item.persistence.ItemRepository;
import com.arthurlamberti.ecommerce.infrastructure.review.persistence.ReviewJpaEntity;
import com.arthurlamberti.ecommerce.infrastructure.review.persistence.ReviewRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ReviewMySQLGateway implements ReviewGateway {

    private final ReviewRepository reviewRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public ReviewMySQLGateway(ReviewRepository reviewRepository, CustomerRepository customerRepository, ItemRepository itemRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public Review create(Review aReview) {
        final var customer = customerRepository.findById(aReview.getCustomerId()).orElse(null);
        final var item = itemRepository.findById(aReview.getItemId()).orElse(null);
        return this.reviewRepository.save(
                ReviewJpaEntity.from(aReview, customer, item)
        ).toAggregate();
    }

    @Override
    public Optional<Review> findById(ReviewID anId) {
        return this.reviewRepository.findById(anId.getValue())
                .map(ReviewJpaEntity::toAggregate);
    }

    @Override
    public List<Review> findAllByItem(String item) {
        return this.reviewRepository.findAllByItemId(item)
                .stream()
                .map(ReviewJpaEntity::toAggregate)
                .toList();
    }
}
