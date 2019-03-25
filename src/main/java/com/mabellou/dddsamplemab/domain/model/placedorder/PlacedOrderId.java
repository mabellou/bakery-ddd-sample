package com.mabellou.dddsamplemab.domain.model.placedorder;

import com.mabellou.dddsamplemab.domain.shared.EntityId;
import com.mabellou.dddsamplemab.domain.shared.ValueObject;

import java.util.Objects;

public class PlacedOrderId extends EntityId<PlacedOrderId> {
    public PlacedOrderId(String id) {
        super(id);
    }
}