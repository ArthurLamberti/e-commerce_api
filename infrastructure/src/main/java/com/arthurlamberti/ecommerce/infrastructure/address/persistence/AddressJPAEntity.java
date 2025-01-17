package com.arthurlamberti.ecommerce.infrastructure.address.persistence;

import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.address.AddressID;
import com.arthurlamberti.ecommerce.infrastructure.customer.persistence.CustomerJPAEntity;
import com.arthurlamberti.ecommerce.infrastructure.seller.persistence.SellerJPAEntity;
import com.arthurlamberti.ecommerce.infrastructure.shipping.persistence.ShippingJpaEntity;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity(name = "addresses")
@Table(name = "addresses")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class AddressJPAEntity {

    @Id
    private String id;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "numeral", nullable = false)
    private String number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerJPAEntity customer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private SellerJPAEntity seller;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private List<ShippingJpaEntity> shipping;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", nullable = true, columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public AddressJPAEntity() {
    }

    public static AddressJPAEntity from(final Address anAddress) {
        return new AddressJPAEntity(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                anAddress.getState(),
                anAddress.getCity(),
                anAddress.getStreet(),
                anAddress.getZipCode(),
                anAddress.getNumeral(),
                anAddress.getComplement(),
                anAddress.isActive(),
                null, null,
                null,
                anAddress.getCreatedAt(),
                anAddress.getUpdatedAt(),
                anAddress.getDeletedAt()
        );
    }

    public static AddressJPAEntity from(Address anAddress, CustomerJPAEntity customer, SellerJPAEntity seller) {
        return new AddressJPAEntity(
                anAddress.getId().getValue(),
                anAddress.getCountry(),
                anAddress.getState(),
                anAddress.getCity(),
                anAddress.getStreet(),
                anAddress.getZipCode(),
                anAddress.getNumeral(),
                anAddress.getComplement(),
                anAddress.isActive(),
                customer,
                seller,
                null,
                anAddress.getCreatedAt(),
                anAddress.getUpdatedAt(),
                anAddress.getDeletedAt()
        );
    }

    public Address toAggregate() {
        return Address.with(
                AddressID.from(this.id),
                this.country,
                this.state,
                this.city,
                this.street,
                this.zipCode,
                this.number,
                this.complement,
                this.active,
                this.customer != null ? this.customer.getId() : null,
                this.seller != null ? this.seller.getId() : null,
                this.createdAt,
                this.updatedAt,
                this.deletedAt
        );
    }
}
