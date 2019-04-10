package com.mabellou.dddsamplemab.domain.model.placedorder;

import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.shared.Event;

import java.time.LocalDateTime;
import java.util.List;

public class OrderPlacedEvent extends Event {
    public final PlacedOrderId placedOrderId;
    public final LocalDateTime creationDate;
    public final CustomerId customerId;
    public final List<PlacedOrderLine> placedOrderLines;

    public OrderPlacedEvent(final PlacedOrderId placedOrderId,
                            final LocalDateTime creationDate,
                            final CustomerId customerId,
                            final List<PlacedOrderLine> placedOrderLines) {
        super(placedOrderId, "PlacedOrder", "OrderPlacedEvent");
        this.placedOrderId = placedOrderId;
        this.creationDate = creationDate;
        this.customerId = customerId;
        this.placedOrderLines = placedOrderLines;
    }

    @Override
    public String toString() {
        return super.toString() + "\n\t\t{" +
                "placedOrderId=" + placedOrderId.idString() +
                ", creationDate=" + creationDate +
                ", customerId=" + customerId.idString() +
                ", placedOrderLines=" + placedOrderLines +
                "}";
    }
}
