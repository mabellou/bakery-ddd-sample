package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.model.customer.Customer;
import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.customer.RegisteredCustomerList;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RegisteredCustomerListInMem implements RegisteredCustomerList {

    private Map<CustomerId, Customer> customersDb;

    public RegisteredCustomerListInMem() {
        this.customersDb = new HashMap<>();
    }

    @Override
    public Customer find(CustomerId customerId) {
        return customersDb.get(customerId);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customersDb.values());
    }

    @Override
    public void register(Customer customer) {
        CustomerId id = customer.customerId();

        if(customersDb.get(id) != null){
            throw new IllegalStateException("The id already exists");
        }

        customersDb.put(id, customer);
    }

    @Override
    public CustomerId nextCustomerId() {
        String random = UUID.randomUUID().toString().toUpperCase();
        return new CustomerId(
                random.substring(0, random.indexOf("-"))
        );
    }
}
