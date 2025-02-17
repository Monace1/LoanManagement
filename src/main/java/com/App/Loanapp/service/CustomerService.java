package com.App.Loanapp.service;

import com.App.Loanapp.dto.CustomerDetails;
import com.App.Loanapp.dto.CustomerRegistration;
import com.App.Loanapp.model.Customer;
import com.App.Loanapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(CustomerRegistration customerRegistration) {
        Customer customer = new Customer();
        customer.setFirstname(customerRegistration.getFirstname());
        customer.setLastname(customerRegistration.getLastname());
        customer.setNationalid(customerRegistration.getNationalid());
        customer.setPhonenumber(customerRegistration.getPhonenumber());

        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer updateCustomer(Long id, CustomerDetails customerDetails) {
        Customer customer = getCustomerById(id);
        customer.setFirstname(customerDetails.getFirstname());
        customer.setLastname(customerDetails.getLastname());
        customer.setPhonenumber(customerDetails.getPhonenumber());
        customer.setNationalid(customerDetails.getNationalid());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public void deleteCustomers(List<Long> ids) {
        customerRepository.deleteAllById(ids);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
