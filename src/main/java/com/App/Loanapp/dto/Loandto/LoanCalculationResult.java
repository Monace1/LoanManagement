package com.App.Loanapp.dto.Loandto;

import com.App.Loanapp.dto.Repaymentdto.PaymentSchedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanCalculationResult {
    private List<PaymentSchedule> paymentSchedule;
    private BigDecimal totalInterest;
    private BigDecimal totalAmount;

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.totalAmount = monthlyPayment.multiply(
                new BigDecimal(paymentSchedule.size())
        );
        this.totalInterest = totalAmount.subtract(
                paymentSchedule.get(0).getPrincipalAmount()
        );
    }

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

}



