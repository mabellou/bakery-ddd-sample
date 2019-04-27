package com.mabellou.dddsamplemab.application.query;

import com.mabellou.dddsamplemab.domain.model.invoice.Invoice;
import com.mabellou.dddsamplemab.domain.model.invoice.InvoiceBook;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacedOrderQueryService {

    private InvoiceBook invoiceBook;

    @Autowired
    public PlacedOrderQueryService(final InvoiceBook invoiceBook) {
        this.invoiceBook = invoiceBook;
    }

    public Invoice getInvoice(String placedOrderId){
        return invoiceBook.findByOrderId(new PlacedOrderId(placedOrderId))
                .orElseThrow(() -> new IllegalStateException("invoice for order not found"));
    }
}
