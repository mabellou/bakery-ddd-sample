package com.mabellou.dddsamplemab.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mabellou.dddsamplemab.domain.model.availableproduct.AvailableProduct;
import com.mabellou.dddsamplemab.domain.model.availableproduct.ProductId;
import com.mabellou.dddsamplemab.domain.model.customer.*;

import java.math.BigDecimal;

public class TestObjects {

    public static final String DATE_STRING_01_01_2019_08_00 = "2019-01-01T08:00:00.000";
    private static final String PAIN_AU_CHOCOLAT = "Pain au chocolat";
    private static final String CROISSANT = "Croissant";

    public static Customer createCustomer(CustomerId customerId){
        return new Customer(
                customerId,
                new CustomerName("test"),
                createAddressBrussels(),
                createEmail()
        );
    }

    public static Email createEmail(){
        return new Email("mab@gmail.com");
    }

    public static Address createAddressBrussels(){
        return new Address(
                "Allée de la rue",
                "5",
                "Bruxelles",
                "au premier étage"
        );
    }

    public static AvailableProduct createPainAuChocolat_1euro(ProductId productId){
        return new AvailableProduct(
                productId,
                PAIN_AU_CHOCOLAT,
                new BigDecimal(1)
        );
    }

    public static AvailableProduct createCroissant_1euro(ProductId productId){
        return new AvailableProduct(
                productId,
                CROISSANT,
                new BigDecimal(1)
        );
    }

    public static String registrationRequest(){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode payload = mapper.createObjectNode();
        payload.put("username", "test");
        payload.put("email", "test@test.be");
        payload.put("street", "Rue de la Guinguette");
        payload.put("streetNumber", "16");
        payload.put("locality", "1030");
        payload.put("comment", "première sonnette sur la droite");
        return payload.toString();
    }

    public static String placeAnOrderRequestWithTwoLines(final CustomerId customerId,
                                                         final Integer numberProduct1,
                                                         final ProductId productId1,
                                                         final Integer numberProduct2,
                                                         final ProductId productId2) {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode payload = mapper.createObjectNode();
        payload.put("creationDate", DATE_STRING_01_01_2019_08_00);
        payload.put("customerId", customerId.idString());

        ObjectNode line1 = mapper.createObjectNode();
        line1.put("productId", productId1.idString());
        line1.put("itemNumber", numberProduct1);

        ObjectNode line2 = mapper.createObjectNode();
        line2.put("productId", productId2.idString());
        line2.put("itemNumber", numberProduct2);

        ArrayNode orderLines = mapper.createArrayNode();
        orderLines.add(line1);
        orderLines.add(line2);

        payload.set("orderLines", orderLines);
        return payload.toString();
    }
}
