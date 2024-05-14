package javaspring.technicaltest.core.repository;

import javaspring.technicaltest.core.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCode(String code);
}
