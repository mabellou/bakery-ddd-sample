package com.mabellou.dddsamplemab.domain.shared;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Event {
       public final LocalDateTime timestamp;
       public final EntityId id;
       public final String entityName;
       public final String eventName;

       public Event(EntityId id, String entityName, String eventName) {
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
