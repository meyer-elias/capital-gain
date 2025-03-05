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
     * @param order order
     */
    private void calculateWeightAverage(Order order) {
        Money averagePrice = weightedAveragePrice.multiply(position);
        Quantity totalPosition = position.sum(order.quantity());

        this.weightedAveragePrice = (averagePrice.sum(order.getTotalCost())).divide(totalPosition);
    }

    /**
     * Add an order to the stock.
     *
     * @param order order
     */
    public void add(Order order) {
        this.calculateWeightAverage(order);
        this.position = position.sum(order.quantity());
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
