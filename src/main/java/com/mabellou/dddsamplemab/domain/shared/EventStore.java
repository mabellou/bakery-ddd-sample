package com.mabellou.dddsamplemab.domain.shared;

import java.util.List;

public interface EventStore {

    EventStream loadEventStream(EntityId id);

    List<EventStream> loadEventStreams(String entityName);

    void appendToStream(EntityId id, int expectedVersion, List<Event> events);
}
