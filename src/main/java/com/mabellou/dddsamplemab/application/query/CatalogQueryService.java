package com.mabellou.dddsamplemab.application.query;

import com.mabellou.dddsamplemab.domain.model.availableproduct.AvailableProduct;
import com.mabellou.dddsamplemab.domain.model.availableproduct.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogQueryService {
    private Logger logger = LoggerFactory.getLogger(CatalogQueryService.class);

    private final Catalog catalog;

    @Autowired
    public CatalogQueryService(Catalog catalog) {
        this.catalog = catalog;
    }

    public List<AvailableProduct> catalog(){
        return catalog.findAll();
    }
}
