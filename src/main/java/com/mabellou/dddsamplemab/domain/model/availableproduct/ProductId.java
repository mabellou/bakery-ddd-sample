package com.mabellou.dddsamplemab.domain.model.availableproduct;

import com.mabellou.dddsamplemab.domain.shared.ValueObject;

import java.util.Objects;

public class ProductId implements ValueObject<ProductId> {
    private final String id;

    public ProductId(final String id){
        Objects.requireNonNull(id);
        this.id = id;
    }

    public String idString(){
        return id;
    }

    @Override
    public boolean sameValueAs(ProductId other) {
        return other != null && this.id.equals(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId that = (ProductId) o;
        return sameValueAs(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProductId{" +
                "id='" + id + '\'' +
                '}';
    }
}
