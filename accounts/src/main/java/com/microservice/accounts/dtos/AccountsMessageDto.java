package com.microservice.accounts.dtos;

/**
 * Data Transfer Object representing the account details sent by the Accounts microservice
 * to trigger notification events.
 *
 * @param accountNumber The unique tracking identifier for the bank account
 * @param customerName  The full name of the primary account holder
 * @param email         The destination email address for email notifications
 * @param mobileNumber  The destination phone number for SMS alerts
 */
public record AccountsMessageDto(
        Long accountNumber,
        String customerName,
        String email,
        String mobileNumber
) {
}
