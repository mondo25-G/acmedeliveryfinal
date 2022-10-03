/******************************************************************************
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package com.service.acmedeliveryfinal.login.payload;

import lombok.Getter;
import lombok.Value;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Value
public class RegisterRequest {

    @NotBlank
    @Size(max = 50)
    @Email
     String email;

//    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
     String password;


    @NotBlank
     String firstName;

    @NotBlank
     String lastName;

    @NotBlank
     String phoneNumber;

    @NotBlank
     String address;


}
