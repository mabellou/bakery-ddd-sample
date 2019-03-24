package com.mabellou.dddsamplemab.domain.model.customer;

import java.util.List;

public interface RegisteredCustomerList {

    Customer find(CustomerId customerId);

    List<Customer> findAll();

    void register(Customer customer);

    CustomerId nextCustomerId();
}
