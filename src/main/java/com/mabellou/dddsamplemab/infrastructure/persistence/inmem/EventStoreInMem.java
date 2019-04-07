package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.shared.EntityId;
import com.mabellou.dddsamplemab.domain.shared.Event;
import com.mabellou.dddsamplemab.domain.shared.EventStore;
import com.mabellou.dddsamplemab.domain.shared.EventStream;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EventStoreInMem implements EventStore {

    private Map<EntityId, List<Event>> eventStore;
    private Map<EntityId, Integer> eventStoreVersion;

    public EventStoreInMem() {
        this.eventStore = new HashMap<>();
        this.eventStoreVersion = new HashMap<>();
    }

    @Override
    public EventStream loadEventStream(EntityId entityId) {
        List<Event> events = eventStore.get(entityId);
        Integer version = eventStoreVersion.get(entityId);

        if(events == null){
            events = new ArrayList<>();
        }

        if(version == null){
            version = 0;
        }

        return new EventStream(version, events);
    }

    @Override
    public void appendToStream(EntityId entityId, int expectedVersion, List<Event> events) {
        List<Event> existingEvents = eventStore.get(entityId);
        Integer existingVersion = eventStoreVersion.get(entityId);

        if(existingEvents != null){
            if(existingVersion == expectedVersion){
                existingEvents.addAll(events);
                existingVersion = existingVersion + 1;
                eventStoreVersion.put(entityId, existingVersion);
            } else{
                throw new IllegalStateException("Optimistic locking issue!");
            }
        } else {
            eventStore.put(entityId, events);
            eventStoreVersion.put(entityId, expectedVersion);
        }
    }

    @Override
    public CustomerId nextCustomerId() {
        String random = UUID.randomUUID().toString().toUpperCase();
        return new CustomerId(
                random.substring(0, random.indexOf("-"))
        );
    }
}
