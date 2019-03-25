package com.mabellou.dddsamplemab.domain.model.customer;

import java.util.List;
import java.util.Optional;

public interface RegisteredCustomerList {

    Optional<Customer> findById(CustomerId customerId);

    List<Customer> findAll();

    void register(Customer customer);

    CustomerId nextCustomerId();
}
