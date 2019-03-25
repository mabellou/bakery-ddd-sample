package com.mabellou.dddsamplemab.domain.model.placedorder;

import com.mabellou.dddsamplemab.domain.model.availableproduct.AvailableProduct;
import com.mabellou.dddsamplemab.domain.model.availableproduct.Catalog;
import com.mabellou.dddsamplemab.domain.model.availableproduct.ProductId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacedOrderLineService {

    private Catalog catalog;

    @Autowired
    public PlacedOrderLineService(Catalog catalog) {
        this.catalog = catalog;
    }

    public PlacedOrderLine placeOrderLine(String productIdString, Integer itemNumber){
        ProductId productId = new ProductId(productIdString);
        AvailableProduct availableProduct = catalog.findById(productId)
                .orElseThrow(() -> new IllegalStateException("product not found"));
        return new PlacedOrderLine(
                itemNumber,
                productId,
                availableProduct.unitPrice()
        );
    }

}
