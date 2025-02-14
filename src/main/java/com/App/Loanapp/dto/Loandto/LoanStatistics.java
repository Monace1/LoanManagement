package com.App.Loanapp.dto.Loandto;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LoanStatistics {
    private BigDecimal totalDisbursed;
    private BigDecimal totalPaid;
    private BigDecimal outstandingBalance;
    private Integer activeLoans;
    private Integer paidOffLoans;


    public BigDecimal getTotalDisbursed() {
        return totalDisbursed;
    }

    public void setTotalDisbursed(BigDecimal totalDisbursed) {
        this.totalDisbursed = totalDisbursed;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public BigDecimal getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(BigDecimal outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    public Integer getActiveLoans() {
        return activeLoans;
    }

    public void setActiveLoans(Integer activeLoans) {
        this.activeLoans = activeLoans;
    }

    public Integer getPaidOffLoans() {
        return paidOffLoans;
    }

    public void setPaidOffLoans(Integer paidOffLoans) {
        this.paidOffLoans = paidOffLoans;
    }
}
