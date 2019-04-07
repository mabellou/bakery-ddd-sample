package com.mabellou.dddsamplemab.domain.shared;

import java.time.LocalDateTime;

public abstract class Event {
       public final LocalDateTime timestamp;
       public final EntityId id;
       public final String name;

       public Event(EntityId id, String name) {
              this.timestamp = LocalDateTime.now();
              this.id = id;
              this.name = name;
       }
}
