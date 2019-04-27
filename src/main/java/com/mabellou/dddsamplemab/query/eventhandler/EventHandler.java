package com.mabellou.dddsamplemab.query.eventhandler;

import com.mabellou.dddsamplemab.domain.model.availableproduct.AvailableProductCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    @EventListener
    public void handleAvailableProductCreatedEvent(AvailableProductCreatedEvent event) {
        System.out.println("--------------------------------------- event with id: " + event.id);
    }
}
