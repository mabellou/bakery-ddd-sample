package com.mabellou.dddsamplemab.domain.model.availableproduct;

import com.mabellou.dddsamplemab.domain.shared.Event;

import java.math.BigDecimal;

public class AvailableProductCreatedEvent extends Event {
    public final ProductId productId;
    public final String name;
    public final BigDecimal unitPrice;
    public final String description;

    public AvailableProductCreatedEvent(final ProductId productId,
                                        final String name,
                                        final BigDecimal unitPrice) {
        super(productId,  "AvailableProduct", "AvailableProductCreatedEvent");
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = null;
    }

    public AvailableProductCreatedEvent(final ProductId productId,
                                        final String name,
                                        final BigDecimal unitPrice,
                                        final String description) {
        super(productId,  "AvailableProduct", "AvailableProductCreatedEvent");
        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
    }
}
