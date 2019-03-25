package com.mabellou.dddsamplemab.domain.model.availableproduct;

import java.util.List;

public interface Catalog {
    List<AvailableProduct> findAll();
    AvailableProduct findById(ProductId productId);
    ProductId nextProductId();
    void add(AvailableProduct product);
}
