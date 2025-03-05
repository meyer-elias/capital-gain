package com.nubank.capitalgain.application.ports.in;

import com.nubank.capitalgain.application.domain.dto.FeeDto;
import com.nubank.capitalgain.application.domain.dto.TradesDto;
import java.util.List;

/**
 * Use case port to calculate the tax.
 */
public interface TaxCalculatorUseCasePort {

    /**
     * Calculate the taxas for the given trades.
     *
     * @param trades list trade
     * @return fees list
     */
    List<FeeDto> calculate(TradesDto trades);

}
