package com.microservice.accounts.services.impl;

import com.microservice.accounts.constants.AccountsConstants;
import com.microservice.accounts.dtos.AccountsDto;
import com.microservice.accounts.dtos.CustomerAccountsDto;
import com.microservice.accounts.dtos.CustomerDto;
import com.microservice.accounts.entities.Accounts;
import com.microservice.accounts.entities.Customer;
import com.microservice.accounts.exception.CustomerAlreadyExistException;
import com.microservice.accounts.exception.ResourceNotFoundException;
import com.microservice.accounts.mapper.AccountsMapper;
import com.microservice.accounts.mapper.CustomerAccountsMapper;
import com.microservice.accounts.mapper.CustomerMapper;
import com.microservice.accounts.repositories.AccountsRepository;
import com.microservice.accounts.repositories.CustomersRepository;
import com.microservice.accounts.services.IAccountsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor // Generates a constructor with all fields.
// Since there is only one constructor, Spring automatically uses it for dependency injection,
// so @Autowired is not required.
public class AccountsServiceImpl implements IAccountsService {
    private AccountsRepository accountsRepository;
    private CustomersRepository customersRepository;


    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customersRepository.findByMobileNumber(customer.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already registered with given mobile number");
        }
        Customer savedCustomer = customersRepository.save(customer);

        accountsRepository.save(createNewAccount(savedCustomer));
    }


    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000); // this can
        // generate duplicate but for now let us keep it as it is.

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    /**
     * @param mobileNumber - String Object
     * @return Accounts Details based on a given mobileNumber
     */
    @Override
    public CustomerAccountsDto fetchCustomerAndAccount(String mobileNumber) {
        Customer customer = customersRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account", "customerId", String.valueOf(customer.getCustomerId())
                        ));

        return CustomerAccountsMapper.mapToCustomerAccountsDto(customer, accounts,
                new CustomerAccountsDto());
    }

    /**
     * Customer should be able to update anything except accountNumber
     *
     * @param customerAccountsDto - CustomerAccountsDto Object
     * @return true if customer details has been updated
     */
    @Override
    public boolean updateAccount(CustomerAccountsDto customerAccountsDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = CustomerAccountsMapper.mapToAccountsDto(customerAccountsDto, new AccountsDto());
        Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account", "accountNumber", customerAccountsDto.getAccountNumber().toString())
                );
        AccountsMapper.mapToAccounts(accountsDto, accounts); // Here, accountNumber will
        // not be updated with new accountNumber as we are searching with the accountNumber
        // first in the database (we can see above). If found than only, we are proceeding
        // with the update.
        accounts = accountsRepository.save(accounts);

        Long customerId = accounts.getCustomerId();
        Customer customer = customersRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));
        CustomerMapper.mapToCustomer(CustomerAccountsMapper.mapToCustomerDto(
                customerAccountsDto, new CustomerDto()
        ), customer);
        customersRepository.save(customer);
        isUpdated = true;
        return isUpdated;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    // Here, we are performing two operation - one deleting the account and second
    // deleting the customer. It should not be like that only account is deleted
    // and customer is not deleted. So, we will annotate this method with @Transactional
    // Because of this @Transactional , both the below delete methods are executed
    // inside the same transaction created at service layer
    @Transactional
    // Since, we have used @Transactional annotation here so we can remove this
    // annotation from the top of our custom defined deleteByCustomerId().
    // Just using @Modifying there is enough. For now I am not removing @Transactional
    // also from there.
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customersRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        // (Q) We have annotated our custom defined deleteByCustomerId() with
        // @Transactional and @Modifying. Who will take care of that part for
        // deleteById() method here? -> It is frameworks method and is taken care by it.
        customersRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
