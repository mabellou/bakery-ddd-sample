package com.mabellou.dddsamplemab.application.command.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public class PlaceAnOrderCommand {
    public final LocalDateTime creationDate;
    public final String customerId;
    public final List<OrderLine> orderLines;

    @JsonCreator
    public PlaceAnOrderCommand(@JsonProperty("creationDate") LocalDateTime creationDate,
                               @JsonProperty("customerId") String customerId,
                               @JsonProperty("orderLines") List<OrderLine> orderLines) {
        this.creationDate = creationDate;
        this.customerId = customerId;
        this.orderLines = orderLines;
    }

    public static class OrderLine {
        public final String productId;
        public final Integer itemNumber;

        @JsonCreator
        public OrderLine(@JsonProperty("productId") String productId,
                         @JsonProperty("itemNumber") Integer itemNumber) {
            this.productId = productId;
            this.itemNumber = itemNumber;
        }
    }
}
