package com.nubank.capitalgain.application.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record FeeDto(
    @JsonProperty("tax")
    BigDecimal tax) {

    public FeeDto(@JsonProperty("tax") String tax) {
        this(new BigDecimal(tax));
    }
}
