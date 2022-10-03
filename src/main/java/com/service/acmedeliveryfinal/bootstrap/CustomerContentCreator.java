package com.service.acmedeliveryfinal.bootstrap;

import com.service.acmedeliveryfinal.base.BaseComponent;
import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.domain.Role;
import com.service.acmedeliveryfinal.domain.enumeration.ERole;
import com.service.acmedeliveryfinal.repository.RoleRepository;
import com.service.acmedeliveryfinal.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Order(2)
public class CustomerContentCreator extends BaseComponent implements CommandLineRunner {

    private final AccountService accountService;

    private final RoleRepository roleRepository;

   private final PasswordEncoder encoder = new BCryptPasswordEncoder();



    @Override
    public void run(String... args)  {


        List<Role> newRole= List.of(
                new Role(ERole.ROLE_USER),
                new Role(ERole.ROLE_ADMIN),
                new Role(ERole.ROLE_MODERATOR)

        );
        roleRepository.saveAll(newRole);

        Role role = roleRepository.findByName(ERole.ROLE_USER).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);



        accountService.create(Account.builder()
                .firstName("Giorgos")
                .lastName("Prassas")
                .userName("george@1g.gr")
                .password(encoder.encode("pras"))
                .phoneNumber("6969696901")
                .address("666, Paradise Street, Floor 1")
                .roles(roles)
                .build());

        accountService.create(Account.builder()
                .firstName("Giorgos")
                .lastName("Chatzidakis")
                .userName("george@2g.gr")
                .password(encoder.encode("hat"))
                .phoneNumber("6969696902")
                .roles(roles)
                .address("666, Paradise Street, Floor 2 ")
                .build());

        accountService.create(Account.builder()
                .firstName("Giorgos")
                .lastName("Theofanous")
                .userName("george@3g.gr")
                .password(encoder.encode("theo"))
                .phoneNumber("6969696903")
                .roles(roles)
                .address("666, Paradise Street, Floor 3 ")
                .build());

    }
}