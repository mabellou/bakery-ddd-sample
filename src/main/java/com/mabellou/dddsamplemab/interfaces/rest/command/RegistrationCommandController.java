package com.mabellou.dddsamplemab.interfaces.rest.command;

import com.mabellou.dddsamplemab.application.RegistrationCommandService;
import com.mabellou.dddsamplemab.application.command.ChangeCustomerAddressCommand;
import com.mabellou.dddsamplemab.application.command.RegistrationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping
public class RegistrationCommandController {

    private final RegistrationCommandService registrationCommandService;

    @Autowired
    public RegistrationCommandController(final RegistrationCommandService registrationCommandService) {
        this.registrationCommandService = registrationCommandService;
    }

    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody RegistrationCommand registrationCommand){

        String customerId = registrationCommandService.registerNewCustomer(registrationCommand);

        URI uri = URI.create(customerId);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/customer/{id}/address")
    public ResponseEntity changeAddress(@RequestBody ChangeCustomerAddressCommand command){
        registrationCommandService.changeCustomerAddress(command);

        return ResponseEntity.noContent().build();
    }
}
