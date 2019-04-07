package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.shared.EntityId;
import com.mabellou.dddsamplemab.domain.shared.Event;
import com.mabellou.dddsamplemab.domain.shared.EventStore;
import com.mabellou.dddsamplemab.domain.shared.EventStream;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EventStoreInMem implements EventStore {

    private Map<EntityId, List<Event>> eventStore;
    private Map<String, List<EntityId>> eventStoreByEntity;
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
    public List<EventStream> loadEventStreams(String entityName) {
        List<EntityId> eventIds = eventStoreByEntity.get(entityName);
        return eventIds.stream()
                .map(id -> new EventStream(eventStoreVersion.get(id), eventStore.get(id)))
                .collect(Collectors.toList());
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

        for(Event e: events){
            String eventName = e.eventName;
            List<EntityId> existingEventsByEntity = eventStoreByEntity.get(eventName);
            if(existingEventsByEntity == null || existingEventsByEntity.isEmpty()){
                eventStoreByEntity.put(eventName, List.of(e.id));
            } else {
                existingEventsByEntity.add(e.id);
                eventStoreByEntity.put(eventName, existingEventsByEntity);
            }
        }
    }
}
