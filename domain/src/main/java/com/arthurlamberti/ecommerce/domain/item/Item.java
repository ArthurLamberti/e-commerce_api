package com.arthurlamberti.ecommerce.domain.item;

import com.arthurlamberti.ecommerce.domain.AggregateRoot;
import com.arthurlamberti.ecommerce.domain.seller.Status;
import com.arthurlamberti.ecommerce.domain.utils.InstantUtils;
import com.arthurlamberti.ecommerce.domain.validation.ValidationHandler;
import lombok.Getter;

import java.time.Instant;

@Getter
public class Item extends AggregateRoot<ItemID> {

    private String sellerId;
    private String name;
    private String description;
    private String imageUrl;
    private Status status;
    private Integer qtyAvailable;
    private Integer qtySold;
    private Integer scoreReview;
    private Integer qtyReviews;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    protected Item(final ItemID itemID,
                   final String sellerId,
                   final String name,
                   final String description,
                   final String imageUrl,
                   final Status status,
                   final Integer qtyAvailable,
                   final Integer qtySold,
                   final Integer scoreReview,
                   final Integer qtyReviews,
                   final Instant createdAt,
                   final Instant updatedAt,
                   final Instant deletedAt) {
        super(itemID);
        this.sellerId = sellerId;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.status = status;
        this.qtyAvailable = qtyAvailable;
        this.qtySold = qtySold;
        this.scoreReview = scoreReview;
        this.qtyReviews = qtyReviews;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Item newItem(
            final String sellerId,
            final String name,
            final String description,
            final String imageUrl
    ) {
        final var anId = ItemID.unique();
        final var now = InstantUtils.now();
        return new Item(anId, sellerId, name, description, imageUrl, null, 0, 0, 0, 0, now, now, null);
    }

    @Override
    public void validate(ValidationHandler handler) {

    }
}
