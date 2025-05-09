package com.nubank.capitalgain.core.commons;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a percentage value.
 */
public record Percentage(BigDecimal value) {

    /**
     * @param value BigDecimal value
     */
    public Percentage {
        if (Objects.isNull(value)) {
            throw new IllegalArgumentException("Percentage must be informed!");
        }
    }

    public Percentage(String value) {
        this(new BigDecimal(value));
    }
}
