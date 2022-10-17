package com.ahmet.e_commerce_oct_back.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundExceptionIntValue extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private int fieldValue;

    public ResourceNotFoundExceptionIntValue() {
    }

    public ResourceNotFoundExceptionIntValue(String resourceName, String fieldName, int fieldValue) {
        super(String.format("%s not found with %s: %s ", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
