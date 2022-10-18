package com.ahmet.e_commerce_oct_back.services;

import com.ahmet.e_commerce_oct_back.DTO.UserDto;
import com.ahmet.e_commerce_oct_back.entities.AppUser;
import com.ahmet.e_commerce_oct_back.entities.Image;
import com.ahmet.e_commerce_oct_back.entities.Role;
import com.ahmet.e_commerce_oct_back.exceptions.ResourceAlreadyExistException;
import com.ahmet.e_commerce_oct_back.exceptions.ResourceNotFoundExceptionLongValue;
import com.ahmet.e_commerce_oct_back.repositories.ImageRep;
import com.ahmet.e_commerce_oct_back.repositories.RoleRep;
import com.ahmet.e_commerce_oct_back.repositories.UserRep;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    private ImageRep imageRep;

    private CloudinaryService cloudinaryService;

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

    public Page<AppUser> getAllUsers(int pageSize, int pageNo, String sortDir, String sortField, String keyword) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        //Sort sort=

        return userRep.findAllByKeyWord(keyword, pageable);
    }

    public List<Role> findAllRoles() {
        return roleRep.findAll();
    }

    public AppUser updateUser(Long userId, UserDto request) {

        AppUser appUser = userRep.findById(userId).get();
        List<Role> roles = new ArrayList<>();
        for (Role r : request.getRoles()) {
            roles.add(new Role(r.getRoleName()));
        }
        if (!request.getImageId().isEmpty()) {
            Optional<Image> profileImage = imageRep.findByImageId(request.getImageId());
            appUser.setImage(profileImage.get());
        }
        appUser.setFirstName(request.getFirstName());
        appUser.setLastName(request.getLastName());
        appUser.setEmail(request.getEmail());
        appUser.setPassword(passwordEncoder.encode(request.getPassword()));
        appUser.setEnabled(request.isEnabled());
        appUser.setRoles((roles));
        return userRep.save(appUser);


    }



    public void deleteUser(long userId) throws IOException {
        Optional<AppUser> appUser = userRep.findById(userId);
        if (appUser.isPresent()) {
            Image userImage = appUser.get().getImage();
            cloudinaryService.deleteImage(userImage.getImageId());
            userRep.deleteById(userId);
        } else {
            throw new UsernameNotFoundException(String.format("user with %s ID is not exist", userId));
        }
    }

    public void updateEnableStatus(long userId) {
        AppUser targetUser = userRep.findById(userId).get();
        if (targetUser.isEnabled() == false) {
            targetUser.setEnabled(true);
            userRep.save(targetUser);
        } else {
            targetUser.setEnabled(false);
            userRep.save(targetUser);
        }
    }


    public List<AppUser> listAllUsers() {
        return userRep.findAll();
    }
}
