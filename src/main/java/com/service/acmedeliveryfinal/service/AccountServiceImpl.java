package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.domain.Order;
import com.service.acmedeliveryfinal.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService{
    private final AccountRepository accountRepository;

    private final StoreService storeService;

    @Override
    public JpaRepository<Account, Long> getRepository() {
        return accountRepository;
    }

    @Override
    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Account register(String email, String password) {
        Account newAccount=Account.builder().email(email).password(password).build();
        return accountRepository.save(newAccount);
    }

    @Override
    public Account login(String email, String password) {
        return accountRepository.findByEmail(email);
    }


    @Override
    public List<Order> findOrdersByAccount(Long id) {
        Optional<List<Order>> orders = accountRepository.getLazyOrders(id);
        if (orders.isPresent()) {
            return orders.get();
        }
        throw new NoSuchElementException(String.format("There were no orders found matching Account id %d.", id));
    }

}
