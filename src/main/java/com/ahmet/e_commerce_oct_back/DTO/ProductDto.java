package com.ahmet.e_commerce_oct_back.DTO;

import com.ahmet.e_commerce_oct_back.entities.Image;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ProductDto {
    @NotEmpty(message = "Product name field should not be empty")
    private String productName;
    @NotEmpty(message = "Product description field should not be empty")
    private String description;

    private double price;

    private int quantity;

    private double rate;

    private String categoryName;

    private List<String> productImages;
}
