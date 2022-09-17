package com.service.acmedeliveryfinal.domain;

import javax.validation.constraints.NotNull;
import lombok.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNTS", indexes = {@Index(name = "ACCOUNT_IDX_01", columnList = "email")})
public class Account extends BaseEntity{

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column
    @JsonIgnore
    private String password;

    @NotNull
    @Column
    private String firstName;

    @NotNull
    @Column
    private String lastName;

    @NotNull
    @Column
    private String phoneNumber;

    @NotNull
    @Column
    private String address;

}
