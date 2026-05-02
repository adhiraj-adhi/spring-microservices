package com.microservice.accounts.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Customer's Complete information"
)
public class CustomerDetailsDto {
    private CustomerDto customerDto;
    private AccountsDto accountsDto;
    private CardsDto cardsDto;
    private LoansDto loansDto;
}

/*
* NOTE: Here, CustomerDetailsDto is sent by us to the client and we are not
* expecting it from client. If we were expecting it from client, we would want
* that all the validation on CustomerDto, AccountsDto, CardsDto, and LoansDto
* should work. Since in this CustomerDetailsDto, we are directly using these
* DTOs, we would need to use @Valid annotation on top of each field so that
* validation is cascade into nested DTOs. Something like:
*
* public class CustomerDetailsDto {
*    @Valid
*    private CustomerDto customerDto;
*    // Other DTOs
* }
*
* */