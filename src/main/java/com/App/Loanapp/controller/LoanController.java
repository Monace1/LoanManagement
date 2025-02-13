package com.App.Loanapp.controller;

import com.App.Loanapp.dto.Loandto.LoanCalculationDTO;
import com.App.Loanapp.dto.Loandto.LoanCalculationResult;
import com.App.Loanapp.dto.Loandto.LoanDTO;
import com.App.Loanapp.model.Customer;
import com.App.Loanapp.model.Loan;
import com.App.Loanapp.repository.LoanRepository;
import com.App.Loanapp.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
private LoanRepository loanRepository;
    @Autowired
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody LoanDTO loanDTO) {
        Loan loan = loanService.createLoan(loanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Loan>> getLoan(@PathVariable Long id) {
        Optional<Loan> loan = loanRepository.findById(id);
        return ResponseEntity.ok(loan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable Long id,
                                           @RequestBody LoanDTO loanDTO) {
        Loan loan = loanService.updateLoan(id, loanDTO);
        return ResponseEntity.ok(loan);
    }

   /* @PostMapping("/calculate")
    public ResponseEntity<LoanCalculationResult> calculateLoan(
            @RequestBody LoanCalculationDTO calculationDTO) {
        LoanCalculationResult result = loanService.calculateLoan(calculationDTO);
        return ResponseEntity.ok(result);
    }*/
}