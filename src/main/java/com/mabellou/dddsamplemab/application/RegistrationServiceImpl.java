package com.mabellou.dddsamplemab.application;

import com.mabellou.dddsamplemab.application.command.ChangeCustomerAddressCommand;
import com.mabellou.dddsamplemab.application.command.RegistrationCommand;
import com.mabellou.dddsamplemab.domain.model.customer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);

    private final RegisteredCustomerList registeredCustomerList;

    @Autowired
    public RegistrationServiceImpl(final RegisteredCustomerList registeredCustomerList) {
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

    public void changeCustomerAddress(ChangeCustomerAddressCommand command){
        final CustomerId customerId = new CustomerId(command.customerId);

        Customer customer = registeredCustomerList.findById(customerId)
                .orElseThrow(()-> new IllegalStateException("Customer not found"));

        Address address = new Address(
                command.street,
                command.streetNumber,
                command.locality,
                command.comment
        );

        customer.changeCustomerAddress(address);
    }
}
