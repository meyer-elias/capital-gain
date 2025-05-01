package com.nubank.capitalgain.adapter.cli.in;


import com.nubank.capitalgain.core.calculator.application.TaxCalculatorUseCasePort;
import com.nubank.capitalgain.core.calculator.application.TaxCalculatorUseCasePortImpl;
import com.nubank.capitalgain.core.calculator.application.dto.TradeDto;
import com.nubank.capitalgain.core.calculator.application.dto.TradesDto;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaxCalculatorAdapterCli {

    private final TaxCalculatorUseCasePort taxCalculateUseCase = new TaxCalculatorUseCasePortImpl();

    private final JsonMapper jsonMapper = new JsonMapper();

    public void calculate() {
        System.out.println("..::Tax calculator | Capital Gain:::..\n");
        Scanner scanner = new Scanner(System.in);
        List<TradesDto> trades = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isBlank()) {
                break;
            }
            List<TradeDto> tradesArray = Arrays.asList((TradeDto[]) jsonMapper.readValue(line, TradeDto[].class));

            trades.add(new TradesDto(tradesArray));
        }
        scanner.close();
        trades.forEach(operations -> System.out.println(jsonMapper.writeValuesAsString(taxCalculateUseCase.calculate(operations))));
    }
}
