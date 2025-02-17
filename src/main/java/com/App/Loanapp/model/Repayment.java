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
    private BigDecimal amountDue;
    @Enumerated(EnumType.STRING)
    private Frequency frequency;
    private BigDecimal emi; // Equated Monthly Installment
    private BigDecimal interestAmount;
    private LocalDate startDate = LocalDate.now();
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    private RepaymentStatus status;
    public Repayment() {
    }

    public Repayment(BigDecimal emi) {
        this.emi = emi;
    }

    public void setPaymentNumber(Integer paymentNumber) {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(BigDecimal amountDue) {
        this.amountDue = amountDue;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public BigDecimal getEmi() {
        return emi;
    }

    public void setEmi(BigDecimal emi) {
        this.emi = emi;
    }

    public BigDecimal getInterestAmount() {
        return interestAmount;
    }

    public void setInterestAmount(BigDecimal interestAmount) {
        this.interestAmount = interestAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public RepaymentStatus getStatus() {
        return status;
    }

    public void setStatus(RepaymentStatus status) {
        this.status = status;
    }

    public void setPrincipalAmount(BigDecimal principalAmount) {
    }

    public void setPaymentDate(LocalDate paymentDate) {
    }

    public void setTotalPayment(BigDecimal totalPayment) {
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
    }

    public BigDecimal getAmount() {
        return amountDue;
    }
}
