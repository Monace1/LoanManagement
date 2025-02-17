package com.App.Loanapp.repository;

import com.App.Loanapp.model.Loan;
import com.App.Loanapp.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("SELECT SUM(l.principalAmount) FROM Loan l")
    BigDecimal findSumOfPrincipalAmount();

    @Query("SELECT SUM(r.amountDue) FROM Repayment r")
    BigDecimal findSumOfRepayments();

    Long countByStatus(LoanStatus status);
}

