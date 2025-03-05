package com.nubank.capitalgain.application.domain.commons;

import com.nubank.capitalgain.application.domain.primitives.Money;
import com.nubank.capitalgain.application.domain.primitives.Quantity;
import java.math.BigDecimal;

/**
 * Record that represents a trading operation.
 *
 * @param typeOperation The typeOperation of operation
 * @param quantity      The quantity of the trade
 * @param unitCost      The unit cost of the trade
 */
public record Trade(TypeOperation typeOperation, Quantity quantity, Money unitCost) {

    public Trade(TypeOperation typeOperation, int quantity, BigDecimal unitCost) {
        this(typeOperation, new Quantity(quantity), new Money(unitCost));
    }

    /**
     * Calculate the capital gain.
     *
     * @return The total of the trade
     */
    public Money operationTotal() {
        return unitCost.multiply(quantity);
    }

    /**
     * Check if the trade is a buy operation.
     *
     * @return True if the trade is a buy operation, false otherwise
     */
    public boolean isBuy() {
        return typeOperation == TypeOperation.BUY;
    }

    /**
     * Check if the trade is a sell operation.
     *
     * @return True if the trade is a sell operation, false otherwise
     */
    public boolean isSell() {
        return typeOperation == TypeOperation.SELL;
    }
}
