import com.nubank.capitalgain.core.calculator.application.TaxCalculatorUseCasePort;
import com.nubank.capitalgain.core.calculator.application.TaxCalculatorUseCasePortImpl;

module com.nubank.capitalgain.core.module {
    exports com.nubank.capitalgain.core.calculator.application.dto;
    exports com.nubank.capitalgain.core.calculator.application;
    provides TaxCalculatorUseCasePort with TaxCalculatorUseCasePortImpl;
}