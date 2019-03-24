package com.mabellou.dddsamplemab.application;

import com.mabellou.dddsamplemab.application.command.RegistrationCommand;
import com.mabellou.dddsamplemab.domain.model.customer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService{

    private Logger logger = LoggerFactory.getLogger(RegistrationService.class);

    private final RegisteredCustomerList registeredCustomerList;

    @Autowired
    public RegistrationService(final RegisteredCustomerList registeredCustomerList) {
        this.registeredCustomerList = registeredCustomerList;
    }

    public String registerNewCustomer(RegistrationCommand registrationCommand) {
        final CustomerId customerId = registeredCustomerList.nextCustomerId();

        Customer customer = new Customer(
                customerId,
                new CustomerName(registrationCommand.username),
                new Address(
                        registrationCommand.street,
                        registrationCommand.streetNumber,
                        registrationCommand.locality,
                        registrationCommand.comment
                ),
                new Email(registrationCommand.email)
        );

        registeredCustomerList.register(customer);

        logger.info("Registered new Customer with id {}", customer.customerId().idString());
        return customer.customerId().idString();
    }
}
