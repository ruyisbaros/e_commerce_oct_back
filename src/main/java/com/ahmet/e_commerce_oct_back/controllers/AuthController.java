package com.ahmet.e_commerce_oct_back.controllers;

import com.ahmet.e_commerce_oct_back.DTO.LoginDto;
import com.ahmet.e_commerce_oct_back.DTO.UserDto;
import com.ahmet.e_commerce_oct_back.JWT.JwtCreate;
import com.ahmet.e_commerce_oct_back.entities.AppUser;
import com.ahmet.e_commerce_oct_back.exceptions.AccessDeniedException;
import com.ahmet.e_commerce_oct_back.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.channels.ScatteringByteChannel;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JwtCreate jwtCreate;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDto request) {
        AppUser createdUser = userService.create(request);
        String jwtToken = jwtCreate.createToken(createdUser);
        Object[] regResponse = {createdUser, jwtToken};
        return new ResponseEntity<>(regResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            AppUser loggedUser = (AppUser) authentication.getPrincipal();
            String jwtToken = jwtCreate.createToken(loggedUser);
            Object[] logResponse = {jwtToken, loggedUser};
            return new ResponseEntity<>(logResponse, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            throw new AccessDeniedException("Invalid Username or Password");
        }
    }
}
