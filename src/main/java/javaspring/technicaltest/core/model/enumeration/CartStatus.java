package javaspring.technicaltest.core.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CartStatus {
    ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), PROCESS("PROCESS"), PAID("PAID"), DELIVERED("DELIVERED");

    private final String code;

    CartStatus(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
