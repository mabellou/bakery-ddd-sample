package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.model.customer.Customer;
import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.shared.EntityId;
import com.mabellou.dddsamplemab.domain.shared.Event;
import com.mabellou.dddsamplemab.domain.shared.EventStore;
import com.mabellou.dddsamplemab.domain.shared.EventStream;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EventStoreInMem implements EventStore {

    private Map<EntityId, List<Event>> eventStore;
    private Map<String, List<EntityId>> eventStoreByEntity;
    private Map<EntityId, Integer> eventStoreVersion;

    public EventStoreInMem() {
        this.eventStore = new HashMap<>();
        this.eventStoreVersion = new HashMap<>();
        this.eventStoreByEntity = new HashMap<>();
    }

    public <T> Optional<T> findById(EntityId entityId, Function<EventStream, T> createEntity) {
        EventStream eventStream = loadEventStream(entityId);
        if(eventStream.events.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(createEntity.apply(eventStream));
    }

    public <T> List<T> findAll(String entityName, Function<EventStream, T> createEntity) {
        List<EventStream> eventStreams = loadEventStreams(entityName);
        return eventStreams.stream()
                .map(createEntity)
                .collect(Collectors.toList());
    }

    public <T> T nextId(Function<String, T> createEntityId) {
        String random = UUID.randomUUID().toString().toUpperCase();
        return createEntityId.apply(
                random.substring(0, random.indexOf("-"))
        );
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
        if(eventIds == null){
            return new ArrayList<>();
        }
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
            String entityName = e.entityName;
            List<EntityId> existingEventsByEntity = eventStoreByEntity.get(entityName);
            if(existingEventsByEntity == null || existingEventsByEntity.isEmpty()){
                List<EntityId> entityIds = new ArrayList<>();
                entityIds.add(e.id);
                eventStoreByEntity.put(entityName, entityIds);
            } else {
                existingEventsByEntity.add(e.id);
                eventStoreByEntity.put(entityName, existingEventsByEntity);
            }
        }
    }
}
