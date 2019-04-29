package com.mabellou.dddsamplemab.query.service;

import com.mabellou.dddsamplemab.domain.model.invoice.Invoice;
import com.mabellou.dddsamplemab.domain.model.invoice.InvoiceBook;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderId;
import com.mabellou.dddsamplemab.query.data.Database;
import com.mabellou.dddsamplemab.query.representation.InvoiceRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacedOrderQueryService {

    private Database database;

    public PlacedOrderQueryService(Database database) {
        this.database = database;
    }

    public InvoiceRepresentation getInvoiceByPlacedOrderId(String placedOrderId){
        InvoiceRepresentation invoice = database.invoiceByPlacedOrderIdMap.get(placedOrderId);

        if(invoice == null){
            throw new IllegalStateException("invoice for order not found");
        }

        return invoice;
    }
}
