package com.mabellou.dddsamplemab.domain.shared;

import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Event extends ApplicationEvent {
       public final LocalDateTime timestamp;
       public final EntityId id;
       public final String entityName;
       public final String eventName;

       public Event(EntityId id, String entityName, String eventName) {
              super(id);
              this.timestamp = LocalDateTime.now();
              this.id = id;
              this.entityName = entityName;
              this.eventName = eventName;
       }

       @Override
       public String toString() {
              return "{Entity: " + entityName + ", " +
                      "Action: " + eventName + ", " +
                      "Time: " + timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:MM:ss")) + "}";
       }
}
