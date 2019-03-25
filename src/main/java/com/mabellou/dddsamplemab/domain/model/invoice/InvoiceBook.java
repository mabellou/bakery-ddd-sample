package com.mabellou.dddsamplemab.domain.model.invoice;

import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderId;

import java.util.List;
import java.util.Optional;

public interface InvoiceBook {
    void add(Invoice invoice);
    List<Invoice> findAll();
    Optional<Invoice> findById(InvoiceId invoiceId);
    Optional<Invoice> findByOrderId(PlacedOrderId placedOrderId);
    InvoiceId nextInvoiceId();
}
