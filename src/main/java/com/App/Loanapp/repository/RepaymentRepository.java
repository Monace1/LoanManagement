package com.App.Loanapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.App.Loanapp.model.Repayment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, Long> {

    List<Repayment> findByLoanId(Long loanId);
}