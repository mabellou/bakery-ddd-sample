package com.mabellou.dddsamplemab.domain.model.customer;

import com.mabellou.dddsamplemab.domain.shared.ValueObject;

import java.util.Objects;

public class CustomerName implements ValueObject<CustomerName> {
    private final String username;

    public CustomerName(final String username) {
        Objects.requireNonNull(username);
        this.username = username;
    }

    @Override
    public boolean sameValueAs(CustomerName other) {
        return other != null && username.equals(other.username);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerName other = (CustomerName) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
