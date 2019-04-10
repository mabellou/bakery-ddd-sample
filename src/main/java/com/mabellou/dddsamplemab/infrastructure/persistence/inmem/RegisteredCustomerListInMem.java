package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.model.customer.Customer;
import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.customer.RegisteredCustomerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RegisteredCustomerListInMem implements RegisteredCustomerList {

    private EventStoreInMem eventStore;

    @Autowired
    public RegisteredCustomerListInMem(EventStoreInMem eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public Optional<Customer> findById(CustomerId customerId) {
        return eventStore.findById(customerId, Customer::new);
    }

    @Override
    public List<Customer> findAll() {
        return eventStore.findAll("Customer", Customer::new);
    }

    @Override
    public void save(Customer customer) {
        eventStore.appendToStream(customer.customerId(), customer.version(), customer.changes());
    }

    @Override
    public CustomerId nextCustomerId() {
        return eventStore.nextId(CustomerId::new);
    }
}
