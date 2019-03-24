package com.mabellou.dddsamplemab.domain.model.placedorder;

import com.mabellou.dddsamplemab.domain.shared.ValueObject;

import java.util.Objects;

public class PlacedOrderId implements ValueObject<PlacedOrderId>{
    private final String id;

    public PlacedOrderId(final String id){
        Objects.requireNonNull(id);
        this.id = id;
    }

    public String idString(){
        return id;
    }

    @Override
    public boolean sameValueAs(PlacedOrderId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlacedOrderId that = (PlacedOrderId) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PlacedOrderId{" +
                "id='" + id + '\'' +
                '}';
    }
}
