package com.nubank.capitalgain.core.calculator.application;


import com.nubank.capitalgain.core.calculator.application.dto.FeeDto;
import com.nubank.capitalgain.core.calculator.application.dto.TradesDto;
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
