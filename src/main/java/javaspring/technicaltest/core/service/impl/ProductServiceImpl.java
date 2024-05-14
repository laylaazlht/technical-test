package javaspring.technicaltest.core.service.impl;

import javaspring.technicaltest.core.model.Product;
import javaspring.technicaltest.core.repository.ProductRepository;
import javaspring.technicaltest.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;

    @Override
    public Product persist(Product from, Product to) {
        to.setCode(UUID.randomUUID().toString());
        to.setName(from.getName());
        to.setType(from.getType());
        to.setPrice(from.getPrice());
        to.setQuantity(from.getQuantity());
        to.setActive(from.isActive());
        repository.save(to);

        return to;
    }

    @Override
    public void delete(String code) {
        var product = repository.findByCode(code);
        product.setActive(false);

        repository.save(product);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Product findByCode(String code) {
        return repository.findByCode(code);
    }

}
