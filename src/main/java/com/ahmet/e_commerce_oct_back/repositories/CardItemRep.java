package com.ahmet.e_commerce_oct_back.repositories;

import com.ahmet.e_commerce_oct_back.entities.AppUser;
import com.ahmet.e_commerce_oct_back.entities.CardItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardItemRep extends JpaRepository<CardItem, Long> {

    List<CardItem> findByAppUser(AppUser appUser);
}
