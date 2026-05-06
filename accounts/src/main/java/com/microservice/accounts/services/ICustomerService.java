package com.microservice.accounts.services;

import com.microservice.accounts.dtos.CustomerDetailsDto;

public interface ICustomerService {
    /**
     * @param correlationIdToken
     * @param mobileNumber       - String Object
     * @return Customer Complete Details based on a given mobileNumber
     */
    CustomerDetailsDto fetchCompleteCustomerDetails(String correlationIdToken, String mobileNumber);
}
