package com.nubank.capitalgain.core.calculator.application.dto;

import com.nubank.capitalgain.core.calculator.model.TypeOperation;
import java.math.BigDecimal;

public record TradeDto(TypeOperation typeOperation, BigDecimal unitCost, int quantity) {

    public TradeDto(TypeOperation typeOperation, String unitCost, int quantity) {
        this(typeOperation, new BigDecimal(unitCost), quantity);
    }
}
