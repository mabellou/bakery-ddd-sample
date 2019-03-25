package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.model.availableproduct.AvailableProduct;
import com.mabellou.dddsamplemab.domain.model.availableproduct.Catalog;
import com.mabellou.dddsamplemab.domain.model.availableproduct.ProductId;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class CatalogInMem implements Catalog {

    private Map<ProductId, AvailableProduct> catalogDb;

    public CatalogInMem() {
        this.catalogDb = new HashMap<>();
        addPainAuChocolat();
        addCroissant();
        addBaguette();
    }

    @Override
    public List<AvailableProduct> findAll() {
        return new ArrayList<>(catalogDb.values());
    }

    @Override
    public ProductId nextProductId() {
        String random = UUID.randomUUID().toString().toUpperCase();
        return new ProductId(
                random.substring(0, random.indexOf("-"))
        );
    }

    private void addPainAuChocolat(){
        ProductId productId = this.nextProductId();

        catalogDb.put(
                productId,
                new AvailableProduct(
                        productId,
                        "Pain au chocolat",
                        new BigDecimal(1.0)
                )
        );
    }

    private void addCroissant(){
        ProductId productId = this.nextProductId();

        catalogDb.put(
                productId,
                new AvailableProduct(
                        productId,
                        "Croissant",
                        new BigDecimal(1.0)
                )
        );
    }

    private void addBaguette(){
        ProductId productId = this.nextProductId();

        catalogDb.put(
                productId,
                new AvailableProduct(
                        productId,
                        "Baguette",
                        new BigDecimal(0.7)
                )
        );
    }
}
