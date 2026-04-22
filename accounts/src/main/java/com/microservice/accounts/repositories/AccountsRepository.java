package com.microservice.accounts.repositories;

import com.microservice.accounts.entities.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional
    @Modifying
    // Whenever we are trying to update or delete with the custom methods that we have
    // written, we need to make sure we are mentioning the above two annotation.
    // @Modifying will tell to Spring data JPA framework that this method is going
    // to modify the data. @Modifying is required when using custom @Query for
    // modifying data.
    // Since, we used @Transactional, it will be run in transaction.
    // When Spring data JPA runs this query inside transaction and if there is some
    // error in midway, the partial change of data will be rolled back.
    void deleteByCustomerId(Long customerId);
}
