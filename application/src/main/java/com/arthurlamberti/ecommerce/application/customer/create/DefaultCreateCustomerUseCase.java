package com.arthurlamberti.ecommerce.application.customer.create;

import com.arthurlamberti.ecommerce.domain.customer.Customer;
import com.arthurlamberti.ecommerce.domain.customer.CustomerGateway;
import com.arthurlamberti.ecommerce.domain.exceptions.NotificationException;
import com.arthurlamberti.ecommerce.domain.validation.handler.Notification;

public class DefaultCreateCustomerUseCase extends CreateCustomerUseCase {

    private final CustomerGateway customerGateway;

    public DefaultCreateCustomerUseCase(final CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    @Override
    public CreateCustomerOutput execute(CreateCustomerCommand aCommand) {
        final var notification = Notification.create();
        final var aCustomer = notification.validate(
                () -> Customer.newCustomer(
                        aCommand.name(),
                        aCommand.email(),
                        aCommand.document()
                )
        );
        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate Customer", notification);
        }
        return CreateCustomerOutput.from(this.customerGateway.create(aCustomer));
    }
}
