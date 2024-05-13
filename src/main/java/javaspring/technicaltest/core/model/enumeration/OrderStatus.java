package javaspring.technicaltest.core.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    PENDING("PENDING"), PAID("PAID"), UNPAID("UNPAID"), RETURNED("RETURNED");

    private final String code;

    OrderStatus(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
