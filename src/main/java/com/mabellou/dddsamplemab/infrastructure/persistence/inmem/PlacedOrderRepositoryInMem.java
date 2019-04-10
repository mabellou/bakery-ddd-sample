package com.mabellou.dddsamplemab.infrastructure.persistence.inmem;

import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrder;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderId;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlacedOrderRepositoryInMem implements PlacedOrderRepository {

    private EventStoreInMem eventStore;

    @Autowired
    public PlacedOrderRepositoryInMem(EventStoreInMem eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public Optional<PlacedOrder> findById(PlacedOrderId placedOrderId) {
        return eventStore.findById(placedOrderId, PlacedOrder::new);
    }

    @Override
    public List<PlacedOrder> findAll() {
        return eventStore.findAll("PlacedOrder", PlacedOrder::new);
    }

    @Override
    public void save(PlacedOrder placedOrder) {
        eventStore.appendToStream(placedOrder.placedOrderId(), placedOrder.version(), placedOrder.changes());
    }

    @Override
    public PlacedOrderId nextPlacedOrderId() {
        return eventStore.nextId(PlacedOrderId::new);
    }
}
