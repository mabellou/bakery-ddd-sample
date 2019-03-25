package com.mabellou.dddsamplemab.domain.model.availableproduct;

import java.util.List;
import java.util.Optional;

public interface Catalog {
    List<AvailableProduct> findAll();
    Optional<AvailableProduct> findById(ProductId productId);
    ProductId nextProductId();
    void add(AvailableProduct product);
}
