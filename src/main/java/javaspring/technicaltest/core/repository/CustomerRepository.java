package javaspring.technicaltest.core.repository;

import javaspring.technicaltest.core.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCode(String code);
}
