package javaspring.technicaltest.core.model.enumeration.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import javaspring.technicaltest.core.model.enumeration.ProductType;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProductTypeConverter implements AttributeConverter<ProductType, String> {
    @Override
    public String convertToDatabaseColumn(ProductType productType) {
        return productType == null ? null : productType.getCode();
    }

    @Override
    public ProductType convertToEntityAttribute(String code) {
        return code == null ? null : Stream.of(ProductType.values())
                .filter(productType -> productType.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
