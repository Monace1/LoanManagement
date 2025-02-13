/*package com.App.Loanapp.dto.Loandto;

import com.App.Loanapp.model.LoanStatus;

import java.math.BigDecimal;

public RepaymentsDto getLoanStatistics() {
    long totalLoans = loanRepository.count();
    long fullyRepaidLoans = loanRepository.countByStatus(LoanStatus.PAID);
    BigDecimal totalLoanAmount = loanRepository.sumTotalLoanAmount();
    BigDecimal totalRepaidAmount = loanRepository.sumTotalRepaidAmount();
    BigDecimal totalInterestEarned = loanRepository.sumTotalInterestEarned();

    return new LoanCalculationResult(totalLoans, fullyRepaidLoans, totalLoanAmount, totalRepaidAmount, totalInterestEarned);
}
*/