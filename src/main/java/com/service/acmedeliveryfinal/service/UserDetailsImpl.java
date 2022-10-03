/******************************************************************************
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package com.service.acmedeliveryfinal.service;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.acmedeliveryfinal.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.*;
import java.io.Serial;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl  implements UserDetails  {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;



    public static UserDetailsImpl build(Account account) {
        List<GrantedAuthority> authorities = account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                account.getId(),
                account.getUserName(),
                account.getPassword(),
                authorities);
    }




    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}