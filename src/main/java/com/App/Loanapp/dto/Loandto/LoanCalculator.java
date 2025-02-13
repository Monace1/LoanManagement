/*package com.App.Loanapp.dto.Loandto;

import com.App.Loanapp.model.Frequency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class LoanCalculator {

    public LoanCalculationResult calculateLoan(LoanCalculationDTO calculationDTO) {
        BigDecimal interestRatePerPeriod = getInterestRatePerPeriod(calculationDTO.getInterestRate(), calculationDTO.getFrequency());
        int numberOfPayments = calculationDTO.getRepaymentPeriod() * getNumberOfPaymentsPerYear(calculationDTO.getFrequency());

        BigDecimal emi = calculateEMI(
                calculationDTO.getPrincipalAmount(),
                interestRatePerPeriod,
                numberOfPayments
        );

        BigDecimal totalPayment = emi.multiply(BigDecimal.valueOf(numberOfPayments));
        BigDecimal totalInterest = totalPayment.subtract(calculationDTO.getPrincipalAmount());

        return new LoanCalculationResult(
                emi.setScale(2, RoundingMode.HALF_UP),
                totalInterest.setScale(2, RoundingMode.HALF_UP),
                totalPayment.setScale(2, RoundingMode.HALF_UP)
        );
    }

    private int getNumberOfPaymentsPerYear(Frequency frequency) {
        return switch (frequency) {
            case Monthly -> 12;
            case Weekly -> 52;
            case Yearly -> 1;
        };
    }

    private BigDecimal getInterestRatePerPeriod(BigDecimal annualRate, Frequency frequency) {
        int periodsPerYear = getNumberOfPaymentsPerYear(frequency);
        return annualRate.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP) // Convert to decimal
                .divide(BigDecimal.valueOf(periodsPerYear), 6, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateEMI(BigDecimal principal, BigDecimal ratePerPeriod, int numberOfPayments) {
        if (ratePerPeriod.compareTo(BigDecimal.ZERO) == 0) {
            return principal.divide(BigDecimal.valueOf(numberOfPayments), 6, RoundingMode.HALF_UP);
        }

        BigDecimal onePlusRPowerN = BigDecimal.valueOf(Math.pow(1 + ratePerPeriod.doubleValue(), numberOfPayments));
        return principal.multiply(ratePerPeriod)
                .multiply(onePlusRPowerN)
                .divide(onePlusRPowerN.subtract(BigDecimal.ONE), 6, RoundingMode.HALF_UP);
    }
}
*/