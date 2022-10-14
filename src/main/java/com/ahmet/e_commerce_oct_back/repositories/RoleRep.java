package com.ahmet.e_commerce_oct_back.repositories;

import com.ahmet.e_commerce_oct_back.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRep extends JpaRepository<Role,String> {

    @Query("select r from Role r where r.roleName = ?1")
    Role findByRoleName(String roleName);
}
