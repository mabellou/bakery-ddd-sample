package com.mabellou.dddsamplemab.domain.model.customer;

import com.mabellou.dddsamplemab.domain.shared.Event;

public class CustomerCreatedEvent extends Event {
    public final CustomerId customerId;
    public final CustomerName customerName;
    public final Address address;
    public final Email email;

    public CustomerCreatedEvent(final CustomerId customerId,
                                final CustomerName customerName,
                                final Address address,
                                final Email email) {
        super(customerId, "CustomerCreatedEvent");
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.email = email;
    }
}
