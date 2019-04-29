package com.mabellou.dddsamplemab.query.service;

import com.mabellou.dddsamplemab.query.data.Database;
import com.mabellou.dddsamplemab.query.representation.AvailableProductRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogQueryService {

    private Database database;

    @Autowired
    public CatalogQueryService(Database database) {
        this.database = database;
    }

    public List<AvailableProductRepresentation> catalog(){
        return new ArrayList<>(database.availableProductByIdMap.values());
    }
}
