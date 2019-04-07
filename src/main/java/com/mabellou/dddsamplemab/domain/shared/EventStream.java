package com.mabellou.dddsamplemab.domain.shared;

import java.util.List;

public class EventStream {
    public final int version;
    public final List<Event> events;

    public EventStream(int version, List<Event> events) {
        this.version = version;
        this.events = events;
    }
}
