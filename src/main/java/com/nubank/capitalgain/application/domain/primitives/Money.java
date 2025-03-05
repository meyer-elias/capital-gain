package com.nubank.capitalgain.application.domain.primitives;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Represents a monetary value.
 */
public class Money {

    private static final int DECIMAL_PLACES_LIMIT = 2;

    private static final NumberFormat FORMATTER = NumberFormat.getInstance(Locale.ENGLISH);

    private final BigDecimal value;

    /**
     * Constructor
     *
     * @param value BigDecimal value
     */
    public Money(BigDecimal value) {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Money must be informed!");
        }

        this.value = value.setScale(DECIMAL_PLACES_LIMIT, RoundingMode.HALF_UP);
    }

    /**
     * Constructor to create a Money object
     *
     * @param value String value
     * @throws IllegalArgumentException if value is blank or not numeric
     */
    public Money(String value) {
        this(new BigDecimal(value));
    }

    /**
     * Method to get the value
     *
     * @return value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Method to add two monetary values
     *
     * @param money Money to sum
     * @return Money with value calculated
     */
    public Money sum(Money money) {
        return new Money(value.add(money.value));
    }

    /**
     * Method to subtract two monetary values
     *
     * @param money Money to subtract
     * @return Money with value calculated
     */
    public Money subtract(Money money) {
        return new Money(value.subtract(money.value));
    }

    /**
     * Method to multiply a monetary value by a quantity
     *
     * @param quantity Quantity
     * @return Money with value calculated
     */
    public Money multiply(Quantity quantity) {
        return new Money(value.multiply(new BigDecimal(quantity.number())));
    }

    /**
     * Method to multiply a monetary value by a percentage
     *
     * @param percentage Percentage
     * @return Money with value calculated
     */
    public Money multiply(Percentage percentage) {
        return new Money(value.multiply(percentage.value()));
    }

    /**
     * Method to divide a monetary value by a quantity
     *
     * @param quantity quantity
     * @return Money with value calculated
     */
    public Money divide(Quantity quantity) {
        return new Money(value.divide(new BigDecimal(quantity.number()), RoundingMode.HALF_UP));
    }

    /**
     * Verify if a monetary value is greater than another
     *
     * @param other Money to compare
     * @return boolean
     */
    public boolean greaterThan(Money other) {
        return value.compareTo(other.value) > 0;
    }

    /**
     * Check if is negative
     *
     * @return true if negative;otherwise false;
     */
    public boolean isNegative() {
        return value.compareTo(BigDecimal.ZERO) < 0;
    }

    /**
     * Check if is positive
     *
     * @return true if positive;otherwise false;
     */
    public boolean isPositive() {
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public String toString() {
        return FORMATTER.format(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Money money) {
            return value.equals(money.value);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
