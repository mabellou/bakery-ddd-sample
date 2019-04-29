package com.mabellou.dddsamplemab.query.representation;

import java.math.BigDecimal;

public class AvailableProductRepresentation {
    public final String id;
    public final String name;
    public final BigDecimal unitPrice;
    public final String description;

    public AvailableProductRepresentation(
            String id,
            String name,
            BigDecimal unitPrice,
            String description
    ) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
    }
}
