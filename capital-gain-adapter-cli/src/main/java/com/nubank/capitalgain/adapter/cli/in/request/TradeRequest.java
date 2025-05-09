package com.nubank.capitalgain.adapter.cli.in.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TradeRequest(@JsonProperty("operation") String typeOperation,
                           @JsonProperty("unit-cost") String unitCost,
                           @JsonProperty("quantity") int quantity) {

}
