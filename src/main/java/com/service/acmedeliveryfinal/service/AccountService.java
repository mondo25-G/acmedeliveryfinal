package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.domain.Order;
import java.util.List;


public interface AccountService extends BaseService<Account> {

    Account findByEmail(String email);
    Account register(String email,String password);
    Account login(String email, String password);
    List<Order> findOrdersByAccount(Long id);

}
