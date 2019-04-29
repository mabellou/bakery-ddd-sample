package com.mabellou.dddsamplemab.query.eventhandler;

import com.mabellou.dddsamplemab.domain.model.availableproduct.AvailableProductCreatedEvent;
import com.mabellou.dddsamplemab.query.data.Database;
import com.mabellou.dddsamplemab.query.representation.AvailableProductRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AvailableProductEventHandler {

    private Database database;

    @Autowired
    public AvailableProductEventHandler(Database database) {
        this.database = database;
    }

    @EventListener
    public void handleAvailableProductCreatedEvent(AvailableProductCreatedEvent event) {
        AvailableProductRepresentation availableProduct = new AvailableProductRepresentation(
                event.id.idString(),
                event.name,
                event.unitPrice,
                event.description
        );

        database.availableProductByIdMap.put(availableProduct.id, availableProduct);
    }


}
