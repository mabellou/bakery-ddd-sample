package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.shared.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EventStoreInMem implements EventStore {

    private Map<EntityId, List<Event>> eventStore;
    private Map<String, List<EntityId>> eventStoreByEntity;
    private Map<EntityId, Integer> eventStoreVersion;
    private File file = new File("append.txt");
    private ApplicationEventPublisher applicationEventPublisher;

    public EventStoreInMem(ApplicationEventPublisher applicationEventPublisher) {
        this.eventStore = new HashMap<>();
        this.eventStoreVersion = new HashMap<>();
        this.eventStoreByEntity = new HashMap<>();
        this.applicationEventPublisher = applicationEventPublisher;
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
            storeEventWhenEntityNotExists(existingVersion, expectedVersion, entityId, existingEvents, events);
        } else {
            storeEvenWhenEntityExists(expectedVersion, entityId, events);
        }
        storeEventByEntity(events);
        publishToEventBus(events);
    }

    private void publishToEventBus(List<Event> events){
        for(Event e: events){
            applicationEventPublisher.publishEvent(e);
        }
    }

    private void storeEventWhenEntityNotExists(
            int existingVersion,
            int expectedVersion,
            EntityId entityId,
            List<Event> existingEvents,
            List<Event> events
    ){
        if(existingVersion == expectedVersion){
            existingVersion = existingVersion + 1;
            appendEventToFile(entityId, existingVersion, events);
            existingEvents.addAll(events);
            eventStoreVersion.put(entityId, existingVersion);
        } else{
            throw new IllegalStateException("Optimistic locking issue!");
        }
    }

    private void storeEvenWhenEntityExists(
            int expectedVersion,
            EntityId entityId,
            List<Event> events
    ){
        appendEventToFile(entityId, expectedVersion, events);
        eventStore.put(entityId, events);
        eventStoreVersion.put(entityId, expectedVersion);
    }

    private void storeEventByEntity(List<Event> events){
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

    private void appendEventToFile(EntityId entityId, int expectedVersion, List<Event> events){
        long pid = ProcessHandle.current().pid();

        if(eventStore.isEmpty()){
            file.delete();
        }

        try (FileWriter writer = new FileWriter(file, true)) {

            for(Event event : events){
                String eventString =
                        String.format("Process %s: %s (version: %s) - \n\t%s\n\n", pid, entityId.idString(), expectedVersion, event);
                writer.write(eventString);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
