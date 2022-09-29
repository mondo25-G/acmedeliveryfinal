package com.service.acmedeliveryfinal.repository;

import com.service.acmedeliveryfinal.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
    Optional<Account> findByEmailOrPhoneNumber(String email,String phoneNumber);

    Optional<Account> findByEmailAndPassword(String email,String phoneNumber);

}
