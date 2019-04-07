package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.model.customer.Customer;
import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.customer.RegisteredCustomerList;
import com.mabellou.dddsamplemab.domain.shared.EntityId;
import com.mabellou.dddsamplemab.domain.shared.Event;
import com.mabellou.dddsamplemab.domain.shared.EventStore;
import com.mabellou.dddsamplemab.domain.shared.EventStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RegisteredCustomerListInMem implements RegisteredCustomerList {

    private EventStore eventStore;

    @Autowired
    public RegisteredCustomerListInMem(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public Optional<Customer> findById(CustomerId customerId) {
        EventStream eventStream = eventStore.loadEventStream(customerId);
        if(eventStream.events.isEmpty()){
          return Optional.empty();
        }
        return Optional.of(new Customer(eventStream));
    }

    @Override
    public List<Customer> findAll() {
        List<EventStream> eventStreams = eventStore.loadEventStreams("Customer");
        return eventStreams.stream()
                .map(Customer::new)
                .collect(Collectors.toList());
    }


    @Override
    public void save(Customer customer) {
        eventStore.appendToStream(customer.customerId(), customer.version(), customer.changes());
    }

    @Override
    public CustomerId nextCustomerId() {
        String random = UUID.randomUUID().toString().toUpperCase();
        return new CustomerId(
                random.substring(0, random.indexOf("-"))
        );
    }
}
