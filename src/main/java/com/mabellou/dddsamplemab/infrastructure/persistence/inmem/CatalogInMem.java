package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.model.availableproduct.AvailableProduct;
import com.mabellou.dddsamplemab.domain.model.availableproduct.Catalog;
import com.mabellou.dddsamplemab.domain.model.availableproduct.ProductId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class CatalogInMem implements Catalog {
    private EventStoreInMem eventStore;

    private static final String PAIN_AU_CHOCOLAT = "Pain au chocolat";
    private static final String CROISSANT = "Croissant";
    private static final String BAGUETTE = "Baguette";

    @Autowired
    public CatalogInMem(EventStoreInMem eventStore) {
        this.eventStore = eventStore;
        addPainAuChocolat();
        addCroissant();
        addBaguette();
    }

    @Override
    public Optional<AvailableProduct> findById(ProductId productId) {
        return eventStore.findById(productId, AvailableProduct::new);
    }

    @Override
    public List<AvailableProduct> findAll() {
        return eventStore.findAll("AvailableProduct", AvailableProduct::new);
    }


    @Override
    public void save(AvailableProduct availableProduct) {
        eventStore.appendToStream(availableProduct.productId(), availableProduct.version(), availableProduct.changes());
    }

    @Override
    public ProductId nextProductId() {
        return eventStore.nextId(ProductId::new);
    }

    private void addPainAuChocolat(){
        ProductId productId = this.nextProductId();

        save(
            new AvailableProduct(
                    productId,
                    PAIN_AU_CHOCOLAT,
                    new BigDecimal(1.0)
            )
        );
    }

    private void addCroissant(){
        ProductId productId = this.nextProductId();

        save(
            new AvailableProduct(
                    productId,
                    CROISSANT,
                    new BigDecimal(1.0)
            )
        );
    }

    private void addBaguette(){
        ProductId productId = this.nextProductId();

        save(
            new AvailableProduct(
                    productId,
                    BAGUETTE,
                    new BigDecimal(0.7)
            )
        );
    }
}
