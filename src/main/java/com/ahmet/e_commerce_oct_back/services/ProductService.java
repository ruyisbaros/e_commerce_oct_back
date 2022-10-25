package com.ahmet.e_commerce_oct_back.services;

import com.ahmet.e_commerce_oct_back.DTO.ProductDto;
import com.ahmet.e_commerce_oct_back.entities.Category;
import com.ahmet.e_commerce_oct_back.entities.Image;
import com.ahmet.e_commerce_oct_back.entities.Product;
import com.ahmet.e_commerce_oct_back.exceptions.ResourceNotFoundExceptionLongValue;
import com.ahmet.e_commerce_oct_back.repositories.ImageRep;
import com.ahmet.e_commerce_oct_back.repositories.ProductRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRep productRep;

    private ImageRep imageRep;

    private CategoryService categoryService;


    public Product getOne(Long id) {
        return productRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionLongValue("Product", "ID", id));
    }

    public List<Product> getAll() {
        return productRep.findAll();
    }

    public List<Product> getByCategory(String name) {
        Category category = categoryService.getOneByName(name);
        return productRep.findByCategory(category);
    }

    public Product createProduct(ProductDto request) {
        Product toCreate = new Product();
        Category category = categoryService.getOneByName(request.getCategoryName());
        toCreate.setCategory(category);
        List<Image> productImages = new ArrayList<>();
        for (String id : request.getProductImages()) {
            Image img = imageRep.findByImageId(id).get();
            productImages.add(img);
        }
        toCreate.setProductImages(productImages);
        toCreate.setProductName(request.getProductName());
        toCreate.setDescription(request.getDescription());
        toCreate.setPrice(request.getPrice());
        toCreate.setQuantity(request.getQuantity());
        toCreate.setRate(request.getRate());
        toCreate.setRate_times(1);
        return productRep.save(toCreate);
    }

    public Product updateRate(Long id, double rate) {
        Product toRate = this.getOne(id);
        toRate.setRate_times(toRate.getRate_times() + 1);
        double newRate = toRate.getRate() + rate;
        toRate.setRate(newRate);
        return productRep.save(toRate);
    }


    public Product updateProduct(ProductDto request, Long id) {
        Product toUpdate = this.getOne(id);
        Category category = categoryService.getOneByName(request.getCategoryName());
        toUpdate.setCategory(category);
        List<Image> productImages = new ArrayList<>();
        for (String Id : request.getProductImages()) {
            Image img = imageRep.findByImageId(Id).get();
            productImages.add(img);
        }
        toUpdate.setProductImages(productImages);
        toUpdate.setProductName(request.getProductName());
        toUpdate.setDescription(request.getDescription());
        toUpdate.setPrice(request.getPrice());
        toUpdate.setQuantity(request.getQuantity());
        toUpdate.setRate(request.getRate());
        toUpdate.setRate_times(1);
        return productRep.save(toUpdate);
    }

    public void deleteProduct(Long id) {
        Product toDelete = this.getOne(id);
        productRep.deleteById(toDelete.getId());
    }
}
