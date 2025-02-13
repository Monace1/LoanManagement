package com.App.Loanapp.dto.Loandto;

import com.App.Loanapp.model.Frequency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanCalculationDTO {
    private BigDecimal principalAmount;
    private BigDecimal interestRate;
    private Integer repaymentPeriod;
    private Frequency frequency;

}


