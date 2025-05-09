package com.nubank.capitalgain.core.calculator.domain;


import com.nubank.capitalgain.core.calculator.model.Trade;
import com.nubank.capitalgain.core.commons.Money;
import com.nubank.capitalgain.core.commons.Percentage;
import com.nubank.capitalgain.core.commons.ZeroMoney;

/**
 * Represents a calculator for fees.
 */
public class CalculatorTax extends AbstractCalculatorDecorator {

    /**
     * Value to be free of tax
     */
    private static final Money FREE_TAX_LIMIT_VALUE = new Money("20000.00");

    /**
     * Percentage of tax
     */
    private static final Percentage TWENTY_PERCENTAGE = new Percentage("0.20");

    public CalculatorTax(Calculator<Trade, Money> calculator) {
        super(calculator);
    }

    @Override
    public Money computedValue(Trade trade) {
        Money capitalGain = super.computedValue(trade);
        if (isTaxable(trade) && hasProfit(capitalGain)) {
            return tax(capitalGain);
        } else {
            // No tax
            return new ZeroMoney();
        }
    }

    /**
     * Check if the capital gain is free of tax
     *
     * @return true if the capital gain is free of tax, false otherwise
     */
    private boolean isTaxable(Trade trade) {
        return trade.operationTotal().greaterThan(FREE_TAX_LIMIT_VALUE) &&
            trade.isSell();
    }

    /**
     * Check if the transaction has profit
     *
     * @return true if the transaction has profit, false otherwise
     */
    private boolean hasProfit(Money capitalGain) {
        return capitalGain.isPositive() && capitalGain.greaterThan(new ZeroMoney());
    }

    private Money tax(Money capitalGain) {
        return capitalGain.multiply(TWENTY_PERCENTAGE);
    }
}
