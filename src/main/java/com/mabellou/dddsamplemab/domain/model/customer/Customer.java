package com.mabellou.dddsamplemab.domain.model.customer;

import com.mabellou.dddsamplemab.domain.shared.Entity;
import com.mabellou.dddsamplemab.domain.shared.Event;
import com.mabellou.dddsamplemab.domain.shared.EventStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Customer extends Entity<Customer> {
    private Logger logger = LoggerFactory.getLogger(Customer.class);

    private CustomerId customerId;
    private CustomerName customerName;
    private Address address;
    private Email email;

    public Customer(EventStream stream){
        buildFrom(stream);
    }

    public Customer(final CustomerId customerId,
                    final CustomerName customerName,
                    final Address address,
                    final Email email){
        Objects.requireNonNull(customerId);
        Objects.requireNonNull(customerName);
        Objects.requireNonNull(address);
        Objects.requireNonNull(email);

        apply(new CustomerCreatedEvent(customerId, customerName, address, email));
    }

    public void changeCustomerAddress(Address address){
        Objects.requireNonNull(address);
        apply(new AddressChangedEvent(this.customerId, address));
    }

    protected void mutate(Event event){
        if(event instanceof CustomerCreatedEvent){
            when((CustomerCreatedEvent) event);
        } else if(event instanceof AddressChangedEvent){
            when((AddressChangedEvent) event);
        }
    }

    protected void when(CustomerCreatedEvent event){
        logger.info("The event {} is applied!", event.eventName);
        this.customerId = event.customerId;
        this.customerName = event.customerName;
        this.address = event.address;
        this.email = event.email;
    }

    protected void when(AddressChangedEvent event){
        logger.info("The event {} is applied!", event.eventName);
        this.address = event.address;
    }

    public CustomerId customerId(){
        return this.customerId;
    }

    public Address address(){
        return address;
    }

    @Override
    public boolean sameIdentityAs(Customer other) {
        return other != null && customerId.equals(other.customerId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer other = (Customer) o;
        return sameIdentityAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName=" + customerName +
                ", address=" + address +
                ", email=" + email +
                '}';
    }
}
