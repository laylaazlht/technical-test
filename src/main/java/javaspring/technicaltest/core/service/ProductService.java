package javaspring.technicaltest.core.service;

import javaspring.technicaltest.core.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product persist(Product from, Product to);

    void delete(String code);

    Page<Product> findAll(Pageable pageable);

    Product findByCode(String code);
}
