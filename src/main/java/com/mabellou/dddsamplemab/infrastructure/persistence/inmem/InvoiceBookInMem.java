package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.model.customer.Customer;
import com.mabellou.dddsamplemab.domain.model.invoice.Invoice;
import com.mabellou.dddsamplemab.domain.model.invoice.InvoiceBook;
import com.mabellou.dddsamplemab.domain.model.invoice.InvoiceId;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class InvoiceBookInMem implements InvoiceBook {

    private EventStoreInMem eventStore;

    @Autowired
    public InvoiceBookInMem(EventStoreInMem eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void save(Invoice invoice) {
        eventStore.appendToStream(invoice.invoiceId(), invoice.version(), invoice.changes());
    }

    @Override
    public List<Invoice> findAll() {
        return eventStore.findAll("Invoice", Invoice::new);
    }

    @Override
    public Optional<Invoice> findById(InvoiceId invoiceId) {
        return eventStore.findById(invoiceId, Invoice::new);
    }

    @Override
    public Optional<Invoice> findByOrderId(PlacedOrderId placedOrderId) {
        return findAll().stream().filter(i -> i.placedOrderId().equals(placedOrderId)).findFirst();
    }

    @Override
    public InvoiceId nextInvoiceId() {
        return eventStore.nextId(InvoiceId::new);
    }

}
