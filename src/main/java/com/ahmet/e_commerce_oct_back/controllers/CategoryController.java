package com.ahmet.e_commerce_oct_back.controllers;

import com.ahmet.e_commerce_oct_back.DTO.CategoryDTO;
import com.ahmet.e_commerce_oct_back.entities.Category;
import com.ahmet.e_commerce_oct_back.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "https://shopwithahmet.netlify.app",allowedHeaders = "*",allowCredentials = "true")
@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping("/admin/create")
    public Category create(@Valid @RequestBody CategoryDTO request){
        return categoryService.createNew(request);
    }

    @GetMapping("/user/all")
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @DeleteMapping("/admin/delete/{id}")
    public void deleteCat(@PathVariable Integer id) throws IOException {
        categoryService.deleteCategory(id);
    }

    @PutMapping("/admin/update/{id}")
    public void updateCat(@Valid @RequestBody CategoryDTO request,@PathVariable Integer id){
        categoryService.updateCatgry(request,id);
    }

    @GetMapping("/user/get_by_id/{id}")
    public Category getCategory(@PathVariable Integer id){
        return categoryService.getCategory(id);
    }
    @GetMapping("/user/get_by_name/{name}")
    public Category getOne(@PathVariable String name){
        return categoryService.getOneByName(name);
    }
}
