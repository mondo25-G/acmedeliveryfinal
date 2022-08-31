package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.domain.Address;
import com.service.acmedeliveryfinal.repository.AccountRepository;
import com.service.acmedeliveryfinal.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl extends BaseServiceImpl<Account> implements AccountService{

    AccountRepository accountRepository;

    OrderService orderService; //we'll see if it stays here ir if it goes.

    @Override
    public JpaRepository<Account, Long> getRepository() {
        return accountRepository;
    }

    //Deal here with aggregates. The same logic applies for PaymentCard aggregate if
    //we choose to implement it.
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void addAddress(Account account, Address deliveryAddress) {
        if (account.getAddresses()==null){
            account.setAddresses(new LinkedHashSet<>());
        }
        account.getAddresses().add(deliveryAddress);
        deliveryAddress.setAccount(account);
        update(account);
    }



    //end of aggregates
}
