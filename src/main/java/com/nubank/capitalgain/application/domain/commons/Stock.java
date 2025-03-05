package com.nubank.capitalgain.application.domain.commons;

import com.nubank.capitalgain.application.domain.exceptions.IllegalOperationStockException;
import com.nubank.capitalgain.application.domain.primitives.Money;
import com.nubank.capitalgain.application.domain.primitives.Quantity;
import com.nubank.capitalgain.application.domain.primitives.ZeroMoney;

/**
 * Represents a stock in the portfolio.
 */
public class Stock {

    private Quantity position;

    private Money weightedAveragePrice;

    /**
     * Constructor default
     */
    public Stock() {
        this.position = new Quantity();
        this.weightedAveragePrice = new ZeroMoney();
    }

    /**
     * Calculate the weighted average price of the stock.
     *
     * @param quantity quantity
     * @param unitCost unitCost
     */
    private void calculateWeightAverage(Money unitCost, Quantity quantity) {
        Money totalOperation = unitCost.multiply(quantity);
        Money averagePrice = weightedAveragePrice.multiply(position);
        Quantity totalPosition = position.sum(quantity);

        this.weightedAveragePrice = (averagePrice.sum(totalOperation)).divide(totalPosition);
    }

    /**
     * Add an order to the stock.
     *
     * @param quantity quantity
     * @param unitCost unitCost
     */
    public void add(Money unitCost, Quantity quantity) {
        this.calculateWeightAverage(unitCost, quantity);
        this.position = position.sum(quantity);
    }

    /**
     * Remove an order from the stock.
     *
     * @param quantity quantity be removed
     * @throws IllegalOperationStockException if the quantity of the order is greater than the position.
     */
    public void remove(Quantity quantity) {
        if (quantity.number() > position.number()) {
            throw new IllegalOperationStockException("The quantity of the order is greater than the position.");
        }
        this.position = quantity.subtract(position);
    }

    /**
     * Calculate the average cost of the stock.
     *
     * @param quantity quantity
     * @return Money with the average cost.
     */
    public Money averageCost(Quantity quantity) {
        return weightedAveragePrice.multiply(quantity);
    }
}
