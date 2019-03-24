package com.mabellou.dddsamplemab.interfaces.rest;

import com.mabellou.dddsamplemab.application.CatalogService;
import com.mabellou.dddsamplemab.application.representation.AvailableProductRepresentation;
import com.mabellou.dddsamplemab.domain.model.availableproduct.AvailableProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    private CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public ResponseEntity catalog(){
        List<AvailableProduct> catalog = catalogService.catalog();

        List<AvailableProductRepresentation> catalogRepresentation = catalog
                .stream()
                .map(AvailableProductRepresentation::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(catalogRepresentation);
    }
}
