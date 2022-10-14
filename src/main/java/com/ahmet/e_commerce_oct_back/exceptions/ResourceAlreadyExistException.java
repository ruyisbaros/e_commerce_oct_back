package com.ahmet.e_commerce_oct_back.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceAlreadyExistException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private String fieldValue;



    public ResourceAlreadyExistException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s already exist with %s: %s ", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
