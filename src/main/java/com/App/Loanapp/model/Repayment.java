package com.App.Loanapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "repayment")
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", nullable = false)
    private Loan loan;

    private BigDecimal interestRate;
    private BigDecimal amount_due;
    @Enumerated(EnumType.STRING)
    private Frequency frequency;
    private BigDecimal emi;
    private BigDecimal interestAmount;
    private LocalDate startdate= LocalDate.now();
    private LocalDate due_Date ;
    @Enumerated(EnumType.STRING)
    private RepaymentStatus status;

    public Repayment(BigDecimal emi) {
        this.emi = emi;
    }

    public static Object builder() {
        return builder();
    }

    public Long getId() {
        return id;
    }
    public Loan getLoan() {
        return loan;
    }
    public BigDecimal getAmount_due() {
        return amount_due;
    }
    public BigDecimal getemi() {
        return emi;
    }
    public BigDecimal getInterestRate() {
        return interestRate;
    }
    public LocalDate getDue_Date() {
        return due_Date;
    }
    public BigDecimal getInterestAmount() {
        return interestAmount;
    }
    public void setLoan(Loan loan) {
        this.loan = loan;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public void setAmount_due(BigDecimal amount_due) {
        this.amount_due = amount_due;
    }
    public void setStatus(RepaymentStatus status) {
        this.status = status;
    }

    public void setDue_Date(LocalDate due_Date) {
        this.due_Date = due_Date;
    }

    public void setemi(BigDecimal emi) {
        emi = emi;
    }

    public RepaymentStatus getStatus() {
        return status;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }



    public void setPaymentDate(LocalDate now) {
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public void setEmi(BigDecimal monthlyPayment) {
    }
    /* public static Object builder() {
        return builder();
    }*/
}
