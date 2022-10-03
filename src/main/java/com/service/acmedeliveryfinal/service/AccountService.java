package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.transfer.AccountDto;



public interface AccountService extends BaseService<Account> {

    AccountDto login(Long Id);

    boolean existsByUsername(String email);

}
