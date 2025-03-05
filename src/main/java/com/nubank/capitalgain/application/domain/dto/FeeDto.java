package com.nubank.capitalgain.application.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public record FeeDto(
    @JsonProperty("tax")
    BigDecimal tax) {

}
