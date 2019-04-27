package com.mabellou.dddsamplemab.domain.model.placedorder;

import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.invoice.Invoice;
import com.mabellou.dddsamplemab.domain.model.invoice.InvoiceId;
import com.mabellou.dddsamplemab.domain.model.invoice.InvoiceStatus;
import com.mabellou.dddsamplemab.domain.shared.Entity;
import com.mabellou.dddsamplemab.domain.shared.Event;
import com.mabellou.dddsamplemab.domain.shared.EventStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PlacedOrder extends Entity<PlacedOrder> {
    private Logger logger = LoggerFactory.getLogger(PlacedOrder.class);

    private PlacedOrderId placedOrderId;
    private LocalDateTime creationDate;
    private CustomerId customerId;
    private List<PlacedOrderLine> placedOrderLines;

    public PlacedOrder(EventStream stream){
        buildFrom(stream);
    }

    public PlacedOrder(final PlacedOrderId placedOrderId,
                       final LocalDateTime creationDate,
                       final CustomerId customerId,
                       final List<PlacedOrderLine> placedOrderLines) {
        Objects.requireNonNull(placedOrderId);
        Objects.requireNonNull(creationDate);
        Objects.requireNonNull(customerId);
        Objects.requireNonNull(placedOrderLines);

        apply(new OrderPlacedEvent(placedOrderId, creationDate, customerId, placedOrderLines));
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

    protected void mutate(Event event){
        if(event instanceof OrderPlacedEvent){
            when((OrderPlacedEvent) event);
        }
    }

    protected void when(OrderPlacedEvent event){
        logger.info("The event {} is applied!", event.eventName);
        this.placedOrderId = event.placedOrderId;
        this.creationDate = event.creationDate;
        this.customerId = event.customerId;
        this.placedOrderLines = event.placedOrderLines;
        this.version = 0;
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
