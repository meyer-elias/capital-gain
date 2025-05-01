package com.nubank.capitalgain.core.commons;

import java.math.BigDecimal;

public final class ZeroMoney extends Money {

    public ZeroMoney() {
        super(BigDecimal.ZERO);
    }
}
