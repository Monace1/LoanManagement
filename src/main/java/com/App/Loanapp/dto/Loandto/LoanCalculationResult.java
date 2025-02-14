package com.App.Loanapp.dto.Loandto;

import com.App.Loanapp.dto.Repaymentdto.PaymentSchedule;
import java.math.BigDecimal;
import java.util.List;

public class LoanCalculationResult {
    private List<PaymentSchedule> paymentSchedule;
    private BigDecimal totalInterest;
    private BigDecimal totalAmount;

    public List<PaymentSchedule> getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(List<PaymentSchedule> paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
    }

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
    }
}