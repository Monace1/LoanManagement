package com.App.Loanapp.dto;

import org.springframework.stereotype.Component;

@Component
public class CustomerDetails {
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String nationalid;

    public CustomerDetails() {}

    public CustomerDetails(String firstname, String lastName, String email,String phonenumber,String nationalid) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.nationalid= nationalid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNationalid() {
        return nationalid;
    }

    public void setNationalid(String nationalid) {
        this.nationalid = nationalid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
