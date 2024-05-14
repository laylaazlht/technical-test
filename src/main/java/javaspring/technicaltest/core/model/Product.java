package javaspring.technicaltest.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import javaspring.technicaltest.core.model.enumeration.ProductType;
import javaspring.technicaltest.core.model.support.WithCodeAndName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "product_uk", columnNames = {"code"}),
})
@Getter
@Setter
public class Product extends WithCodeAndName {

    private ProductType type;

    private BigDecimal price;

    private int quantity;

    private boolean isActive;

}
