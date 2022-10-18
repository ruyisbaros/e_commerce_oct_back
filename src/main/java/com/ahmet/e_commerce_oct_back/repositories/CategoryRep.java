package com.ahmet.e_commerce_oct_back.repositories;

import com.ahmet.e_commerce_oct_back.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRep extends JpaRepository<Category, Integer> {

    @Query("select c from Category c where c.categoryName = ?1")
    Category findByCategoryName(String categoryName);
}
