package com.ahmet.e_commerce_oct_back.controllers;

import com.ahmet.e_commerce_oct_back.DTO.UserDto;
import com.ahmet.e_commerce_oct_back.entities.AppUser;
import com.ahmet.e_commerce_oct_back.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> create(@Valid @RequestBody UserDto request){
        AppUser createdUser=userService.create(request);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
