package com.nubank.capitalgain;

import com.nubank.capitalgain.adapter.in.TaxCalculatorAdapterConsoleCli;

public class CapitalGainMain {

    public static void main(String[] args) {
        TaxCalculatorAdapterConsoleCli taxCalculatorAdapterConsoleCli = new TaxCalculatorAdapterConsoleCli();
        taxCalculatorAdapterConsoleCli.calculate();
    }
}
