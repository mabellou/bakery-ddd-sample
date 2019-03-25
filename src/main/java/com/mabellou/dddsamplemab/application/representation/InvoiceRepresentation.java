package com.mabellou.dddsamplemab.application.representation;

import com.mabellou.dddsamplemab.domain.model.invoice.Invoice;

public class InvoiceRepresentation {
    public final String amount;
    public final String status;

    public InvoiceRepresentation(Invoice invoice) {
        this.amount = invoice.amount().toString();
        this.status = invoice.invoiceStatus().toString();
    }
}
