package com.mabellou.dddsamplemab.application.command.command;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChangeCustomerAddressCommand {
    public final String customerId;
    public final String street;
    public final String streetNumber;
    public final String locality;
    public final String comment;

    @JsonCreator
    public ChangeCustomerAddressCommand(@JsonProperty("id") final String customerId,
                                        @JsonProperty("street") final String street,
                                        @JsonProperty("streetNumber") final String streetNumber,
                                        @JsonProperty("locality") final String locality,
                                        @JsonProperty("comment")final String comment) {
        this.customerId = customerId;
        this.street = street;
        this.streetNumber = streetNumber;
        this.locality = locality;
        this.comment = comment;
    }
}
