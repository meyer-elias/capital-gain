package com.nubank.capitalgain.adapter.cli.in;

import com.nubank.capitalgain.adapter.cli.in.request.TradeRequest;
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
            List<TradeRequest> tradesArray = Arrays.asList((TradeRequest[]) jsonMapper.readValue(line, TradeRequest[].class));

            trades.add(new TradesDto(tradesArray
                .stream()
                .map(this::mapperFrom)
                .toList()));
        }
        scanner.close();
        trades.forEach(operations -> System.out.println(jsonMapper.writeValuesAsString(taxCalculateUseCase.calculate(operations))));
    }

    private TradeDto mapperFrom(TradeRequest tradeRequest) {
        return new TradeDto(tradeRequest.typeOperation(), tradeRequest.unitCost(), tradeRequest.quantity());
    }
}
