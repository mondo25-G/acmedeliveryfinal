/******************************************************************************
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package com.service.acmedeliveryfinal.service;


import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.repository.AccountRepository;
import com.service.acmedeliveryfinal.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
    @Autowired
    AccountRepository accountRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserName(email)
                .orElseThrow(() -> new UsernameNotFoundException("Users Not Found with username: " + email));

        return UserDetailsImpl.build(account);
    }


}
