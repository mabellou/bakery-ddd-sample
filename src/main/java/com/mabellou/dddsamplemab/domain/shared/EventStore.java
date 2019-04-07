package com.mabellou.dddsamplemab.domain.shared;

import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;

import java.util.List;

public interface EventStore {

    EventStream loadEventStream(EntityId id);

    void appendToStream(EntityId id, int expectedVersion, List<Event> events);

    CustomerId nextCustomerId();
}
