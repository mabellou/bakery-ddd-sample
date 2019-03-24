package com.mabellou.dddsamplemab.domain.model.placedorder;

import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.shared.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class PlacedOrder implements Entity<PlacedOrder> {
    private PlacedOrderId placedOrderId;
    private LocalDateTime creationDate;
    private CustomerId customer;
    private List<PlacedOrderLine> placedOrderLines;

    public PlacedOrder(final PlacedOrderId placedOrderId,
                       final LocalDateTime creationDate,
                       final CustomerId customer,
                       final List<PlacedOrderLine> placedOrderLines) {
        Objects.requireNonNull(placedOrderId);
        Objects.requireNonNull(creationDate);
        Objects.requireNonNull(customer);
        Objects.requireNonNull(placedOrderLines);

        this.placedOrderId = placedOrderId;
        this.creationDate = creationDate;
        this.customer = customer;
        this.placedOrderLines = placedOrderLines;
    }

    public PlacedOrderId placedOrderId() {
        return placedOrderId;
    }

    public LocalDateTime creationDate() {
        return creationDate;
    }

    public CustomerId customer() {
        return customer;
    }

    public List<PlacedOrderLine> placedOrderLines() {
        return placedOrderLines;
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
                customer,
                placedOrderLines
        );
    }
}
