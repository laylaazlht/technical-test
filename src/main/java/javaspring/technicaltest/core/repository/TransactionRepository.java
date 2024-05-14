package javaspring.technicaltest.core.repository;

import javaspring.technicaltest.core.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findByCode(String code);

}
