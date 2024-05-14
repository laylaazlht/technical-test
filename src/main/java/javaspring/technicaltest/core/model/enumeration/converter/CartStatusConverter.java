package javaspring.technicaltest.core.model.enumeration.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import javaspring.technicaltest.core.model.enumeration.CartStatus;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class CartStatusConverter implements AttributeConverter<CartStatus, String> {
    @Override
    public String convertToDatabaseColumn(CartStatus cartStatus) {
        return cartStatus == null ? null : cartStatus.getCode();
    }

    @Override
    public CartStatus convertToEntityAttribute(String code) {
        return code == null ? null : Stream.of(CartStatus.values())
                .filter(cartStatus -> cartStatus.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
