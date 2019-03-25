package com.mabellou.dddsamplemab.domain.shared;

import java.util.Objects;

public abstract class EntityId<T extends EntityId> implements ValueObject<T> {
    protected final String id;

    public EntityId(final String id){
        Objects.requireNonNull(id);
        this.id = id;
    }

    public String idString(){
        return id;
    }

    @Override
    public boolean sameValueAs(T other) {
        return other != null && this.id.equals(other.idString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        T that = (T) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EntityId{" +
                "id='" + id + '\'' +
                '}';
    }
}