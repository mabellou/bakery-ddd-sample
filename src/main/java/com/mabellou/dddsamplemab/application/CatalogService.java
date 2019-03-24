package com.mabellou.dddsamplemab.application;

import com.mabellou.dddsamplemab.domain.model.availableproduct.AvailableProduct;
import com.mabellou.dddsamplemab.domain.model.availableproduct.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    private Logger logger = LoggerFactory.getLogger(CatalogService.class);

    private final Catalog catalog;

    @Autowired
    public CatalogService(Catalog catalog) {
        this.catalog = catalog;
    }

    public List<AvailableProduct> catalog(){
        return catalog.findAll();
    }
}
