package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.model.invoice.Invoice;
import com.mabellou.dddsamplemab.domain.model.invoice.InvoiceBook;
import com.mabellou.dddsamplemab.domain.model.invoice.InvoiceId;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderId;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InvoiceBookInMem implements InvoiceBook {

    private Map<InvoiceId, Invoice> invoiceBookDbById;
    private Map<PlacedOrderId, Invoice> invoiceBookDbByOrderId;

    public InvoiceBookInMem() {
        invoiceBookDbById = new HashMap<>();
        invoiceBookDbByOrderId = new HashMap<>();
    }

    @Override
    public void add(Invoice invoice) {
        invoiceBookDbById.put(invoice.invoiceId(), invoice);
        invoiceBookDbByOrderId.put(invoice.placedOrderId(), invoice);
    }

    @Override
    public List<Invoice> findAll() {
        return new ArrayList<>(invoiceBookDbById.values());
    }

    @Override
    public Optional<Invoice> findById(InvoiceId invoiceId) {
        Invoice invoice = invoiceBookDbById.get(invoiceId);
        return invoice == null ? Optional.empty() : Optional.of(invoice);
    }

    @Override
    public Optional<Invoice> findByOrderId(PlacedOrderId placedOrderId) {
        Invoice invoice = invoiceBookDbByOrderId.get(placedOrderId);
        return invoice == null ? Optional.empty() : Optional.of(invoice);
    }

    @Override
    public InvoiceId nextInvoiceId() {
        String random = UUID.randomUUID().toString().toUpperCase();
        return new InvoiceId(
                random.substring(0, random.indexOf("-"))
        );
    }

}
