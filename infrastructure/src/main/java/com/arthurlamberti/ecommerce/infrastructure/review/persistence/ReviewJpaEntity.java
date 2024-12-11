package com.arthurlamberti.ecommerce.infrastructure.review.persistence;


import com.arthurlamberti.ecommerce.domain.reviews.Review;
import com.arthurlamberti.ecommerce.domain.reviews.ReviewID;
import com.arthurlamberti.ecommerce.infrastructure.customer.persistence.CustomerJPAEntity;
import com.arthurlamberti.ecommerce.infrastructure.item.persistence.ItemJpaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "reviews")
@Table(name = "reviews")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class ReviewJpaEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerJPAEntity customer;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemJpaEntity item;
    private String description;
    private Double rating;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", nullable = true, columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public static ReviewJpaEntity from(final Review review, final CustomerJPAEntity customer, final ItemJpaEntity item) {
        return new ReviewJpaEntity(
                review.getId().getValue(),
                customer,
                item,
                review.getDescription(),
                review.getRating(),
                review.getCreatedAt(),
                review.getUpdatedAt(),
                review.getDeletedAt()
        );
    }

    public Review toAggregate() {
        return Review.with(
                ReviewID.from(this.id),
                this.customer.getId(),
                this.item.getId(),
                this.description,
                this.rating,
                this.createdAt,
                this.updatedAt,
                this.deletedAt
        );
    }
}
