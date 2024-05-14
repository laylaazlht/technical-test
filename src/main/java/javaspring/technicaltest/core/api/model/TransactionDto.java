package javaspring.technicaltest.core.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
public class TransactionDto {

    private String customerCode;

    private String cartCode;

    private List<TransactionItem> transactionItems = new ArrayList<>();

    @Setter
    @Getter
    public static class TransactionItem {

        private String productCode;

        private int quantity;

        public TransactionItem() {
        }
    }
}
