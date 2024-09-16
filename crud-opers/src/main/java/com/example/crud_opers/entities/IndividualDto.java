package com.example.crud_opers.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class IndividualDto {
    @NotNull(message = "National ID is required!")
    public int getNationalID() {
        return nationalID;
    }

    public void setNationalID(@NotEmpty(message = "National ID is required!") int nationalID) {
        this.nationalID = nationalID;
    }

    public @NotEmpty(message = "Name is required!") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "Name is required!") String name) {
        this.name = name;
    }

    @NotNull(message = "Balance is required")
    public int getBalance() {
        return balance;
    }

    public void setBalance(@NotEmpty(message = "Balance is required") int balance) {
        this.balance = balance;
    }

    public boolean isLoan() {
        return loan;
    }

    public void setLoan(boolean loan) {
        this.loan = loan;
    }

    @NotNull(message = "National ID is required!")
    private int nationalID;

    @NotNull(message = "Balance is required")
    private int balance;

    @NotEmpty(message = "Name is required!")
    private String name;



    private boolean loan;

}
