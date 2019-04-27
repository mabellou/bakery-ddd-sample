package com.mabellou.dddsamplemab.interfaces.rest.command;

import com.mabellou.dddsamplemab.application.PlacedOrderCommandService;
import com.mabellou.dddsamplemab.application.command.PlaceAnOrderCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping("/placedorder")
public class PlacedOrderCommandController {

    private final PlacedOrderCommandService placedOrderCommandService;

    @Autowired
    public PlacedOrderCommandController(PlacedOrderCommandService placedOrderCommandService) {
        this.placedOrderCommandService = placedOrderCommandService;
    }

    @PostMapping
    public ResponseEntity placeAnOrder(@RequestBody PlaceAnOrderCommand placeAnOrderCommand){
        String placedOrderId = placedOrderCommandService.placeAnOrder(placeAnOrderCommand);

        URI uri = URI.create(placedOrderId);

        return ResponseEntity.created(uri).build();
    }
}
