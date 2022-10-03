/******************************************************************************
 * Copyright (c) 2022.                                                        *
 ******************************************************************************/

package com.service.acmedeliveryfinal.login;

import com.service.acmedeliveryfinal.domain.Account;
import com.service.acmedeliveryfinal.domain.Role;
import com.service.acmedeliveryfinal.domain.enumeration.ERole;
import com.service.acmedeliveryfinal.login.jwt.JwtUtils;
import com.service.acmedeliveryfinal.login.payload.LoginRequest;
import com.service.acmedeliveryfinal.login.payload.MessageResponse;
import com.service.acmedeliveryfinal.login.payload.RegisterRequest;
import com.service.acmedeliveryfinal.login.payload.UserInfoResponse;
import com.service.acmedeliveryfinal.repository.RoleRepository;
import com.service.acmedeliveryfinal.service.UserDetailsImpl;
import com.service.acmedeliveryfinal.service.AccountService;
import com.service.acmedeliveryfinal.transfer.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/authentication")
@AllArgsConstructor
public class AuthController {


   private final AuthenticationManager authenticationManager;

   private final AccountService accountService;

   private final RoleRepository roleRepository;

   private final PasswordEncoder encoder;

   private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<?>> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,jwtCookie.toString()).body(ApiResponse.builder().data(
                (new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername()
                        ))).build());



    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {

        if (accountService.existsByUsername(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        Role role = roleRepository.findByName(ERole.ROLE_USER).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);

       Account account= Account.builder().firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .userName(signUpRequest.getEmail())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .password(encoder.encode(signUpRequest.getPassword()))
                .address(signUpRequest.getAddress())
                .roles(roles).build();
        accountService.create(account);

       // Set<String> strRoles = signUpRequest.getRole();


       /* if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        } */




        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,jwtCookie.toString()).body(ApiResponse.builder().data(
                (new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername()
                ))).build());



    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}