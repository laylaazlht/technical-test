package javaspring.technicaltest.core.api.admin;

import io.swagger.annotations.ApiOperation;
import javaspring.technicaltest.core.model.Product;
import javaspring.technicaltest.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/v1/product")
public class ProductAdminApiV1 {

    @Autowired
    ProductService service;

    @GetMapping()
    @ApiOperation("List")
    public Page<Product> list(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id"));
        return service.findAll(pageable);
    }

    @GetMapping("/{code}")
    public Product get(@PathVariable String code) {
        return service.findByCode(code);
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return service.persist(product, new Product());
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        var stored = service.findByCode(product.getCode());
        service.persist(product, stored);

        return stored;
    }

    @DeleteMapping()
    public void delete(@RequestParam String code){
        service.delete(code);
    }
}
