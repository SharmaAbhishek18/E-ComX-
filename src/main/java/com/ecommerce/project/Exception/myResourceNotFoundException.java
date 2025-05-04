package com.ecommerce.project.Exception;

public class myResourceNotFoundException extends RuntimeException {
    String resourceName;
    String field;
    String fieldName;
    Long fieldId;

    public myResourceNotFoundException(String message, String resourceName, String field, String fieldName) {
        super(String.format("%s not found with %s: %s",resourceName,field,fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }
    public myResourceNotFoundException(String resourceName, String field, Long fieldId)
    {   //Super calls the parent Constructor here runtime Exception
        super(String.format("%s not found with %s: %d",resourceName,field,fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }

    public myResourceNotFoundException() {

    }
}
