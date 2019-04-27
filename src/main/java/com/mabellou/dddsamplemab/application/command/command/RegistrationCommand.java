package com.mabellou.dddsamplemab.application.command.command;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationCommand {
    public final String username;
    public final String email;
    public final String street;
    public final String streetNumber;
    public final String locality;
    public final String comment;

    @JsonCreator
    public RegistrationCommand(@JsonProperty("username") final String username,
                               @JsonProperty("email") final String email,
                               @JsonProperty("street") final String street,
                               @JsonProperty("streetNumber") final String streetNumber,
                               @JsonProperty("locality") final String locality,
                               @JsonProperty("comment")final String comment) {
        this.username = username;
        this.email = email;
        this.street = street;
        this.streetNumber = streetNumber;
        this.locality = locality;
        this.comment = comment;
    }
}
