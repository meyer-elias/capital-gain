package com.nubank.capitalgain.core.calculator.model;

import com.nubank.capitalgain.core.commons.Money;

/**
 * Class that represents the tax calculated on a monetary value.
 */
public class Fee {

    private final Money tax;

    public Fee(Money tax) {
        this.tax = tax;
    }

    public Money getTax() {
        return tax;
    }
}
