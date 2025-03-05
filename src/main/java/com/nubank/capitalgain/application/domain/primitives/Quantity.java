package com.nubank.capitalgain.application.domain.primitives;

/**
 * Represents a quantity of something.
 */
public record Quantity(Integer number) {

    /**
     * Constructor to create a quantity.
     *
     * @param number Quantity
     * @throws IllegalArgumentException if the quantity is negative.
     */
    public Quantity {
        if (number < 0) {
            throw new IllegalArgumentException("Quantity is negative!");
        }
    }

    /**
     * Constructor to create a zero quantity.
     */
    public Quantity() {
        this(0);
    }

    /**
     * Method to add a quantity.
     *
     * @return Quantity with the sum of the quantities.
     */
    public Quantity sum(Quantity quantity) {
        return new Quantity(number + quantity.number);
    }

    /**
     * Method to subtract a quantity.
     *
     * @return Quantity with the subtraction of the quantities.
     */
    public Quantity subtract(Quantity quantity) {
        return new Quantity(quantity.number - number);
    }
}
