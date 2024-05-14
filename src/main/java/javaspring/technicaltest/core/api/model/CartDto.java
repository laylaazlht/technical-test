package javaspring.technicaltest.core.api.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class CartDto {

    private String customerCode;

    private String productCode;

    private int quantity;
}
