package com.mabellou.dddsamplemab.domain.model.placedorder;

import java.util.List;
import java.util.Optional;

public interface PlacedOrderRepository {
    Optional<PlacedOrder> findById(PlacedOrderId placedOrderId);

    List<PlacedOrder> findAll();

    void add(PlacedOrder placedOrder);

    PlacedOrderId nextPlacedOrderId();
}
