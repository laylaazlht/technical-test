package javaspring.technicaltest.core.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javaspring.technicaltest.core.model.enumeration.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class TransactionCartDto {

    private String cartCode;

    private String customerCode;

    private String customerName;

    private String customerAddress;

    private List<ProductItem> productItems = new ArrayList<>();

    private BigDecimal total;

    @Getter
    @Setter
    public static class ProductItem{

        private String productCode;

        private String productName;

        private ProductType type;

        private BigDecimal price;

        private int quantity;

        private BigDecimal subTotal;

        private boolean isActive;
    }
}
