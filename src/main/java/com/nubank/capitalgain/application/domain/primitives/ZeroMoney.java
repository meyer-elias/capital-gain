package com.nubank.capitalgain.application.domain.primitives;

import java.math.BigDecimal;

public final class ZeroMoney extends Money {

    public ZeroMoney() {
        super(BigDecimal.ZERO);
    }
}
