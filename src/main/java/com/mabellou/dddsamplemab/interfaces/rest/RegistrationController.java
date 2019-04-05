package com.mabellou.dddsamplemab.interfaces.rest;

import com.mabellou.dddsamplemab.application.RegistrationService;
import com.mabellou.dddsamplemab.application.command.ChangeCustomerAddressCommand;
import com.mabellou.dddsamplemab.application.command.RegistrationCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(final RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody RegistrationCommand registrationCommand){

        String customerId = registrationService.registerNewCustomer(registrationCommand);

        URI uri = URI.create(customerId);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/customer/{id}/address")
    public ResponseEntity changeAddress(@RequestBody ChangeCustomerAddressCommand command){
        registrationService.changeCustomerAddress(command);

        return ResponseEntity.noContent().build();
    }
}
