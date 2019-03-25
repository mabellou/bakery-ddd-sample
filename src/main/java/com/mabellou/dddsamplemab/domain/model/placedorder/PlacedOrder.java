package com.mabellou.dddsamplemab.domain.model.placedorder;

import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.invoice.Invoice;
import com.mabellou.dddsamplemab.domain.model.invoice.InvoiceId;
import com.mabellou.dddsamplemab.domain.model.invoice.InvoiceStatus;
import com.mabellou.dddsamplemab.domain.shared.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PlacedOrder implements Entity<PlacedOrder> {
    private PlacedOrderId placedOrderId;
    private LocalDateTime creationDate;
    private CustomerId customerId;
    private List<PlacedOrderLine> placedOrderLines;

    public PlacedOrder(final PlacedOrderId placedOrderId,
                       final LocalDateTime creationDate,
                       final CustomerId customerId,
                       final List<PlacedOrderLine> placedOrderLines) {
        Objects.requireNonNull(placedOrderId);
        Objects.requireNonNull(creationDate);
        Objects.requireNonNull(customerId);
        Objects.requireNonNull(placedOrderLines);

        this.placedOrderId = placedOrderId;
        this.creationDate = creationDate;
        this.customerId = customerId;
        this.placedOrderLines = placedOrderLines;
    }

    public PlacedOrderId placedOrderId() {
        return placedOrderId;
    }

    public LocalDateTime creationDate() {
        return creationDate;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public List<PlacedOrderLine> placedOrderLines() {
        return placedOrderLines;
    }

    public BigDecimal totalPrice(){
        return placedOrderLines.stream()
                .map(PlacedOrderLine::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Invoice generateInvoice(InvoiceId invoiceId){
        return new Invoice(
                invoiceId,
                this.customerId,
                this.placedOrderId,
                InvoiceStatus.NOT_PAID,
                this.totalPrice()
        );
    }

    @Override
    public boolean sameIdentityAs(PlacedOrder other) {
        return other != null && placedOrderId.equals(other.placedOrderId);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlacedOrder other = (PlacedOrder) o;
        return sameIdentityAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                placedOrderId,
                creationDate,
                customerId,
                placedOrderLines
        );
    }
}
