package com.App.Loanapp.service;

import com.App.Loanapp.dto.Loandto.LoanCalculationResult;
import com.App.Loanapp.dto.Loandto.LoanDTO;
import com.App.Loanapp.dto.Loandto.LoanStatistics;
import com.App.Loanapp.dto.Repaymentdto.PaymentSchedule;
import com.App.Loanapp.model.Loan;
import com.App.Loanapp.model.Repayment;
import com.App.Loanapp.repository.LoanRepository;
import com.App.Loanapp.repository.RepaymentRepository;
import jakarta.transaction.Transactional;
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
    @Autowired
    private RepaymentRepository repaymentRepository;

    @Transactional
    public LoanCalculationResult generateAmortizationSchedule(LoanDTO loanDTO) {
        validateLoanInputs(loanDTO);
        Loan loan = loanRepository.findById(loanDTO.getLoanId())
                .orElseThrow(() -> new IllegalArgumentException("Loan not found"));

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
                numberOfPayments,
                loan
        );

        return createLoanCalculationResult(paymentSchedule, monthlyPayment);
    }

    private BigDecimal calculateMonthlyInterestRate(BigDecimal interestRate) {

        BigDecimal monthlyInterestRate = interestRate.divide(new BigDecimal("100"), RoundingMode.HALF_UP)
                .divide(new BigDecimal("12"), RoundingMode.HALF_UP);

        return monthlyInterestRate;
        
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal principalAmount, BigDecimal monthlyInterestRate, int numberOfPayments) {

        if (monthlyInterestRate.compareTo(BigDecimal.ZERO) == 0) {
            // If there's no interest, the monthly payment is simply the principal divided by the number of payments
            return principalAmount.divide(new BigDecimal(numberOfPayments), RoundingMode.HALF_UP);
        }

        // Calculate the monthly payment using the formula
        BigDecimal onePlusR = BigDecimal.ONE.add(monthlyInterestRate); // (1 + r)
        BigDecimal onePlusRToThePowerN = onePlusR.pow(numberOfPayments); // (1 + r)^n
        BigDecimal numerator = principalAmount.multiply(monthlyInterestRate).multiply(onePlusRToThePowerN);
        BigDecimal denominator = onePlusRToThePowerN.subtract(BigDecimal.ONE); // (1 + r)^n - 1
        BigDecimal monthlyPayment = numerator.divide(denominator, RoundingMode.HALF_UP); // Rounding to two decimal places

        return monthlyPayment;

    }

    private LoanCalculationResult createLoanCalculationResult(List<PaymentSchedule> paymentSchedule, BigDecimal monthlyPayment) {

        LoanCalculationResult result = new LoanCalculationResult();

        result.setPaymentSchedule(paymentSchedule);
        result.setMonthlyPayment(monthlyPayment);

        return result;
    }



    private void validateLoanInputs(LoanDTO loanDTO) {
    }

    private List<PaymentSchedule> generatePaymentSchedule(BigDecimal principal,
                                                          BigDecimal monthlyPayment,
                                                          BigDecimal monthlyRate,
                                                          int numberOfPayments,
                                                          Loan loan) {
        List<PaymentSchedule> schedule = new ArrayList<>();
        BigDecimal remainingBalance = principal;

        for (int month = 1; month <= numberOfPayments; month++) {
            PaymentSchedule payment = createPaymentSchedule(
                    month,
                    remainingBalance,
                    monthlyPayment,
                    monthlyRate,
                    numberOfPayments
            );
            schedule.add(payment);
            saveRepayment(payment, loan);
            remainingBalance = remainingBalance.subtract(payment.getPrincipalAmount());
        }

        return schedule;
    }

    private void saveRepayment(PaymentSchedule payment, Loan loan) {
    }

    private PaymentSchedule createPaymentSchedule(int month,
                                                  BigDecimal remainingBalance,
                                                  BigDecimal monthlyPayment,
                                                  BigDecimal monthlyRate,
                                                  int numberOfPayments) {
        PaymentSchedule payment = new PaymentSchedule();
        payment.setPaymentNumber(month);
        payment.setPaymentDate(LocalDate.now().plusMonths(month));

        BigDecimal interestAmount = remainingBalance.multiply(monthlyRate);
        BigDecimal principalAmount = monthlyPayment.subtract(interestAmount);

        if (month == numberOfPayments) {
            principalAmount = remainingBalance;
            monthlyPayment = principalAmount.add(interestAmount);
        }

        payment.setInterestAmount(interestAmount);
        payment.setPrincipalAmount(principalAmount);
        payment.setTotalPayment(monthlyPayment);
        payment.setRemainingBalance(remainingBalance.subtract(principalAmount));

        return payment;
    }

    public LoanStatistics calculateLoanStatistics() {

        List<Loan> loans = loanRepository.findAll();

        BigDecimal totalDisbursed = BigDecimal.ZERO;
        BigDecimal totalPaid = BigDecimal.ZERO;
        BigDecimal outstandingBalance = BigDecimal.ZERO;
        int activeLoans = 0;
        int paidOffLoans = 0;

        for (Loan loan : loans) {
            // Total Disbursed: Sum of principal amounts
            totalDisbursed = totalDisbursed.add(loan.getPrincipalAmount());

            // Calculate Total Paid: Sum of repayment amounts
            for (Repayment repayment : loan.getRepayments()) {
                totalPaid = totalPaid.add(repayment.getAmount()); // Assuming Repayment has a getAmount method
            }

            // Calculate Outstanding Balance: Remaining principal after repayments
            BigDecimal loanBalance = loan.getPrincipalAmount().subtract(totalPaid);
            outstandingBalance = outstandingBalance.add(loanBalance.compareTo(BigDecimal.ZERO) > 0 ? loanBalance : BigDecimal.ZERO);

            // Active Loans: Count loans that have not been fully paid off
            if (loanBalance.compareTo(BigDecimal.ZERO) > 0) {
                activeLoans++;
            } else {
                paidOffLoans++;
            }
        }

        // Create LoanStatistics object with the calculated values
        return new LoanStatistics(totalDisbursed, totalPaid, outstandingBalance, activeLoans, paidOffLoans);
    }
}