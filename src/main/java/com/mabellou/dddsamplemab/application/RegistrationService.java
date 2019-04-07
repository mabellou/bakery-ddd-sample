package com.mabellou.dddsamplemab.application;

import com.mabellou.dddsamplemab.application.command.ChangeCustomerAddressCommand;
import com.mabellou.dddsamplemab.application.command.RegistrationCommand;

public interface RegistrationService {
    String registerNewCustomer(RegistrationCommand registrationCommand);
    void changeCustomerAddress(ChangeCustomerAddressCommand command);
}
