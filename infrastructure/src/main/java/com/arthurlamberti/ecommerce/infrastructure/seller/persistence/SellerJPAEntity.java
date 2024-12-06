package com.arthurlamberti.ecommerce.infrastructure.seller.persistence;

import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.customer.CustomerID;
import com.arthurlamberti.ecommerce.domain.seller.Seller;
import com.arthurlamberti.ecommerce.domain.seller.SellerID;
import com.arthurlamberti.ecommerce.infrastructure.address.persistence.AddressJPAEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity(name = "sellers")
@Table(name = "sellers")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class SellerJPAEntity {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "document")
    private String document;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<AddressJPAEntity> addresses;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", nullable = true, columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public SellerJPAEntity(){}

    public static SellerJPAEntity from(final Seller seller) {
        return new SellerJPAEntity(
                seller.getId().getValue(),
                seller.getName(),
                seller.getEmail(),
                seller.getDocument(),
                seller.getDescription(),
                seller.isActive(),
//                List.copyOf(customer.getAddress())
                null,
                seller.getCreatedAt(),
                seller.getUpdatedAt(),
                seller.getDeletedAt()
        );
    }

    public Seller toAggregate() {
        return Seller.with(
                SellerID.from(this.id),
                this.name,
                this.email,
                this.document,
                this.description,
                this.active,
                this.createdAt,
                this.updatedAt,
                this.deletedAt
        );
    }
}
