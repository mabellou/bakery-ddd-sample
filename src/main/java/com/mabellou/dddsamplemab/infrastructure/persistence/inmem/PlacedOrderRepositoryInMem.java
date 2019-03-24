package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrder;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderId;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PlacedOrderRepositoryInMem implements PlacedOrderRepository {

    private Map<PlacedOrderId, PlacedOrder> placedOrderDb;

    public PlacedOrderRepositoryInMem() {
        this.placedOrderDb = new HashMap<>();
    }

    @Override
    public PlacedOrder find(PlacedOrderId placedOrderId) {
        return placedOrderDb.get(placedOrderId);
    }

    @Override
    public List<PlacedOrder> findAll() {
        return new ArrayList<>(placedOrderDb.values());
    }

    @Override
    public void add(PlacedOrder placedOrder) {
        PlacedOrderId id = placedOrder.placedOrderId();

        if(placedOrderDb.get(id) != null){
            throw new IllegalStateException("The id already exists");
        }

        placedOrderDb.put(id, placedOrder);
    }

    @Override
    public PlacedOrderId nextPlacedOrderId() {
        String random = UUID.randomUUID().toString().toUpperCase();
        return new PlacedOrderId(
                random.substring(0, random.indexOf("-"))
        );
    }
}
