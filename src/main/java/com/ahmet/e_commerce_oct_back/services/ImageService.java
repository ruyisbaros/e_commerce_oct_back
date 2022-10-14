package com.ahmet.e_commerce_oct_back.services;

import com.ahmet.e_commerce_oct_back.entities.Image;
import com.ahmet.e_commerce_oct_back.repositories.ImageRep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class ImageService {

    private ImageRep imageRep;

    public void createImage(Image image) {
        imageRep.save(image);
    }

    public boolean isImageExist(String id) {
        return imageRep.findByImageId(id).isPresent();
    }

    public Image findImage(String id) {
        return imageRep.findByImageId(id).get();
    }

    public void deleteImage(String imageId) {
        imageRep.deleteByImageId(imageId);
    }
}
