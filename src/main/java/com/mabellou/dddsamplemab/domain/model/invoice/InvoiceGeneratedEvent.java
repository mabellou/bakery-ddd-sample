package com.mabellou.dddsamplemab.domain.model.invoice;

import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderId;
import com.mabellou.dddsamplemab.domain.shared.Event;

import java.math.BigDecimal;

public class InvoiceGeneratedEvent extends Event {
    public final InvoiceId invoiceId;
    public final CustomerId customerId;
    public final PlacedOrderId placedOrderId;
    public final InvoiceStatus invoiceStatus;
    public final BigDecimal amount;

    public InvoiceGeneratedEvent(final InvoiceId invoiceId,
                                 final CustomerId customerId,
                                 final PlacedOrderId placedOrderId,
                                 final InvoiceStatus invoiceStatus,
                                 final BigDecimal amount) {
        super(invoiceId, "Invoice", "InvoiceGeneratedEvent");
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.placedOrderId = placedOrderId;
        this.invoiceStatus = invoiceStatus;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\t\t{" +
                "invoiceId=" + invoiceId.idString() +
                ", customerId=" + customerId.idString() +
                ", placedOrderId=" + placedOrderId.idString() +
                ", invoiceStatus=" + invoiceStatus +
                ", amount=" + amount +
                "}";
    }
}
