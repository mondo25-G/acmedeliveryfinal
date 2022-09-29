package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.transfer.AccountDto;



public interface AccountService extends BaseService<Account> {

    AccountDto register(String email,String phoneNumber,String password, String firstName,String lastName,String address);
    AccountDto login(String email, String password);

}
