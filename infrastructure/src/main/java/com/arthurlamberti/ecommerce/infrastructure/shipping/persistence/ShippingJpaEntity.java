package com.arthurlamberti.ecommerce.infrastructure.shipping.persistence;

import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.enums.ShippingStatus;
import com.arthurlamberti.ecommerce.domain.shipping.Shipping;
import com.arthurlamberti.ecommerce.domain.shipping.ShippingID;
import com.arthurlamberti.ecommerce.infrastructure.address.persistence.AddressJPAEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.Optional;

@Entity(name = "shippings")
@Table(name = "shippings")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Data
public class ShippingJpaEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressJPAEntity address;

    @Column
    private String code;

    @Column
    private Double price;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", nullable = true, columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public static ShippingJpaEntity from(Shipping aShipping, AddressJPAEntity address) {
        return new ShippingJpaEntity(
                aShipping.getId().getValue(),
                address,
                aShipping.getCode(),
                aShipping.getPrice(),
                aShipping.getStatus(),
                aShipping.getCreatedAt(),
                aShipping.getUpdatedAt(),
                aShipping.getDeletedAt()
        );
    }

    public Shipping toAggregate() {
        return Shipping.with(
                ShippingID.from(this.id),
                this.address.getId(),
                null,
                this.code,
                this.price,
                this.status,
                this.createdAt,
                this.updatedAt,
                this.deletedAt
        );
    }
}
