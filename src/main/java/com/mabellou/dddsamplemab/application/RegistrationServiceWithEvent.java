package com.mabellou.dddsamplemab.application;

import com.mabellou.dddsamplemab.application.command.ChangeCustomerAddressCommand;
import com.mabellou.dddsamplemab.application.command.RegistrationCommand;
import com.mabellou.dddsamplemab.domain.model.customer.*;
import com.mabellou.dddsamplemab.domain.shared.EventStore;
import com.mabellou.dddsamplemab.domain.shared.EventStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RegistrationServiceWithEvent implements RegistrationService{

    private Logger logger = LoggerFactory.getLogger(RegistrationServiceWithEvent.class);

    private EventStore eventStore;

    @Autowired
    public RegistrationServiceWithEvent(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public String registerNewCustomer(RegistrationCommand registrationCommand) {
        final CustomerId customerId = eventStore.nextCustomerId();

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

        eventStore.appendToStream(customerId, 0, customer.changes());

        return customer.customerId().idString();
    }

    @Override
    public void changeCustomerAddress(ChangeCustomerAddressCommand command) {
        final CustomerId customerId = new CustomerId(command.customerId);

        EventStream eventStream = eventStore.loadEventStream(customerId);

        Customer customer = new Customer(eventStream.events);

        Address address = new Address(
                command.street,
                command.streetNumber,
                command.locality,
                command.comment
        );

        customer.changeCustomerAddress(address);

        eventStore.appendToStream(customerId, eventStream.version, customer.changes());
    }
}
