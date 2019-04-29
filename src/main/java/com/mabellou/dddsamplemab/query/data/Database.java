package com.mabellou.dddsamplemab.query.data;

import com.mabellou.dddsamplemab.query.representation.AvailableProductRepresentation;
import com.mabellou.dddsamplemab.query.representation.InvoiceRepresentation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Database {
    public Map<String, AvailableProductRepresentation> availableProductByIdMap = new HashMap<>();
    public Map<String, InvoiceRepresentation> invoiceByPlacedOrderIdMap = new HashMap<>();
}
