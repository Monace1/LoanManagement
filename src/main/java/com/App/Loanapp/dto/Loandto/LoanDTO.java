package com.App.Loanapp.dto.Loandto;

import com.App.Loanapp.model.Frequency;
import com.App.Loanapp.model.Loan;
import com.App.Loanapp.model.LoanStatus;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Component
public class LoanDTO {

    private Long customerId;
    private BigDecimal principalAmount;
    private BigDecimal interestRate;
    private Integer repaymentPeriod;
    private LocalDate duedate;
    private LocalDate created_at;
    private Frequency frequency;
    private LoanStatus status;
    private Long loanid;


    public BigDecimal getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(BigDecimal principalAmount) {
        this.principalAmount = principalAmount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setRepaymentPeriod(Integer repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    public LocalDate getDuedate()
    {
        return duedate;
    }

    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }

    public Frequency getFrequency() {
        return frequency;
    }
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public LoanStatus getStatus() {
        return status;
    }
    public void setStatus(LoanStatus status) {
        this.status = status;
    }
    public LocalDate getCreated_at() {
        return created_at;
    }
    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public Long getLoanId() {
        return loanid;
    }

    /*public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }*/

   /* public void setLoan_id(Long loan_id) {
        this.loan_id = loan_id;
    }*/

   /* public Loan getLoan() {
        return loan;
    }
    public void setLoan(Loan loan) {
        this.loan = loan;
    }*/
}

