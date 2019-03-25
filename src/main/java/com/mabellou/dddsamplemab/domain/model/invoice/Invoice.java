package com.mabellou.dddsamplemab.domain.model.invoice;

import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderId;
import com.mabellou.dddsamplemab.domain.shared.Entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Invoice implements Entity<Invoice> {
    private InvoiceId invoiceId;
    private CustomerId customerId;
    private PlacedOrderId placedOrderId;
    private InvoiceStatus invoiceStatus;
    private BigDecimal amount;

    public Invoice(final InvoiceId invoiceId,
                   final CustomerId customerId,
                   final PlacedOrderId placedOrderId,
                   final InvoiceStatus invoiceStatus,
                   final BigDecimal amount) {
        Objects.requireNonNull(invoiceId);
        Objects.requireNonNull(customerId);
        Objects.requireNonNull(placedOrderId);
        Objects.requireNonNull(invoiceStatus);
        Objects.requireNonNull(amount);

        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.placedOrderId = placedOrderId;
        this.invoiceStatus = invoiceStatus;
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            this.invoiceStatus = InvoiceStatus.PAID;
        }
        this.amount = amount;
    }

    public InvoiceId invoiceId() {
        return invoiceId;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public PlacedOrderId placedOrderId() {
        return placedOrderId;
    }

    public InvoiceStatus invoiceStatus() {
        return invoiceStatus;
    }

    public BigDecimal amount() {
        return amount;
    }

    @Override
    public boolean sameIdentityAs(Invoice other) {
        return other != null &&
                invoiceId.equals(other.invoiceId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice other = (Invoice) o;
        return sameIdentityAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", placedOrderId=" + placedOrderId +
                ", invoiceStatus=" + invoiceStatus +
                ", amount=" + amount +
                '}';
    }
}
