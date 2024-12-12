package com.arthurlamberti.ecommerce.infrastructure.address;

import com.arthurlamberti.ecommerce.domain.address.Address;
import com.arthurlamberti.ecommerce.domain.address.AddressGateway;
import com.arthurlamberti.ecommerce.domain.address.AddressID;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.pagination.Pagination;
import com.arthurlamberti.ecommerce.domain.pagination.SearchQuery;
import com.arthurlamberti.ecommerce.infrastructure.address.persistence.AddressJPAEntity;
import com.arthurlamberti.ecommerce.infrastructure.address.persistence.AddressRepository;
import com.arthurlamberti.ecommerce.infrastructure.customer.persistence.CustomerRepository;
import com.arthurlamberti.ecommerce.infrastructure.seller.persistence.SellerRepository;
import com.arthurlamberti.ecommerce.infrastructure.utils.SpecificationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.arthurlamberti.ecommerce.infrastructure.utils.SpecificationUtils.like;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static org.springframework.data.jpa.domain.Specification.where;

@Component
@Transactional
public class AddressMySQLGateway implements AddressGateway {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

    public AddressMySQLGateway(
            final AddressRepository addressRepository,
            final CustomerRepository customerRepository,
            final SellerRepository sellerRepository) {
        this.addressRepository = requireNonNull(addressRepository);
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Address create(final Address anAddress) {
        return save(anAddress);
    }

    @Override
    public Optional<Address> findById(AddressID anId) {
        return this.addressRepository.findById(anId.getValue())
                .map(AddressJPAEntity::toAggregate);
    }

    @Override
    public Address update(final Address address) {
        return save(address);
    }

    @Override
    public void deleteById(AddressID anId) {
        final var existingAddress = this.addressRepository.findById(anId.getValue());
        if (existingAddress.isPresent()) {
            final var address = existingAddress.get().toAggregate();
            address.deactivate();
            save(address);
        }
    }

    @Override
    public Pagination<Address> findAll(SearchQuery aQuery) {
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );
        final var where = Optional.of(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);
        final var pageResult = this.addressRepository.findAll(where(where), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(AddressJPAEntity::toAggregate).toList()
        );
    }

    private Address save(Address anAddress) {
        final var customer = customerRepository.findById(anAddress.getCustomerId()).orElse(null);
        final var seller = sellerRepository.findById(anAddress.getSellerId()).orElse(null);

        return this.addressRepository.save(AddressJPAEntity.from(anAddress, customer, seller))
                .toAggregate();
    }

    private Specification<AddressJPAEntity> assembleSpecification(final String terms) {
        final Specification<AddressJPAEntity> countryLike = like("country", terms);
        final Specification<AddressJPAEntity> cityLike = like("city", terms);
        final Specification<AddressJPAEntity> streetLike = like("street", terms);
        return countryLike.or(cityLike).or(streetLike);
    }
}
