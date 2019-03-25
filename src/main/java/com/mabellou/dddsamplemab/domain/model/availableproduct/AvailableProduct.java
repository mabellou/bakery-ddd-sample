package com.mabellou.dddsamplemab.domain.model.availableproduct;

import com.mabellou.dddsamplemab.domain.shared.Entity;

import java.math.BigDecimal;
import java.util.Objects;

public class AvailableProduct implements Entity<AvailableProduct> {

    private ProductId productId;
    private String name;
    private String description;
    private BigDecimal unitPrice;

    public AvailableProduct(ProductId productId, String name, BigDecimal unitPrice) {
        Objects.requireNonNull(productId);
        Objects.requireNonNull(name);
        Objects.requireNonNull(unitPrice);

        this.productId = productId;
        this.name = name;
        this.unitPrice = unitPrice;
    }

    public AvailableProduct(ProductId productId, String name, BigDecimal unitPrice, String description) {
        this(productId, name, unitPrice);

        Objects.requireNonNull(description);
        this.description = description;
    }

    public ProductId productId(){
        return productId;
    }

    public String name(){
        return name;
    }

    public String description(){
        return description;
    }

    public BigDecimal unitPrice(){
        return unitPrice;
    }

    @Override
    public boolean sameIdentityAs(AvailableProduct other) {
        return other != null && productId.equals(other.productId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableProduct other = (AvailableProduct) o;
        return sameIdentityAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "AvailableProduct{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", unitPrice='" + unitPrice + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
