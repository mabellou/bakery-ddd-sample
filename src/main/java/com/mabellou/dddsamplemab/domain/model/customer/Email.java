package com.mabellou.dddsamplemab.domain.model.customer;

import com.mabellou.dddsamplemab.domain.shared.ValueObject;

import java.util.Objects;

public class Email implements ValueObject<Email> {

    private final String email;

    public Email(final String email) {
        Objects.requireNonNull(email);
        this.email = email;
    }

    @Override
    public boolean sameValueAs(Email other) {
        return other != null && email.equals(other.email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email other = (Email) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
