package javaspring.technicaltest.core.repository;

import javaspring.technicaltest.core.model.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Long> {
    TransactionDetail findByCode(String code);

}
