package com.ahmet.e_commerce_oct_back.repositories;

import com.ahmet.e_commerce_oct_back.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRep extends JpaRepository<AppUser,Long> {

   @Query("select u from AppUser u where u.email = ?1")
    Optional<AppUser> findByEmail(String email);
}
