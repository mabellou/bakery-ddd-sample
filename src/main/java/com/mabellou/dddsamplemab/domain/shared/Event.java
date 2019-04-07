package com.mabellou.dddsamplemab.domain.shared;

import java.time.LocalDateTime;

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
}
