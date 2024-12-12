package com.arthurlamberti.ecommerce.infrastructure.purchase.persistence;


import com.arthurlamberti.ecommerce.domain.enums.PurchaseStatus;
import com.arthurlamberti.ecommerce.domain.purchase.Purchase;
import com.arthurlamberti.ecommerce.domain.purchase.PurchaseID;
import com.arthurlamberti.ecommerce.infrastructure.customer.persistence.CustomerJPAEntity;
import com.arthurlamberti.ecommerce.infrastructure.item.persistence.ItemJpaEntity;
import com.arthurlamberti.ecommerce.infrastructure.purchase_item.persistence.PurchasedItemJpaEntity;
import com.arthurlamberti.ecommerce.infrastructure.seller.persistence.SellerJPAEntity;
import com.arthurlamberti.ecommerce.infrastructure.shipping.persistence.ShippingJpaEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Entity(name = "purchases")
@Table(name = "purchases")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class PurchaseJpaEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private SellerJPAEntity seller;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerJPAEntity customer;

    @ManyToOne
    @JoinColumn(name = "shipping_id")
    private ShippingJpaEntity shipping;

    @OneToMany(mappedBy = "purchase")
    private List<PurchasedItemJpaEntity> purchasedItems;

    @Column(name = "total_value")
    private Double totalValue;

    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", nullable = true, columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public static PurchaseJpaEntity from(Purchase aPurchase, CustomerJPAEntity customer, SellerJPAEntity seller, List<ItemJpaEntity> items, ShippingJpaEntity shipping) {

        return new PurchaseJpaEntity(
                aPurchase.getId().getValue(),
                seller,
                customer,
                shipping,
                new ArrayList<>(),
                aPurchase.getTotalValue(),
                aPurchase.getStatus(),
                aPurchase.getCreatedAt(),
                aPurchase.getUpdatedAt(),
                aPurchase.getDeletedAt()
        );
    }

    public Purchase toAggregate() {
        return Purchase.with(
                PurchaseID.from(this.id),
                this.customer.getId(),
                this.seller.getId(),
                this.purchasedItems.stream().map(it -> it.getItem().getId()).toList(),
                isNull(this.shipping) ? "" : this.shipping.getId(),
                this.totalValue,
                this.status,
                this.createdAt,
                this.updatedAt,
                this.deletedAt
        );
    }
}
