package com.App.Loanapp.service;

import com.App.Loanapp.dto.Loandto.LoanCalculationResult;
import com.App.Loanapp.dto.Loandto.LoanDTO;
import com.App.Loanapp.dto.Loandto.LoanStatistics;
import com.App.Loanapp.dto.Repaymentdto.PaymentSchedule;
import com.App.Loanapp.model.LoanStatus;
import com.App.Loanapp.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepaymentService {

    @Autowired
    private LoanRepository loanRepository;
    private Object PaymentSchedule;

    public LoanCalculationResult generateAmortizationSchedule(LoanDTO loanDTO) {
        // Validate inputs
        validateLoanInputs(loanDTO);

        BigDecimal monthlyInterestRate = calculateMonthlyInterestRate(loanDTO.getInterestRate());
        int numberOfPayments = loanDTO.getRepaymentPeriod() * 12;
        BigDecimal monthlyPayment = calculateMonthlyPayment(
                loanDTO.getPrincipalAmount(),
                monthlyInterestRate,
                numberOfPayments
        );

        List<PaymentSchedule> paymentSchedule = generatePaymentSchedule(
                loanDTO.getPrincipalAmount(),
                monthlyPayment,
                monthlyInterestRate,
                numberOfPayments
        );

        return createLoanCalculationResult(paymentSchedule, monthlyPayment);
    }

    private LoanCalculationResult createLoanCalculationResult(List<PaymentSchedule> paymentSchedule, BigDecimal monthlyPayment) {

        LoanCalculationResult result = new LoanCalculationResult();
        result.setPaymentSchedule(paymentSchedule);
        result.setMonthlyPayment(monthlyPayment);
        return result;

    }

    private void validateLoanInputs(LoanDTO loanDTO) {
    }

    private BigDecimal calculateMonthlyInterestRate(BigDecimal annualRate) {
        return annualRate.divide(new BigDecimal("12"), 8, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal principal,
                                               BigDecimal monthlyRate,
                                               int numberOfPayments) {
        // Using the formula: M = P[r(1+r)^n]/[(1+r)^n – 1]
        BigDecimal numerator = principal.multiply(monthlyRate)
                .multiply(BigDecimal.ONE.add(monthlyRate).pow(numberOfPayments));
        BigDecimal denominator = BigDecimal.ONE.add(monthlyRate).pow(numberOfPayments)
                .subtract(BigDecimal.ONE);

        return numerator.divide(denominator, 8, RoundingMode.HALF_UP);
    }

    private List<PaymentSchedule> generatePaymentSchedule(BigDecimal principal,
                                                          BigDecimal monthlyPayment,
                                                          BigDecimal monthlyRate,
                                                          int numberOfPayments) {
        List<PaymentSchedule> schedule = new ArrayList<>();
        BigDecimal remainingBalance = principal;

        for (int month = 1; month <= numberOfPayments; month++) {
            PaymentSchedule payment = new PaymentSchedule();
            payment.setPaymentNumber(month);
            payment.setPaymentDate(LocalDate.now().plusMonths(month));

            BigDecimal interestAmount = remainingBalance.multiply(monthlyRate);
            BigDecimal principalAmount = monthlyPayment.subtract(interestAmount);

            payment.setInterestAmount(interestAmount);
            payment.setPrincipalAmount(principalAmount);
            payment.setTotalPayment(monthlyPayment);
            payment.setRemainingBalance(remainingBalance.subtract(principalAmount));

            schedule.add(payment);
            remainingBalance = remainingBalance.subtract(principalAmount);
        }

        return schedule;
    }

    public LoanStatistics calculateLoanStatistics() {
        LoanStatistics statistics = new LoanStatistics();

        // Calculate total disbursed amount
        BigDecimal totalDisbursed = loanRepository.findSumOfPrincipalAmount();
        statistics.setTotalDisbursed(totalDisbursed);

        // Calculate total paid amount
        BigDecimal totalPaid = loanRepository.findSumOfRepayments();
        statistics.setTotalPaid(totalPaid);

        // Calculate outstanding balance
        statistics.setOutstandingBalance(
                totalDisbursed.subtract(totalPaid)
        );

        // Count active and paid-off loans
        Long activeLoans = loanRepository.countByStatus(LoanStatus.ACTIVE);
        Long paidOffLoans = loanRepository.countByStatus(LoanStatus.PAID_OFF);

        statistics.setActiveLoans(activeLoans.intValue());
        statistics.setPaidOffLoans(paidOffLoans.intValue());

        return statistics;
    }
}
