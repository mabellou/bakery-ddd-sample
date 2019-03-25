package com.mabellou.dddsamplemab.domain.model.placedorder;

import com.mabellou.dddsamplemab.domain.model.availableproduct.ProductId;
import com.mabellou.dddsamplemab.domain.shared.ValueObject;

import java.math.BigDecimal;
import java.util.Objects;

public class PlacedOrderLine implements ValueObject<PlacedOrderLine> {
    private final Integer itemNumber;
    private final ProductId productId;
    private final BigDecimal unitPrice;

    public PlacedOrderLine(final Integer itemNumber,
                           final ProductId productId,
                           final BigDecimal unitPrice) {
        Objects.requireNonNull(itemNumber);
        Objects.requireNonNull(productId);
        Objects.requireNonNull(unitPrice);

        this.itemNumber = itemNumber;
        this.productId = productId;
        this.unitPrice = unitPrice;
    }

    public BigDecimal totalPrice(){
        return unitPrice.multiply(new BigDecimal(itemNumber));
    }

    @Override
    public boolean sameValueAs(PlacedOrderLine other) {
        return other != null &&
                Objects.equals(itemNumber, other.itemNumber) &&
                Objects.equals(productId, other.productId) &&
                Objects.equals(unitPrice, other.unitPrice);
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
        return Objects.hash(itemNumber, productId, unitPrice);
    }
}
