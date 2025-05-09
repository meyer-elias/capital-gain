package com.nubank.capitalgain.core.calculator.application.dto;

import java.math.BigDecimal;

public record FeeDto(BigDecimal tax) {

    public FeeDto(String tax) {
        this(new BigDecimal(tax));
    }
}
