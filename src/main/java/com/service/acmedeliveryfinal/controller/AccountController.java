package com.service.acmedeliveryfinal.controller;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.login.payload.LoginRequest;
import com.service.acmedeliveryfinal.service.AccountService;
import com.service.acmedeliveryfinal.service.BaseService;
import com.service.acmedeliveryfinal.transfer.AccountDto;
import com.service.acmedeliveryfinal.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
public class AccountController extends BaseController<Account>{
    private final AccountService accountService;
    @Override
    protected BaseService<Account> getBaseService() {
        return accountService;
    }

    @GetMapping("/details")
    public ResponseEntity<ApiResponse<AccountDto>> login(@RequestParam Long id){
        AccountDto accountDto= accountService.login(id);
        return ResponseEntity.ok(ApiResponse.<AccountDto>builder().data(accountDto).build());
    }

}
