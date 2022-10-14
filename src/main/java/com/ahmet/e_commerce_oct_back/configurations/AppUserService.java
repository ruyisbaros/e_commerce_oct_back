package com.ahmet.e_commerce_oct_back.configurations;

import com.ahmet.e_commerce_oct_back.entities.AppUser;
import com.ahmet.e_commerce_oct_back.exceptions.ResourceNotFoundExceptionStringValue;
import com.ahmet.e_commerce_oct_back.repositories.UserRep;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private UserRep userRep;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRep.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundExceptionStringValue("User", "emailID", email));
        return user;
    }
}
