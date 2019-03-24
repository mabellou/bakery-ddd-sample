package com.mabellou.dddsamplemab.domain.model.placedorder;

import com.mabellou.dddsamplemab.domain.model.availableproduct.ProductId;
import com.mabellou.dddsamplemab.domain.shared.ValueObject;

import java.util.Objects;

public class PlacedOrderLine implements ValueObject<PlacedOrderLine> {
    private final Integer itemNumber;
    private final ProductId product;


    public PlacedOrderLine(final Integer itemNumber,
                           final ProductId product) {
        this.itemNumber = itemNumber;
        this.product = product;
    }

    @Override
    public boolean sameValueAs(PlacedOrderLine other) {
        return other != null &&
                Objects.equals(itemNumber, other.itemNumber) &&
                Objects.equals(product, other.product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlacedOrderLine other = (PlacedOrderLine) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemNumber, product);
    }
}
