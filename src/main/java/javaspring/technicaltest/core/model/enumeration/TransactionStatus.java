package javaspring.technicaltest.core.model.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionStatus {
    PENDING("PENDING"), PAID("PAID"), UNPAID("UNPAID"), RETURNED("RETURNED");

    private final String code;

    TransactionStatus(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}
