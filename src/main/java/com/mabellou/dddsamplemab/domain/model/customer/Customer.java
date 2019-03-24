package com.mabellou.dddsamplemab.domain.model.customer;

import com.mabellou.dddsamplemab.domain.shared.Entity;

import java.util.Objects;

public class Customer implements Entity<Customer> {

    private CustomerId customerId;
    private CustomerName customerName;
    private Address address;
    private Email email;

    public Customer(final CustomerId customerId,
                    final CustomerName customerName,
                    final Address address,
                    final Email email){
        Objects.requireNonNull(customerId);
        Objects.requireNonNull(customerName);
        Objects.requireNonNull(address);
        Objects.requireNonNull(email);

        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.email = email;
    }

    public CustomerId customerId(){
        return this.customerId;
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
