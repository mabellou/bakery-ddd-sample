package com.mabellou.dddsamplemab.interfaces.rest.query;

import com.mabellou.dddsamplemab.query.PlacedOrderQueryService;
import com.mabellou.dddsamplemab.query.representation.InvoiceRepresentation;
import com.mabellou.dddsamplemab.domain.model.invoice.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/placedorder")
public class PlacedOrderQueryController {

    private final PlacedOrderQueryService placedOrderQueryService;

    @Autowired
    public PlacedOrderQueryController(PlacedOrderQueryService placedOrderQueryService) {
        this.placedOrderQueryService = placedOrderQueryService;
    }

    @GetMapping("/{id}/invoice")
    public ResponseEntity getInvoice(@PathVariable("id") String orderId){
        Invoice invoice = placedOrderQueryService.getInvoice(orderId);
        InvoiceRepresentation invoiceRepresentation = new InvoiceRepresentation(invoice);

        return ResponseEntity.ok(invoiceRepresentation);
    }

}
