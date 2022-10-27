package com.ahmet.e_commerce_oct_back.controllers;

import com.ahmet.e_commerce_oct_back.DTO.ProductDto;
import com.ahmet.e_commerce_oct_back.entities.Product;
import com.ahmet.e_commerce_oct_back.exceptions.ApiResponse;
import com.ahmet.e_commerce_oct_back.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin
@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping("/user/all")
    public List<Product> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/user/one/{id}")
    public Product findOne(@PathVariable Long id) {
        return productService.getOne(id);
    }

    @PutMapping("/admin/update/{id}")
    public Product updateProduct(@Valid @RequestBody ProductDto request, @PathVariable Long id) {
        return productService.updateProduct(request, id);
    }

    @GetMapping("/user/all/category/{name}")
    public List<Product> getProductByCategory(@PathVariable String name) {
        return productService.getByCategory(name);
    }

    @PostMapping("/admin/create")
    public Product createProduct(@Valid @RequestBody ProductDto request) {
        return productService.createProduct(request);
    }

    @PutMapping("/user/update/rate/{id}")
    public Product updateRate(@PathVariable Long id, @RequestParam double rate) {
        System.out.println(id);
        System.out.println(rate);
        return productService.updateRate(id, rate);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(new ApiResponse("Product has been deleted", true), HttpStatus.OK);
    }
}
