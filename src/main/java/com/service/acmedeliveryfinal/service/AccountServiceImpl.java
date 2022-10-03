package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.repository.AccountRepository;
import com.service.acmedeliveryfinal.transfer.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService{
    private final AccountRepository accountRepository;

    @Override
    public JpaRepository<Account, Long> getRepository() {
        return accountRepository;
    }

    @Override
    public AccountDto login(Long id) {

        if (accountRepository.findById(id).isEmpty()){
            throw new RuntimeException("The email or password is wrong.");
        }
        return createAccountDto(accountRepository.findById(id).get());
    }

    @Override
    public boolean existsByUsername(String email) {
        return accountRepository.existsByUserName(email);
    }

    private AccountDto createAccountDto(Account account){
        return new AccountDto() {
            @Override
            public Long getId() {
                return account.getId();
            }
            @Override
            public String getUser() {
                return account.getFirstName()+" "+account.getLastName();
            }

            @Override
            public String getAddress() {
                return account.getAddress();
            }

            @Override
            public String getPhoneNumber() {
                return account.getPhoneNumber();
            }
        };
    }


}