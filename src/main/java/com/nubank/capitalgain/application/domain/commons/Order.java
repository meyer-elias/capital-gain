package com.nubank.capitalgain.application.domain.commons;

import com.nubank.capitalgain.application.domain.primitives.Money;
import com.nubank.capitalgain.application.domain.primitives.Quantity;


/**
 * Class that represents an order stock.
 *
 * @param unitCost
 * @param quantity
 */
public record Order(Money unitCost, Quantity quantity) {

    /**
     * Calculate the total cost of the order.
     *
     * @return The total cost of the order
     */
    Money getTotalCost() {
        return unitCost.multiply(quantity);
    }
}
