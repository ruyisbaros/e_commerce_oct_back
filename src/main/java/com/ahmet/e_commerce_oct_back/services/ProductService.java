package com.ahmet.e_commerce_oct_back.services;

import com.ahmet.e_commerce_oct_back.repositories.ImageRep;
import com.ahmet.e_commerce_oct_back.repositories.ProductRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRep productRep;

    private ImageRep imageRep;
}
