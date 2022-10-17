package com.ahmet.e_commerce_oct_back.controllers;

import com.ahmet.e_commerce_oct_back.DTO.CategoryDTO;
import com.ahmet.e_commerce_oct_back.entities.Category;
import com.ahmet.e_commerce_oct_back.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("/create")
    public Category create(@Valid @RequestBody CategoryDTO request){
        return categoryService.createNew(request);
    }

    @GetMapping("/all")
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCat(@PathVariable Integer id) throws IOException {
        categoryService.deleteCategory(id);
    }
}
