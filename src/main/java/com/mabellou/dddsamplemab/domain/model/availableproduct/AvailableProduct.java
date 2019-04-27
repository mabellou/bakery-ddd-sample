package com.mabellou.dddsamplemab.domain.model.availableproduct;

import com.mabellou.dddsamplemab.domain.shared.Entity;
import com.mabellou.dddsamplemab.domain.shared.Event;
import com.mabellou.dddsamplemab.domain.shared.EventStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Objects;

public class AvailableProduct extends Entity<AvailableProduct> {
    private Logger logger = LoggerFactory.getLogger(AvailableProduct.class);

    private ProductId productId;
    private String name;
    private String description;
    private BigDecimal unitPrice;

    public AvailableProduct(EventStream stream){
        buildFrom(stream);
    }

    public AvailableProduct(ProductId productId, String name, BigDecimal unitPrice) {
        Objects.requireNonNull(productId);
        Objects.requireNonNull(name);
        Objects.requireNonNull(unitPrice);

        apply(new AvailableProductCreatedEvent(productId, name, unitPrice));
    }

    public AvailableProduct(ProductId productId, String name, BigDecimal unitPrice, String description) {
        Objects.requireNonNull(productId);
        Objects.requireNonNull(name);
        Objects.requireNonNull(unitPrice);

        apply(new AvailableProductCreatedEvent(productId, name, unitPrice, description));
    }

    protected void mutate(Event event){
        if(event instanceof AvailableProductCreatedEvent){
            when((AvailableProductCreatedEvent) event);
        }
    }

    protected void when(AvailableProductCreatedEvent event){
        logger.info("The event {} is applied!", event.name);
        this.productId = event.productId;
        this.name = event.name;
        this.unitPrice = event.unitPrice;
        this.description = event.description;
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
                "productId=" + productId.idString() +
                ", name='" + name + '\'' +
                ", unitPrice='" + unitPrice.toPlainString() + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
