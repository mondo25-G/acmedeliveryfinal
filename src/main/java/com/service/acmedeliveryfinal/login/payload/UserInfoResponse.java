/******************************************************************************
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package com.service.acmedeliveryfinal.login.payload;

import lombok.Value;

@Value
public class UserInfoResponse {
    private Long id;
    private String email;

}
