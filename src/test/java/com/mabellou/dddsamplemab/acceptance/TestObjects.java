package com.mabellou.dddsamplemab.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TestObjects {

    public static final String DATE_STRING_01_01_2019_08_00 = "2019-01-01T08:00:00.000";

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

    public static String placeAnOrderRequest() {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode payload = mapper.createObjectNode();
        payload.put("creationDate", DATE_STRING_01_01_2019_08_00);
        payload.put("customerId", "1C");

        ObjectNode line1 = mapper.createObjectNode();
        line1.put("productId", "1P");
        line1.put("itemNumber", "1");

        ObjectNode line2 = mapper.createObjectNode();
        line2.put("productId", "2P");
        line2.put("itemNumber", "2");

        ArrayNode orderLines = mapper.createArrayNode();
        orderLines.add(line1);
        orderLines.add(line2);

        payload.set("orderLines", orderLines);
        return payload.toString();
    }
}
