package com.microservice.accounts.constants;


// It is good practice to define constants this way
public class AccountsConstants {
    private AccountsConstants() {
        // restricting the instantiation
    }

    public static final String SAVINGS = "Savings"; // final so that no one can change it
    // static so that we can access it without creating object
    public static final String ADDRESS = "123 Gachibowli, Hyderbad";
    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Account created successfully";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact Dev Team";
}
