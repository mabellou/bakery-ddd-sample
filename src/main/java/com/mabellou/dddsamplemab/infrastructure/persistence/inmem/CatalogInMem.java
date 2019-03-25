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
    private static final String PAIN_AU_CHOCOLAT = "Pain au chocolat";
    private static final String CROISSANT = "Croissant";
    private static final String BAGUETTE = "Baguette";

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
    public AvailableProduct findById(ProductId productId){
        return catalogDb.get(productId);
    }

    @Override
    public ProductId nextProductId() {
        String random = UUID.randomUUID().toString().toUpperCase();
        return new ProductId(
                random.substring(0, random.indexOf("-"))
        );
    }

    @Override
    public void add(AvailableProduct availableProduct){
        catalogDb.put(availableProduct.productId(), availableProduct);
    }

    private void addPainAuChocolat(){
        ProductId productId = this.nextProductId();

        add(
            new AvailableProduct(
                    productId,
                    PAIN_AU_CHOCOLAT,
                    new BigDecimal(1.0)
            )
        );
    }

    private void addCroissant(){
        ProductId productId = this.nextProductId();

        add(
            new AvailableProduct(
                    productId,
                    CROISSANT,
                    new BigDecimal(1.0)
            )
        );
    }

    private void addBaguette(){
        ProductId productId = this.nextProductId();

        add(
            new AvailableProduct(
                    productId,
                    BAGUETTE,
                    new BigDecimal(0.7)
            )
        );
    }
}
