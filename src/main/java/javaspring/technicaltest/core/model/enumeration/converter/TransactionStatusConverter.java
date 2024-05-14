package javaspring.technicaltest.core.model.enumeration.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import javaspring.technicaltest.core.model.enumeration.TransactionStatus;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class TransactionStatusConverter implements AttributeConverter<TransactionStatus, String> {
    @Override
    public String convertToDatabaseColumn(TransactionStatus transactionStatus) {
        return transactionStatus == null ? null : transactionStatus.getCode();
    }

    @Override
    public TransactionStatus convertToEntityAttribute(String code) {
        return code == null ? null : Stream.of(TransactionStatus.values())
                .filter(transactionStatus -> transactionStatus.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
