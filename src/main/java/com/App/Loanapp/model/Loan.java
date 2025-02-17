package com.App.Loanapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Entity
@Table(name = "loan")
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private BigDecimal principalAmount;
    private BigDecimal interestRate;
    private Integer repaymentPeriod;
    private LocalDate duedate;
    private LocalDate startdate = LocalDate.now();
   // @Enumerated(EnumType.STRING)
  //  private Frequency frequency;
    @Enumerated(EnumType.STRING)
    private LoanStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private Customer customer;
    @OneToMany(mappedBy = "loan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Repayment> repayments;
    public void setPrincipalAmount(BigDecimal principalAmount) {
        this.principalAmount = principalAmount;
    }
    public void setRepaymentPeriod(Integer repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    /*public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }*/
    public void setStatus(LoanStatus status) {
        this.status = status;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }
    public Long getId() {
        return id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Integer getRepaymentPeriod() {
        return repaymentPeriod;
    }
    public LoanStatus getStatus() {
        return status;
    }
    /*public Frequency getFrequency() {
        return frequency;
    }*/
    public BigDecimal getPrincipalAmount() {
        return principalAmount;
    }

    public List<Repayment> getRepayments() {
        return repayments;
    }

    public LocalDate getDuedate() {
        return duedate;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getStartDate() {
        return LocalDate.now();
    }

    public BigDecimal getAmount() {
        return principalAmount;
    }
}
