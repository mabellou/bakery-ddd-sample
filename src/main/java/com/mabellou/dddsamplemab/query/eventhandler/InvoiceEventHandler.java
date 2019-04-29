package com.mabellou.dddsamplemab.query.eventhandler;

import com.mabellou.dddsamplemab.domain.model.invoice.InvoiceGeneratedEvent;
import com.mabellou.dddsamplemab.query.data.Database;
import com.mabellou.dddsamplemab.query.representation.InvoiceRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InvoiceEventHandler {

    private Database database;

    @Autowired
    public InvoiceEventHandler(Database database) {
        this.database = database;
    }

    @EventListener
    public void handleInvoiceGeneratedEvent(InvoiceGeneratedEvent event) {
        InvoiceRepresentation invoice = new InvoiceRepresentation(
                event.id.idString(),
                event.placedOrderId.idString(),
                event.amount,
                event.invoiceStatus.toString()
        );

        if(database.invoiceByPlacedOrderIdMap.get(invoice.placedOrderId) != null){
            throw new IllegalStateException("An invoice already exists for this order");
        }

        database.invoiceByPlacedOrderIdMap.put(invoice.placedOrderId, invoice);
    }
}
