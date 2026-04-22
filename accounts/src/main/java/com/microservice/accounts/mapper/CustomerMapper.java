package com.microservice.accounts.mapper;

import com.microservice.accounts.dtos.CustomerDto;
import com.microservice.accounts.entities.Customer;

public class CustomerMapper {
    //  We could use ModelMapper to simplify mapping,
    // but manual mapping or MapStruct is often preferred for better performance and control.
    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
