package com.microservice.accounts.services;

import com.microservice.accounts.dtos.CustomerAccountsDto;
import com.microservice.accounts.dtos.CustomerDto;

public interface IAccountsService {
    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber - String Object
     * @return Accounts Details based on a given mobileNumber
     */
    CustomerAccountsDto fetchCustomerAndAccount(String mobileNumber);

    /**
     * Customer should be able to update anything except accountNumber
     * @param customerAccountsDto - CustomerAccountsDto Object
     * @return true if customer details has been updated
     */
    boolean updateAccount(CustomerAccountsDto customerAccountsDto);

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String mobileNumber);
}
