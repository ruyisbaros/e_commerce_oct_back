package com.ahmet.e_commerce_oct_back.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CategoryDTO {
    @NotEmpty(message = "Category name field should not be empty")
    private String categoryName;
    @NotEmpty(message = "Description field should not be empty")
    private String description;
    @NotEmpty(message = "Image field should not be empty")
    private String imageId;
}
