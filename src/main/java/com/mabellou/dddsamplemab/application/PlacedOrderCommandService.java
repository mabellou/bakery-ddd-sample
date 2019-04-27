package com.mabellou.dddsamplemab.application;

import com.mabellou.dddsamplemab.application.command.PlaceAnOrderCommand;
import com.mabellou.dddsamplemab.domain.model.customer.CustomerId;
import com.mabellou.dddsamplemab.domain.model.invoice.Invoice;
import com.mabellou.dddsamplemab.domain.model.invoice.InvoiceBook;
import com.mabellou.dddsamplemab.domain.model.placedorder.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlacedOrderCommandService {

    private Logger logger = LoggerFactory.getLogger(PlacedOrderCommandService.class);

    private PlacedOrderRepository placedOrderRepository;
    private PlacedOrderLineService placedOrderLineService;
    private InvoiceBook invoiceBook;

    @Autowired
    public PlacedOrderCommandService(final PlacedOrderRepository placedOrderRepository,
                                     final PlacedOrderLineService placedOrderLineService,
                                     final InvoiceBook invoiceBook) {
        this.placedOrderRepository = placedOrderRepository;
        this.placedOrderLineService = placedOrderLineService;
        this.invoiceBook = invoiceBook;
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

        Invoice invoice = placedOrder.generateInvoice(invoiceBook.nextInvoiceId());

        placedOrderRepository.save(placedOrder);
        invoiceBook.save(invoice);

        logger.info("A new order has been placed with the id :{}", placedOrder.placedOrderId());
        logger.info("A new invoice has been add to the book with the id :{}", invoice.invoiceId());

        return placedOrder.placedOrderId().idString();
    }

    private List<PlacedOrderLine> toPlacedOrderLines(PlaceAnOrderCommand placeAnOrderCommand){
        return placeAnOrderCommand.orderLines.stream()
                .map(orderLine -> placedOrderLineService.placeOrderLine(orderLine.productId, orderLine.itemNumber))
                .collect(Collectors.toList());
    }
}
