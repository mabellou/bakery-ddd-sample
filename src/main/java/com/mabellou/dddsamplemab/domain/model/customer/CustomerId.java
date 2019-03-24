package com.mabellou.dddsamplemab.domain.model.customer;

import com.mabellou.dddsamplemab.domain.shared.ValueObject;

import java.util.Objects;

public class CustomerId implements ValueObject<CustomerId> {
    private final String id;

    public CustomerId(final String id){
        Objects.requireNonNull(id);
        this.id = id;
    }

    public String idString(){
        return id;
    }

    @Override
    public boolean sameValueAs(CustomerId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerId that = (CustomerId) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CustomerId{" +
                "id='" + id + '\'' +
                '}';
    }
}
