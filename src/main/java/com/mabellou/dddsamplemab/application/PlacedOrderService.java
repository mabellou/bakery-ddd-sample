package com.mabellou.dddsamplemab.application;

import com.mabellou.dddsamplemab.application.command.PlaceAnOrderCommand;
import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.placedorder.*;
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
    private PlacedOrderLineService placedOrderLineService;

    @Autowired
    public PlacedOrderService(final PlacedOrderRepository placedOrderRepository,
                              final PlacedOrderLineService placedOrderLineService) {
        this.placedOrderRepository = placedOrderRepository;
        this.placedOrderLineService = placedOrderLineService;
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
                .map(orderLine -> placedOrderLineService.placeOrderLine(orderLine.productId, orderLine.itemNumber))
                .collect(Collectors.toList());
    }
}
