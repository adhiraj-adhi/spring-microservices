package com.microservice.accounts.services.impl;

import com.microservice.accounts.dtos.*;
import com.microservice.accounts.entities.Accounts;
import com.microservice.accounts.entities.Customer;
import com.microservice.accounts.exception.ResourceNotFoundException;
import com.microservice.accounts.services.feigns.CardsFeignClient;
import com.microservice.accounts.services.feigns.LoansFeignClient;
import com.microservice.accounts.mapper.AccountsMapper;
import com.microservice.accounts.mapper.CustomerMapper;
import com.microservice.accounts.repositories.AccountsRepository;
import com.microservice.accounts.repositories.CustomersRepository;
import com.microservice.accounts.services.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private AccountsRepository accountsRepository;
    private CustomersRepository customersRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;
    /**
     * @param correlationIdToken
     * @param mobileNumber       - String Object
     * @return Customer Complete Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCompleteCustomerDetails(String correlationIdToken, String mobileNumber) {
        Customer customer = customersRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account", "customerId", String.valueOf(customer.getCustomerId())
                        ));

        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
        customerDetailsDto.setCustomerDto(CustomerMapper.mapToCustomerDto(customer, new CustomerDto()));
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationIdToken, mobileNumber);
        if (cardsDtoResponseEntity!=null) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }


        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationIdToken, mobileNumber);
        if (loansDtoResponseEntity!=null) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        return customerDetailsDto;
    }
}
