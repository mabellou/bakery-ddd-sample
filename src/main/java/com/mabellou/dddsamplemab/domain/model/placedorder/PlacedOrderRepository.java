package com.mabellou.dddsamplemab.domain.model.placedorder;

import java.util.List;

public interface PlacedOrderRepository {
    PlacedOrder find(PlacedOrderId placedOrderId);

    List<PlacedOrder> findAll();

    void add(PlacedOrder placedOrder);

    PlacedOrderId nextPlacedOrderId();
}
