package com.nubank.capitalgain.core.calculator.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.nubank.capitalgain.core.calculator.application.dto.FeeDto;
import com.nubank.capitalgain.core.calculator.application.dto.TradeDto;
import com.nubank.capitalgain.core.calculator.application.dto.TradesDto;
import com.nubank.capitalgain.core.calculator.model.TypeOperation;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test Case")
class TaxCalculatorUseCasePortImplTest {

    static final FeeDto ZeroFee = new FeeDto(new BigDecimal("0.00"));

    private final TaxCalculatorUseCasePort taxCalculatorUseCasePort = new TaxCalculatorUseCasePortImpl();

    private TradesDto trades;

    @BeforeEach
    void init() {
        trades = new TradesDto();
    }

    @Test
    @DisplayName("Test case #1")
    void shouldTestSuccessfullyCase1() {
        trades = new TradesDto(Arrays.asList(
            new TradeDto(TypeOperation.BUY, "10.00", 100),
            new TradeDto(TypeOperation.SELL, "15.00", 50),
            new TradeDto(TypeOperation.SELL, "15.00", 50)));

        List<FeeDto> taxes = taxCalculatorUseCasePort.calculate(trades);

        assertAll("taxes",
            () -> assertThat(taxes).hasSize(3),
            () -> assertThat(taxes).satisfies(fee -> fee.equals(ZeroFee))
        );
    }

    @Test
    @DisplayName("Test case #2")
    void shouldTestSuccessfullyCase2() {
        trades = new TradesDto(Arrays.asList(
            new TradeDto(TypeOperation.BUY, "10.00", 10000),
            new TradeDto(TypeOperation.SELL, "20.00", 5000),
            new TradeDto(TypeOperation.SELL, "5.00", 5000)));

        List<FeeDto> taxes = taxCalculatorUseCasePort.calculate(trades);

        assertAll("taxes",
            () -> assertThat(taxes).first().isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(1).isEqualTo(new FeeDto("10000.00")),
            () -> assertThat(taxes).last().isEqualTo(ZeroFee)
        );
    }

    @Test
    @DisplayName("Test case #3")
    void shouldTestSuccessfullyCase3() {
        trades = new TradesDto(Arrays.asList(
            new TradeDto(TypeOperation.BUY, "10.00", 10000),
            new TradeDto(TypeOperation.SELL, "5.00", 5000),
            new TradeDto(TypeOperation.SELL, "20.00", 3000)));

        List<FeeDto> taxes = taxCalculatorUseCasePort.calculate(trades);

        assertAll("taxes",
            () -> assertThat(taxes).hasSize(3),
            () -> assertThat(taxes).first().isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(1).isEqualTo(ZeroFee),
            () -> assertThat(taxes).last().isEqualTo(new FeeDto("1000.00"))
        );
    }

    @Test
    @DisplayName("Test case #4")
    void shouldTestSuccessfullyCase4() {
        trades = new TradesDto(Arrays.asList(
            new TradeDto(TypeOperation.BUY, "10.00", 10000),
            new TradeDto(TypeOperation.BUY, "25.00", 5000),
            new TradeDto(TypeOperation.SELL, "15.00", 10000)));

        List<FeeDto> taxes = taxCalculatorUseCasePort.calculate(trades);

        assertAll("taxes",
            () -> assertThat(taxes).hasSize(3),
            () -> assertThat(taxes).first().isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(1).isEqualTo(ZeroFee),
            () -> assertThat(taxes).last().isEqualTo(ZeroFee)
        );
    }

    @Test
    @DisplayName("Test case #5")
    void shouldTestSuccessfullyCase5() {
        trades = new TradesDto(Arrays.asList(
            new TradeDto(TypeOperation.BUY, "10.00", 10000),
            new TradeDto(TypeOperation.BUY, "25.00", 5000),
            new TradeDto(TypeOperation.SELL, "15.00", 10000),
            new TradeDto(TypeOperation.SELL, "25.00", 5000)));

        List<FeeDto> taxes = taxCalculatorUseCasePort.calculate(trades);

        assertAll("taxes",
            () -> assertThat(taxes).hasSize(4),
            () -> assertThat(taxes).first().isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(1).isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(2).isEqualTo(ZeroFee),
            () -> assertThat(taxes).last().isEqualTo(new FeeDto("10000.00"))
        );
    }

    @Test
    @DisplayName("Test case #6")
    void shouldTestSuccessfullyCase6() {
        trades = new TradesDto(Arrays.asList(
            new TradeDto(TypeOperation.BUY, "10.00", 10000),
            new TradeDto(TypeOperation.SELL, "2.00", 5000),
            new TradeDto(TypeOperation.SELL, "20.00", 2000),
            new TradeDto(TypeOperation.SELL, "20.00", 2000),
            new TradeDto(TypeOperation.SELL, "25.00", 1000)));

        List<FeeDto> taxes = taxCalculatorUseCasePort.calculate(trades);

        assertAll("taxes",
            () -> assertThat(taxes).hasSize(5),
            () -> assertThat(taxes).first().isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(1).isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(2).isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(3).isEqualTo(ZeroFee),
            () -> assertThat(taxes).last().isEqualTo(new FeeDto("3000.00"))
        );
    }

    @Test
    @DisplayName("Test case #7")
    void shouldTestSuccessfullyCase7() {
        trades = new TradesDto(Arrays.asList(
            new TradeDto(TypeOperation.BUY, "10.00", 10000),
            new TradeDto(TypeOperation.SELL, "2.00", 5000),
            new TradeDto(TypeOperation.SELL, "20.00", 2000),
            new TradeDto(TypeOperation.SELL, "20.00", 2000),
            new TradeDto(TypeOperation.SELL, "25.00", 1000),
            new TradeDto(TypeOperation.BUY, "20.00", 10000),
            new TradeDto(TypeOperation.SELL, "15.00", 5000),
            new TradeDto(TypeOperation.SELL, "30.00", 4350),
            new TradeDto(TypeOperation.SELL, "30.00", 650)));

        List<FeeDto> taxes = taxCalculatorUseCasePort.calculate(trades);

        assertAll("taxes",
            () -> assertThat(taxes).hasSize(9),
            () -> assertThat(taxes).first().isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(1).isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(2).isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(3).isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(4).isEqualTo(new FeeDto("3000.00")),
            () -> assertThat(taxes).element(5).isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(6).isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(7).isEqualTo(new FeeDto("3700.00")),
            () -> assertThat(taxes).last().isEqualTo(ZeroFee)
        );
    }

    @Test
    @DisplayName("Test case #8")
    void shouldTestSuccessfullyCase8() {
        trades = new TradesDto(Arrays.asList(
            new TradeDto(TypeOperation.BUY, "10.00", 10000),
            new TradeDto(TypeOperation.SELL, "50.00", 10000),
            new TradeDto(TypeOperation.BUY, "20.00", 10000),
            new TradeDto(TypeOperation.SELL, "50.00", 10000)));

        List<FeeDto> taxes = taxCalculatorUseCasePort.calculate(trades);

        assertAll("taxes",
            () -> assertThat(taxes).hasSize(4),
            () -> assertThat(taxes).first().isEqualTo(ZeroFee),
            () -> assertThat(taxes).element(1).isEqualTo(new FeeDto("80000.00")),
            () -> assertThat(taxes).element(2).isEqualTo(ZeroFee),
            () -> assertThat(taxes).last().isEqualTo(new FeeDto("60000.00"))
        );
    }
}