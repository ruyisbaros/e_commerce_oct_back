package com.ahmet.e_commerce_oct_back.controllers;

import com.ahmet.e_commerce_oct_back.entities.Image;
import com.ahmet.e_commerce_oct_back.exceptions.ApiResponse;
import com.ahmet.e_commerce_oct_back.exceptions.ResourceNotFoundExceptionStringValue;
import com.ahmet.e_commerce_oct_back.services.CloudinaryService;
import com.ahmet.e_commerce_oct_back.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/images")
@AllArgsConstructor
public class ImageController {

    private ImageService imageService;

    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile multipartFile) throws IOException {
        Map result = cloudinaryService.uploadImage(multipartFile);

        Image image = new Image((String) result.get("public_id"), (String) result.get("url"));
        imageService.createImage(image);

        return ResponseEntity.ok(image);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable String id) throws IOException {
        if(imageService.isImageExist(id)){
            Image found=imageService.findImage(id);
            cloudinaryService.deleteImage(found.getImageId());
            imageService.deleteImage(found.getImageId());

            return new ResponseEntity<>(new ApiResponse("Image has been deleted",true), HttpStatus.OK);
        }else{
            throw new ResourceNotFoundExceptionStringValue("Image","ID",id);
        }
    }
}
