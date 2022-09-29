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
@SequenceGenerator(name = "idGenerator", sequenceName = "ACCOUNTS_SEQ", initialValue = 1, allocationSize = 1)
public class Account extends BaseEntity{

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    @Column
    private String firstName;

    @NotNull
    @Column
    private String password;

    @NotNull
    @Column
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String phoneNumber;

    @NotNull
    @Column
    private String address;

}
