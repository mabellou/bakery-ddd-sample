package com.mabellou.dddsamplemab.domain.model.invoice;

import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderId;
import com.mabellou.dddsamplemab.domain.shared.Entity;
import com.mabellou.dddsamplemab.domain.shared.Event;
import com.mabellou.dddsamplemab.domain.shared.EventStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Invoice implements Entity<Invoice> {
    private Logger logger = LoggerFactory.getLogger(Invoice.class);

    private InvoiceId invoiceId;
    private CustomerId customerId;
    private PlacedOrderId placedOrderId;
    private InvoiceStatus invoiceStatus;
    private BigDecimal amount;

    private List<Event> events = new ArrayList<>();
    private Integer version;

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

        apply(new InvoiceGeneratedEvent(invoiceId, customerId, placedOrderId, invoiceStatus, amount));
    }

    public Invoice(EventStream stream){
        this.version = stream.version;
        for(Event event: stream.events){
            mutate(event);
        }
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

    public List<Event> changes() {
        return events;
    }

    public Integer version(){
        return version;
    }

    public void apply(Event event){
        events.add(event);
        mutate(event);
    }

    protected void mutate(Event event){
        if(event instanceof InvoiceGeneratedEvent){
            when((InvoiceGeneratedEvent) event);
        }
    }

    protected void when(InvoiceGeneratedEvent event){
        logger.info("The event {} is applied!", event.eventName);
        this.invoiceId = event.invoiceId;
        this.customerId = event.customerId;
        this.placedOrderId = event.placedOrderId;
        this.invoiceStatus = event.invoiceStatus;
        if(BigDecimal.ZERO.compareTo(event.amount) >= 0){
            this.invoiceStatus = InvoiceStatus.PAID;
        }
        this.amount = event.amount;
        this.version = 0;
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
