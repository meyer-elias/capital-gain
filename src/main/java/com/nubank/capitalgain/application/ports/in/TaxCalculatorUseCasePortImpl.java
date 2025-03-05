package com.nubank.capitalgain.application.ports.in;


import com.nubank.capitalgain.application.domain.calculator.Calculator;
import com.nubank.capitalgain.application.domain.calculator.CalculatorGainCapital;
import com.nubank.capitalgain.application.domain.calculator.CalculatorTax;
import com.nubank.capitalgain.application.domain.commons.Fee;
import com.nubank.capitalgain.application.domain.commons.Trade;
import com.nubank.capitalgain.application.domain.dto.FeeDto;
import com.nubank.capitalgain.application.domain.dto.TradeDto;
import com.nubank.capitalgain.application.domain.dto.TradesDto;
import com.nubank.capitalgain.application.domain.primitives.Money;
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
