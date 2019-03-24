package com.mabellou.dddsamplemab.interfaces.rest;

import com.mabellou.dddsamplemab.application.RegistrationService;
import com.mabellou.dddsamplemab.application.command.RegistrationCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(final RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity register(@RequestBody RegistrationCommand registrationCommand){

        String customerId = registrationService.registerNewCustomer(registrationCommand);

        URI uri = URI.create(customerId);
        return ResponseEntity.created(uri).build();
    }

}
