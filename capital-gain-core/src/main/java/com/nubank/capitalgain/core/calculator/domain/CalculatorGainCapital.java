package com.nubank.capitalgain.core.calculator.domain;

import com.nubank.capitalgain.core.calculator.model.Stock;
import com.nubank.capitalgain.core.calculator.model.Trade;
import com.nubank.capitalgain.core.commons.Money;
import com.nubank.capitalgain.core.commons.ZeroMoney;

/**
 * Represents a calculator for the capital gain.
 */
public class CalculatorGainCapital implements Calculator<Trade, Money> {

    private final Stock stock = new Stock();
    private Money capitalGain = new ZeroMoney();

    /**
     * Compute the trade
     *
     * @param trade the input
     * @return Money computed (profit or loss)
     */
    @Override
    public Money computedValue(Trade trade) {
        if (trade.isBuy()) {
            this.buy(trade);
        } else {
            this.sell(trade);
        }
        return this.capitalGain;
    }

    /**
     * Calculate when buy a trade
     *
     * @param trade trade
     */
    void buy(Trade trade) {
        stock.add(trade.unitCost(), trade.quantity());
        this.capitalGain = new ZeroMoney();
    }

    /**
     * Calculate when sell a trade
     *
     * @param trade trade
     */
    void sell(Trade trade) {
        stock.remove(trade.quantity());

        Money costStock = stock.averageCost(trade.quantity());

        Money capitalGainOperation = trade.operationTotal().subtract(costStock);

        if (!capitalGain.isNegative()) {
            this.capitalGain = capitalGainOperation;
            return;
        }

        this.capitalGain = this.capitalGain.sum(capitalGainOperation);
    }
}
