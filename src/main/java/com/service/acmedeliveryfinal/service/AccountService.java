package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.domain.Address;

import java.util.List;
import java.util.Set;

public interface AccountService extends BaseService<Account>{

    //Aggregate methods

    //List<Address> findAllAddresses();
    //Address findAddress(Address address);
     void addAddress(Account account, Address address);
    //Address removeAddress(Address address);
    //Address updateAddress(Address address);

    //
}
