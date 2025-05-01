package com.nubank.capitalgain.core.calculator.application;


import com.nubank.capitalgain.core.calculator.application.dto.FeeDto;
import com.nubank.capitalgain.core.calculator.application.dto.TradeDto;
import com.nubank.capitalgain.core.calculator.application.dto.TradesDto;
import com.nubank.capitalgain.core.calculator.domain.Calculator;
import com.nubank.capitalgain.core.calculator.domain.CalculatorGainCapital;
import com.nubank.capitalgain.core.calculator.domain.CalculatorTax;
import com.nubank.capitalgain.core.calculator.model.Fee;
import com.nubank.capitalgain.core.calculator.model.Trade;
import com.nubank.capitalgain.core.commons.Money;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the TaxCalculatorUseCasePort.
 */
public class TaxCalculatorUseCasePortImpl implements TaxCalculatorUseCasePort {

    private final Calculator<Trade, Money> calculatorTax = new CalculatorTax(new CalculatorGainCapital());

    public List<FeeDto> calculate(TradesDto trades) {
        List<Fee> fees = new ArrayList<>(trades.size());
        trades
            .operations()
            .stream()
            .map(this::mapperFrom)
            .map(t -> new Fee(calculatorTax.computedValue(t)))
            .forEach(fees::add);

        return fees
            .stream()
            .map(this::mapperFrom)
            .toList();
    }

    private Trade mapperFrom(TradeDto dto) {
        return new Trade(dto.typeOperation(), dto.quantity(), dto.unitCost());
    }

    private FeeDto mapperFrom(Fee fee) {
        return new FeeDto(fee.getTax().getValue());
    }
}
