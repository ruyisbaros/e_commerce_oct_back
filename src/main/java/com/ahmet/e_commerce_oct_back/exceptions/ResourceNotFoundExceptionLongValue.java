package com.ahmet.e_commerce_oct_back.exceptions;

import lombok.Data;

@Data
public class ResourceNotFoundExceptionLongValue extends RuntimeException {

    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundExceptionLongValue() {
    }

    public ResourceNotFoundExceptionLongValue(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s: %s ", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
