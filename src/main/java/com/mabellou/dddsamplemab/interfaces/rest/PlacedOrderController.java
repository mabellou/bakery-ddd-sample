package com.mabellou.dddsamplemab.interfaces.rest;

import com.mabellou.dddsamplemab.application.PlacedOrderService;
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
public class PlacedOrderController {

    private final PlacedOrderService placedOrderService;

    @Autowired
    public PlacedOrderController(PlacedOrderService placedOrderService) {
        this.placedOrderService = placedOrderService;
    }

    @PostMapping
    public ResponseEntity placeAnOrder(@RequestBody PlaceAnOrderCommand placeAnOrderCommand){
        String placedOrderId = placedOrderService.placeAnOrder(placeAnOrderCommand);

        URI uri = URI.create(placedOrderId);

        return ResponseEntity.created(uri).build();
    }


}
