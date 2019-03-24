package com.mabellou.dddsamplemab.application.representation;

import com.mabellou.dddsamplemab.domain.model.availableproduct.AvailableProduct;

public class AvailableProductRepresentation {
    public final String id;
    public final String name;
    public final String description;

    public AvailableProductRepresentation(AvailableProduct availableProduct) {
        this.id = availableProduct.productId().idString();
        this.name = availableProduct.name();
        this.description = availableProduct.description();
    }
}
