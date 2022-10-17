package com.ahmet.e_commerce_oct_back.repositories;

import com.ahmet.e_commerce_oct_back.entities.Category;
import com.ahmet.e_commerce_oct_back.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRep extends JpaRepository<Product, Long> {

    List<Product> findByCategory(Category category);
}
