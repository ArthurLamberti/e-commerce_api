package com.arthurlamberti.ecommerce.infrastructure.item.persistence;


import com.arthurlamberti.ecommerce.domain.item.Item;
import com.arthurlamberti.ecommerce.domain.item.ItemID;
import com.arthurlamberti.ecommerce.infrastructure.purchase.persistence.PurchaseJpaEntity;
import com.arthurlamberti.ecommerce.infrastructure.purchase_item.persistence.PurchasedItemJpaEntity;
import com.arthurlamberti.ecommerce.infrastructure.review.persistence.ReviewJpaEntity;
import com.arthurlamberti.ecommerce.infrastructure.seller.persistence.SellerJPAEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "items")
@Table(name = "items")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class ItemJpaEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private SellerJPAEntity seller;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column
    private Double price;

    @Column(name = "qty_available", nullable = false)
    private Integer qtyAvailable;

    @OneToMany(mappedBy = "item")
    private List<ReviewJpaEntity> reviews;

    @OneToMany(mappedBy = "item")
    private List<PurchasedItemJpaEntity> purchase;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", nullable = true, columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public ItemJpaEntity() {
    }

    public static ItemJpaEntity from(final Item item, final SellerJPAEntity seller) {
        return new ItemJpaEntity(
                item.getId().getValue(),
                seller,
                item.getName(),
                item.getDescription(),
                item.getImageUrl(),
                item.getPrice(),
                item.getQtyAvailable(),
                new ArrayList<>(),
                new ArrayList<>(),
                item.getCreatedAt(),
                item.getUpdatedAt(),
                item.getDeletedAt()
        );
    }

    public Item toAggregate() {
        return Item.with(
                ItemID.from(this.id),
                this.name,
                this.description,
                this.imageUrl,
                this.price,
                this.qtyAvailable,
                this.seller.getId(),
                this.createdAt,
                this.updatedAt,
                this.deletedAt
        );
    }
}
