package com.arthurlamberti.ecommerce.infrastructure.customer.persistence;

import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.customer.CustomerID;
import com.arthurlamberti.ecommerce.infrastructure.address.persistence.AddressJPAEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "customers")
@Table(name = "customers")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class CustomerJPAEntity {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "document")
    private String document;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "customer")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AddressJPAEntity> addresses;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", nullable = true, columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    public CustomerJPAEntity() {
    }

    public static CustomerJPAEntity from(final Customer customer) {
        return new CustomerJPAEntity(
                customer.getId().getValue(),
                customer.getName(),
                customer.getEmail(),
                customer.getDocument(),
                customer.isActive(),
//                List.copyOf(customer.getAddress())
                new ArrayList<>(),
                customer.getCreatedAt(),
                customer.getUpdatedAt(),
                customer.getDeletedAt()
        );
    }

    public Customer toAggregate() {
        final var addressList = this.addresses
                .stream()
                .map(AddressJPAEntity::toAggregate)
                .toList();
        return Customer.with(
                CustomerID.from(this.id),
                this.name,
                this.email,
                this.document,
                this.active,
                addressList,
                this.createdAt,
                this.updatedAt,
                this.deletedAt
        );
    }
}
