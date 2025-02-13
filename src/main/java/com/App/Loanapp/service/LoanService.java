package com.App.Loanapp.service;

import com.App.Loanapp.dto.Loandto.LoanCalculationDTO;
import com.App.Loanapp.dto.Loandto.LoanCalculationResult;
//import com.App.Loanapp.dto.Loandto.LoanCalculator;
import com.App.Loanapp.dto.Loandto.LoanDTO;
import com.App.Loanapp.model.Customer;
import com.App.Loanapp.model.Loan;
import com.App.Loanapp.model.LoanStatus;
import com.App.Loanapp.repository.CustomerRepository;
import com.App.Loanapp.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    //private final LoanCalculator loanCalculator;
    @Autowired
    private RepaymentService repaymentService;

    @Autowired
    public LoanService(LoanRepository loanRepository,
                       CustomerRepository customerRepository
                      /* LoanCalculator loanCalculator*/) {
        this.loanRepository = loanRepository;
        this.customerRepository = customerRepository;
      //  this.loanCalculator = loanCalculator;
    }

    public List<Loan> getAllCustomers() {
        return loanRepository.findAll();
    }

    public Loan createLoan(LoanDTO loanDTO) {
        if (loanDTO.getCustomerId() == null) {
            logger.error("Customer ID is missing in the request.");
            throw new RuntimeException("Customer ID must be provided.");
        }
        Customer customer = customerRepository.findById(loanDTO.getCustomerId())
                .orElseThrow(() -> {
                    logger.error("Customer not found with ID: {}", loanDTO.getCustomerId());
                    return new RuntimeException("Customer not found with ID: " + loanDTO.getCustomerId());
                });

        Loan loan = new Loan();
        loan.setPrincipalAmount(loanDTO.getPrincipalAmount());
        loan.setInterestRate(loanDTO.getInterestRate());
        loan.setRepaymentPeriod(loanDTO.getRepaymentPeriod());
        loan.setDuedate(loanDTO.getDuedate());
        loan.setFrequency(loanDTO.getFrequency());
        loan.setStatus(LoanStatus.PENDING);
        loan.setCustomer(customer);
        Loan savedLoan = loanRepository.save(loan);
        logger.info("Loan created successfully with ID: {}", savedLoan.getId());
        return savedLoan;
    }


    public Loan getLoan(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Loan not found with ID: {}", id);
                    return new RuntimeException("Loan not found with ID: " + id);
                });
    }

    public Loan updateLoan(Long id, LoanDTO loanDTO) {
        Loan loan = getLoan(id);

        loan.setPrincipalAmount(loanDTO.getPrincipalAmount());
        loan.setInterestRate(loanDTO.getInterestRate());
        loan.setRepaymentPeriod(loanDTO.getRepaymentPeriod());
        loan.setDuedate(loanDTO.getDuedate());
        loan.setFrequency(loanDTO.getFrequency());

        if (loanDTO.getCustomerId() != null && !loan.getCustomer().getId().equals(loanDTO.getCustomerId())) {
            Customer customer = customerRepository.findById(loanDTO.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found with ID: " + loanDTO.getCustomerId()));
            loan.setCustomer(customer);
        }

        Loan updatedLoan = loanRepository.save(loan);
        logger.info("Loan updated successfully with ID: {}", updatedLoan.getId());
        return updatedLoan;
    }

  /*  public LoanCalculationResult calculateLoan(LoanCalculationDTO calculationDTO) {
        return loanCalculator.calculateLoan(calculationDTO);
    }*/

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(Long loanId) {
        return getLoanById(loanId);
    }
}
