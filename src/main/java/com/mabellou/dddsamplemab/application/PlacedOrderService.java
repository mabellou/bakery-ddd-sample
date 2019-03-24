package com.mabellou.dddsamplemab.application;

import com.mabellou.dddsamplemab.application.command.PlaceAnOrderCommand;
import com.mabellou.dddsamplemab.domain.model.availableproduct.ProductId;
import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrder;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderId;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderLine;
import com.mabellou.dddsamplemab.domain.model.placedorder.PlacedOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlacedOrderService {

    private Logger logger = LoggerFactory.getLogger(PlacedOrderService.class);

    private PlacedOrderRepository placedOrderRepository;

    @Autowired
    public PlacedOrderService(final PlacedOrderRepository placedOrderRepository) {
        this.placedOrderRepository = placedOrderRepository;
    }

    public String placeAnOrder(PlaceAnOrderCommand placeAnOrderCommand){
        final PlacedOrderId id = placedOrderRepository.nextPlacedOrderId();

        List<PlacedOrderLine> placedOrderLines = toPlacedOrderLines(placeAnOrderCommand);

        PlacedOrder placedOrder = new PlacedOrder(
                id,
                placeAnOrderCommand.creationDate,
                new CustomerId(placeAnOrderCommand.customerId),
                placedOrderLines
        );

        placedOrderRepository.add(placedOrder);
        logger.info("A new order has been placed with the id :{}", placedOrder.placedOrderId().idString());
        return placedOrder.placedOrderId().idString();
    }

    private List<PlacedOrderLine> toPlacedOrderLines(PlaceAnOrderCommand placeAnOrderCommand){
        return placeAnOrderCommand.orderLines.stream()
                .map(orderLine -> new PlacedOrderLine(
                        orderLine.itemNumber,
                        new ProductId(orderLine.productId)
                ))
                .collect(Collectors.toList());
    }
}
