package com.nubank.capitalgain.core.calculator.application.dto;

import java.util.ArrayList;
import java.util.List;

public record TradesDto(List<TradeDto> operations) {

    public TradesDto() {
        this(new ArrayList<>());
    }

    public List<TradeDto> operations() {
        return new ArrayList<>(operations);
    }

    public int size() {
        return this.operations.size();
    }
}
