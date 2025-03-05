package com.nubank.capitalgain.application.domain.commons;

import com.nubank.capitalgain.application.domain.primitives.Money;

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
