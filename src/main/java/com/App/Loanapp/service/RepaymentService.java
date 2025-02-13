package com.App.Loanapp.service;

import com.App.Loanapp.model.Loan;
import com.App.Loanapp.model.Repayment;
import com.App.Loanapp.repository.RepaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Service
public class RepaymentService {
    @Autowired
    private RepaymentRepository repaymentRepository;

    public void generateRepaymentSchedule(Loan loan) {
        BigDecimal principal = loan.getPrincipal_amount();
        BigDecimal interestRate = loan.getInterestRate().divide(BigDecimal.valueOf(100), 8, RoundingMode.HALF_UP);
        int term = loan.getRepayment_period();
        BigDecimal monthlyRate = interestRate.divide(BigDecimal.valueOf(12), 8, RoundingMode.HALF_UP);
        BigDecimal emi = principal.multiply(monthlyRate).divide(BigDecimal.ONE.subtract(BigDecimal.ONE.divide(
                BigDecimal.ONE.add(monthlyRate).pow(term), 8, RoundingMode.HALF_UP)), 2, RoundingMode.HALF_UP);

        LocalDate startDate = loan.getStartdate();
        BigDecimal remainingBalance = principal;

        for (int i = 0; i < term; i++) {
            BigDecimal interestPayment = remainingBalance.multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);
            BigDecimal principalPayment = emi.subtract(interestPayment).setScale(2, RoundingMode.HALF_UP);
            remainingBalance = remainingBalance.subtract(principalPayment);

            Repayment repayment = new Repayment();
            repayment.setLoan(loan);
            repayment.setAmount_due(emi);
            repayment.setInterestAmount(interestPayment);
            repayment.setStartdate(startDate.plusMonths(i + 1));
            repayment.setDue_Date(startDate.plusMonths(i + 1));
            repaymentRepository.save(repayment);
        }
    }
}
