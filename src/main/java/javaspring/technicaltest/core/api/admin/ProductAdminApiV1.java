package javaspring.technicaltest.core.api.admin;

import javaspring.technicaltest.core.model.Product;
import javaspring.technicaltest.core.repository.ProductRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/v1/product")
public class ProductAdminApiV1 {

    private ProductRepository productRepository;

    @GetMapping
    public List<Product> list() {
        return productRepository.findAll();
    }

    @GetMapping("/{code}")
    public Product get(@PathVariable String code) {
        return productRepository.findByCode(code);
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        return persist(product, new Product());
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        var stored = productRepository.findByCode(product.getCode());
        persist(product, stored);

        return stored;
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        Product product = productRepository.findByCode(code);
        if (product == null) {
            throw new RuntimeException("Product not found with code: " + code);
        }
        productRepository.delete(product);
    }

    private Product persist(Product from, Product to) {
        to.setCode(UUID.randomUUID().toString());
        to.setName(from.getName());
        to.setPrice(from.getPrice());
        to.setQuantity(from.getQuantity());
        productRepository.save(to);

        return to;
    }
}
