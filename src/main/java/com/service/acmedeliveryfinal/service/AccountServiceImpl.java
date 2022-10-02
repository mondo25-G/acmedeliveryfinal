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
    public AccountDto register(String email,String phoneNumber, String password, String firstName,String lastName,String address) {
        if (accountRepository.findByUserNameOrPhoneNumber(email,phoneNumber).isPresent()){
            throw new RuntimeException("There is already an existing account with same email or password.");
        }
        Account newAccount=Account.builder().userName(email).phoneNumber(phoneNumber).password(password).firstName(firstName).lastName(lastName).address(address).build();
        return createAccountDto( accountRepository.save(newAccount));
    }

    @Override
    public AccountDto login(String email, String password) {

        if (accountRepository.findByUserNameAndPassword(email,password).isEmpty()){
            throw new RuntimeException("The email or password is wrong.");
        }
        return createAccountDto(accountRepository.findByUserName(email));
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
        };
    }


}