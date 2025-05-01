package com.nubank.capitalgain.adapter.cli;

import com.nubank.capitalgain.adapter.cli.in.TaxCalculatorAdapterCli;

public class CapitalGainMain {

    public static void main(String[] args) {
        TaxCalculatorAdapterCli taxCalculatorAdapterConsoleCli = new TaxCalculatorAdapterCli();
        taxCalculatorAdapterConsoleCli.calculate();
    }
}