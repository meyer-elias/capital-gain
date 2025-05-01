package com.nubank.capitalgain.core.calculator.domain;


import com.nubank.capitalgain.core.calculator.model.Trade;
import com.nubank.capitalgain.core.commons.Money;

/**
 * Represents a decorator for a calculator.
 */
abstract class AbstractCalculatorDecorator implements Calculator<Trade, Money> {

    protected final Calculator<Trade, Money> calculator;

    protected AbstractCalculatorDecorator(Calculator<Trade, Money> calculator) {
        this.calculator = calculator;
    }

    @Override
    public Money computedValue(Trade trade) {
        return calculator.computedValue(trade);
    }
}
