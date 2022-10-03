package com.service.acmedeliveryfinal.transfer;

import lombok.Getter;
import lombok.Value;

@Getter
public class LoginDto {
    private String email;
    private String password;
}


