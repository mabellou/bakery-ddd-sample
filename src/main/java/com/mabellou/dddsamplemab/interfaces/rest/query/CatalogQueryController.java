package com.mabellou.dddsamplemab.interfaces.rest.query;

import com.mabellou.dddsamplemab.query.service.CatalogQueryService;
import com.mabellou.dddsamplemab.query.representation.AvailableProductRepresentation;
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
public class CatalogQueryController {

    private final CatalogQueryService catalogQueryService;

    @Autowired
    public CatalogQueryController(CatalogQueryService catalogQueryService) {
        this.catalogQueryService = catalogQueryService;
    }

    @GetMapping
    public ResponseEntity catalog(){
        List<AvailableProduct> catalog = catalogQueryService.catalog();

        List<AvailableProductRepresentation> catalogRepresentation = catalog
                .stream()
                .map(AvailableProductRepresentation::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(catalogRepresentation);
    }
}
