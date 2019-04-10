package com.mabellou.dddsamplemab.domain.model.customer;

import com.mabellou.dddsamplemab.domain.shared.ValueObject;

import java.util.Objects;

public class Address implements ValueObject<Address>{
    private final String street;
    private final String streetNumber;
    private final String locality;
    private final String comment;

    public Address(final String street,
                   final String streetNumber,
                   final String locality,
                   final String comment) {
        Objects.requireNonNull(street);
        Objects.requireNonNull(streetNumber);
        Objects.requireNonNull(locality);

        this.street = street;
        this.streetNumber = streetNumber;
        this.locality = locality;
        this.comment = comment;
    }

    public String street(){
        return street;
    }

    public String streetNumber(){
        return streetNumber;
    }

    public String locality(){
        return locality;
    }

    public String comment(){
        return comment;
    }

    @Override
    public boolean sameValueAs(Address other) {
        return other!= null &&
                Objects.equals(street, other.street) &&
                Objects.equals(streetNumber, other.streetNumber) &&
                Objects.equals(locality, other.locality);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address other = (Address) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, streetNumber, locality, comment);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", locality='" + locality + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
