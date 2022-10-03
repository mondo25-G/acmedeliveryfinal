/******************************************************************************
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package com.service.acmedeliveryfinal.login.payload;

import lombok.Getter;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
