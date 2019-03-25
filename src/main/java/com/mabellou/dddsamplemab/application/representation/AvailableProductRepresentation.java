package com.mabellou.dddsamplemab.application.representation;

import com.mabellou.dddsamplemab.domain.model.availableproduct.AvailableProduct;

import java.math.BigDecimal;

public class AvailableProductRepresentation {
    public final String id;
    public final String name;
    public final BigDecimal unitPrice;
    public final String description;

    public AvailableProductRepresentation(AvailableProduct availableProduct) {
        this.id = availableProduct.productId().idString();
        this.name = availableProduct.name();
        this.unitPrice = availableProduct.unitPrice();
        this.description = availableProduct.description();
    }
}
