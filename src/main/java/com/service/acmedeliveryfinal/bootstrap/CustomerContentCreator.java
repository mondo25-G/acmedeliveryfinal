package com.service.acmedeliveryfinal.bootstrap;

import com.service.acmedeliveryfinal.base.BaseComponent;
import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Order(2)
public class CustomerContentCreator extends BaseComponent implements CommandLineRunner {

    private final AccountService accountService;

    @Override
    public void run(String... args)  {

        accountService.create(Account.builder()
                .firstName("Giorgos")
                .lastName("Prassas")
                .email("george@1g.gr")
                .password("pras")
                .phoneNumber("6969696901")
                .address("666, Paradise Street, Floor 1")
                .build());

        accountService.create(Account.builder()
                .firstName("Giorgos")
                .lastName("Chatzidakis")
                .email("george@2g.gr")
                .password("hat")
                .phoneNumber("6969696902")
                .address("666, Paradise Street, Floor 2 ")
                .build());

        accountService.create(Account.builder()
                .firstName("Giorgos")
                .lastName("Theofanous")
                .email("george@3g.gr")
                .password("theo")
                .phoneNumber("6969696903")
                .address("666, Paradise Street, Floor 3 ")
                .build());

    }
}