package com.App.Loanapp.controller;

import com.App.Loanapp.model.Repayment;
import com.App.Loanapp.repository.RepaymentRepository;
import com.App.Loanapp.service.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repayments")
public class RepaymentController {
    @Autowired
    private RepaymentService repaymentService;
    @Autowired
    private RepaymentRepository repaymentRepository;

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<List<Repayment>> getRepaymentSchedule(@PathVariable Long loanId) {
        List<Repayment> repayments = repaymentRepository.findByLoanId(loanId);
        return ResponseEntity.ok(repayments);
    }
}