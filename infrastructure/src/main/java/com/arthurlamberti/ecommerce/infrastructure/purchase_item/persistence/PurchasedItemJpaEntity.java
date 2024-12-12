package com.arthurlamberti.ecommerce.infrastructure.purchase_item.persistence;

import com.arthurlamberti.ecommerce.infrastructure.item.persistence.ItemJpaEntity;
import com.arthurlamberti.ecommerce.infrastructure.purchase.persistence.PurchaseJpaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "purchase_items")
@Table(name = "purchase_items")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class PurchasedItemJpaEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private PurchaseJpaEntity purchase;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemJpaEntity item;

    private Integer qty;
    private Double price;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", nullable = true, columnDefinition = "DATETIME(6)")
    private Instant deletedAt;
}
