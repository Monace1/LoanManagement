package com.App.Loanapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String nationalid;
    private String phonenumber;
    private LocalDate created_at;
    private LocalDate updated_at;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Loan> loans;

    public Customer() {}

    public Customer(String firstname, String lastname, String phonenumber, String nationalid,
                    LocalDate created_at, LocalDate updated_at, List<Loan> loans) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.nationalid = nationalid;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.loans = loans;
    }

    public Long getId() {
        return id;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setNationalid(String nationalid) {
        this.nationalid = nationalid;
    }

    public void setUpdated_at(LocalDate updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public LocalDate getUpdated_at() {
        return updated_at;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNationalid() {
        return nationalid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}
