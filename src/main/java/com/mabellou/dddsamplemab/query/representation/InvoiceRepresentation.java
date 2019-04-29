package com.mabellou.dddsamplemab.query.representation;

import java.math.BigDecimal;

public class InvoiceRepresentation {
    public final String id;
    public final String placedOrderId;
    public final BigDecimal amount;
    public final String status;

    public InvoiceRepresentation(
            String id,
            String placedOrderId,
            BigDecimal amount,
            String status
    ) {
        this.id = id;
        this.placedOrderId = placedOrderId;
        this.amount = amount;
        this.status = status;
    }
}
