package com.mabellou.dddsamplemab.domain.shared;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity<T> {

    protected List<Event> events = new ArrayList<>();
    protected Integer version = 0;


    public Entity() {
    }

    protected void buildFrom(EventStream stream){
        this.version = stream.version;
        for(Event event: stream.events){
            mutate(event);
        }
    }

    protected void apply(Event event){
        events.add(event);
        mutate(event);
    }

    protected abstract void mutate(Event event);
    public abstract boolean sameIdentityAs(T other);

    public List<Event> changes() {
        return events;
    }

    public Integer version(){
        return version;
    }
}
