package com.mabellou.dddsamplemab.domain.model.customer;

import com.mabellou.dddsamplemab.domain.shared.Event;

public class AddressChangedEvent extends Event {
    public final Address address;

    public AddressChangedEvent(CustomerId customerId, Address address) {
        super(customerId,  "Customer", "AddressChangedEvent");
        this.address = address;
    }
}
