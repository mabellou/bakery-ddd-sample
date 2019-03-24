package com.mabellou.dddsamplemab.domain.model.availableproduct;

import java.util.List;

public interface Catalog {
    List<AvailableProduct> findAll();
    ProductId nextProductId();
}
