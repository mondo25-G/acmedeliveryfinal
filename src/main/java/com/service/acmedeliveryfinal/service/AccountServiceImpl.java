package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.repository.AccountRepository;
import com.service.acmedeliveryfinal.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService{

    AccountRepository accountRepository;

    OrderService orderService;

    @Override
    public JpaRepository<Account, Long> getRepository() {
        return accountRepository;
    }
}
