package com.ahmet.e_commerce_oct_back.controllers;

import com.ahmet.e_commerce_oct_back.entities.AppUser;
import com.ahmet.e_commerce_oct_back.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/get_user/{userId}")
    public ResponseEntity<AppUser> getUser(@PathVariable Long userId) {
        AppUser foundUser = userService.findUser(userId);

        return ResponseEntity.ok(foundUser);
    }
}
