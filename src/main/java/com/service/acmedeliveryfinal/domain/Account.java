package com.service.acmedeliveryfinal.domain;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNTS", uniqueConstraints  = { @UniqueConstraint(columnNames = "username")})
@SequenceGenerator(name = "idGenerator", sequenceName = "ACCOUNTS_SEQ", initialValue = 1, allocationSize = 1)
public class Account extends BaseEntity{

    @NotNull
    @Column(unique = true)
    private String userName;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public Account(String email, String encode) {

        this.userName = email;
        this.password = encode;

    }

}
