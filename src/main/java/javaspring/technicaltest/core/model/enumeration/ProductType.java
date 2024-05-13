package javaspring.technicaltest.core.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ProductType {
    LAPTOP("Laptop"), BOOK("Book");

    private final String code;

    ProductType(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
