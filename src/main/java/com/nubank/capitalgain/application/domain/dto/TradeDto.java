package com.nubank.capitalgain.application.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nubank.capitalgain.application.domain.commons.TypeOperation;
import java.math.BigDecimal;

public record TradeDto(
    @JsonProperty("operation")
    TypeOperation typeOperation,
    @JsonProperty("unit-cost")
    BigDecimal unitCost,
    @JsonProperty("quantity")
    int quantity) {

    public TradeDto(TypeOperation typeOperation, String unitCost, int quantity) {
        this(typeOperation, new BigDecimal(unitCost), quantity);
    }
}
