package com.microservices.messages.functions;

import com.microservices.messages.dto.AccountsMessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {
    private static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

    /**
     * Function 1: Processes email dispatching.
     * It accepts the account details (via AccountsMessageDto) as input,
     * sends email, and outputs the same AccountsMessageDto, so they can be passed on.
     */
    @Bean
    public Function<AccountsMessageDto, AccountsMessageDto> sendEmail() {
        return accountsMessageDto -> { // Here, accountsMessageDto -> Input of type AccountsMessageDto
            logger.debug("Here we will have logic to send email to: "+accountsMessageDto.toString());
            return accountsMessageDto; // Returning same accountsMessageDto so that next function in
            // the chain can use it
        };
    }

    /**
     * Function 2: Processes SMS dispatching.
     * It accepts the account details (via AccountsMessageDto) and outputs only the account
     * number as a confirmation tracking ID.
     */
    @Bean
    public Function<AccountsMessageDto, Long> sendSms() {
        return accountsMessageDto -> {
            logger.debug("Here we will have logic to send sms to: "+accountsMessageDto.toString());
            // Pulling the account number using the auto-generated record access method
            return accountsMessageDto.accountNumber();
        };
    }

//    @Bean
//    public Function<AccountsMessageDto, Long> sendEmailsendSms() {
//        return sendEmail().andThen(sendSms());
//    }
}
