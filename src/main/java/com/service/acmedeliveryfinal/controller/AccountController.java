package com.service.acmedeliveryfinal.controller;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.service.AccountService;
import com.service.acmedeliveryfinal.service.BaseService;
import com.service.acmedeliveryfinal.transfer.AccountDto;
import com.service.acmedeliveryfinal.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountController extends BaseController<Account>{
    private final AccountService accountService;

    @Override
    protected BaseService<Account> getBaseService() {
        return accountService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AccountDto>> register(@RequestParam String email,String phoneNumber, String password, String firstName, String lastName, String address){
        AccountDto accountDto= accountService.register(email,phoneNumber,password,firstName,lastName,address);
        return ResponseEntity.ok(ApiResponse.<AccountDto>builder().data(accountDto).build());
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse<AccountDto>> login(@RequestParam String email, String password){
        AccountDto accountDto= accountService.login(email,password);
        return ResponseEntity.ok(ApiResponse.<AccountDto>builder().data(accountDto).build());
    }

}
