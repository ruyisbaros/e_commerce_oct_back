package com.ahmet.e_commerce_oct_back.services;

import com.ahmet.e_commerce_oct_back.DTO.UserDto;
import com.ahmet.e_commerce_oct_back.entities.AppUser;
import com.ahmet.e_commerce_oct_back.entities.Image;
import com.ahmet.e_commerce_oct_back.entities.Role;
import com.ahmet.e_commerce_oct_back.exceptions.ResourceAlreadyExistException;
import com.ahmet.e_commerce_oct_back.exceptions.ResourceNotFoundExceptionLongValue;
import com.ahmet.e_commerce_oct_back.repositories.RoleRep;
import com.ahmet.e_commerce_oct_back.repositories.UserRep;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRep userRep;

    private RoleRep roleRep;

    private ImageService imageService;

    private PasswordEncoder passwordEncoder;

    //    pw4gq42vstslcyqzi81o  default image.png
    public AppUser create(UserDto request) {
        boolean isExist = userRep.findByEmail(request.getEmail()).isPresent();

        if (isExist) {
            throw new ResourceAlreadyExistException("User", "EmailID", request.getEmail());
        } else {
            AppUser newUser = new AppUser();

            if (request.getRoles() != null) {
                List<Role> roles = new ArrayList<>();
                for (Role role : request.getRoles()) {
                    Role r = roleRep.findByRoleName(role.getRoleName());
                    roles.add(r);
                }
                newUser.setRoles(roles);
            }
            if (request.getImageId() != null) {
                Image image = imageService.findImage(request.getImageId());
                newUser.setImage(image);
            }

            newUser.setFirstName(request.getFirstName());
            newUser.setLastName(request.getLastName());
            newUser.setEmail(request.getEmail());

            newUser.setPassword(passwordEncoder.encode(request.getPassword()));

            return userRep.save(newUser);
        }


    }

    public AppUser findUser(Long userId) {
        return userRep.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("User", "Id", userId));
    }
}
