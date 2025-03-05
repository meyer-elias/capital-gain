package com.nubank.capitalgain.application.domain.calculator;

import com.nubank.capitalgain.application.domain.commons.Trade;
import com.nubank.capitalgain.application.domain.primitives.Money;

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
