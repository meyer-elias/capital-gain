package com.nubank.capitalgain.application.domain.dto;

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
