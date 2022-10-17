package com.ahmet.e_commerce_oct_back.services;

import com.ahmet.e_commerce_oct_back.DTO.CategoryDTO;
import com.ahmet.e_commerce_oct_back.entities.Category;
import com.ahmet.e_commerce_oct_back.entities.Image;
import com.ahmet.e_commerce_oct_back.exceptions.ResourceNotFoundExceptionIntValue;
import com.ahmet.e_commerce_oct_back.repositories.CategoryRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRep categoryRep;
    private ImageService imageService;

    private CloudinaryService cloudinaryService;

    public Category createNew(CategoryDTO request) {

        Category created = new Category();
        if (request.getImageId() != null) {
            Image image = imageService.findImage(request.getImageId());
            created.setImage(image);
        }
        created.setCategoryName(request.getCategoryName());
        created.setDescription(request.getDescription());
        return categoryRep.save(created);
    }

    public List<Category> getAll() {
        return categoryRep.findAll();
    }

    public void deleteCategory(Integer id) throws IOException {
        Category category = categoryRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionIntValue("Category", "ID", id));
        //Let's delete from cloud as well. we should think our cloud space!!!
        cloudinaryService.deleteImage(category.getImage().getImageId());
        categoryRep.deleteById(id);
    }

    public void updateCatgry(CategoryDTO request, Integer id) {
        Category toUpdate = categoryRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionIntValue("Category", "ID", id));

        if (request.getImageId() != null) {
            Image image = imageService.findImage(request.getImageId());
            toUpdate.setImage(image);
        }
        toUpdate.setCategoryName(request.getCategoryName());
        toUpdate.setDescription(request.getDescription());
        categoryRep.save(toUpdate);
    }

    public Category getOne(Integer id) {

        return categoryRep.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptionIntValue("Category", "ID", id));
    }
}
