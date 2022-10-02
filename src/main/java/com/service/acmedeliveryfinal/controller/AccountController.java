package com.service.acmedeliveryfinal.controller;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.service.AccountService;
import com.service.acmedeliveryfinal.service.BaseService;
import com.service.acmedeliveryfinal.transfer.AccountDto;
import com.service.acmedeliveryfinal.transfer.ApiResponse;
import com.service.acmedeliveryfinal.transfer.LoginDto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
@CrossOrigin
public class AccountController extends BaseController<Account>{
    private final AccountService accountService;
    @Override
    protected BaseService<Account> getBaseService() {
        return accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AccountDto>> register(@RequestBody Account account ){
        AccountDto accountDto= accountService.register(account.getUserName(), account.getPhoneNumber(), account.getPassword(), account.getFirstName(), account.getLastName(), account.getAddress());
        logger.info("{}",accountDto);
        return ResponseEntity.ok(ApiResponse.<AccountDto>builder().data(accountDto).build());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AccountDto>> login(@RequestBody LoginDto loginDto){
        AccountDto accountDto= accountService.login(loginDto.getEmail(), loginDto.getPassword());
        logger.info("{}",accountDto);
        return ResponseEntity.ok(ApiResponse.<AccountDto>builder().data(accountDto).build());
    }

}
