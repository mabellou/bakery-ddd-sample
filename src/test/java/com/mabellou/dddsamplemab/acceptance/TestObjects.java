package com.mabellou.dddsamplemab.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class TestObjects {

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
}
